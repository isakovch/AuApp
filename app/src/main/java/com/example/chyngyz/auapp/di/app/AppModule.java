package com.example.chyngyz.auapp.di.app;

import android.content.Context;

import com.example.chyngyz.auapp.data.resource.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {

    private final Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    ResourceManager provideResourceManager(Context context) {
        return new ResourceManager(context);
    }
}
