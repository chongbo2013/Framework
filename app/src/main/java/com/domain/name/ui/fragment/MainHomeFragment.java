package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.di.component.DaggerPresenterComponent;
import com.domain.name.di.model.PresentersModel;
import com.domain.name.mvp.contract.HomeContract;
import com.domain.name.mvp.presenter.HomePresenter;
import com.liux.util.ScreenUtil;
import com.domain.name.R;
import com.domain.name.base.BaseFragment;
import com.domain.name.ui.activity.WebViewActivity;
import com.liux.view.SingleToast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Liux on 2017/11/6.
 */

public class MainHomeFragment extends BaseFragment implements HomeContract.View {

    @Inject
    HomePresenter mHomePresenter;

    Unbinder unbinder;

    @Override
    protected void onInitData(Bundle savedInstanceState) {
        DaggerPresenterComponent.builder()
                .presentersModel(PresentersModel.create(this))
                .build()
                .inject(this);
    }

    @Override
    protected View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLazyLoad() {
        mHomePresenter.loadBanner();
    }

    @Override
    protected void onRestoreData(Bundle data) {

    }

    @Override
    protected void onSaveData(Bundle data) {

    }

    @Override
    protected void onVisibleChanged() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_webview)
    public void onViewClicked() {
        WebViewActivity.startWebView(getContext(), "http://6xyun.cn");
    }

    @Override
    public void loadSucceed(List<JSONObject> jsonObjects) {

    }

    @Override
    public void loadFailure(String msg) {
        SingleToast.makeText(getContext(), msg, SingleToast.LENGTH_SHORT).show();
    }
}
