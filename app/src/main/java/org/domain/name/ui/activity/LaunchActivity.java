package org.domain.name.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.liux.abstracts.titlebar.TitleBar;
import com.liux.abstracts.titlebar.TransparentTitleBar;
import com.liux.framework.base.BaseMvpActivity;
import com.liux.tool.CountDownTimer;
import com.liux.util.ScreenUtil;

import org.domain.name.R;
import org.domain.name.mvp.contract.StartContract;

import java.util.Locale;

/**
 * 2017/8/17
 * By Liux
 * lx0758@qq.com
 */

public class LaunchActivity extends BaseMvpActivity<StartContract.Presenter> implements StartContract.View {

    private TextView mTextView;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        getWindow().setBackgroundDrawableResource(R.drawable.launch_bg);

        super.onCreate(bundle);

        initView();
        setContentView(mTextView);

        mCountDownTimer = new CountDownTimer.Builder()
                .gross(3 * 1000)
                .interval(1 * 1000)
                .listener(new CountDownTimer.OnTimerListener() {
                    @Override
                    public void onReset(int requestCode) {

                    }

                    @Override
                    public void onTick(int requestCode, long surplus) {
                        mTextView.setText(String.format(Locale.CHINA, "%d 跳过", (int)(surplus / 1000.0 + 0.5)));
                    }

                    @Override
                    public void onFinish(int requestCode) {
                        if (mPresenter.showGuide()) {
                            startActivity(new Intent(LaunchActivity.this, GuideActivity.class));
                        } else {
                            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                        }
                        finish();
                    }
                })
                .build();
        mCountDownTimer.start();
    }

    private void initView() {
        mTextView = new TextView(this);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12.0f);
        mTextView.setTextColor(Color.parseColor("#FFFFFF"));
        mTextView.setBackgroundColor(Color.parseColor("#80000000"));
        int padding = ScreenUtil.dp2px(this, 8.0f);
        mTextView.setPadding(padding, padding / 2, padding, padding / 2);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.RIGHT;
        layoutParams.topMargin = padding * 3;
        layoutParams.rightMargin = padding * 3;
        mTextView.setLayoutParams(layoutParams);
        mTextView.setOnClickListener(v -> mCountDownTimer.finish());
    }
}
