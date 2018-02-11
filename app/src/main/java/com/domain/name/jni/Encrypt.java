package com.domain.name.jni;

/**
 * 2017/11/12
 * By Liux
 * lx0758@qq.com
 */

public class Encrypt {

    public native String encode(String string);

    public native String decode(String string);
}
