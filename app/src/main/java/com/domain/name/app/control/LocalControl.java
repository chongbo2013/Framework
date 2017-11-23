package com.domain.name.app.control;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.domain.name.app.AppControl;
import com.domain.name.data.bean.UserBean;
import com.liux.util.DeviceUtil;

import java.util.List;
import java.util.Vector;

/**
 * Created by Liux on 2017/11/6.
 */

public class LocalControl implements AppControl.Presenter, AppControl.Model, AppControl.View {
    private Application mApplication;

    public LocalControl(Application application) {
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);

        mSharedPreferences = mApplication.getSharedPreferences(FILE_XML, Context.MODE_PRIVATE);
    }

    /* =========================== View ========================== */

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

    /* ========================== Presenter =========================== */

    @Override
    public boolean showGuide() {
        return !getGuide();
    }

    @Override
    public void saveGuide() {
        putGuide();
    }

    @Override
    public boolean isLogin() {
        UserBean userBean = getUserInfo();
        return userBean != null && !TextUtils.isEmpty(userBean.getToken());
    }

    @Override
    public void login(UserBean userBean) {
        putUserInfo(userBean);
    }

    @Override
    public void logout() {
        putUserInfo(null);
    }

    @Override
    public String getAuthorization() {
        if (!isLogin()) return "";
        return "Bearer " + getUserInfo().getToken();
    }

    /* ========================== Model =========================== */
    private static final String FILE_XML = "config";

    private static final String KEY_GUIDE = "GUIDE";
    private static final String KEY_USER_DATA = "USER_DATA";

    private UserBean mUserBean;
    private SharedPreferences mSharedPreferences;

    @Override
    public void putUserInfo(UserBean userBean) {
        mUserBean = userBean;
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (userBean != null) {
            editor.putString(KEY_USER_DATA, JSON.toJSONString(userBean));
        } else {
            editor.remove(KEY_USER_DATA);
        }
        editor.apply();
    }

    @Override
    public UserBean getUserInfo() {
        if (mUserBean == null) {
            String data = mSharedPreferences.getString(KEY_USER_DATA, null);
            if (!TextUtils.isEmpty(data)) {
                mUserBean = JSON.parseObject(data, UserBean.class);
            }
        }
        return mUserBean;
    }

    @Override
    public void putGuide() {
        int code = DeviceUtil.getVersionCode(mApplication);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(KEY_GUIDE, code);
        editor.apply();
    }

    @Override
    public boolean getGuide() {
        int code = DeviceUtil.getVersionCode(mApplication);
        return code == mSharedPreferences.getInt(KEY_GUIDE, 0);
    }
}
