package com.domain.name.app;

import android.app.Activity;
import android.content.Context;

import com.domain.name.data.bean.UserBean;

import java.util.List;

/**
 * Created by Liux on 2016/12/1.
 */

public interface AppControl {

    interface View {

        Context getContext();

        Activity getTopActivity();

        List<Activity> getActivitys();
    }

    interface Presenter {

        boolean isLogin();

        void login(UserBean userBean);

        void logout();

        String getAuthorization();
    }

    interface Model {

        void putUserInfo(UserBean userBean);

        UserBean getUserInfo();
    }
}
