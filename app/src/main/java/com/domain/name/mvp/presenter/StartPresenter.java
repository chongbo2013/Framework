package com.domain.name.mvp.presenter;

import com.domain.name.base.BasePresenter;
import com.domain.name.di.component.DaggerModelComponent;
import com.domain.name.di.model.ModelsModel;
import com.domain.name.mvp.contract.StartContract;
import com.domain.name.mvp.model.impl.DiskModelImpl;
import com.liux.util.AppUtil;

import javax.inject.Inject;

/**
 * Created by Liux on 2017/12/15.
 */

public class StartPresenter extends BasePresenter<StartContract.View> implements StartContract.Presenter {

    @Inject
    DiskModelImpl mDiskModelImpl;

    public StartPresenter(StartContract.View view) {
        super(view);
        DaggerModelComponent.builder()
                .modelsModel(new ModelsModel(view.getAppView().getContext()))
                .build()
                .inject(this);
    }

    @Override
    public boolean showGuide() {
        int guide_old = mDiskModelImpl.getGuide();
        int guide_new = AppUtil.getVersionCode(getAppView().getContext());
        return guide_old != guide_new;
    }

    @Override
    public void saveGuide() {
        int guide_new = AppUtil.getVersionCode(getAppView().getContext());
        mDiskModelImpl.putGuide(guide_new);
    }
}
