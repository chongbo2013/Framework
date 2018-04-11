package org.domain.name.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;
import com.liux.framework.base.BaseActivity;

import org.domain.name.R;
import org.domain.name.ui.adapter.TabAdapter;
import org.domain.name.ui.fragment.MainCenterFragment;
import org.domain.name.ui.fragment.MainCircleFragment;
import org.domain.name.ui.fragment.MainHomeFragment;
import org.domain.name.ui.fragment.MainServerFragment;
import org.domain.name.ui.view.TabNavigationView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.tnv_navigation)
    TabNavigationView tnvNavigation;

    private long mLastBackTime;

    private Fragment[] mFragments = {
            new MainHomeFragment(),
            new MainCircleFragment(),
            new MainServerFragment(),
            new MainCenterFragment()
    };

    @Override
    public TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onInitView() {
        vpContent.setOffscreenPageLimit(4);
        vpContent.setAdapter(new TabAdapter(getSupportFragmentManager(), mFragments));
        tnvNavigation.setItemTextSize(12.0f, 12.0f);
        tnvNavigation.setupWithViewPager(vpContent);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastBackTime < 1500) {
            super.onBackPressed();
        } else {
            mUiProvider.provideIToast().showInfo("再次点击退出应用");
            mLastBackTime = System.currentTimeMillis();
        }
    }
}
