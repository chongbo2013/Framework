package com.domain.name.di.component;

import com.domain.name.di.model.PresentersModel;
import com.domain.name.ui.activity.*;
import com.domain.name.ui.fragment.*;

import dagger.Component;

/**
 * Created by Liux on 2017/12/15.
 */

@Component(modules = PresentersModel.class)
public interface PresenterComponent {

    void inject(LaunchActivity activity);

    void inject(MainHomeFragment fragment);

    void inject(GuideActivity activity);
}
