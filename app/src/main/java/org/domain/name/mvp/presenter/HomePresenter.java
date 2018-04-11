package org.domain.name.mvp.presenter;

import com.liux.framework.base.BasePresenter;

import org.domain.name.mvp.contract.HomeContract;
import org.domain.name.mvp.model.ApiModel;
import org.domain.name.mvp.model.bean.BannerBean;
import org.domain.name.rx.observer.ApiObserver;
import org.domain.name.rx.transformer.ApiTransformer;

import java.util.List;

import javax.inject.Inject;

/**
 * 2017/11/11
 * By Liux
 * lx0758@qq.com
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    ApiModel mApiModel;

    @Inject
    HomePresenter() {
    }

    @Override
    public void loadBanner() {
        mApiModel.loadBanner()
                .compose(ApiTransformer.api(mView))
                .subscribe(new ApiObserver<List<BannerBean>>() {
                    @Override
                    public void onSucceed(List<BannerBean> bannerBeans) {
                        mView.loadSucceed(bannerBeans);
                    }

                    @Override
                    public void onFailure(Throwable e, String msg) {
                        mView.loadFailure(msg);
                    }
                });
    }
}
