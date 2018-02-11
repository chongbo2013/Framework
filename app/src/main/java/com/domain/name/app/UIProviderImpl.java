package com.domain.name.app;

import android.content.Context;

import com.domain.framework.app.UIProvider;
import com.domain.framework.ui.IAlertDialog;
import com.domain.framework.ui.IProgressDialog;
import com.domain.framework.ui.IToast;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public class UIProviderImpl implements UIProvider {

    @Override
    public IToast provideIToast() {
        return null;
    }

    @Override
    public IAlertDialog provideIAlertDialog(Context context) {
        return null;
    }

    @Override
    public IProgressDialog provideIProgressDialog(Context context) {
        return null;
    }
}
