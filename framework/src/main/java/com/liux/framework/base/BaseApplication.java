package com.liux.framework.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;

import com.liux.framework.app.AppControl;
import com.liux.framework.app.UIProvider;
import com.liux.rx.error.ErrorHandlerManager;
import com.liux.rx.error.OnErrorListener;
import com.liux.rx.lifecycle.LifecyleProviderManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasContentProviderInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.HasServiceInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseApplication extends Application
        implements HasActivityInjector, HasFragmentInjector, HasServiceInjector,
        HasBroadcastReceiverInjector, HasContentProviderInjector, HasSupportFragmentInjector {

    private volatile boolean mNeedToInject = true;
    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;
    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> mBroadcastReceiverInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;
    @Inject
    DispatchingAndroidInjector<Service> mServiceInjector;
    @Inject
    DispatchingAndroidInjector<ContentProvider> mContentProviderInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> mSupportFragmentInjector;

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static AppControl mAppControl;
    private static UIProvider mUIProvider;

    private static RefWatcher mRefWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LifecyleProviderManager.install(this);
        ErrorHandlerManager.registerListener(new OnErrorListener() {
            @Override
            public void onError(Throwable throwable) {
                onGlobalError(throwable);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injectIfNecessary();

        mContext = getApplicationContext();

        mAppControl = initAppControl();
        mUIProvider = initUIProvide();

        mRefWatcher = LeakCanary.install(this);

        CrashReport.initCrashReport(getApplicationContext());
    }

    public static Context getContext() {
        return mContext;
    }

    public static AppControl getAppControl() {
        return mAppControl;
    }

    public static UIProvider getUIProvide() {
        return mUIProvider;
    }

    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    protected abstract void onGlobalError(Throwable throwable);

    protected abstract AppControl initAppControl();

    protected abstract UIProvider initUIProvide();

    @Inject
    void setInjected() {
        mNeedToInject = false;
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> fragmentInjector() {
        return mFragmentInjector;
    }

    @Override
    public DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return mBroadcastReceiverInjector;
    }

    @Override
    public DispatchingAndroidInjector<Service> serviceInjector() {
        return mServiceInjector;
    }

    @Override
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        injectIfNecessary();
        return mContentProviderInjector;
    }

    @Override
    public DispatchingAndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return mSupportFragmentInjector;
    }

    protected abstract AndroidInjector<? extends BaseApplication> applicationInjector();

    private void injectIfNecessary() {
        if (mNeedToInject) {
            synchronized (this) {
                if (mNeedToInject) {
                    @SuppressWarnings("unchecked")
                    AndroidInjector<BaseApplication> applicationInjector =
                            (AndroidInjector<BaseApplication>) applicationInjector();
                    applicationInjector.inject(this);
                    if (mNeedToInject) {
                        throw new IllegalStateException(
                                "The AndroidInjector returned from applicationInjector() did not inject the "
                                        + getClass());
                    }
                }
            }
        }
    }
}
