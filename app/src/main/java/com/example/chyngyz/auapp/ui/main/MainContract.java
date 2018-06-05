package com.example.chyngyz.auapp.ui.main;

import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.ui.IProgressBar;
import com.example.chyngyz.auapp.ui.Lifecycle;

import java.util.ArrayList;

public interface MainContract {

    interface View extends IProgressBar {
        void showAllVacancies(ArrayList<Vacancy> vacancyList);

        void showGetVacanciesError(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void getAllVacancies();
    }
}
