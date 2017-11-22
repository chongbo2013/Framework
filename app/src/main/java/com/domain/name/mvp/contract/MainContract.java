package com.domain.name.mvp.contract;

import com.domain.name.base.BaseContract;

/**
 * Created by Liux on 2017/11/11.
 */

public interface MainContract {

    interface View extends BaseContract.View {

        void loadSucceed();

        void loadFailure(String msg);
    }

    interface Presenter extends BaseContract.Presenter {

        void loadBanner();
    }
}
