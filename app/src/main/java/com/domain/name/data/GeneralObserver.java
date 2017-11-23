package com.domain.name.data;

import com.domain.name.base.BaseContract;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.data.conf.CODE;

import java.io.IOException;
import java.net.UnknownHostException;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * Created by Liux on 2017/9/13
 */

public abstract class GeneralObserver<T> extends DisposableObserver<T> {
    private BaseContract.Presenter mPresenter;

    public GeneralObserver(BaseContract.Presenter presenter) {
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
    public void onError(@NonNull Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            // 无网络
            onGlobalFailure(CODE.ERROR_NETWORK_NONE.code(), CODE.ERROR_NETWORK_NONE.info());
        } else if (throwable instanceof HttpException || throwable instanceof IOException) {
            // 网络异常
            onGlobalFailure(CODE.ERROR_NETWORK.code(), CODE.ERROR_NETWORK.info());
        } else if (throwable instanceof ResultBean.ResultException) {
            // 业务异常
            ResultBean.ResultException exception = (ResultBean.ResultException) throwable;
            onGlobalFailure(exception.getStatus(), exception.getMessage());
        } else {
            // 未知错误
            onGlobalFailure(CODE.ERROR_UNKNOWN.code(), throwable.getMessage());
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
        if (mPresenter.interceptFailure(code, msg)) return;
        onFailure(code, msg);
    }

    /**
     * 访问接口失败
     * @param code
     * @param msg
     */
    public abstract void onFailure(int code, String msg);
}