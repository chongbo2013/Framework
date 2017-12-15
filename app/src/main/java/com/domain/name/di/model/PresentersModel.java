package com.domain.name.di.model;

import com.domain.name.base.BaseContract;
import com.domain.name.mvp.contract.*;
import com.domain.name.mvp.presenter.*;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liux on 2017/12/15.
 */

@Module
public class PresentersModel {

    public static PresentersModel create(BaseContract.View view) {
        return new PresentersModel(view);
    }

    private BaseContract.View mView;

    public PresentersModel(BaseContract.View view) {
        mView = view;
    }

    @Provides
    StartPresenter provideStart() {
        return new StartPresenter((StartContract.View) mView);
    }

    @Provides
    HomePresenter provideHome() {
        return new HomePresenter((HomeContract.View) mView);
    }
}
