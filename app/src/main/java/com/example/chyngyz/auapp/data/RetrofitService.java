package com.example.chyngyz.auapp.data;

import com.example.chyngyz.auapp.config.AppConstants;
import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST(AppConstants.COMMON_ENDPOINT)
    Call<ArrayList<Vacancy>> getAllVacancies(@Field("login") String login,
                                             @Field("f") String form,
                                             @Field("limit") int limit,
                                             @Field("page") int page);

}
