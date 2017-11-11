package com.domain.name.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.domain.name.IUserInfo;
import com.liux.other.Logger;

/**
 * Created by Liux on 2017/11/11.
 */

public class LocalService extends Service {
    public static final String TAG = "LocalService";

    private IBinder mIBinder = new IUserInfo.Stub() {
        @Override
        public String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            return String.valueOf(System.currentTimeMillis());
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
