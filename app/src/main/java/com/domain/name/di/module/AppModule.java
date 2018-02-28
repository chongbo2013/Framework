package com.domain.name.di.module;

import android.content.Context;

import com.liux.framework.app.UIProvider;
import com.liux.framework.base.BaseApplication;
import com.liux.http.HttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module(includes = {ActivityModule.class, FragmentModule.class, PresenterModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return BaseApplication.getContext();
    }

    @Provides
    @Singleton
    HttpClient provideHttpClient() {
        return HttpClient.getInstance();
    }

    @Provides
    @Singleton
    UIProvider provideUIProvider() {
        return BaseApplication.getUIProvide();
    }

}
