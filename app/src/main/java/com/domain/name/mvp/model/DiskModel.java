package com.domain.name.mvp.model;

import com.liux.framework.base.BaseContract;

/**
 * 2017/12/15
 * By Liux
 * lx0758@qq.com
 */

public interface DiskModel extends BaseContract.Model {

    int getGuide();

    void putGuide(int guide);
}
