package com.domain.name.mvp.presenter;

import com.domain.name.base.BasePresenter;
import com.domain.name.mvp.contract.StartContract;
import com.domain.name.mvp.model.DiskModel;
import com.domain.name.mvp.model.impl.DiskModelImpl;
import com.liux.util.AppUtil;

/**
 * Created by Liux on 2017/12/15.
 */

public class StartPresenter extends BasePresenter<StartContract.View> implements StartContract.Presenter {
    private DiskModel mDiskModel;

    public StartPresenter(StartContract.View view) {
        super(view);
        mDiskModel = new DiskModelImpl(getAppView().getContext());
    }

    @Override
    public boolean showGuide() {
        int guide_old = mDiskModel.getGuide();
        int guide_new = AppUtil.getVersionCode(getAppView().getContext());
        return guide_old != guide_new;
    }

    @Override
    public void saveGuide() {
        int guide_new = AppUtil.getVersionCode(getAppView().getContext());
        mDiskModel.putGuide(guide_new);
    }
}
