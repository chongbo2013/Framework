package com.domain.framework.di.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 2018/2/11
 * By Liux
 * lx0758@qq.com
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Target {
    Class value() default Object.class;
}
