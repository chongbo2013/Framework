package com.domain.name.mvp.contract;

import com.alibaba.fastjson.JSONObject;
import com.domain.name.base.BaseContract;

import java.util.List;

/**
 * Created by Liux on 2017/11/11.
 */

public interface HomeContract {

    interface View extends BaseContract.View {

        void loadSucceed(List<JSONObject> jsonObjects);

        void loadFailure(String msg);
    }

    interface Presenter extends BaseContract.Presenter {

        void loadBanner();
    }
}
