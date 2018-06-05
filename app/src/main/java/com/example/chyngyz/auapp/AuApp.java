package com.example.chyngyz.auapp;

import android.app.Application;
import android.content.Context;

import com.example.chyngyz.auapp.data.NetworkBuilder;
import com.example.chyngyz.auapp.data.RetrofitService;

public class AuApp extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initRetrofit();
    }

    public static AuApp get(Context context) {
        return (AuApp) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
