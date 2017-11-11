package com.domain.name.app;

import android.app.Activity;

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
    }
}
