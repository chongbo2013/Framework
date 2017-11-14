package com.domain.name.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.domain.name.R;

/**
 * Created by Liux on 2017/11/6.
 */

public abstract class BaseDialog<T extends BaseDialog> extends com.liux.base.BaseDialog {

    private View mRoot;
    private TextView mCancel, mEnsure;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_ensure:
                    if (!onEnsure()) return;
                    break;
                case R.id.tv_cancel:
                default:
                    if (!onCancel()) return;
                    break;
            }
            dismiss();
        }
    };

    public BaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public T setCancelText(CharSequence cancel) {
        mCancel.setText(cancel);
        return (T) this;
    }

    public T setEnsureText(CharSequence ensure) {
        mEnsure.setText(ensure);
        return (T) this;
    }

    public T hideCancel() {
        mEnsure.setBackgroundResource(R.drawable.dialog_base_bg_right_all);
        mCancel.setVisibility(View.GONE);
        return (T) this;
    }

    public T hideEnsure() {
        mCancel.setBackgroundResource(R.drawable.dialog_base_bg_left_all);
        mEnsure.setVisibility(View.GONE);
        return (T) this;
    }

    protected abstract void initView(ViewGroup viewGroup);

    protected abstract boolean onCancel();

    protected abstract boolean onEnsure();

    private void init() {
        mRoot = View.inflate(getContext(), R.layout.dialog_base, null);
        mRoot.setOnClickListener(mOnClickListener);

        setContentView(mRoot);

        mCancel = (TextView) findViewById(R.id.tv_cancel);
        mEnsure = (TextView) findViewById(R.id.tv_ensure);
        mCancel.setOnClickListener(mOnClickListener);
        mEnsure.setOnClickListener(mOnClickListener);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.fl_content);
        initView(viewGroup);
    }
}
