package com.example.chyngyz.auapp.di.app;

import com.example.chyngyz.auapp.di.main.MainComponent;
import com.example.chyngyz.auapp.di.main.MainModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class, DbModule.class})
public interface AppComponent {

    MainComponent include(MainModule module);

}

