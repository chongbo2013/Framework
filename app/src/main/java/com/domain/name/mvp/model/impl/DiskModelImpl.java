package com.domain.name.mvp.model.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.domain.name.base.BaseModel;
import com.domain.name.mvp.model.DiskModel;

/**
 * Created by Liux on 2017/12/15.
 */

public class DiskModelImpl extends BaseModel implements DiskModel {
    private static final String FILE_XML = "config";

    private static final String KEY_GUIDE = "GUIDE";

    private SharedPreferences mSharedPreferences;

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
