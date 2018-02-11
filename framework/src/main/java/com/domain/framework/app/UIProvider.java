package com.domain.framework.app;

import android.content.Context;

import com.domain.framework.ui.IAlertDialog;
import com.domain.framework.ui.IProgressDialog;
import com.domain.framework.ui.IToast;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public interface UIProvider {

    IToast provideIToast();

    IAlertDialog provideIAlertDialog(Context context);

    IProgressDialog provideIProgressDialog(Context context);
}
