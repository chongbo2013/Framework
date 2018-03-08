package org.domain.name.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liux.abstracts.titlebar.DefaultTitleBar;
import com.liux.framework.base.BaseActivity;
import com.liux.util.ScreenUtil;
import com.liux.view.SingleToast;
import com.tencent.bugly.crashreport.CrashReport;

import org.domain.name.R;
import org.domain.name.ui.js.JavaScript;

import java.io.Serializable;
import java.util.Map;

/**
 * 2017/11/7
 * By Liux
 * lx0758@qq.com
 */

public class WebViewActivity extends BaseActivity {
    private static final String PARAM_URL = "WebViewActivity_url";
    private static final String PARAM_TITLE = "WebViewActivity_title";
    private static final String PARAM_HEADER = "WebViewActivity_header";

    private JavaScript mJavaScript;

    private WebView mWebView;
    private ProgressBar mProgressBar;

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            mProgressBar.setProgress(0);
            mProgressBar.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

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
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress > 85) {
                // 大于85是国际惯例
                mProgressBar.setProgress(100);
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        addContentView(mWebView, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));

        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        addContentView(mProgressBar, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.dp2px(this, 1.5f)
        ));
    }

    @Override
    protected void onInitData(@Nullable Bundle savedInstanceState, Intent intent) {
        mUrl = intent.getStringExtra(PARAM_URL);
        mTitle = intent.getBooleanExtra(PARAM_TITLE, false);
        mHeader = (Map<String, String>) intent.getSerializableExtra(PARAM_HEADER);
    }

    @Override
    public void onRestoreData(Map<String, Object> data) {
        mUrl = (String) data.get(PARAM_URL);
        mTitle = (Boolean) data.get(PARAM_TITLE);
        mHeader = (Map<String, String>) data.get(PARAM_HEADER);
    }

    @Override
    protected void onInitView() {
        if (mWebView == null || mProgressBar == null) return;

        if (!mTitle) {
            ((DefaultTitleBar) getTitleBar()).getView().setVisibility(View.GONE);
        } else {
            setTitle(mUrl);
        }

        mJavaScript = new JavaScript(this, mWebView);

        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setDownloadListener(mDownloadListener);
        mWebView.addJavascriptInterface(mJavaScript, JavaScript.CLASS_NAME);
        initSetting();

        Drawable[] drawables = new Drawable[3];
        drawables[0] = new ColorDrawable(0x60000000);
        drawables[1] = new ClipDrawable(
                new ColorDrawable(0x60000000),
                Gravity.LEFT,
                ClipDrawable.HORIZONTAL
        );
        drawables[2] = new ClipDrawable(
                new ColorDrawable(getResources().getColor(R.color.colorAccent)),
                Gravity.LEFT,
                ClipDrawable.HORIZONTAL
        );
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        mProgressBar.setProgressDrawable(layerDrawable);
        mProgressBar.setMax(100);

        CrashReport.setJavascriptMonitor(mWebView, true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHeader != null) {
            mWebView.loadUrl(mUrl, mHeader);
        } else {
            mWebView.loadUrl(mUrl);
        }
    }

    @Override
    public void onSaveData(Map<String, Object> data) {
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
            SingleToast.makeText(this, R.string.web_open_error, SingleToast.LENGTH_SHORT).show();
        }
    }
}
