package com.domain.framework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.domain.framework.app.UIProvider;
import com.liux.abstracts.AbstractsFragment;
import com.liux.rx.lifecycle.BindLifecycle;
import com.liux.rx.lifecycle.LifecyleProviderManager;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseFragment extends AbstractsFragment
        implements BindLifecycle, HasSupportFragmentInjector {

    @Inject
    protected UIProvider mUiProvider;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> mChildFragmentInjector;

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitData(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public <T> LifecycleTransformer<T> bindLifeCycle() {
        return LifecyleProviderManager.getLifecycleProvider(this).bindToLifecycle();
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }

    protected void onInitData(Bundle savedInstanceState) {

    }
}
