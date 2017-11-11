package com.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liux.base.titlebar.TitleBar;
import com.liux.base.titlebar.TransparentTitleBar;
import com.domain.name.R;
import com.domain.name.base.BaseActivity;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by Liux on 2017/8/17.
 */

public class GuideActivity extends BaseActivity {

    @Override
    protected TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle, Intent intent) {
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
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
