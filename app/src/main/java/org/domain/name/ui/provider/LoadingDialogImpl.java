package org.domain.name.ui.provider;

import android.app.ProgressDialog;
import android.content.Context;

import com.liux.framework.ui.ILoadingDialog;

/**
 * 2018/3/8
 * By Liux
 * lx0758@qq.com
 */

public class LoadingDialogImpl implements ILoadingDialog {

    private ProgressDialog mProgressDialog;

    public LoadingDialogImpl(Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
    }

    @Override
    public boolean isShowing() {
        return mProgressDialog.isShowing();
    }

    @Override
    public void show() {
        mProgressDialog.show();
    }

    @Override
    public void dismiss() {
        mProgressDialog.dismiss();
    }

    @Override
    public void cancel() {
        mProgressDialog.cancel();
    }

    @Override
    public void setCancelable(boolean cancelable) {
        mProgressDialog.setCancelable(cancelable);
    }

    @Override
    public void setOnCancelListener(OnCancelListener onCancelListener) {
        mProgressDialog.setOnCancelListener(dialog -> {
            if (onCancelListener != null) onCancelListener.onCancel(LoadingDialogImpl.this);
        });
    }

    @Override
    public void setOnDismissListener(OnDismissListener onDismissListener) {
        mProgressDialog.setOnDismissListener(dialog -> {
            if (onDismissListener != null) onDismissListener.onDismiss(LoadingDialogImpl.this);
        });
    }

    @Override
    public void setOnShowListener(OnShowListener onShowListener) {
        mProgressDialog.setOnShowListener(dialog -> {
            if (onShowListener != null) onShowListener.onShow(LoadingDialogImpl.this);
        });
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {

    }

    @Override
    public void setMessage(CharSequence message) {
        mProgressDialog.setMessage(message);
    }

    @Override
    public void setMessage(int messageResId) {
        setMessage(mProgressDialog.getContext().getString(messageResId));
    }
}
