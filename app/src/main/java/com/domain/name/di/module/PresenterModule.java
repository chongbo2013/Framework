package com.domain.name.di.module;

import com.domain.name.mvp.contract.*;
import com.domain.name.mvp.presenter.*;

import dagger.Module;
import dagger.Provides;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

@Module(includes = ModelModule.class)
public class PresenterModule {

    @Provides
    StartContract.Presenter provideStartPresenter(StartPresenter startPresenter) {
        return startPresenter;
    }

    @Provides
    HomeContract.Presenter provideHomePresenter(HomePresenter homePresenter) {
        return homePresenter;
    }
}
