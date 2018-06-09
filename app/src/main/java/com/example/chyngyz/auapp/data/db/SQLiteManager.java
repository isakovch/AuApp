package com.example.chyngyz.auapp.data.db;

import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;

public interface SQLiteManager {
    void saveVacancyList(ArrayList<Vacancy> vacancyList);

    ArrayList<Vacancy> getMainVacancyList();

    void saveVacancy(Vacancy vacancy);

    void deleteVacancy(Vacancy vacancy);

    void deleteAllVacancies();
}
