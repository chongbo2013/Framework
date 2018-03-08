package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.R;
import com.domain.name.mvp.contract.HomeContract;
import com.domain.name.ui.activity.WebViewActivity;
import com.liux.framework.base.BaseMvpFragment;
import com.liux.util.ScreenUtil;
import com.liux.view.SingleToast;
import com.mobsandgeeks.saripaar.annotation.Url;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainHomeFragment extends BaseMvpFragment<HomeContract.Presenter> implements HomeContract.View {

    @Url(message = "请输入正确的网址")
    @BindView(R.id.et_url)
    EditText etUrl;
    @BindView(R.id.btn_webview)
    Button btnWebview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_home, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void loadSucceed(List<JSONObject> jsonObjects) {
        SingleToast.makeText(getContext(), "载入Banner成功", SingleToast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFailure(String msg) {
        mUiProvider.provideIToast().showError(msg);
    }
}
