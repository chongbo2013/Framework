package com.domain.name.mvp.model.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * API 返回状态数据封装
 * 2016/12/19
 * By Liux
 * lx0758@qq.com
 */

public class Resp<T> {
    // 结果
    @JSONField(name = "code")
    private int status;
    // 信息
    @JSONField(name = "msg")
    private String message;
    // 实际数据
    @JSONField(name = "data")
    private T data;

    public Resp() {}

    public int getStatus() {
        return status;
    }

    public Resp<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Resp<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Resp<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    /**
     * API 业务异常
     */
    public static class RespException extends Exception {
        private Resp mResp;

        public RespException(Resp resp) {
            super(resp.getMessage());
            mResp = resp;
        }

        public Resp getResponse() {
            return mResp;
        }

        public void setResp(Resp resp) {
            mResp = resp;
        }
    }
}
