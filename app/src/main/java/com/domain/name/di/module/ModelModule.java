package com.domain.name.di.module;

import android.content.Context;

import com.domain.name.mvp.model.DiskModel;
import com.domain.name.mvp.model.GeneralApiModel;
import com.domain.name.mvp.model.impl.DiskModelImpl;
import com.domain.name.mvp.model.impl.GeneralApiModelImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Liux on 2017/12/15.
 */

@Module(includes = ApiModule.class)
public class ModelModule {

    @Provides
    DiskModel provideDiskModel(Context context) {
        return new DiskModelImpl(context);
    }

    @Provides
    GeneralApiModel provideGeneralApiModel() {
        return new GeneralApiModelImpl();
    }
}
