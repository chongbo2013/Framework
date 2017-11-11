package com.domain.name.app.control;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.domain.name.IUserInfo;
import com.domain.name.app.AppControl;
import com.domain.name.app.service.LocalService;

import java.util.List;
import java.util.Vector;

/**
 * Created by Liux on 2017/11/6.
 */

public class RemoteControl implements AppControl.Presenter, AppControl.View {
    private Application mApplication;

    private Activity mTopActivity;
    private Vector<Activity> mAppActivitys = new Vector<>();

    private Application.ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            mAppActivitys.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            mTopActivity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {
            mTopActivity = null;
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            mAppActivitys.remove(activity);
        }
    };

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
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);

        Intent intent = new Intent(application, LocalService.class);
        application.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public Activity getTopActivity() {
        if (mTopActivity == null && !mAppActivitys.isEmpty()) {
            return mAppActivitys.get(mAppActivitys.size() - 1);
        } else {
            return mTopActivity;
        }
    }

    @Override
    public List<Activity> getActivitys() {
        return mAppActivitys;
    }

    @Override
    public boolean showGuide() {
        return false;
    }

    @Override
    public void saveGuide() {

    }
}
