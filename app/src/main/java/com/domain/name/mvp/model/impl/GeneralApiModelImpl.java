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
                .map(new Function<Resp<String>, List<JSONObject>>() {
                    @Override
                    public List<JSONObject> apply(Resp<String> stringResp) throws Exception {
                        return new ArrayList<>();
                    }
                });
    }

    @Override
    public Observable<JSONObject> uploadFile(File file) {
        MultipartBody.Part part = HttpUtil.parsePart("files[]", file);
        return mGeneralApi.uploadFile(part)
                .map(new Function<Resp<List<JSONObject>>, JSONObject>() {
                    @Override
                    public JSONObject apply(Resp<List<JSONObject>> listResp) throws Exception {
                        return listResp.getData().get(0);
                    }
                });
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
                .map(new Function<Resp<List<JSONObject>>, List<JSONObject>>() {
                    @Override
                    public List<JSONObject> apply(Resp<List<JSONObject>> listResp) throws Exception {
                        return listResp.getData();
                    }
                });
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
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = card_1;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new Function<Resp<List<JSONObject>>, String>() {
                                    @Override
                                    public String apply(Resp<List<JSONObject>> listResp) throws Exception {
                                        return listResp.getData().get(0).getString("id");
                                    }
                                })
                                .map(new Function<String, Map<String, String>>() {
                                    @Override
                                    public Map<String, String> apply(String s) throws Exception {
                                        stringStringMap.put("card_front_id", s);
                                        return stringStringMap;
                                    }
                                });
                    }
                })
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = card_2;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new Function<Resp<List<JSONObject>>, String>() {
                                    @Override
                                    public String apply(Resp<List<JSONObject>> listResp) throws Exception {
                                        return listResp.getData().get(0).getString("id");
                                    }
                                })
                                .map(new Function<String, Map<String, String>>() {
                                    @Override
                                    public Map<String, String> apply(String s) throws Exception {
                                        stringStringMap.put("card_back_id", s);
                                        return stringStringMap;
                                    }
                                });
                    }
                })
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = card_3;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new Function<Resp<List<JSONObject>>, String>() {
                                    @Override
                                    public String apply(Resp<List<JSONObject>> listResp) throws Exception {
                                        return listResp.getData().get(0).getString("id");
                                    }
                                })
                                .map(new Function<String, Map<String, String>>() {
                                    @Override
                                    public Map<String, String> apply(String s) throws Exception {
                                        stringStringMap.put("picture_id", s);
                                        return stringStringMap;
                                    }
                                });
                    }
                })
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = license_1;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new Function<Resp<List<JSONObject>>, String>() {
                                    @Override
                                    public String apply(Resp<List<JSONObject>> listResp) throws Exception {
                                        return listResp.getData().get(0).getString("id");
                                    }
                                })
                                .map(new Function<String, Map<String, String>>() {
                                    @Override
                                    public Map<String, String> apply(String s) throws Exception {
                                        stringStringMap.put("qualification_id", s);
                                        return stringStringMap;
                                    }
                                });
                    }
                })
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = license_2;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return mGeneralApi.uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new Function<Resp<List<JSONObject>>, String>() {
                                    @Override
                                    public String apply(Resp<List<JSONObject>> listResp) throws Exception {
                                        return listResp.getData().get(0).getString("id");
                                    }
                                })
                                .map(new Function<String, Map<String, String>>() {
                                    @Override
                                    public Map<String, String> apply(String s) throws Exception {
                                        stringStringMap.put("team_id", s);
                                        return stringStringMap;
                                    }
                                });
                    }
                })
                .switchMap(new Function<Map<String, String>, ObservableSource<Resp>>() {
                    @Override
                    public ObservableSource<Resp> apply(final Map<String, String> stringStringMap) throws Exception {
                        return mGeneralApi.submitCompanyInfo(stringStringMap);
                    }
                });
    }
}
