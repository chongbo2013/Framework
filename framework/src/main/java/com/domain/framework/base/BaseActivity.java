package com.domain.framework.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.domain.framework.app.UIProvider;
import com.liux.abstracts.AbstractsActivity;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 2018/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseActivity<P extends BaseContract.Presenter> extends AbstractsActivity
        implements BaseContract.View, Validator.ValidationListener, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    protected P mPresenter;
    @Inject
    protected UIProvider mUiProvider;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> mFrameworkFragmentInjector;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> mSupportFragmentInjector;

    protected Validator mValidator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    @Override
    protected void onInitViewFinish() {
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
        ButterKnife.bind(this);
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return mFrameworkFragmentInjector;
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return mSupportFragmentInjector;
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        if (!errors.isEmpty()) {
            ValidationError error = errors.get(0);
            List<Rule> rules = error.getFailedRules();
            if (rules != null && !rules.isEmpty()) {
                String message = rules.get(0).getMessage(this);
                View view = error.getView();
                boolean isNeedToast = true;
                if (view instanceof TextView) {
                    ((TextView) view).setError(message);
                    if (view instanceof EditText && view.isEnabled()) {
                        view.requestFocus();
                        isNeedToast = false;
                    }
                }

                if(isNeedToast) {
                    mUiProvider.provideIToast().showError(message);
                }
            }
        }
    }
}
