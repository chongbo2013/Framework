package com.domain.name.rx.transformer;

import com.domain.name.mvp.model.bean.Resp;
import com.domain.name.mvp.model.conf.CODE;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 2018/2/28
 * By Liux
 * lx0758@qq.com
 */

public class ApiTransformer {

    public static <T> ObservableTransformer<Resp<T>, T> create() {
        return upstream -> upstream
                .map((Function<Resp<T>, T>) tResp -> {
                    if (tResp.getStatus() != CODE.API_SUCCEED.code()) {
                        throw new Resp.RespException(tResp);
                    }
                    return tResp.getData();
                });
    }
}
