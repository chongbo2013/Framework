package com.domain.name.mvp.presenter;

import android.text.TextUtils;

import com.domain.name.base.BaseContract;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.data.conf.CODE;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Liux on 2017/9/13
 */

public abstract class PresenterObserver<T> extends DisposableObserver<T> {
    private BaseContract.Presenter mPresenter;

    public PresenterObserver(BaseContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onStart() {
        mPresenter.getView().registerDisposable(this);

        if (isShowProgress()) {
            Disposable disposable = isBindDisposable() ? this : null;
            mPresenter.getView().showProgressBar(disposable);
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        onSucceed(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof ResultBean.ResultException) {
            ResultBean bean = ((ResultBean.ResultException) e).getResultBean();
            onGlobalFailure(bean.getStatus(), bean.getMsg());
        } else {
            onGlobalFailure(CODE.API_ERROR.code(), e.getMessage());
        }

        mPresenter.getView().unregisterDisposable(this);
        mPresenter.getView().hideProgressBar(this);
    }

    @Override
    public void onComplete() {
        mPresenter.getView().unregisterDisposable(this);
        mPresenter.getView().hideProgressBar(this);
    }

    /**
     * 返回是否显示 ProgressBar
     * @return
     */
    public boolean isShowProgress() {
        return true;
    }

    /**
     * 返回是否绑定 Disposable
     * @return
     */
    public boolean isBindDisposable() {
        return true;
    }

    /**
     * 成功访问到数据
     * @param t
     */
    public abstract void onSucceed(T t);

    /**
     * 处理分发全局/局部错误
     *
     * @param code
     * @param msg
     */
    public void onGlobalFailure(int code, String msg) {
        boolean isGlobal = 1 == 1;
        if (isGlobal) {
            // handler global error
        }
        onFailure(code, msg);
    }

    /**
     * 访问接口失败
     * @param code
     * @param msg
     */
    public abstract void onFailure(int code, String msg);
}