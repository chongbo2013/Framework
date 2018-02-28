package com.liux.framework.saripaar;

import com.liux.framework.saripaar.rules.PhoneNumberRule;
import com.mobsandgeeks.saripaar.annotation.ValidateUsing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LuoHaifeng on 2018/2/7 0007.
 * Email:496349136@qq.com
 */
@ValidateUsing(PhoneNumberRule.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PhoneNumber {
    Mode mode() default Mode.MODE_MOBILE;

    int sequence()      default -1;
    int messageResId()  default -1;
    String message()    default "无效的手机号码";
    enum Mode{
        MODE_MOBILE,
        MODE_MOBILE_INCLUDE_VIRTUAL,
        MODE_TELEPHONE,
        MODE_MOBILE_TELEPHONE,
        MODE_MOBILE_INCLUDE_VIRTUAL_TELEPHONE
    }
}
