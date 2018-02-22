package com.domain.name.mvp.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.domain.framework.base.BaseModel;
import com.domain.name.mvp.model.DiskModel;

import javax.inject.Inject;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

public class DiskModelImpl extends BaseModel implements DiskModel {
    private static final String FILE_XML = "config";
    private static final String KEY_GUIDE = "GUIDE";

    private SharedPreferences mSharedPreferences;

    @Inject
    public DiskModelImpl(Context context) {
        mSharedPreferences = context.getSharedPreferences(FILE_XML, Context.MODE_PRIVATE);
    }

    @Override
    public int getGuide() {
        return mSharedPreferences.getInt(KEY_GUIDE, 0);
    }

    @Override
    public void putGuide(int guide) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(KEY_GUIDE, guide);
        editor.apply();
    }
}
