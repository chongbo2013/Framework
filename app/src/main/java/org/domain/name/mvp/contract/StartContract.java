package org.domain.name.mvp.contract;

import com.liux.framework.base.BaseContract;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

public class StartContract {

    public interface View extends BaseContract.View {

    }

    public interface Presenter extends BaseContract.Presenter<View> {

        boolean showGuide();

        void saveGuide();
    }
}
