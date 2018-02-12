package com.domain.name.di.module;

import com.domain.name.mvp.model.api.*;
import com.liux.http.HttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    GeneralApi provideCommonApi(HttpClient httpClient) {
        return httpClient.getRetrofit().create(GeneralApi.class);
    }
}
