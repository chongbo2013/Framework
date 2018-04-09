package org.domain.name.di.module;

import android.content.Context;

import com.liux.framework.app.UIProvider;
import com.liux.http.Http;

import org.domain.name.app.ApplicationInstance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;
import retrofit2.Retrofit;

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
    Http provideHttp() {
        return Http.get();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return Http.get().getRetrofit();
    }

    @Provides
    @Singleton
    UIProvider provideUIProvider() {
        return ApplicationInstance.getUIProvide();
    }
}
