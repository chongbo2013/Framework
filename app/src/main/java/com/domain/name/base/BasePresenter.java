package com.domain.name.base;

import com.domain.name.app.AppControl;
import com.domain.name.app.ApplicationCus;

/**
 * Created by Liux on 2017/8/17.
 */

public class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter {

    private V mView;

    public BasePresenter(V view) {
        mView = view;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public AppControl.View getAppView() {
        return ApplicationCus.getAppView();
    }

    @Override
    public AppControl.Presenter getAppPresenter() {
        return ApplicationCus.getAppPresenter();
    }

    @Override
    public boolean interceptFailure(int code, String msg) {
        return false;
    }
}
