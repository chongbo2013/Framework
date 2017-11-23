package com.domain.name.app;

import android.app.Activity;

import com.domain.name.data.bean.UserBean;

import java.util.List;

/**
 * Created by Liux on 2016/12/1.
 */

public interface AppControl {

    interface View {

        Activity getTopActivity();

        List<Activity> getActivitys();
    }

    interface Presenter {

        boolean showGuide();

        void saveGuide();

        boolean isLogin();

        void login(UserBean userBean);

        void logout();

        String getAuthorization();
    }

    interface Model {

        void putUserInfo(UserBean userBean);

        UserBean getUserInfo();

        void putGuide();

        boolean getGuide();
    }
}
