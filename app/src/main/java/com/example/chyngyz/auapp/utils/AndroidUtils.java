package com.example.chyngyz.auapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public final class AndroidUtils {

    public static void showShortSnackBar(Activity activity, String msg) {
        showSnackBar(activity, msg, Snackbar.LENGTH_SHORT);
    }

    public static void showLongSnackBar(Activity activity, String msg) {
        showSnackBar(activity, msg, Snackbar.LENGTH_LONG);
    }

    private static void showSnackBar(Activity activity, String message, int length) {
        Snackbar.make(activity.findViewById(android.R.id.content), message, length).show();
    }

    public static void showShortToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showLongToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    private static void showToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }


}
