package com.domain.name.data.bean;

/**
 * API 返回状态数据封装
 * Created by Liux on 2016/12/19.
 */
public class ResultBean<T> {
    // 结果
    private int status;
    // 信息
    private String message;
    // 实际数据
    private T data;

    public ResultBean() {}

    public int getStatus() {
        return status;
    }

    public ResultBean setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultBean setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultBean setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * API 正常报错包装类
     */
    public static class ResultException extends RuntimeException {
        private int status;

        public ResultException(int status, String msg) {
            super(msg);
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public ResultException setStatus(int status) {
            this.status = status;
            return this;
        }
    }
}
