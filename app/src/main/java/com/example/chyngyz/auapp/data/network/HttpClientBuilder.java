package com.example.chyngyz.auapp.data.network;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public final class HttpClientBuilder {

    private static final String TAG = "AuApplication";
    private static final long TIMEOUT_INTERVAL = 30;

    public OkHttpClient initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, "OKHTTP => " + message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(chain -> {
                    Request.Builder ongoing = chain.request()
                            .newBuilder();
//                            .addHeader("authorization", "Bearer " + prefsManager.getToken());

                    return chain.proceed(ongoing.build());
                })
                .addInterceptor(interceptor)
                .writeTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_INTERVAL, TimeUnit.SECONDS)
                .build();
    }

}
