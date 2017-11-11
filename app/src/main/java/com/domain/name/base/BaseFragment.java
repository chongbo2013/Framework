package com.domain.name.base;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * Created by Liux on 2017/11/6.
 */

public abstract class BaseFragment extends com.liux.base.BaseFragment implements BaseContract.View {

    private BaseContract.View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mView = new BaseViewImpl(getActivity());
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
        if (mView != null && disposable != null) {
            mView.registerDisposable(disposable);
        }
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