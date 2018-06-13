package com.example.chyngyz.auapp.di.app;

import android.content.Context;

import com.example.chyngyz.auapp.data.storage.RealmBuilder;
import com.example.chyngyz.auapp.data.storage.RealmManager;
import com.example.chyngyz.auapp.data.storage.RealmService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
@Singleton
public class DbModule {

    private final Realm mRealm;

    public DbModule(Context context) {
        this.mRealm = new RealmBuilder(context).getInstance();
    }

    @Singleton
    @Provides
    RealmManager provideRealmManager() {
        return new RealmService(mRealm);
    }
}