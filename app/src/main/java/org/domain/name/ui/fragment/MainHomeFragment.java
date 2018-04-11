package org.domain.name.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.liux.abstracts.util.TitleBarUtil;
import com.liux.banner.BannerAdapter;
import com.liux.banner.BannerHolder;
import com.liux.banner.BannerView;
import com.liux.banner.DefaultIndicator;
import com.liux.framework.base.BaseMvpFragment;
import com.liux.glide.GlideApp;
import com.liux.util.ScreenUtil;
import com.liux.view.SingleToast;
import com.mobsandgeeks.saripaar.annotation.Url;

import org.domain.name.R;
import org.domain.name.mvp.contract.HomeContract;
import org.domain.name.mvp.model.bean.BannerBean;
import org.domain.name.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainHomeFragment extends BaseMvpFragment<HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.cl_title)
    CoordinatorLayout clTitle;
    @BindView(R.id.abl_title)
    AppBarLayout ablTitle;
    @BindView(R.id.ctl_title)
    CollapsingToolbarLayout ctlTitle;
    @BindView(R.id.bv_banner)
    BannerView bvBanner;
    @BindView(R.id.tb_title)
    Toolbar tbTitle;
    @Url(message = "请输入正确的网址")
    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.btn_webview)
    Button btnWebview;

    private List<BannerBean> mBanners;
    private BannerAdapter<BannerBean> mBannerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) rootView.findViewById(R.id.tb_title).getLayoutParams();
        marginLayoutParams.topMargin += TitleBarUtil.getTransparentStatusBarHeight(getActivity());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        srlRefresh.setOnRefreshListener(() -> {
            srlRefresh.postDelayed(() -> {
                srlRefresh.setRefreshing(false);
            }, 1000);
        });

        // https://stackoverflow.com/questions/40928788/cant-call-void-android-view-view-setelevationfloat-on-null-object-in-lapism-s
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ablTitle.setStateListAnimator(null);
        }
        ablTitle.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            srlRefresh.setEnabled(verticalOffset == 0);
            // verticalOffset 取值 0 - -appBarLayout.getTotalScrollRange()
            float verticalOffsetFloat = Math.abs(verticalOffset) + 0.0f;
            tbTitle.setAlpha(verticalOffsetFloat / appBarLayout.getTotalScrollRange());
        });

        mBanners = new ArrayList<>();
        mBanners.add(new BannerBean());
        mBannerAdapter = new BannerAdapter<BannerBean>(mBanners, R.layout.item_banner) {
            @Override
            public void onBindData(BannerHolder holder, BannerBean bannerBean, int index) {
                holder
                        .setText(R.id.tv_hint, bannerBean.getTitle());
                ImageView imageView = holder.getView(R.id.iv_pic);
                GlideApp.with(imageView)
                        .asBitmap()
                        .load(bannerBean.getUrl())
                        .into(imageView);
            }
        };
        bvBanner.setAdapter(mBannerAdapter);
        bvBanner.setIndicator(new DefaultIndicator(getContext(), R.drawable.indicator_bg));

        addIgnoreView(btnWebview);

        mPresenter.loadBanner();
    }

    @OnClick(R.id.btn_webview)
    void onViewClicked() {
        getValidator().setViewValidatedAction(view -> {
            WebViewActivity.startWebView(getContext(), etUrl.getText().toString());
        });
        getValidator().validate();
    }

    @Override
    public void loadSucceed(List<BannerBean> bannerBeans) {
        mBanners.clear();
        mBanners.addAll(bannerBeans);
        mBannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadFailure(String msg) {
        mUiProvider.provideIToast().showError(msg);
    }
}
