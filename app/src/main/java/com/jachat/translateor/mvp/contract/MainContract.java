package com.jachat.translateor.mvp.contract;

import com.jachat.translateor.base.BasePresenter;
import com.jachat.translateor.base.BaseView;
import com.jachat.translateor.mvp.model.ResInfoResponse;

import java.util.List;

/**
 * Created by plcgo on 2016/12/21.
 */

public interface MainContract {

    interface View extends BaseView {
    }

    abstract class Presenter extends BasePresenter {
        public abstract void fileOpe(List<ResInfoResponse.ItemsBean> list);
    }
}
