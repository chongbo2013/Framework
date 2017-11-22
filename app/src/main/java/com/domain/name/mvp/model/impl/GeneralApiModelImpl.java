package com.domain.name.mvp.model.impl;

import com.domain.name.base.BaseModel;
import com.domain.name.data.api.GeneralApi;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.mvp.model.GeneralApiModel;
import com.liux.http.HttpClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liux on 2017/11/22.
 */

public class GeneralApiModelImpl extends BaseModel implements GeneralApiModel {
    @Override
    public void loadBanner(Observer<ResultBean> observer) {
        HttpClient.getInstance().getRetrofitService(GeneralApi.class).get("")
                .filter(new DataHandle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
