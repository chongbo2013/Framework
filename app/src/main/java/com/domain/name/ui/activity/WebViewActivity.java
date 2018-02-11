package com.domain.name.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.domain.framework.base.BaseActivity;
import com.domain.name.ui.js.JavaScript;
import com.liux.abstracts.titlebar.DefaultTitleBar;
import com.liux.view.SingleToast;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Liux on 2017/11/7.
 */

public class WebViewActivity extends BaseActivity {
    private static final String PARAM_URL = "WebViewActivity_url";
    private static final String PARAM_TITLE = "WebViewActivity_title";
    private static final String PARAM_HEADER = "WebViewActivity_header";

    private WebView mWebView;
    private JavaScript mJavaScript;

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (view.getHitTestResult().getType() == WebView.HitTestResult.UNKNOWN_TYPE) {
                return false;
            } else {
                startWebView(WebViewActivity.this, url, null, true);
                return true;
            }
        }
    };

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    };

    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            openExternalUrl(url, mimetype);
            if (url.equals(mUrl)) finish();
        }
    };

    private String mUrl;
    private Boolean mTitle;
    private Map<String, String> mHeader;

    public static void startWebView(Context context, String url) {
        startWebView(context, url, null, true);
    }

    public static void startWebView(Context context, String url, Map<String, String> header, boolean title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(PARAM_URL, url);
        if (header != null) {
            intent.putExtra(PARAM_HEADER, (Serializable) header);
        }
        intent.putExtra(PARAM_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState, Intent intent) {
        mWebView = new WebView(this);
        setContentView(mWebView);
    }

    @Override
    protected void onInitData(@Nullable Bundle savedInstanceState, Intent intent) {
        mUrl = intent.getStringExtra(PARAM_URL);
        mTitle = intent.getBooleanExtra(PARAM_TITLE, false);
        mHeader = (Map<String, String>) intent.getSerializableExtra(PARAM_HEADER);
    }

    @Override
    protected void onRestoreData(Map<String, Object> data) {
        mUrl = (String) data.get(PARAM_URL);
        mTitle = (Boolean) data.get(PARAM_TITLE);
        mHeader = (Map<String, String>) data.get(PARAM_HEADER);
    }

    @Override
    protected void onInitView(@Nullable Bundle savedInstanceState) {
        if (!mTitle) {
            ((DefaultTitleBar) getTitleBar()).getView().setVisibility(View.GONE);
        }

        mJavaScript = new JavaScript(this, mWebView);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setDownloadListener(mDownloadListener);
        mWebView.addJavascriptInterface(mJavaScript, JavaScript.CLASS_NAME);

        initSetting();

        CrashReport.setJavascriptMonitor(mWebView, true);
    }

    @Override
    protected void onLazyLoad() {
        if (mHeader != null) {
            mWebView.loadUrl(mUrl, mHeader);
        } else {
            mWebView.loadUrl(mUrl);
        }
    }

    @Override
    protected void onSaveData(Map<String, Object> data) {
        data.put(PARAM_URL, mUrl);
        data.put(PARAM_TITLE, mTitle);
        data.put(PARAM_HEADER, mHeader);
    }

    @Override
    public void onDestroy() {
        mWebView.removeAllViews();
        mWebView.destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    /**
     * 初始化 WebView 各项设置
     */
    private void initSetting() {
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);

        webSettings.setDatabaseEnabled (true);
        webSettings.setDomStorageEnabled (true);
        webSettings.setGeolocationEnabled (true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void openExternalUrl(String url, String mimeType) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(url), mimeType);
            startActivity(intent);
        } catch (Exception e) {
            SingleToast.makeText(this, "没有找到对应的应用程序,打开链接失败", SingleToast.LENGTH_SHORT).show();
        }
    }
}
