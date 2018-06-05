package com.example.chyngyz.auapp.ui.main;

import android.support.annotation.NonNull;

import com.example.chyngyz.auapp.config.AppConstants;
import com.example.chyngyz.auapp.data.RetrofitService;
import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private RetrofitService mService;

    MainPresenter(RetrofitService service) {
        this.mService = service;
    }

    @Override
    public void getAllVacancies() {
        mView.showLoadingIndicator();
        mService.getAllVacancies(AppConstants.API_LOGIN, AppConstants.FORM_GET_ALL_VACANCIES, 20, 1)
                .enqueue(new Callback<ArrayList<Vacancy>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<Vacancy>> call, @NonNull Response<ArrayList<Vacancy>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (isViewAttached()) {
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
