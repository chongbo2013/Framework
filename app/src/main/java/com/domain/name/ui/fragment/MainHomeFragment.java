package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.domain.framework.base.BaseMvpFragment;
import com.domain.name.mvp.contract.HomeContract;
import com.liux.util.ScreenUtil;
import com.domain.name.R;
import com.domain.name.ui.activity.WebViewActivity;
import com.liux.view.SingleToast;

import java.util.List;

import butterknife.OnClick;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainHomeFragment extends BaseMvpFragment<HomeContract.Presenter> implements HomeContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        return rootView;
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
