package org.domain.name.mvp.model.bean;

/**
 * 2018/4/11
 * By Liux
 * lx0758@qq.com
 */
public class BannerBean {

    private String title;
    private String url;

    public BannerBean() {
    }

    public BannerBean(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
