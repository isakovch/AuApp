package com.example.chyngyz.auapp.data.storage;

import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public interface RealmManager {
    void saveVacancyList(RealmList<Vacancy> vacancyList);

    RealmResults<Vacancy> getMainVacancyList();

    void saveVacancy(Vacancy vacancy);

    void deleteVacancy(Vacancy vacancy);

    void deleteAllVacancies();

    void closeRealm();
}
