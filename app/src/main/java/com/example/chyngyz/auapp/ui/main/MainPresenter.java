package com.example.chyngyz.auapp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.chyngyz.auapp.R;
import com.example.chyngyz.auapp.data.entity.Vacancy;
import com.example.chyngyz.auapp.data.network.RetrofitService;
import com.example.chyngyz.auapp.data.resource.ResourceManager;
import com.example.chyngyz.auapp.data.storage.RealmManager;
import com.example.chyngyz.auapp.utils.AppConstants;
import com.example.chyngyz.auapp.utils.NetworkStatus;

import java.util.ArrayList;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private RetrofitService mService;
    private RealmManager mRealmManager;
    private ResourceManager mResourceManager;

    public MainPresenter(RetrofitService service, RealmManager realmManager, ResourceManager resourceManager) {
        this.mService = service;
        this.mRealmManager = realmManager;
        this.mResourceManager = resourceManager;
    }

    @Override
    public void getAllVacancies(Context context) {
        if (!NetworkStatus.isNetworkAvailable(context)) {
            mView.showAllVacancies(mRealmManager.getMainVacancyList());
            mView.showActionMessage(mResourceManager.getStringResource(R.string.msg_no_internet_connection));
            return;
        }

        mView.showLoadingIndicator();
        mService.getAllVacancies(AppConstants.API_LOGIN, AppConstants.FORM_GET_ALL_VACANCIES, 20, 1)
                .enqueue(new Callback<RealmList<Vacancy>>() {
                    @Override
                    public void onResponse(@NonNull Call<RealmList<Vacancy>> call, @NonNull Response<RealmList<Vacancy>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            if (isViewAttached()) {
                                save(response.body());
                                mView.showAllVacancies(response.body());

                            }
                        } else {
                            if (isViewAttached()) {
                                mView.showGetVacanciesError(response.message());
                            }
                        }

                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<RealmList<Vacancy>> call, @NonNull Throwable t) {
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
            mRealmManager.saveVacancy(vacancy);
        } else {
            mRealmManager.deleteVacancy(vacancy);
        }

        mView.showActionMessage(isChecked ?
                mResourceManager.getStringResource(R.string.action_vacancy_added) :
                mResourceManager.getStringResource(R.string.action_vacancy_deleted));
    }


    private void save(RealmList<Vacancy> vacancyList) {
        mRealmManager.deleteAllVacancies();
        mRealmManager.saveVacancyList(vacancyList);
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
