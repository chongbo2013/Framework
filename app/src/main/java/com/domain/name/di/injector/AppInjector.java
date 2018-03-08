package com.domain.name.di.injector;

import dagger.Module;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Module(includes = {ActivityInjector.class, FragmentInjector.class})
public abstract class AppInjector {

}
