package org.domain.name.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import org.domain.name.app.IUserInfo;

/**
 * 2017/11/11
 * By Liux
 * lx0758@qq.com
 */

public class LocalService extends Service {

    private IBinder mIBinder = new IUserInfo.Stub() {
        @Override
        public boolean isLogin() throws RemoteException {
            return false;
        }

        @Override
        public void login(String data) throws RemoteException {

        }

        @Override
        public void logout() throws RemoteException {

        }

        @Override
        public String getAuthorization() throws RemoteException {
            return null;
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
