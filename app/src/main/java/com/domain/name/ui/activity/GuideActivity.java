package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.domain.name.R;
import com.domain.name.base.BaseActivity;
import com.domain.name.di.component.DaggerPresenterComponent;
import com.domain.name.di.model.PresentersModel;
import com.domain.name.mvp.contract.StartContract;
import com.domain.name.mvp.presenter.StartPresenter;
import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;

import java.util.Map;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Liux on 2017/8/17.
 */

public class GuideActivity extends BaseActivity implements StartContract.View {

    @Inject
    StartPresenter mStartPresenter;

    @Override
    protected TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle, Intent intent) {
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mStartPresenter.saveGuide();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onInitData(@Nullable Bundle bundle, Intent intent) {
        DaggerPresenterComponent.builder()
                .presentersModel(new PresentersModel(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onInitView(@Nullable Bundle bundle) {

    }

    @Override
    protected void onRestoreData(Map<String, Object> map) {

    }

    @Override
    protected void onLazyLoad() {

    }

    @Override
    protected void onSaveData(Map<String, Object> map) {

    }
}
