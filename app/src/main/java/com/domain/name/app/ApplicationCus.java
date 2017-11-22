package com.domain.name.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.domain.name.app.control.LocalControl;
import com.domain.name.data.conf.URL;
import com.liux.http.HttpClient;
import com.liux.util.DeviceUtil;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by Liux on 2017/8/17.
 */

public class ApplicationCus extends Application {

    private static Context mContext;
    private static AppControl.View mView;
    private static AppControl.Presenter mPresenter;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        HttpClient.initialize(this, URL.URL_ROOT);
        HttpClient.getInstance().setOnCheckHeadersListener(new HttpClient.OnCheckHeadersListener() {
            @Override
            public void onCheckHeaders(Request request, Map<String, String> headers) {
                if (request.url().toString().startsWith(URL.URL_ROOT)) {
                    headers.put("Accept", "application/json");
                }
            }
        });

        if (DeviceUtil.isMainProcess(this)) {
            LocalControl control = new LocalControl(this);
            mView = control;
            mPresenter = control;
        } else {

        }
    }

    public static Context getContext() {
        return mContext;
    }

    public static AppControl.View getAppView() {
        return mView;
    }

    public static AppControl.Presenter getAppPresenter() {
        return mPresenter;
    }
}
