package com.example.chyngyz.auapp.data.network;

import com.example.chyngyz.auapp.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {

    private RetrofitService mService = null;

    public RetrofitService initRetrofit(OkHttpClient okHttpClient) {
        if (mService == null) {
            mService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .build()
                    .create(RetrofitService.class);
        }

        return mService;
    }
}
