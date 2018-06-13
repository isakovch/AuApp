package com.example.chyngyz.auapp.utils;

import android.app.Activity;
import android.os.Handler;

public class GuiThreadUtils {
    private static Handler sGuiThreadHandler = new Handler();

    public static void doInGuiThread(Runnable runnable) {
        sGuiThreadHandler.post(runnable);
    }

    public static void doInAsyncThread(Runnable runnable) {
        if (runnable != null)
            new Thread(runnable)
                    .start();
    }

    private static void doInActivityThreadImmediately(Activity activity, Runnable runnable) {
        if (activity != null && runnable != null)
            activity.runOnUiThread(runnable);
    }

    public static void doInAsyncActivityThread(Activity activity, final Runnable runnable) {
        if (runnable != null)
            new Thread(() -> doInActivityThreadImmediately(activity, runnable)).start();
    }
}
