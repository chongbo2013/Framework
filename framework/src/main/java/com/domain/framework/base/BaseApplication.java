package com.domain.framework.base;

import android.annotation.SuppressLint;
import android.content.Context;

import com.domain.framework.app.AppControl;
import com.domain.framework.app.UIProvider;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import dagger.android.support.DaggerApplication;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseApplication extends DaggerApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private static AppControl mAppControl;
    private static UIProvider mUIProvider;

    private static RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        mAppControl = initAppControl();
        mUIProvider = initUIProvide();

        mRefWatcher = LeakCanary.install(this);

        CrashReport.initCrashReport(getApplicationContext());
    }

    protected abstract AppControl initAppControl();

    protected abstract UIProvider initUIProvide();

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
}
