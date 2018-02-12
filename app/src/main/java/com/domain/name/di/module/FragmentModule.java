package com.domain.name.di.module;

import com.domain.name.ui.fragment.*;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MainHomeFragment mainHomeFragmentInjector();

    @ContributesAndroidInjector
    abstract MainCircleFragment mainCircleFragmentInjector();

    @ContributesAndroidInjector
    abstract MainServerFragment mainServerFragmentInjector();

    @ContributesAndroidInjector
    abstract MainCenterFragment mainCenterFragmentInjector();

}
