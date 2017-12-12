package com.domain.name.ui.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.domain.name.R;

/**
 * Created by Liux on 2017/11/11.
 */

public class GeneralPopup extends PopupWindow {

    public GeneralPopup(Context context) {
        super(context);

        View rootView = LayoutInflater.from(context).inflate(
                R.layout.popup_general,
                null,
                false
        );

        setContentView(rootView);
        setTouchable(false);
        setFocusable(false);
        setOutsideTouchable(false);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
