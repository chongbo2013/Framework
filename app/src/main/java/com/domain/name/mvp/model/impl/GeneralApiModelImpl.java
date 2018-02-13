package com.domain.name.mvp.model.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.domain.framework.base.BaseModel;
import com.domain.name.mvp.model.GeneralApiModel;
import com.domain.name.mvp.model.api.GeneralApi;
import com.domain.name.mvp.model.bean.Resp;
import com.liux.http.HttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.MultipartBody;

/**
 * 2017/11/22
 * By Liux
 * lx0758@qq.com
 */

public class GeneralApiModelImpl extends BaseModel implements GeneralApiModel {
    
    @Inject
    GeneralApi mGeneralApi;

    @Override
    public Observable<List<JSONObject>> loadBanner() {
        return mGeneralApi.industry("hangye")
                .map(stringResp -> new ArrayList<>());
    }

    @Override
    public Observable<JSONObject> uploadFile(File file) {
        MultipartBody.Part part = HttpUtil.parsePart("files[]", file);
        return mGeneralApi.uploadFile(part)
                .map(listResp -> listResp.getData().get(0));
    }

    @Override
    public Observable<JSONObject> uploadFile(String path) {
        return uploadFile(new File(path));
    }

    @Override
    public Observable<List<JSONObject>> uploadFiles(File[] files) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : files) {
            parts.add(HttpUtil.parsePart("files[]", file));
        }
        return mGeneralApi.uploadFiles(parts)
                .map(Resp::getData);
    }

    @Override
    public Observable<List<JSONObject>> uploadFiles(String[] paths) {
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        return uploadFiles(files);
    }

    @Override
    public Observable<Resp> submitCompanyInfo(String name, final String tel, String linkman, int industry, final String card_1, final String card_2, final String card_3, final String license_1, final String license_2) {
        Map<String, String> data = new HashMap<>();
        data.put("company", name);
        data.put("realname", linkman);
        data.put("telphone", tel);
        data.put("category_id", String.valueOf(industry));
        return Observable.just(data)
                .switchMap(stringStringMap -> {
                    String file = card_1;
                    if (TextUtils.isEmpty(file)) {
                        return Observable.just(stringStringMap);
                    }
                    return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                            .map(listResp -> listResp.getData().get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("card_front_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> {
                    String file = card_2;
                    if (TextUtils.isEmpty(file)) {
                        return Observable.just(stringStringMap);
                    }
                    return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                            .map(listResp -> listResp.getData().get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("card_back_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> {
                    String file = card_3;
                    if (TextUtils.isEmpty(file)) {
                        return Observable.just(stringStringMap);
                    }
                    return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                            .map(listResp -> listResp.getData().get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("picture_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> {
                    String file = license_1;
                    if (TextUtils.isEmpty(file)) {
                        return Observable.just(stringStringMap);
                    }
                    return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                            .map(listResp -> listResp.getData().get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("qualification_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> {
                    String file = license_2;
                    if (TextUtils.isEmpty(file)) {
                        return Observable.just(stringStringMap);
                    }
                    return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                            .map(listResp -> listResp.getData().get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("team_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> mGeneralApi.submitCompanyInfo(stringStringMap));
    }
}
