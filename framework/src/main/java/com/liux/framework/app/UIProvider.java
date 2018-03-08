package com.liux.framework.app;

import android.content.Context;

import com.liux.framework.ui.IAlertDialog;
import com.liux.framework.ui.ILoadingDialog;
import com.liux.framework.ui.IProgressDialog;
import com.liux.framework.ui.IToast;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public interface UIProvider {

    IToast provideIToast();

    IAlertDialog provideIAlertDialog(Context context);

    ILoadingDialog provideILoadingDialog(Context context);

    IProgressDialog provideIProgressDialog(Context context);
}
