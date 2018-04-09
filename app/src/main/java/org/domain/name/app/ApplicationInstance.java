package org.domain.name.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.liux.framework.app.AppControl;
import com.liux.framework.app.UIProvider;
import com.liux.framework.base.BaseApplication;
import com.liux.http.Http;
import com.liux.http.interceptor.HttpLoggingInterceptor;
import com.liux.tool.Logger;
import com.liux.util.AppUtil;

import org.domain.name.BuildConfig;
import org.domain.name.app.control.LocalControl;
import org.domain.name.app.control.RemoteControl;
import org.domain.name.di.component.DaggerAppComponent;
import org.domain.name.mvp.model.conf.URL;

import dagger.android.AndroidInjector;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class ApplicationInstance extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.DEBUG = BuildConfig.DEBUG;
        Http.init(this, URL.URL_API);
        Http.get().setLoggingLevel(
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE
        );
    }

    @Override
    protected void onGlobalError(Throwable throwable) {

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
        return new UIProviderImpl(getContext());
    }
}
