package com.liux.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

/**
 * 2018/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseMvpActivity<P extends BaseContract.Presenter> extends BaseActivity
        implements BaseContract.View {

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
    }
}
