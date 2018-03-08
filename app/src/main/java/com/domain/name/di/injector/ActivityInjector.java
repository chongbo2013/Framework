package com.domain.name.di.injector;

import com.domain.name.ui.activity.*;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public abstract class ActivityInjector {

    @ContributesAndroidInjector
    abstract WebViewActivity webViewActivityInjector();

    @ContributesAndroidInjector
    abstract LaunchActivity launchActivityInjector();

    @ContributesAndroidInjector
    abstract GuideActivity guideActivityInjector();

    @ContributesAndroidInjector
    abstract MainActivity mainActivityInjector();
}
