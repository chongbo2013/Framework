package com.domain.name.di.component;

import com.domain.name.app.ApplicationInstance;
import com.domain.name.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, AndroidSupportInjectionModule.class})
public abstract class AppComponent implements AndroidInjector<ApplicationInstance> {

    @Component.Builder
    public static abstract class Builder extends AndroidInjector.Builder<ApplicationInstance> {

    }
}
