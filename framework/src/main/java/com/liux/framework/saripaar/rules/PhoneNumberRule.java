package com.liux.framework.saripaar.rules;

import com.liux.framework.saripaar.PhoneNumber;
import com.mobsandgeeks.saripaar.AnnotationRule;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/**
 * Created by LuoHaifeng on 2018/2/7 0007.
 * Email:496349136@qq.com
 */

public class PhoneNumberRule extends AnnotationRule<PhoneNumber, String> {
    private static final String REG_MOBILE_PHONE_EXT_VIRTUAL = "^(13[0-9]|14[579]|15[012356789]|17[35678]|18[0-9])[0-9]{8}$";//不包含虚拟号段
    private static final String REG_MOBILE_PHONE = "^(13[0-9]|14[1456789]|15[012356789]|16[6]|17[01345678]|18[0-9]|19[89])[0-9]{8}$";
    private static final String REG_FIX_PHONE = "^0\\d{2,3}-?[1-9]\\d{4,7}$";

    /**
     * Constructor. It is mandatory that all subclasses MUST have a constructor with the same
     * signature.
     *
     * @param phoneNumber The rule {@link Annotation} instance to which
     *                    this rule is paired.
     */
    protected PhoneNumberRule(PhoneNumber phoneNumber) {
        super(phoneNumber);
    }

    @Override
    public boolean isValid(String s) {
        switch (mRuleAnnotation.mode()) {
            case MODE_MOBILE:
                return Pattern.matches(REG_MOBILE_PHONE_EXT_VIRTUAL, s);
            case MODE_MOBILE_INCLUDE_VIRTUAL:
                return Pattern.matches(REG_MOBILE_PHONE, s);
            case MODE_TELEPHONE:
                return Pattern.matches(REG_FIX_PHONE, s);
            case MODE_MOBILE_TELEPHONE:
                return Pattern.matches(REG_MOBILE_PHONE_EXT_VIRTUAL, s) || Pattern.matches(REG_FIX_PHONE, s);
            case MODE_MOBILE_INCLUDE_VIRTUAL_TELEPHONE:
                return Pattern.matches(REG_MOBILE_PHONE, s) || Pattern.matches(REG_FIX_PHONE, s);
            default:
                return false;
        }
    }
}
