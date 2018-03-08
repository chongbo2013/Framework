package org.domain.name.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;
import com.liux.framework.base.BaseMvpActivity;

import org.domain.name.R;
import org.domain.name.mvp.contract.StartContract;
import org.domain.name.ui.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnPageChange;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class GuideActivity extends BaseMvpActivity<StartContract.Presenter> implements StartContract.View {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.ll_point)
    LinearLayout llPoint;
    @BindView(R.id.btn_join)
    Button btnJion;

    private int[] mImages = {R.drawable.launch_bg, R.drawable.launch_bg, R.drawable.launch_bg};
    private List<View> mViews = new ArrayList<>();

    @Override
    public TitleBar onInitTitleBar() {
        return new TransparentTitleBar(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_guide);

        initPager();
    }

    private void initPager() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        for (int i = 0; i < mImages.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(params);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(mImages[i]);
            mViews.add(iv);
        }
        // 加入适配器
        vpContent.setAdapter(new GuideAdapter(mViews));


        // 1.根据图片多少，添加多少小圆点
        for (int i = 0; i < mImages.length; i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 0) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(20, 0, 0, 0);
            }
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(pointParams);
            iv.setBackgroundResource(R.drawable.guide_point_normal);
            llPoint.addView(iv);
        }

        if (mImages.length > 0) {
            llPoint.getChildAt(0).setBackgroundResource(R.drawable.guide_point_checked);
        } else {
            btnJion.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_join)
    void onViewClicked() {
        mPresenter.saveGuide();
        startActivity(new Intent(GuideActivity.this, MainActivity.class));
        finish();
    }

    @OnPageChange(R.id.vp_content)
    void onPageSelected(int position) {
        monitorPoint(position);
    }

    private void monitorPoint(int position) {
        for (int i = 0; i < mImages.length; i++) {
            if (i == position) {
                llPoint.getChildAt(position).setBackgroundResource(R.drawable.guide_point_checked);
            } else {
                llPoint.getChildAt(i).setBackgroundResource(R.drawable.guide_point_normal);
            }
        }
        // 3.当滑动到最后一个添加按钮点击进入，
        if (position == mImages.length - 1) {
            btnJion.setVisibility(View.VISIBLE);
        } else {
            btnJion.setVisibility(View.GONE);
        }
    }
}
