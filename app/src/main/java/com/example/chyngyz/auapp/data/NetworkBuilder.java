package com.example.chyngyz.auapp.data;

import com.example.chyngyz.auapp.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {

    private static RetrofitService sService = null;

    public static RetrofitService initRetrofit() {
        if (sService == null) {
            sService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
                    .create(RetrofitService.class);
        }

        return sService;
    }
}
