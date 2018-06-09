package com.jachat.translateor.base;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.jachat.translateor.R;
import com.jachat.translateor.others.swipeback.SwipeBackHelper;
import com.jachat.translateor.others.swipeback.SwipeBackLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by plcgo on 2016/12/21.
 */

public abstract class BaseActivity<P extends BasePresenter> extends SupportActivity {

    protected P mPresenter;

    private Unbinder mUnBinder;

    private CompositeSubscription mCompositeSubscription;

    private OnKeyListener mOnKeyListener;

    private SwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackHelper = new SwipeBackHelper(this);
        mSwipeBackHelper.onCreate();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if(getLayout() != 0){
            setContentView(getLayout());
        }

        mUnBinder = ButterKnife.bind(this);
        if(onCreatePresenter() != null){
            mPresenter = onCreatePresenter();
        }

        if(setEventBus()){
            EventBus.getDefault().register(this);
        }

        initData();
        //initWindow();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY|
                        View.SYSTEM_UI_FLAG_IMMERSIVE

        );

    }

    @Override
    public void setContentView(int layoutResID) {
        if(layoutResID != 0){
            super.setContentView(layoutResID);
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getSwipeBackLayout().recovery();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mSwipeBackHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mSwipeBackHelper != null)
            return mSwipeBackHelper.findViewById(id);
        return v;
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mSwipeBackHelper.getSwipeBackLayout();
    }

    /**
     * 设置是否可以边缘滑动返回
     *
     * @param enable true可以边缘滑动返回
     */
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setSwipeBackEnable(enable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.unSubscribe();
        }
        if(setEventBus()){
            EventBus.getDefault().unregister(this);
        }
        mUnBinder.unbind();

    }

    public void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

    public void setOnKeyListener(OnKeyListener onKeyListener){
        mOnKeyListener = onKeyListener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Logger.e("onKeyDown  keyCode = " + keyCode);
        if(mOnKeyListener != null) mOnKeyListener.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //Logger.e("onKeyUp  keyCode = " + keyCode);
        if(mOnKeyListener != null) mOnKeyListener.onKeyUp(keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    protected abstract int getLayout();

    protected abstract P onCreatePresenter();

    protected abstract boolean setEventBus();

    protected abstract void initData();

    public interface OnKeyListener{
        void onKeyDown(int keyCode, KeyEvent event);
        void onKeyUp(int keyCode, KeyEvent event);
    }



}
