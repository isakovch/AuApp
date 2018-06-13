package com.example.chyngyz.auapp.ui.main;

import android.content.Context;

import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.ui.IProgressBar;
import com.example.chyngyz.auapp.ui.Lifecycle;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public interface MainContract {

    interface View extends IProgressBar {
        void showAllVacancies(List<Vacancy> vacancyList);

        void showGetVacanciesError(String msg);

        void showActionMessage(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void getAllVacancies(Context context);

        void saveOrDeleteVacancy(boolean isChecked, Vacancy vacancy);
    }
}
