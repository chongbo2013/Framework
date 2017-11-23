package com.domain.name.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.domain.name.app.ApplicationCus;
import com.domain.name.app.IUserInfo;
import com.domain.name.data.bean.UserBean;
import com.liux.other.Logger;

/**
 * Created by Liux on 2017/11/11.
 */

public class LocalService extends Service {
    public static final String TAG = "LocalService";

    private IBinder mIBinder = new IUserInfo.Stub() {
        @Override
        public boolean isLogin() throws RemoteException {
            return ApplicationCus.getAppPresenter().isLogin();
        }

        @Override
        public void login(String data) throws RemoteException {
            UserBean userBean = JSON.parseObject(data, UserBean.class);
            ApplicationCus.getAppPresenter().login(userBean);
        }

        @Override
        public void logout() throws RemoteException {
            ApplicationCus.getAppPresenter().logout();
        }

        @Override
        public String getAuthorization() throws RemoteException {
            return ApplicationCus.getAppPresenter().getAuthorization();
        }
    };

    @Override
    public void onCreate() {
        Logger.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.i(TAG, "onBind");
        return mIBinder;
    }

    @Override
    public void onDestroy() {
        Logger.i(TAG, "onDestroy");
    }
}
