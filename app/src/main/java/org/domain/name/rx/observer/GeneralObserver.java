package org.domain.name.rx.observer;

import com.alibaba.fastjson.JSONException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 2018/2/28
 * By Liux
 * lx0758@qq.com
 */

public abstract class GeneralObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public final void onNext(T t) {
        onSucceed(t);
    }

    @Override
    public final void onError(Throwable e) {
        String message;
        if (e.getClass().getPackage().getName().equals("java.net")) {
            // 没有网络
            message = "网络连接失败,请检查网络连接";
        } else if (e instanceof JSONException) {
            // 解析异常
            message = "服务器数据解析异常";
        } else {
            message = e.getMessage();
        }
        onFailure(e, message);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 成功访问到数据
     * @param t
     */
    public abstract void onSucceed(T t);

    /**
     * 访问接口失败
     * @param e
     * @param msg
     */
    public abstract void onFailure(Throwable e, String msg);
}
