package com.domain.name.di.component;

import com.domain.name.app.ApplicationInstance;
import com.domain.name.di.injector.AppInjector;
import com.domain.name.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Singleton
@Component(modules = {AppInjector.class, AppModule.class})
public abstract class AppComponent implements AndroidInjector<ApplicationInstance> {

    @Component.Builder
    public static abstract class Builder extends AndroidInjector.Builder<ApplicationInstance> {

    }
}
