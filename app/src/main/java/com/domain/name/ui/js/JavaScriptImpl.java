package com.domain.name.ui.js;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by Liux on 2017/11/29.
 */

public class JavaScriptImpl implements JavaScript {
    public static final String CLASS_NAME = "user_app";

    private Activity mActivty;
    private WebView mWebView;

    public JavaScriptImpl(Activity activity, WebView webView) {
        mActivty = activity;
        mWebView = webView;
    }

    @Override
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
                mWebView.evaluateJavascript(cmd, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {
                        // 接收返回值
                    }
                });
            }
        });
    }

    @Override
    @JavascriptInterface
    public String getToken() {
        String token = "";
        return token;
    }
}
