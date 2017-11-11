package com.domain.name.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Liux on 2017/11/11.
 */

public class HandlerService extends IntentService {

    public HandlerService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
