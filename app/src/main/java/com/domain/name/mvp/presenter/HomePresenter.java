package com.domain.name.mvp.presenter;

import com.domain.framework.base.BasePresenter;
import com.domain.framework.rx.ThreadTransformer;
import com.domain.name.mvp.contract.HomeContract;
import com.domain.name.mvp.model.GeneralApiModel;

import javax.inject.Inject;

/**
 * 2017/11/11
 * By Liux
 * lx0758@qq.com
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    GeneralApiModel mGeneralApiModel;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void loadBanner() {
        mGeneralApiModel.loadBanner()
                .compose(ThreadTransformer.io_Main())
                .compose(mView.bindLifeCycle())
                .subscribe(jsonObjects -> {
                    mView.loadSucceed(jsonObjects);
                }, e -> {
                    mView.loadFailure(e.getMessage());
                });
    }
}
