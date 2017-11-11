package com.domain.name.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;

/**
 * Created by Liux on 2017/11/6.
 */

public abstract class BaseActivity extends com.liux.base.BaseActivity implements BaseContract.View {

    private BaseContract.View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mView = new BaseViewImpl(this);
        super.onCreate(savedInstanceState);
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
        super.onDestroy();
        mView.onDestroy();
    }
}
