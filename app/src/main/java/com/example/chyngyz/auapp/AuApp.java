package com.example.chyngyz.auapp;

import android.app.Application;

import com.example.chyngyz.auapp.di.app.AppComponent;
import com.example.chyngyz.auapp.di.app.AppModule;
import com.example.chyngyz.auapp.di.app.DaggerAppComponent;
import com.example.chyngyz.auapp.di.app.DbModule;
import com.example.chyngyz.auapp.di.app.NetworkModule;

public class AuApp extends Application {

    private static AppComponent sComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependency();
    }

    private void initDependency() {
        sComponent = DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .dbModule(new DbModule(this))
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getComponent() {
        return sComponent;
    }
}
