package com.domain.framework.base;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.domain.framework.app.UIProvider;
import com.liux.abstracts.AbstractsFragment;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 2017/11/6
 * By Liux
 * lx0758@qq.com
 */

public abstract class BaseFragment<P extends BaseContract.Presenter> extends AbstractsFragment
        implements BaseContract.View, Validator.ValidationListener, HasSupportFragmentInjector {

    @Inject
    protected P mPresenter;
    @Inject
    protected UIProvider mUiProvider;
    @Inject
    DispatchingAndroidInjector<android.support.v4.app.Fragment> mChildFragmentInjector;

    protected Validator mValidator;

    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        AndroidSupportInjection.inject(this);

        mValidator = new Validator(context);
        mValidator.setValidationListener(this);
    }

    @Override
    protected void onInitViewFinish(View view) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public AndroidInjector<android.support.v4.app.Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
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
                String message = rules.get(0).getMessage(getContext());
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
