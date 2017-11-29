package com.domain.name.ui.js;

import android.webkit.JavascriptInterface;

/**
 * Created by Liux on 2017/11/29.
 */

public class JavaScriptImpl implements JavaScript {
    public static final String CLASS_NAME = "user_app";

    @Override
    @JavascriptInterface
    public String j2nr(String param1, String param2) {
        return null;
    }

    @Override
    @JavascriptInterface
    public void Link(String param) {

    }
}
