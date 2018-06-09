package com.jachat.translateor.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Nicholas on 2016/11/5.
 */

public abstract class BaseFragment<P extends BasePresenter> extends SupportFragment {

    protected P mPresenter;

    private View view;

    private GestureDetector gesture; //手势识别

    protected static final float FLIP_DISTANCE = 30;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        if (setEventBus()) {
            EventBus.getDefault().register(this);
        }
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
        initData();

        //根据父窗体getActivity()为fragment设置手势识别
        gesture = new GestureDetector(this.getActivity(), new MyOnGestureListener());
        //为fragment添加OnTouchListener监听器
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);//返回手势识别触发的事件
            }
        });
        return view;
    }

    //设置手势识别监听器
    private class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override//此方法必须重写且返回真，否则onFling不起效
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                //Logger.e("<--- left, left, go go go");
                return true;
            }
            if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                //Logger.e("right, right, go go go --->");
                return true;
            }
            if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                //Logger.e("向上滑...");
                onUpFling();
                return true;
            }
            if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                onDownFling();
                return true;
            }
            //Logger.e(e2.getX() + " " + e2.getY());
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        if (setEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected void onLeftFling() {
    }

    protected void onRightFling() {
    }

    protected void onUpFling() {
    }

    protected void onDownFling() {
    }

    protected abstract int getLayoutId();

    protected abstract P onCreatePresenter();

    protected abstract boolean setEventBus();

    protected abstract void initData();
}
