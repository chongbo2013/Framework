package com.domain.name.data.conf;

/**
 * Created by Liux on 2017/11/11.
 */

public enum CODE {

    ERROR_UNKNOWN(-1, "内部错误"),
    ERROR_NETWORK(-2, "网络错误"),
    ERROR_NETWORK_NONE(-3, "没有网络连接"),

    API_SUCCEED(1, "访问成功"),;

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
