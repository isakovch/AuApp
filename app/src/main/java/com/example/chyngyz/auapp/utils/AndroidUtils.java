package com.example.chyngyz.auapp.utils;

import android.content.Context;
import android.widget.Toast;

public final class AndroidUtils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
