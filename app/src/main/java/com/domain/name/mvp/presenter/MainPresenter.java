package com.domain.name.mvp.presenter;

import com.domain.name.base.BasePresenter;
import com.domain.name.data.GeneralObserver;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.mvp.contract.MainContract;
import com.domain.name.mvp.model.GeneralApiModel;
import com.domain.name.mvp.model.impl.GeneralApiModelImpl;

/**
 * Created by Liux on 2017/11/11.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    private GeneralApiModel mGeneralApiModel;

    public MainPresenter(MainContract.View view) {
        super(view);
        mGeneralApiModel = new GeneralApiModelImpl();
    }

    @Override
    public void loadBanner() {
        mGeneralApiModel.loadBanner(new GeneralObserver<ResultBean>(this) {
            @Override
            public void onSucceed(ResultBean resultBean) {
                getView().loadSucceed();
            }

            @Override
            public void onFailure(int code, String msg) {
                getView().loadFailure(msg);
            }
        });
    }
}
