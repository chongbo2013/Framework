package com.domain.name.di.module;

import android.content.Context;

import com.domain.name.mvp.model.*;
import com.domain.name.mvp.model.impl.*;

import dagger.Module;
import dagger.Provides;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

@Module(includes = ApiModule.class)
public class ModelModule {

    @Provides
    DiskModel provideDiskModel(DiskModelImpl diskModel) {
        return diskModel;
    }

    @Provides
    GeneralApiModel provideGeneralApiModel(GeneralApiModelImpl generalApiModel) {
        return generalApiModel;
    }
}
