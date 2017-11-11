package com.domain.name.app.control;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.domain.name.app.AppControl;

import java.util.List;
import java.util.Vector;

/**
 * Created by Liux on 2017/11/6.
 */

public class LocalControl implements AppControl.Presenter, AppControl.View {
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

    public LocalControl(Application application) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
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
