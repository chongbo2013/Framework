package org.domain.name.rx.transformer;

import com.liux.rx.error.ErrorTransformer;
import com.liux.rx.lifecycle.BindLifecycle;
import com.liux.rx.transformer.ThreadTransformer;

import org.domain.name.mvp.model.bean.Resp;
import org.domain.name.mvp.model.conf.CODE;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 * 2018/2/28
 * By Liux
 * lx0758@qq.com
 */

public class ApiTransformer {

    public static <T> ObservableTransformer<Resp<T>, T> resp() {
        return upstream -> upstream
                .map((Function<Resp<T>, T>) tResp -> {
                    if (tResp.getStatus() != CODE.API_SUCCEED.code()) {
                        throw new Resp.RespException(tResp);
                    }
                    return tResp.getData();
                });
    }

    public static <T> ObservableTransformer<T, T> api(BindLifecycle bindLifecycle) {
        return upstream -> upstream
                .compose(ThreadTransformer.io_Main())
                .compose(ErrorTransformer.get())
                .compose(bindLifecycle.bindLifeCycle());
    }
}
