package com.example.chyngyz.auapp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.chyngyz.auapp.config.AppConstants;
import com.example.chyngyz.auapp.data.RetrofitService;
import com.example.chyngyz.auapp.data.db.SQLiteManager;
import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.utils.NetworkStatus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private RetrofitService mService;
    private SQLiteManager mSQLiteManager;

    MainPresenter(RetrofitService service, SQLiteManager sqLiteManager) {
        this.mService = service;
        this.mSQLiteManager = sqLiteManager;
    }

    @Override
    public void getAllVacancies(Context context) {
        if (!NetworkStatus.isNetworkAvailable(context)) {
            mView.showAllVacancies(mSQLiteManager.getMainVacancyList());
            return;
        }

        mView.showLoadingIndicator();
        mService.getAllVacancies(AppConstants.API_LOGIN, AppConstants.FORM_GET_ALL_VACANCIES, 20, 1)
                .enqueue(new Callback<ArrayList<Vacancy>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Vacancy>> call, @NonNull Response<ArrayList<Vacancy>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (isViewAttached()) {
                                save(response.body());
                                mView.showAllVacancies(response.body());
                                mView.hideLoadingIndicator();
                            }
                        } else {
                            if (isViewAttached()) {
                                mView.showGetVacanciesError(response.message());
                                mView.hideLoadingIndicator();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<Vacancy>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.showGetVacanciesError(t.getMessage());
                            mView.hideLoadingIndicator();
                        }
                    }
                });
    }

    @Override
    public void saveOrDeleteVacancy(boolean isChecked, Vacancy vacancy) {
        if (isChecked) {
            mSQLiteManager.saveVacancy(vacancy);
        } else {
            mSQLiteManager.deleteVacancy(vacancy);
        }
    }


    private void save(ArrayList<Vacancy> vacancyList) {
        mSQLiteManager.deleteAllVacancies();
        mSQLiteManager.saveVacancyList(vacancyList);
    }

    @Override
    public void bind(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbind() {
        this.mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
