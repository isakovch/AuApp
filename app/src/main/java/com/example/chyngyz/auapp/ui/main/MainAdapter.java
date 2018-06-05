package com.example.chyngyz.auapp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.ui.base.BaseVacancyAdapter;
import com.example.chyngyz.auapp.ui.base.BaseVacancyCallback;

import java.util.ArrayList;

public class MainAdapter extends BaseVacancyAdapter {

    public MainAdapter(@NonNull Context context, ArrayList<Vacancy> vacancyList, ArrayList<String> favouriteList, ArrayList<String> viewedList) {
        super(context, vacancyList, favouriteList, viewedList);
    }

    @Override
    protected void onCheckBoxClicked(boolean isChecked, String vacancyId) {
        /*TODO Save vacancies in main table */
        if (isChecked) {
             // save vacancy
        } else {
            // delete vacancy
        }
    }
}
