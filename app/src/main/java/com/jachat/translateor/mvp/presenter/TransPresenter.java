package com.jachat.translateor.mvp.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.jachat.translateor.api.ApiEngine;
import com.jachat.translateor.api.RxSchedulers;
import com.jachat.translateor.config.LngConstant;
import com.jachat.translateor.mvp.contract.TransContract;
import com.jachat.translateor.mvp.model.HttpResponse;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by jachat01 on 2017/12/30.
 */

public class TransPresenter extends TransContract.Presenter{

    private Context mContext;
    private TransContract.View mView;

    public TransPresenter(Context context, TransContract.View view) {
        mContext = context;
        mView = view;
    }


}
