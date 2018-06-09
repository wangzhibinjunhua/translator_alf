package com.jachat.translateor.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by plcgo on 2016/12/21.
 */

public class BasePresenter<V extends BaseView> {

    protected V mView;

    private CompositeSubscription mCompositeSubscription;

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.clear();
        }
    }

}
