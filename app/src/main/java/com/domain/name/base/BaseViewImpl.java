package com.domain.name.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 实现等待弹窗和 RxJava 生命周期绑定
 * Created by Liux on 2017/9/13.
 */

public class BaseViewImpl implements BaseContract.View {

    private Context mContext;
    private List<Disposable> mDisposables;
    private ProgressDialog mProgressDialog;
    private CompositeDisposable mCompositeDisposable;

    private DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Iterator<Disposable> iterator = mDisposables.iterator();
            while (iterator.hasNext()) {
                Disposable disposable = iterator.next();
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
                iterator.remove();
            }
        }
    };

    public BaseViewImpl(Context context) {
        mContext = context;
        mDisposables = new ArrayList<>();
        mProgressDialog = new ProgressDialog(mContext);
        mCompositeDisposable = new CompositeDisposable();

        mProgressDialog.setMessage("加载中...");
        mProgressDialog.setOnDismissListener(mOnDismissListener);
    }

    @Override
    public void showProgressBar(Disposable disposable) {
        if (disposable != null) {
            mDisposables.add(disposable);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgressBar(Disposable disposable) {
        if (disposable != null) {
            mDisposables.remove(disposable);
        }
        if (mProgressDialog.isShowing() && mDisposables.isEmpty()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void registerDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unregisterDisposable(Disposable disposable) {
        mCompositeDisposable.delete(disposable);
    }

    @Override
    public void onDestroy() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
        if (mProgressDialog != null) {
            mProgressDialog.hide();
            mProgressDialog = null;
        }
        if (mDisposables != null) {
            Iterator<Disposable> iterator = mDisposables.iterator();
            while (iterator.hasNext()) {
                Disposable disposable = iterator.next();
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
                iterator.remove();
            }
            mDisposables = null;
        }
        mContext = null;
    }
}
