package com.domain.name.base;

import com.alibaba.fastjson.JSON;
import com.domain.name.data.bean.ResultBean;
import com.domain.name.data.conf.CODE;

import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Liux on 2017/8/20.
 */

public class BaseModel implements BaseContract.Model {

    /**
     * 检查返回数据
     */
    public static class DataHandle implements Function<ResultBean, ResultBean>, Predicate<ResultBean> {
        @Override
        public ResultBean apply(ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED_200.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean.getStatus(), resultBean.getMessage());
            }
            return resultBean;
        }

        @Override
        public boolean test(ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED_200.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean.getStatus(), resultBean.getMessage());
            }
            return true;
        }
    }

    /**
     * 检查返回数据 JSONArray
     */
    public static class ArrayDataHandle<T> implements Function<ResultBean, List<T>> {
        private Class<T> clazz;

        public ArrayDataHandle(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public List<T> apply(ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED_200.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean.getStatus(), resultBean.getMessage());
            }
            String data = String.valueOf(resultBean.getData());
            if (data == null || !data.matches("^\\[.*\\]$")) {
                throw new ResultBean.ResultException(CODE.ERROR_UNKNOWN.code(), "与服务器通信错误");
            }
            return JSON.parseArray(data, this.clazz);
        }
    }

    /**
     * 检查返回数据 JSONObject
     */
    public static class ObjectDataHandle<T> implements Function<ResultBean, T> {
        private Class<T> clazz;

        public ObjectDataHandle(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T apply(ResultBean resultBean) throws Exception {
            if (CODE.API_SUCCEED_200.code() != resultBean.getStatus()) {
                throw new ResultBean.ResultException(resultBean.getStatus(), resultBean.getMessage());
            }
            String data = String.valueOf(resultBean.getData());
            if (data == null || !data.matches("^\\{.*\\}$")) {
                throw new ResultBean.ResultException(CODE.ERROR_UNKNOWN.code(), "与服务器通信错误");
            }
            return JSON.parseObject(data, this.clazz);
        }
    }
}
