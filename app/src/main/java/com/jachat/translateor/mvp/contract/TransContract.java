package com.jachat.translateor.mvp.contract;

import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.base.BaseView;

/**
 * Created by jachat01 on 2017/12/30.
 */

public interface TransContract {

    interface View extends BaseView {
    }


    abstract class Presenter extends BasePresenter {
    }
}
