package com.domain.name.base;

import com.domain.name.app.AppControl;

import io.reactivex.disposables.Disposable;

/**
 * Created by Liux on 2016/12/1.
 */

public class BaseContract {

    public interface View {

        AppControl.View getAppView();

        AppControl.Presenter getAppPresenter();

        boolean interceptFailure(int code, String msg);

        void showProgressBar(Disposable disposable);

        void hideProgressBar(Disposable disposable);

        void registerDisposable(Disposable disposable);

        void unregisterDisposable(Disposable disposable);

        void onDestroy();
    }

    public interface Presenter {

        View getView();
    }

    public interface Model {

    }
}
