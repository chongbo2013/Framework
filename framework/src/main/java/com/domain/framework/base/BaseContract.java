package com.domain.framework.base;

/**
 * 2016/12/1
 * By Liux
 * lx0758@qq.com
 */

public class BaseContract {

    public interface View {

    }

    public interface Presenter<V extends View> {
        void bindView(V view);
    }

    public interface Model {

    }
}
