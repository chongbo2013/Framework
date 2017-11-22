package com.domain.name.base;

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
}
