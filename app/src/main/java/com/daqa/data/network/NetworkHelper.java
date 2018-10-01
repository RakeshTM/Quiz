package com.daqa.data.network;

import android.support.annotation.NonNull;

import com.daqa.data.network.model.NetworkQuizModel;
import com.daqa.data.network.service.IRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public final class NetworkHelper implements INetworkHelper {

    private final Retrofit mRetrofit_;

    @Inject
    NetworkHelper() {
        mRetrofit_ = new Retrofit.Builder().baseUrl("https://demo0590264.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Override
    public void fetchQuestions(final NetworkQuizCallback callback) {
        final IRequest request = mRetrofit_.create(IRequest.class);
        Call<NetworkQuizModel> call = request.fetchQuestions();
        call.enqueue(new Callback<NetworkQuizModel>() {
            @Override
            public void onResponse(@NonNull Call<NetworkQuizModel> call, @NonNull Response<NetworkQuizModel> response) {
                if (!response.isSuccessful()) {
                    callback.onDataNotAvailable("Failed");
                    return;
                }
                NetworkQuizModel body = response.body();
                if (body == null) {
                    callback.onDataNotAvailable("Failed");
                    return;
                }
                if (body.getStatus().isIsError()) {
                    callback.onDataNotAvailable(body.getStatus().getMessage());
                    return;
                }
                callback.onQuestionsLoaded(body.getQuestions());

            }

            @Override
            public void onFailure(Call<NetworkQuizModel> call, Throwable t) {
                callback.onDataNotAvailable(t.getMessage());
            }
        });


    }


}
