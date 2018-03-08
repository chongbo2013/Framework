package org.domain.name.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 2017/11/23
 * By Liux
 * lx0758@qq.com
 */

public class UserBean implements Parcelable {
    // 鉴权证书
    private String token;
    // 用户名
    private String username;

    public UserBean() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.username);
    }

    protected UserBean(Parcel in) {
        this.token = in.readString();
        this.username = in.readString();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
