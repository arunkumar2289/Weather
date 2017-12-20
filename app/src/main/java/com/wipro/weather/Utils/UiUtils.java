package com.wipro.weather.Utils;

import android.support.design.widget.Snackbar;
import android.view.View;

public class UiUtils {

    public static void showSnackbar(View view, String message, int length) {
        Snackbar.make(view, message, length).setAction("Action", null).show();
    }


}
