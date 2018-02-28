package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.domain.name.R;
import com.domain.name.mvp.contract.StartContract;
import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;
import com.liux.framework.base.BaseMvpActivity;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class LaunchActivity extends BaseMvpActivity<StartContract.Presenter> implements StartContract.View {

    @Override
    public TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        getWindow().setBackgroundDrawableResource(R.drawable.launch_bg);

        super.onCreate(bundle);

        new Handler().postDelayed(() -> {
            if (mPresenter.showGuide()) {
                startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
            } else {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            }
            finish();
        }, 1500);
    }
}
