package com.domain.name.rx.observer;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 2018/2/28
 * By Liux
 * lx0758@qq.com
 */

public class EmptyObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        d.dispose();
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
