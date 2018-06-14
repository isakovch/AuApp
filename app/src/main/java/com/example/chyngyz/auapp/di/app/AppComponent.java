package com.example.chyngyz.auapp.di.app;

import com.example.chyngyz.auapp.di.favourite.FavouriteComponent;
import com.example.chyngyz.auapp.di.favourite.FavouriteModule;
import com.example.chyngyz.auapp.di.main.MainComponent;
import com.example.chyngyz.auapp.di.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, DbModule.class})
public interface AppComponent {

    MainComponent include(MainModule module);

    FavouriteComponent include(FavouriteModule module);

}

