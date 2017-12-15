package com.domain.name.mvp.model;

import com.domain.name.base.BaseContract;

/**
 * Created by Liux on 2017/12/15.
 */

public interface DiskModel extends BaseContract.Model {

    int getGuide();

    void putGuide(int guide);
}
