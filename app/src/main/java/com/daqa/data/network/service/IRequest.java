package com.daqa.data.network.service;

import com.daqa.data.network.model.NetworkQuizModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IRequest {

    @GET("questions")
    Call<NetworkQuizModel> fetchQuestions();
}
