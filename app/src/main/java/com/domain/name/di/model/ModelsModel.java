package com.domain.name.di.model;

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

@Module()
public class ModelsModel {

    private Context mContext;

    public ModelsModel() {
    }

    public ModelsModel(Context context) {
        mContext = context;
    }

    @Provides
    DiskModel provideDiskModel() {
        return new DiskModelImpl(mContext);
    }

    @Provides
    GeneralApiModel provideGeneralApiModel() {
        return new GeneralApiModelImpl();
    }
}
