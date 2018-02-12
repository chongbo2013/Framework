package com.domain.name.app;

import android.app.Activity;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.domain.framework.app.AppControl;
import com.domain.framework.app.UIProvider;
import com.domain.framework.base.BaseApplication;
import com.domain.name.BuildConfig;
import com.domain.name.app.control.LocalControl;
import com.domain.name.app.control.RemoteControl;
import com.domain.name.di.component.DaggerAppComponent;
import com.domain.name.mvp.model.conf.URL;
import com.liux.http.HttpClient;
import com.liux.http.interceptor.HttpLoggingInterceptor;
import com.liux.tool.Logger;
import com.liux.util.AppUtil;

import dagger.android.AndroidInjector;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class ApplicationInstance extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.DEBUG = BuildConfig.DEBUG;
        HttpClient.initialize(this, URL.URL_API);
        HttpClient.getInstance().setLoggingLevel(
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE
        );
    }

    @Override
    protected AndroidInjector<? extends BaseApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }

    @Override
    protected AppControl initAppControl() {
        if (AppUtil.isMainProcess(this)) {
            return new LocalControl();
        } else {
            return new RemoteControl();
        }
    }

    @Override
    protected UIProvider initUIProvide() {
        return new UIProviderImpl();
    }
}
