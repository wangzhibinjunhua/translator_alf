package com.jachat.translateor.others.swipeback;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jachat.translateor.base.BaseActivity;
import com.jachat.translateor.base.BasePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class SwipeBackActivity<P extends BasePresenter> extends BaseActivity
{
    private SwipeBackHelper mSwipeBackHelper;

    protected P mPresenter;

    private Unbinder mUnBinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
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
    }

    @Override
    public void setContentView(int layoutResID) {
        if(layoutResID != 0){
            super.setContentView(layoutResID);
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mSwipeBackHelper.onPostCreate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus)
        {
            getSwipeBackLayout().recovery();
        }
    }

    @Override
    public View findViewById(int id)
    {
        View v = super.findViewById(id);
        if (v == null && mSwipeBackHelper != null)
            return mSwipeBackHelper.findViewById(id);
        return v;
    }

    public SwipeBackLayout getSwipeBackLayout()
    {
        return mSwipeBackHelper.getSwipeBackLayout();
    }

    /**
     * 设置是否可以边缘滑动返回
     * @param enable    true可以边缘滑动返回
     */
    public void setSwipeBackEnable(boolean enable)
    {
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


    protected abstract int getLayout();

    protected abstract P onCreatePresenter();

    protected abstract boolean setEventBus();

    protected abstract void initData();
}
