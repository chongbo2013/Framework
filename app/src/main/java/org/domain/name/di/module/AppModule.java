package org.domain.name.di.module;

import android.content.Context;

import com.liux.framework.app.UIProvider;
import com.liux.http.HttpClient;

import org.domain.name.app.ApplicationInstance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module(includes = {AndroidInjectionModule.class, AndroidSupportInjectionModule.class, PresenterModule.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return ApplicationInstance.getContext();
    }

    @Provides
    @Singleton
    HttpClient provideHttpClient() {
        return HttpClient.getInstance();
    }

    @Provides
    @Singleton
    UIProvider provideUIProvider() {
        return ApplicationInstance.getUIProvide();
    }
}
