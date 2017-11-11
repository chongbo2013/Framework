package com.domain.name.base;

/**
 * Created by Liux on 2017/8/17.
 */

public class BasePresenterImpl implements BaseContract.Presenter {

    private BaseContract.View mView;

    public BasePresenterImpl(BaseContract.View view) {
        mView = view;
    }

    @Override
    public BaseContract.View getView() {
        return mView;
    }
}
