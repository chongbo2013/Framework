package com.domain.name.data.conf;

/**
 * Created by Liux on 2017/11/11.
 */

public enum CODE {

    API_SUCCEED(200, "访问成功"),
    API_ERROR (0, "内部错误"),

    API_NETWORK(400, "网络错误"),;

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
