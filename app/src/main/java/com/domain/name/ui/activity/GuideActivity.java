package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.domain.name.R;
import com.domain.name.base.BaseActivity;
import com.domain.name.mvp.contract.StartContract;
import com.domain.name.mvp.presenter.StartPresenter;
import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Liux on 2017/8/17.
 */

public class GuideActivity extends BaseActivity implements StartContract.View {

    private StartContract.Presenter mPresenter = new StartPresenter(this);

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
                mPresenter.saveGuide();
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onInitData(@Nullable Bundle bundle, Intent intent) {

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
