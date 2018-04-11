package org.domain.name.di.module;

import org.domain.name.mvp.model.DiskModel;
import org.domain.name.mvp.model.ApiModel;
import org.domain.name.mvp.model.impl.DiskModelImpl;
import org.domain.name.mvp.model.impl.ApiModelImpl;

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
    ApiModel provideGeneralApiModel(ApiModelImpl generalApiModel) {
        return generalApiModel;
    }
}
