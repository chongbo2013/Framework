package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liux.util.ScreenUtil;
import com.domain.name.R;
import com.domain.framework.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainCenterFragment extends BaseFragment {

    @BindView(R.id.iv_header)
    ImageView ivHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_center, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        return rootView;
    }

    @OnClick(R.id.iv_header)
    public void onViewClicked() {

    }
}
