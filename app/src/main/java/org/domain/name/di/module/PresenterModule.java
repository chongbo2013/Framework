package org.domain.name.di.module;

import org.domain.name.mvp.contract.HomeContract;
import org.domain.name.mvp.contract.StartContract;
import org.domain.name.mvp.presenter.HomePresenter;
import org.domain.name.mvp.presenter.StartPresenter;

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
