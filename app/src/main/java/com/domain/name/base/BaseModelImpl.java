package com.domain.name.base;


import com.domain.name.data.bean.ResultBean;
import com.domain.name.data.conf.CODE;

import java.io.IOException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import retrofit2.HttpException;

/**
 * Created by Liux on 2017/8/20.
 */

public class BaseModelImpl implements BaseContract.Model {

    /**
     * 处理API调用出错时返回统一的提示
     */
    public static class ErrorHandle implements Function<Throwable, ResultBean> {
        @Override
        public ResultBean apply(@NonNull Throwable throwable) throws Exception {
            // OKHttp网络异常
            if (throwable instanceof HttpException || throwable instanceof IOException) {
                return new ResultBean()
                        .setStatus(CODE.API_NETWORK.code())
                        .setMsg(CODE.API_NETWORK.info());
            }

            // 未知错误
            return new ResultBean()
                    .setStatus(CODE.API_ERROR.code())
                    .setMsg(throwable.getMessage());
        }
    }

    /**
     * 检查返回数据
     * 在SubscriberEx<T>.onError(Throwable e)中处理
     */
    public static class DataHandle implements Predicate<ResultBean> {
        @Override
        public boolean test(@NonNull ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean);
            }
            return true;
        }
    }

    /**
     * 检查返回数据 JSONArray
     * 在SubscriberEx<T>.onError(Throwable e)中处理
     */
    public static class ArrayDataHandle extends DataHandle {
        @Override
        public boolean test(@NonNull ResultBean resultBean) throws Exception {
            super.test(resultBean);
            if (resultBean.getData() == null || !resultBean.getData().toString().matches("^\\[.*\\]$")) {
                throw new ResultBean.ResultException(resultBean, "服务器通信错误");
            }
            return true;
        }
    }

    /**
     * 检查返回数据 JSONObject
     * 在SubscriberEx<T>.onError(Throwable e)中处理
     */
    public static class ObjectDataHandle extends DataHandle {
        @Override
        public boolean test(@NonNull ResultBean resultBean) throws Exception {
            super.test(resultBean);
            if (resultBean.getData() == null || !resultBean.getData().toString().matches("^\\{.*\\}$")) {
                throw new ResultBean.ResultException(resultBean, "服务器通信错误");
            }
            return true;
        }
    }
}
