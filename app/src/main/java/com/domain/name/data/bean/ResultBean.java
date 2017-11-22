package com.domain.name.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * API 返回状态数据封装
 * Created by Liux on 2016/12/19.
 */
public class ResultBean implements Parcelable {
    // 结果
    private int status;
    // 信息
    private String message;
    // 实际数据
    private Object data;

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

    public Object getData() {
        return data;
    }

    public ResultBean setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
        dest.writeString(this.data.toString());
    }

    protected ResultBean(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
        this.data = in.readString();
    }

    public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel source) {
            return new ResultBean(source);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };

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
