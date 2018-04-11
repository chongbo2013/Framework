package org.domain.name.mvp.contract;

import com.liux.framework.base.BaseContract;

import org.domain.name.mvp.model.bean.BannerBean;

import java.util.List;

/**
 * 2017/11/11
 * By Liux
 * lx0758@qq.com
 */

public interface HomeContract {

    interface View extends BaseContract.View {

        void loadSucceed(List<BannerBean> bannerBeans);

        void loadFailure(String msg);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadBanner();
    }
}
