package com.liux.framework.ui;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

public interface IProgressDialog extends IDialog {

    void setMax(int max);

    void setProgress(int progress);
}
