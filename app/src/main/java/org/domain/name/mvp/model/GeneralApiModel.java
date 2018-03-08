package org.domain.name.mvp.model;

import com.alibaba.fastjson.JSONObject;

import org.domain.name.mvp.model.bean.Resp;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * 2017/11/22
 * By Liux
 * lx0758@qq.com
 */

public interface GeneralApiModel {

    Observable<List<JSONObject>> loadBanner();

    Observable<JSONObject> uploadFile(File file);

    Observable<JSONObject> uploadFile(String path);

    Observable<List<JSONObject>> uploadFiles(File[] files);

    Observable<List<JSONObject>> uploadFiles(String[] paths);

    Observable<Resp> submitCompanyInfo(String name, String tel, String linkman, int industry, String card_1, String card_2, String card_3, String license_1, String license_2);
}
