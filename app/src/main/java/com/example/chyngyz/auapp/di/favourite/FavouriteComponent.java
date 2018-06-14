package com.example.chyngyz.auapp.di.favourite;

import com.example.chyngyz.auapp.ui.favourite.FavouriteFragment;
import com.example.chyngyz.auapp.ui.main.MainFragment;

import dagger.Subcomponent;

@Subcomponent(modules = FavouriteModule.class)
@FavouriteScope
public interface FavouriteComponent {
    void inject(FavouriteFragment fragment);
}