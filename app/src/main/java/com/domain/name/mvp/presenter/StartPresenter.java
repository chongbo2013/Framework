package com.domain.name.mvp.presenter;

import android.content.Context;

import com.domain.framework.base.BasePresenter;
import com.domain.name.mvp.contract.StartContract;
import com.domain.name.mvp.model.DiskModel;
import com.liux.util.AppUtil;

import javax.inject.Inject;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

public class StartPresenter extends BasePresenter<StartContract.View> implements StartContract.Presenter {

    @Inject
    Context mContext;
    @Inject
    DiskModel mDiskModel;

    @Inject
    public StartPresenter() {
    }

    @Override
    public boolean showGuide() {
        int guide_old = mDiskModel.getGuide();
        int guide_new = AppUtil.getVersionCode(mContext);
        return guide_old != guide_new;
    }

    @Override
    public void saveGuide() {
        int guide_new = AppUtil.getVersionCode(mContext);
        mDiskModel.putGuide(guide_new);
    }
}
