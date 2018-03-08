package org.domain.name.mvp.presenter;

import com.alibaba.fastjson.JSONObject;
import com.liux.framework.base.BasePresenter;

import org.domain.name.mvp.contract.HomeContract;
import org.domain.name.mvp.model.GeneralApiModel;
import org.domain.name.rx.observer.GeneralObserver;
import org.domain.name.rx.transformer.ApiTransformer;

import java.util.List;

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
    HomePresenter() {
    }

    @Override
    public void loadBanner() {
        mGeneralApiModel.loadBanner()
                .compose(ApiTransformer.api(mView))
                .subscribe(new GeneralObserver<List<JSONObject>>() {
                    @Override
                    public void onSucceed(List<JSONObject> jsonObjects) {
                        mView.loadSucceed(jsonObjects);
                    }

                    @Override
                    public void onFailure(Throwable e, String msg) {
                        mView.loadFailure(msg);
                    }
                });
    }
}
