package com.example.chyngyz.auapp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.ui.base.BaseVacancyAdapter;

import java.util.ArrayList;

public class MainAdapter extends BaseVacancyAdapter<MainAdapterCallback> {

    private MainAdapterCallback mCallback;

    MainAdapter(@NonNull Context context,
                       ArrayList<Vacancy> vacancyList,
                       ArrayList<String> favouriteList,
                       ArrayList<String> viewedList,
                       MainAdapterCallback callback) {

        super(context, vacancyList, favouriteList, viewedList, callback);

        this.mCallback = callback;
    }

    @Override
    protected void onCheckBoxClicked(boolean isChecked, Vacancy vacancy) {
        mCallback.checkClicked(isChecked, vacancy);
    }
}
