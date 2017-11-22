package com.domain.name.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.domain.name.base.BaseDialog;

/**
 * Created by Liux on 2017/11/11.
 */

public class MessageDialog extends BaseDialog<MessageDialog> {

    private TextView mTextView;

    private OnMessageListener mOnMessageListener;

    public MessageDialog(Context context) {
        super(context);
    }

    @Override
    protected void initView(ViewGroup viewGroup) {
        int color = Color.parseColor("#333333");
        mTextView = new TextView(getContext());
        mTextView.setTextColor(color);
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18.0F);

        setCancelTextColor(color);
        setEnsureTextColor(color);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;
        viewGroup.addView(mTextView, layoutParams);
    }

    @Override
    protected boolean onCancel() {
        if (mOnMessageListener != null) {
            return mOnMessageListener.onCancel();
        }
        return true;
    }

    @Override
    protected boolean onEnsure() {
        if (mOnMessageListener != null) {
            return mOnMessageListener.onEnsure();
        }
        return true;
    }

    public MessageDialog setMessage(int msgid) {
        mTextView.setText(msgid);
        return this;
    }

    public MessageDialog setMessage(CharSequence message) {
        mTextView.setText(message);
        return this;
    }

    public MessageDialog setOnMessageListener(OnMessageListener listener) {
        mOnMessageListener = listener;
        return this;
    }

    public interface OnMessageListener {

        boolean onCancel();

        boolean onEnsure();
    }
}
