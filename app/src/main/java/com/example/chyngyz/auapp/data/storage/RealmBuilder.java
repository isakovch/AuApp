package com.example.chyngyz.auapp.data.storage;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmBuilder {

    public RealmBuilder(Context context) {
        Realm.init(context);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("com.auapp")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    public Realm getInstance() {
        return Realm.getDefaultInstance();
    }

}
