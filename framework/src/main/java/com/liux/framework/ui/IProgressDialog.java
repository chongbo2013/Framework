package com.liux.framework.ui;

import android.support.annotation.StringRes;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public interface IProgressDialog {

    void setTitle(CharSequence title);

    void setTitle(@StringRes int title);

    void setMax(int max);

    void setProgress(int progress);
}
