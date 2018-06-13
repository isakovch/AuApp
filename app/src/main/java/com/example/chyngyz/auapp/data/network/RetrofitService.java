package com.example.chyngyz.auapp.data.network;

import com.example.chyngyz.auapp.BuildConfig;
import com.example.chyngyz.auapp.utils.AppConstants;
import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST(BuildConfig.BASE_ENDPOINT)
    Call<RealmList<Vacancy>> getAllVacancies(@Field("login") String login,
                                             @Field("f") String form,
                                             @Field("limit") int limit,
                                             @Field("page") int page);

}
