package org.domain.name.mvp.model.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.liux.framework.base.BaseModel;
import com.liux.http.HttpUtil;

import org.domain.name.mvp.model.GeneralApiModel;
import org.domain.name.mvp.model.api.GeneralApi;
import org.domain.name.mvp.model.bean.Resp;
import org.domain.name.rx.transformer.ApiTransformer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * 2017/11/22
 * By Liux
 * lx0758@qq.com
 */

public class GeneralApiModelImpl extends BaseModel implements GeneralApiModel {

    @Inject
    GeneralApi mGeneralApi;

    @Inject
    GeneralApiModelImpl() {
    }

    @Override
    public Observable<List<JSONObject>> loadBanner() {
        return mGeneralApi.industry("hangye")
                .compose(ApiTransformer.resp())
                .map(string -> new ArrayList<>());
    }

    @Override
    public Observable<JSONObject> uploadFile(String path) {
        return uploadFile(new File(path));
    }

    @Override
    public Observable<JSONObject> uploadFile(File file) {
        MultipartBody.Part part = HttpUtil.parseFilePart("files[]", file, null);
        return mGeneralApi.uploadFile(part)
                .compose(ApiTransformer.resp())
                .map(jsonObjects -> jsonObjects.get(0));
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
    public Observable<List<JSONObject>> uploadFiles(File[] files) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : files) {
            parts.add(HttpUtil.parseFilePart("files[]", file, null));
        }
        return mGeneralApi.uploadFiles(parts)
                .compose(ApiTransformer.resp());
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
                    return mGeneralApi.uploadFile(HttpUtil.parseFilePart("files[]", new File(file), null))
                            .compose(ApiTransformer.resp())
                            .map(jsonObjects -> jsonObjects.get(0).getString("id"))
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
                    return mGeneralApi.uploadFile(HttpUtil.parseFilePart("files[]", new File(file), null))
                            .compose(ApiTransformer.resp())
                            .map(jsonObjects -> jsonObjects.get(0).getString("id"))
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
                    return mGeneralApi.uploadFile(HttpUtil.parseFilePart("files[]", new File(file), null))
                            .compose(ApiTransformer.resp())
                            .map(jsonObjects -> jsonObjects.get(0).getString("id"))
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
                    return mGeneralApi.uploadFile(HttpUtil.parseFilePart("files[]", new File(file), null))
                            .compose(ApiTransformer.resp())
                            .map(jsonObjects -> jsonObjects.get(0).getString("id"))
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
                    return mGeneralApi.uploadFile(HttpUtil.parseFilePart("files[]", new File(file), null))
                            .compose(ApiTransformer.resp())
                            .map(jsonObjects -> jsonObjects.get(0).getString("id"))
                            .map(s -> {
                                stringStringMap.put("team_id", s);
                                return stringStringMap;
                            });
                })
                .switchMap(stringStringMap -> mGeneralApi.submitCompanyInfo(stringStringMap));
    }
}