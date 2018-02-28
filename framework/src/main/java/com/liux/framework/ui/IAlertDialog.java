package com.liux.framework.ui;

import android.support.annotation.StringRes;
import android.view.View;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public interface IAlertDialog {

    void setTitle(CharSequence title);
    
    void setTitle(@StringRes int title);
    
    void setContent(CharSequence content);
    
    void setContent(@StringRes int content);

    void setPositiveButton(CharSequence buttonText, View.OnClickListener listener);
    
    void setPositiveButton(@StringRes int buttonText, View.OnClickListener listener);
    
    void setNegativeButton(CharSequence buttonText, View.OnClickListener listener);
    
    void setNegativeButton(@StringRes int buttonText, View.OnClickListener listener);
}
