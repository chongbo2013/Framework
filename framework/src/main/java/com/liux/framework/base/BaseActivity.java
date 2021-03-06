package com.liux.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liux.abstracts.AbstractsActivity;
import com.liux.framework.app.UIProvider;
import com.liux.rx.lifecycle.BindLifecycle;
import com.liux.rx.lifecycle.Event;
import com.liux.rx.lifecycle.EventHelper;
import com.liux.rx.lifecycle.LifecyleProviderManager;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 2018/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseActivity extends AbstractsActivity
        implements BindLifecycle, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    protected UIProvider mUiProvider;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> mFrameworkFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> mSupportFragmentInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        onInitData(savedInstanceState, getIntent());

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        onInitView();
    }

    @Override
    public <T> LifecycleTransformer<T> bindLifecycle() {
        //return LifecyleProviderManager.getLifecycleProvider(this).bindToLifecycle();
        return LifecyleProviderManager.getLifecycleProvider(this).bindUntilEvent(ActivityEvent.DESTROY);
    }

    @Override
    public <T> LifecycleTransformer<T> bindLifecycle(Event event) {
        ActivityEvent activityEvent = EventHelper.with(this).get(event);
        return LifecyleProviderManager.getLifecycleProvider(this).bindUntilEvent(activityEvent);
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return mFrameworkFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return mSupportFragmentInjector;
    }

    protected void onInitData(@Nullable Bundle savedInstanceState, Intent intent) {

    }

    protected void onInitView() {

    }
}
