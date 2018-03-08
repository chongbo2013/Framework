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

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainActivity extends BaseActivity {
    private static final int INDEX_HOME = 0;
    private static final int INDEX_CIRCLE = 1;
    private static final int INDEX_SERVER = 2;
    private static final int INDEX_CENTER = 3;

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.iv_shouye)
    ImageView ivShouye;
    @BindView(R.id.tv_shouye)
    TextView tvShouye;
    @BindView(R.id.iv_zhishiquan)
    ImageView ivZhishiquan;
    @BindView(R.id.tv_zhishiquan)
    TextView tvZhishiquan;
    @BindView(R.id.iv_kefu)
    ImageView ivKefu;
    @BindView(R.id.tv_kefu)
    TextView tvKefu;
    @BindView(R.id.iv_wode)
    ImageView ivWode;
    @BindView(R.id.tv_wode)
    TextView tvWode;

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
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabButton(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        changeTabButton(vpContent.getCurrentItem());
    }

    @OnClick({R.id.ll_shouye, R.id.ll_zhishiquan, R.id.ll_kefu, R.id.ll_wode})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shouye:
                vpContent.setCurrentItem(INDEX_HOME);
                break;
            case R.id.ll_zhishiquan:
                vpContent.setCurrentItem(INDEX_CIRCLE);
                break;
            case R.id.ll_kefu:
                vpContent.setCurrentItem(INDEX_SERVER);
                break;
            case R.id.ll_wode:
                vpContent.setCurrentItem(INDEX_CENTER);
                break;
        }
    }

    private void changeTabButton(int position) {
        ivShouye.setSelected(false);
        tvShouye.setSelected(false);
        ivZhishiquan.setSelected(false);
        tvZhishiquan.setSelected(false);
        ivKefu.setSelected(false);
        tvKefu.setSelected(false);
        ivWode.setSelected(false);
        tvWode.setSelected(false);
        switch (position) {
            case INDEX_HOME:
                ivShouye.setSelected(true);
                tvShouye.setSelected(true);
                break;
            case INDEX_CIRCLE:
                ivZhishiquan.setSelected(true);
                tvZhishiquan.setSelected(true);
                break;
            case INDEX_SERVER:
                ivKefu.setSelected(true);
                tvKefu.setSelected(true);
                break;
            case INDEX_CENTER:
                ivWode.setSelected(true);
                tvWode.setSelected(true);
                break;
        }
    }
}
