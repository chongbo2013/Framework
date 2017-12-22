package com.domain.name.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.domain.name.BuildConfig;
import com.domain.name.app.control.LocalControl;
import com.domain.name.app.control.RemoteControl;
import com.domain.name.data.conf.URL;
import com.liux.http.HttpClient;
import com.liux.http.interceptor.HttpLoggingInterceptor;
import com.liux.tool.Logger;
import com.liux.util.AppUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Liux on 2017/8/17.
 */

public class ApplicationInstance extends Application {

    private static Context mContext;
    private static RefWatcher mRefWatcher;

    private static AppControl.View mView;
    private static AppControl.Model mModel;
    private static AppControl.Presenter mPresenter;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this.getApplicationContext();

        Logger.DEBUG = BuildConfig.DEBUG;
        HttpClient.initialize(this, URL.URL_ROOT);
        HttpClient.getInstance().setLoggingLevel(
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE
        );
        mRefWatcher = LeakCanary.install(this);
        CrashReport.initCrashReport(getApplicationContext());

        if (AppUtil.isMainProcess(this)) {
            LocalControl control = new LocalControl(this);
            mView = control;
            mModel = control;
            mPresenter = control;
        } else {
            RemoteControl control = new RemoteControl(this);
            mView = null;
            mModel = null;
            mPresenter = control;
        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static AppControl.View getAppView() {
        return mView;
    }

    public static AppControl.Model getAppModel() {
        return mModel;
    }

    public static AppControl.Presenter getAppPresenter() {
        return mPresenter;
    }
}
