package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.domain.framework.base.BaseMvpActivity;
import com.domain.name.R;
import com.domain.name.mvp.contract.StartContract;
import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;

import java.util.Map;

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
        super.onCreate(bundle);
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (mPresenter.showGuide()) {
                    startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1000);
    }
}
