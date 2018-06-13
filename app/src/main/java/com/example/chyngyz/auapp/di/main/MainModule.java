package com.example.chyngyz.auapp.di.main;

import com.example.chyngyz.auapp.data.network.RetrofitService;
import com.example.chyngyz.auapp.data.resource.ResourceManager;
import com.example.chyngyz.auapp.data.storage.RealmManager;
import com.example.chyngyz.auapp.ui.main.MainActivity;
import com.example.chyngyz.auapp.ui.main.MainContract;
import com.example.chyngyz.auapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainContract.Presenter providePresenter(RetrofitService service, RealmManager realmManager, ResourceManager resourceManager) {
        return new MainPresenter(service, realmManager, resourceManager);
    }
}
