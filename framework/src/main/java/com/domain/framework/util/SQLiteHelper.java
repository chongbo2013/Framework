package com.domain.framework.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Liux on 2017/11/11.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db";

    private static final int DB_VERSION_10000 = 10000;
    private static final int DB_VERSION_20000 = 20000;
    private static final int DB_VERSION_30000 = 30000;

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION_10000);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE 'xxx' (" +
                        // 唯一ID
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        // 更新时间
                        "'update'  INTEGER);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case DB_VERSION_10000:
                // 阶段升级代码
            case DB_VERSION_20000:
                // 阶段升级代码
            case DB_VERSION_30000:
                // 阶段升级代码
                break;
            default:
                break;
        }
    }
}
