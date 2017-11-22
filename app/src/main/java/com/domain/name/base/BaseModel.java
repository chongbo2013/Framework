package com.domain.name.base;

import com.domain.name.data.bean.ResultBean;
import com.domain.name.data.conf.CODE;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by Liux on 2017/8/20.
 */

public class BaseModel implements BaseContract.Model {

    /**
     * 检查返回数据
     * 在SubscriberEx<T>.onError(Throwable e)中处理
     */
    public static class DataHandle implements Predicate<ResultBean> {
        @Override
        public boolean test(@NonNull ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean.getStatus(), resultBean.getMessage());
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
                throw new ResultBean.ResultException(CODE.ERROR_UNKNOWN.code(), "与服务器通信错误");
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
                throw new ResultBean.ResultException(CODE.ERROR_UNKNOWN.code(), "与服务器通信错误");
            }
            return true;
        }
    }
}
