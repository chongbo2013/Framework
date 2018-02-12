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

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class GuideActivity extends BaseMvpActivity<StartContract.Presenter> implements StartContract.View {

    @Override
    public TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
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
}
