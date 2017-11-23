package com.domain.name.app.control;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.alibaba.fastjson.JSON;
import com.domain.name.app.AppControl;
import com.domain.name.app.IUserInfo;
import com.domain.name.app.service.LocalService;
import com.domain.name.data.bean.UserBean;

/**
 * Created by Liux on 2017/11/6.
 */

public class RemoteControl implements AppControl.Presenter {
    private Application mApplication;

    private IUserInfo mIUserInfo;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIUserInfo = IUserInfo.Stub.asInterface(service);
            ;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIUserInfo = null;
        }
    };

    public RemoteControl(Application application) {
        mApplication = application;

        Intent intent = new Intent(application, LocalService.class);
        application.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean showGuide() {
        return false;
    }

    @Override
    public void saveGuide() {

    }

    @Override
    public boolean isLogin() {
        try {
            return mIUserInfo.isLogin();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void login(UserBean userBean) {
        try {
            mIUserInfo.login(JSON.toJSON(userBean).toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logout() {
        try {
            mIUserInfo.logout();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAuthorization() {
        try {
            return mIUserInfo.getAuthorization();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
    }
}
