package com.example.chyngyz.auapp.di.favourite;

import com.example.chyngyz.auapp.data.network.RetrofitService;
import com.example.chyngyz.auapp.data.resource.ResourceManager;
import com.example.chyngyz.auapp.data.storage.RealmManager;
import com.example.chyngyz.auapp.ui.favourite.FavouriteContract;
import com.example.chyngyz.auapp.ui.favourite.FavouritePresenter;
import com.example.chyngyz.auapp.ui.main.MainContract;
import com.example.chyngyz.auapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteModule {

    @Provides
    FavouriteContract.Presenter providePresenter(RetrofitService service, RealmManager realmManager) {
        return new FavouritePresenter(service, realmManager);
    }
}
