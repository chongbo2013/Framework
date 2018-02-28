package com.domain.name.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domain.name.R;
import com.liux.framework.base.BaseFragment;
import com.liux.util.ScreenUtil;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public class MainServerFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_server, container, false);
        rootView.findViewById(R.id.fl_status_bar).setPadding(0, ScreenUtil.getTransparentStatusBarHeight(getContext()), 0, 0);
        return rootView;
    }
}
