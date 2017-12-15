package com.domain.name.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.domain.name.app.AppControl;
import com.liux.abstracts.AbstractsActivity;

import io.reactivex.disposables.Disposable;

/**
 * Created by Liux on 2017/11/6.
 */

public abstract class BaseActivity extends AbstractsActivity implements BaseContract.View {

    private BaseContract.View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mView = new BaseView(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public AppControl.View getAppView() {
        return mView.getAppView();
    }

    @Override
    public AppControl.Presenter getAppPresenter() {
        return mView.getAppPresenter();
    }

    @Override
    public void showProgressBar(Disposable disposable) {
        mView.showProgressBar(disposable);
    }

    @Override
    public void hideProgressBar(Disposable disposable) {
        mView.hideProgressBar(disposable);
    }

    @Override
    public void registerDisposable(Disposable disposable) {
        mView.registerDisposable(disposable);
    }

    @Override
    public void unregisterDisposable(Disposable disposable) {
        mView.unregisterDisposable(disposable);
    }

    @Override
    public void onDestroy() {
        mView.onDestroy();
        super.onDestroy();
    }
}
