package com.example.chyngyz.auapp.di.app;

import android.preference.PreferenceManager;

import com.example.chyngyz.auapp.data.network.HttpClientBuilder;
import com.example.chyngyz.auapp.data.network.NetworkBuilder;
import com.example.chyngyz.auapp.data.network.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
@Singleton
public class NetworkModule {

    @Singleton
    @Provides
    RetrofitService provideRetrofitApiService(OkHttpClient okHttpClient) {
        return new NetworkBuilder().initRetrofit(okHttpClient);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new HttpClientBuilder().initOkHttpClient();
    }
}
