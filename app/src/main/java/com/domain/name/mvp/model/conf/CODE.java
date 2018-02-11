package com.domain.name.mvp.model.conf;

public enum CODE {

    ERROR_UNKNOWN(-1, "内部错误"),
    ERROR_NETWORK(-2, "网络错误"),
    ERROR_NETWORK_NONE(-3, "没有网络连接"),

    API_SUCCEED(0, "OK"),

    API_ERROR(400, "Bad Request"),

    ;

    /**
     * 2017/11/11
     * By Liux
     * lx0758@qq.com
     */
    private int code;
    private String info;
    CODE(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int code() {
        return this.code;
    }

    public String info() {
        return this.info;
    }
}
