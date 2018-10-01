package com.daqa.data;

import com.daqa.data.db.model.QuizData;

import java.util.List;

public interface IDataManager {
    void fetchQuestions(boolean forceLoad, LoadQuizCallback callback);

    boolean isAppInitialised();

    void setAppInitialised();

    interface LoadQuizCallback {

        void onQuestionsLoaded(List<QuizData> tasks);

        void onDataNotAvailable(String message);
    }

}
