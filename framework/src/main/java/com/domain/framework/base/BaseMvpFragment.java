package com.domain.framework.base;

import android.content.Context;

import javax.inject.Inject;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseMvpFragment<P extends BaseContract.Presenter> extends BaseFragment
        implements BaseContract.View {

    @Inject
    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
    }
}
