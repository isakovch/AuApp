package com.example.chyngyz.auapp.data.storage;

import android.util.Log;

import com.example.chyngyz.auapp.data.entity.Vacancy;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmService implements RealmManager {
    private Realm mRealm;

    public RealmService(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public void saveVacancyList(RealmList<Vacancy> vacancyList) {
        RealmResults<Vacancy> results = mRealm.where(Vacancy.class).findAll();
        mRealm.executeTransaction(realm -> {
            results.deleteAllFromRealm();

            realm.copyToRealmOrUpdate(vacancyList);
        });
    }

    @Override
    public RealmResults<Vacancy> getMainVacancyList() {
        RealmResults<Vacancy> vacancies = mRealm.where(Vacancy.class).findAll();
        for (Vacancy vacancy : vacancies) {
            Log.d("REALM", "getMainVacancyList: " + vacancy.getPid());
        }
        return mRealm.where(Vacancy.class).findAll();
    }

    @Override
    public void saveVacancy(Vacancy vacancy) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(vacancy);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteVacancy(Vacancy vacancy) {
        RealmResults<Vacancy> results = mRealm
                .where(Vacancy.class)
                .equalTo("pid", vacancy.getPid())
                .findAll();

        mRealm.executeTransaction(realm -> results.deleteAllFromRealm());
    }

    @Override
    public void deleteAllVacancies() {
        mRealm.executeTransactionAsync(realm -> realm.deleteAll());
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }
}
