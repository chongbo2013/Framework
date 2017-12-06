package com.domain.name.ui.js;

/**
 * Created by Liux on 2017/11/7.
 */

public interface JavaScript {

    /**
     * 调用JS方法
     * @param method
     * @param params
     */
    void callJavaScript(String method, Object... params);

    /**
     * 获取Token
     */
    String getToken();
}
