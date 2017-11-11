package com.domain.name.ui.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.liux.base.titlebar.DefaultTitleBar;
import com.liux.util.ScreenUtil;
import com.domain.name.R;

/**
 * Created by Liux on 2017/11/7.
 */

public class WhiteTitleBar extends DefaultTitleBar {

    public WhiteTitleBar(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void initView() {
        super.initView();
        getBackIcon().setImageResource(R.drawable.b2_nav_icon);

        setStatusBarColor(Color.WHITE);
        setTitleBarColor(Color.WHITE);
        setTitleColor(Color.parseColor("#333333"));

        View view = new View(getActivity());
        view.setBackgroundColor(Color.parseColor("#EEEEEE"));
        ((ViewGroup) getView()).addView(view, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.dp2px(getActivity(), 1.0f)
        ));

        setStatusBarMode(true);
    }
}
