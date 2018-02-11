package com.domain.name.ui.js;

import android.app.Activity;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * 2017/11/7
 * By Liux
 * lx0758@qq.com
 */

public class JavaScript {
    public static final String CLASS_NAME = "user_app";

    private Activity mActivty;
    private WebView mWebView;

    public JavaScript(Activity activity, WebView webView) {
        mActivty = activity;
        mWebView = webView;
    }

    public void callJavaScript(String method, Object... params) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(method).append('(');

        if (params != null && params.length > 0) {
            for (Object param : params) {
                stringBuffer
                        .append('\'')
                        .append(String.valueOf(param))
                        .append('\'')
                        .append(',');
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }

        stringBuffer.append(");");

        final String cmd = stringBuffer.toString();
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    mWebView.evaluateJavascript(cmd, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            // 接收返回值
                        }
                    });
                } else {
                    mWebView.loadUrl(cmd);
                }
            }
        });
    }

    @JavascriptInterface
    public String getToken() {
        String token = "";
        return token;
    }
}
