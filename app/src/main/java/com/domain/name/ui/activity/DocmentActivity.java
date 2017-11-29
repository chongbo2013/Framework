package com.domain.name.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.domain.name.base.BaseActivity;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.Map;

/**
 * Created by Liux on 2017/11/29.
 */

public class DocmentActivity extends BaseActivity {
    private static final String PARAM_PATH = "DocmentActivity_PARAM_FILE";

    private TbsReaderView mTbsReaderView;

    private String mPath;

    public static void startDocmentView(Context context, String path) {
        Intent intent = new Intent(context, DocmentActivity.class);
        intent.putExtra(PARAM_PATH, path);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState, Intent intent) {
        mTbsReaderView = new TbsReaderView(this, new TbsReaderView.ReaderCallback() {
            @Override
            public void onCallBackAction(Integer integer, Object o, Object o1) {

            }
        });
        setContentView(mTbsReaderView);
    }

    @Override
    protected void onInitData(@Nullable Bundle savedInstanceState, Intent intent) {
        mPath = intent.getStringExtra(PARAM_PATH);
    }

    @Override
    protected void onRestoreData(Map<String, Object> data) {
        mPath = (String) data.get("mPath");
    }

    @Override
    protected void onInitView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected void onLazyLoad() {
        File cache = getExternalCacheDir();
        if (!cache.exists()) {
            cache = getCacheDir();
        }
        Bundle localBundle = new Bundle();
        localBundle.putString(TbsReaderView.KEY_FILE_PATH, mPath);
        localBundle.putString(TbsReaderView.KEY_TEMP_PATH, cache.getPath());
        boolean bool = mTbsReaderView.preOpen(getFileSuffix(mPath), false);
        if (bool) {
            mTbsReaderView.openFile(localBundle);
        }
    }

    @Override
    protected void onSaveData(Map<String, Object> data) {
        data.put("mPath", mPath);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }

    private String getFileSuffix(String path) {
        String suffix = "";
        if (TextUtils.isEmpty(path)) {
            return suffix;
        }
        int i = path.lastIndexOf('.');
        if (i <= -1) {
            return suffix;
        }
        suffix = path.substring(i + 1);
        return suffix;
    }
}
