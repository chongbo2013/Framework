package com.domain.name.mvp.presenter;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.base.BasePresenter;
import com.domain.name.data.GeneralObserver;
import com.domain.name.di.component.DaggerModelComponent;
import com.domain.name.mvp.contract.HomeContract;
import com.domain.name.mvp.model.GeneralApiModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Liux on 2017/11/11.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    GeneralApiModel mGeneralApiModel;

    public HomePresenter(HomeContract.View view) {
        super(view);
        DaggerModelComponent.create().inject(this);
    }

    @Override
    public void loadBanner() {
        mGeneralApiModel.loadBanner(new GeneralObserver<List<JSONObject>>(this) {
            @Override
            public void onSucceed(List<JSONObject> jsonObjects) {
                getView().loadSucceed(jsonObjects);
            }

            @Override
            public void onFailure(int code, String msg) {
                getView().loadFailure(msg);
            }
        });
    }
}
