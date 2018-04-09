package org.domain.name.di.module;

import org.domain.name.mvp.model.api.GeneralApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    GeneralApi provideCommonApi(Retrofit retrofit) {
        return retrofit.create(GeneralApi.class);
    }
}
