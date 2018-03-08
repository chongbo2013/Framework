package org.domain.name.di.injector;

import org.domain.name.ui.fragment.MainCenterFragment;
import org.domain.name.ui.fragment.MainCircleFragment;
import org.domain.name.ui.fragment.MainHomeFragment;
import org.domain.name.ui.fragment.MainServerFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module
public abstract class FragmentInjector {

    @ContributesAndroidInjector
    abstract MainHomeFragment mainHomeFragmentInjector();

    @ContributesAndroidInjector
    abstract MainCircleFragment mainCircleFragmentInjector();

    @ContributesAndroidInjector
    abstract MainServerFragment mainServerFragmentInjector();

    @ContributesAndroidInjector
    abstract MainCenterFragment mainCenterFragmentInjector();

}
