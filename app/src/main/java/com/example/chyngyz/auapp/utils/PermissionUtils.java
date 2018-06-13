package com.example.chyngyz.auapp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public final class PermissionUtils {

    private static String[] sCallPermissions = new String[]{
            Manifest.permission.CALL_PHONE
    };

    private static boolean permissionsAreDenied(Context context, String[] permissions) {
        boolean denied = false;

        for (String permission : permissions) {
            denied = ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;
            if (!denied) break;
        }
        return denied;
    }

    public static boolean checkCallPermission(Activity activity) {
        if (permissionsAreDenied(activity, sCallPermissions)) {
            ActivityCompat.requestPermissions(activity, sCallPermissions, AppConstants.REQUEST_CODE_CALL_PHONE_PERMISSIONS);
            return false;
        }

        return true;
    }
}