package com.domain.name.mvp.contract;

import com.domain.name.base.BaseContract;

/**
 * Created by Liux on 2017/12/15.
 */

public class StartContract {

    public interface View extends BaseContract.View {

    }

    public interface Presenter extends BaseContract.Presenter {

        boolean showGuide();

        void saveGuide();
    }
}
