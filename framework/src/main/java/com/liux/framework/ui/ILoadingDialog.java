package com.liux.framework.ui;

import android.support.annotation.StringRes;

/**
 * 2018-3-8
 * By Liux
 * lx0758@qq.com
 */

public interface ILoadingDialog extends IDialog {

    void setMessage(CharSequence message);

    void setMessage(@StringRes int messageResId);
}
