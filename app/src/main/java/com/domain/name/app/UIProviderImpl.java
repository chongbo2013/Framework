package com.domain.name.app;

import android.content.Context;

import com.domain.name.ui.provider.AlertDialogImpl;
import com.domain.name.ui.provider.LoadingDialogImpl;
import com.domain.name.ui.provider.ProgressDialogImpl;
import com.domain.name.ui.provider.ToastImpl;
import com.liux.framework.app.UIProvider;
import com.liux.framework.ui.IAlertDialog;
import com.liux.framework.ui.ILoadingDialog;
import com.liux.framework.ui.IProgressDialog;
import com.liux.framework.ui.IToast;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public class UIProviderImpl implements UIProvider {

    public UIProviderImpl(Context context) {
        ToastImpl.initialize(context);
    }

    @Override
    public IToast provideIToast() {
        return ToastImpl.getInstance();
    }

    @Override
    public IAlertDialog provideIAlertDialog(Context context) {
        return new AlertDialogImpl(context);
    }

    @Override
    public ILoadingDialog provideILoadingDialog(Context context) {
        return new LoadingDialogImpl(context);
    }

    @Override
    public IProgressDialog provideIProgressDialog(Context context) {
        return new ProgressDialogImpl(context);
    }
}
