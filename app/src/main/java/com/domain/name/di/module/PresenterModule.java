package com.domain.name.di.module;

import com.domain.framework.base.BaseContract;
import com.domain.framework.base.BasePresenter;
import com.domain.framework.di.annotation.Target;
import com.domain.name.mvp.contract.*;
import com.domain.name.mvp.presenter.*;
import com.domain.name.ui.activity.GuideActivity;
import com.domain.name.ui.activity.LaunchActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liux on 2017/12/15.
 */

@Module(includes = {ModelModule.class})
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
