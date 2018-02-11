package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liux.util.ScreenUtil;
import com.domain.name.R;
import com.domain.framework.base.BaseFragment;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainCircleFragment extends BaseFragment {
    @Override
    protected void onInitData(Bundle savedInstanceState) {

    }

    @Override
    protected View onInitView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_circle, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        return rootView;
    }

    @Override
    protected void onLazyLoad() {

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
}
