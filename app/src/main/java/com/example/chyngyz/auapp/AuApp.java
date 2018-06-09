package com.example.chyngyz.auapp;

import android.app.Application;
import android.content.Context;

import com.example.chyngyz.auapp.data.NetworkBuilder;
import com.example.chyngyz.auapp.data.RetrofitService;
import com.example.chyngyz.auapp.data.db.SQLiteHelper;
import com.example.chyngyz.auapp.data.db.SQLiteManager;

public class AuApp extends Application {

    private RetrofitService mService;
    private SQLiteManager mSQLiteManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initRetrofit();

        mSQLiteManager = new SQLiteHelper(this);
    }

    public static AuApp get(Context context) {
        return (AuApp) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }

    public SQLiteManager getSQLiteManager() {
        return mSQLiteManager;
    }
}
