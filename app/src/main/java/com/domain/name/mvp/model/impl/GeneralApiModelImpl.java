package com.domain.name.mvp.model.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.base.BaseModel;
import com.domain.name.data.api.GeneralApi;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.mvp.model.GeneralApiModel;
import com.liux.http.HttpClient;
import com.liux.http.HttpUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * Created by Liux on 2017/11/22.
 */

public class GeneralApiModelImpl extends BaseModel implements GeneralApiModel {
    @Override
    public void loadBanner(Observer<List<JSONObject>> observer) {
        HttpClient.getInstance().getService(GeneralApi.class).industry("hangye")
                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void uploadFile(File file, Observer<JSONObject> observer) {
        MultipartBody.Part part = HttpUtil.parsePart("files[]", file);
        HttpClient.getInstance().getService(GeneralApi.class).uploadFile(part)
                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                .map(new Function<List<JSONObject>, JSONObject>() {
                    @Override
                    public JSONObject apply(List<JSONObject> jsonObjects) throws Exception {
                        return jsonObjects.get(0);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void uploadFile(String path, Observer<JSONObject> observer) {
        uploadFile(new File(path), observer);
    }

    @Override
    public void uploadFiles(File[] files, Observer<List<JSONObject>> observer) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : files) {
            parts.add(HttpUtil.parsePart("files[]", file));
        }
        HttpClient.getInstance().getService(GeneralApi.class).uploadFiles(parts)
                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void uploadFiles(String[] paths, Observer<List<JSONObject>> observer) {
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        uploadFiles(files, observer);
    }

    @Override
    public void submitCompanyInfo(String name, final String tel, String linkman, int industry, final String card_1, final String card_2, final String card_3, final String license_1, final String license_2, Observer<ResultBean> observer) {
        Map<String, String> data = new HashMap<>();
        data.put("company", name);
        data.put("realname", linkman);
        data.put("telphone", tel);
        data.put("category_id", String.valueOf(industry));
        Observable.just(data)
                .switchMap(new Function<Map<String, String>, ObservableSource<Map<String, String>>>() {
                    @Override
                    public ObservableSource<Map<String, String>> apply(final Map<String, String> stringStringMap) throws Exception {
                        String file = card_1;
                        if (TextUtils.isEmpty(file)) {
                            return Observable.just(stringStringMap);
                        }
                        return HttpClient.getInstance().getService(GeneralApi.class).uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                                .map(new Function<List<JSONObject>, String>() {
                                    @Override
                                    public String apply(List<JSONObject> jsonObjects) throws Exception {
                                        return jsonObjects.get(0).getString("id");
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
                        return HttpClient.getInstance().getService(GeneralApi.class).uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                                .map(new Function<List<JSONObject>, String>() {
                                    @Override
                                    public String apply(List<JSONObject> jsonObjects) throws Exception {
                                        return jsonObjects.get(0).getString("id");
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
                        return HttpClient.getInstance().getService(GeneralApi.class).uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                                .map(new Function<List<JSONObject>, String>() {
                                    @Override
                                    public String apply(List<JSONObject> jsonObjects) throws Exception {
                                        return jsonObjects.get(0).getString("id");
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
                        return HttpClient.getInstance().getService(GeneralApi.class).uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                                .map(new Function<List<JSONObject>, String>() {
                                    @Override
                                    public String apply(List<JSONObject> jsonObjects) throws Exception {
                                        return jsonObjects.get(0).getString("id");
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
                        return HttpClient.getInstance().getService(GeneralApi.class).uploadFile(HttpUtil.parsePart("files[]", new File(file)))
                                .map(new ArrayDataHandle<JSONObject>(JSONObject.class))
                                .map(new Function<List<JSONObject>, String>() {
                                    @Override
                                    public String apply(List<JSONObject> jsonObjects) throws Exception {
                                        return jsonObjects.get(0).getString("id");
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
                .switchMap(new Function<Map<String, String>, ObservableSource<ResultBean>>() {
                    @Override
                    public ObservableSource<ResultBean> apply(final Map<String, String> stringStringMap) throws Exception {
                        return HttpClient.getInstance().getService(GeneralApi.class).submitCompanyInfo(stringStringMap);
                    }
                })
                .map(new DataHandle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
