package com.domain.name.di.module;

import com.domain.framework.base.BaseActivity;
import com.domain.name.ui.activity.GuideActivity;
import com.domain.name.ui.activity.LaunchActivity;
import com.domain.name.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract LaunchActivity launchActivityInjector();

    @ContributesAndroidInjector
    abstract GuideActivity guideActivityInjector();
}
