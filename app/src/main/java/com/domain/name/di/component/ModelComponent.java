package com.domain.name.di.component;

import com.domain.name.di.model.ModelsModel;
import com.domain.name.mvp.presenter.*;

import dagger.Component;

/**
 * Created by Liux on 2017/12/15.
 */

@Component(modules = ModelsModel.class)
public interface ModelComponent {

    void inject(StartPresenter presenter);

    void inject(HomePresenter presenter);
}
