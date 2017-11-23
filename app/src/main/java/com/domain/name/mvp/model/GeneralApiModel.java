package com.domain.name.mvp.model;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.base.BaseContract;
import com.domain.name.data.GeneralObserver;
import com.domain.name.data.bean.ResultBean;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;

/**
 * Created by Liux on 2017/11/22.
 */

public interface GeneralApiModel extends BaseContract.Model {

    void loadBanner(Observer<ResultBean> observer);

    void uploadFile(File file, Observer<JSONObject> observer);

    void uploadFile(String path, Observer<JSONObject> observer);

    void uploadFiles(File[] files, Observer<List<JSONObject>> observer);

    void uploadFiles(String[] paths, Observer<List<JSONObject>> observer);

    void submitCompanyInfo(String name, String tel, String linkman, int industry, String card_1, String card_2, String card_3, String license_1, String license_2, Observer<ResultBean> observer);
}
