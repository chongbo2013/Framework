package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.liux.base.titlebar.TitleBar;
import com.liux.base.titlebar.TransparentTitleBar;
import com.domain.name.R;
import com.domain.name.base.BaseActivity;

import java.util.Map;

/**
 * Created by Liux on 2017/8/17.
 */

public class LaunchActivity extends BaseActivity {

    @Override
    protected TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle, Intent intent) {
        setContentView(R.layout.activity_launch);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (getAppPresenter().showGuide()) {
                    startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                }
                finish();
            }
        }, 1000);
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
