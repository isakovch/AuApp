package com.example.chyngyz.auapp.di.main;

import com.example.chyngyz.auapp.ui.main.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MainModule.class)
@MainScope
public interface MainComponent {
    void inject(MainActivity activity);
}