package com.domain.name.mvp.model;

import com.domain.name.base.BaseContract;
import com.domain.name.data.GeneralObserver;
import com.domain.name.data.bean.ResultBean;

import io.reactivex.Observer;

/**
 * Created by Liux on 2017/11/22.
 */

public interface GeneralApiModel extends BaseContract.Model {

    void loadBanner(Observer<ResultBean> observer);
}
