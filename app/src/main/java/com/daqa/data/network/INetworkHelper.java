package com.daqa.data.network;

import com.daqa.data.IDataManager;
import com.daqa.data.network.model.NetworkQuestionModel;

import java.util.List;

public interface INetworkHelper {

    interface NetworkQuizCallback {

        void onQuestionsLoaded(List<NetworkQuestionModel> tasks);

        void onDataNotAvailable(String message);
    }

    void fetchQuestions(NetworkQuizCallback callback);
}
