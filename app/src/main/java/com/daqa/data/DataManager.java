package com.daqa.data;

import com.daqa.data.db.IDBHelper;
import com.daqa.data.db.model.QuizData;
import com.daqa.data.network.INetworkHelper;
import com.daqa.data.network.model.NetworkQuestionModel;
import com.daqa.data.sp.ISPHelper;
import com.daqa.mapper.ModelMapper;
import com.daqa.utils.ThreadExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DataManager implements IDataManager {

    private final ISPHelper mSPHelper_;
    private INetworkHelper mNetworkHelper_;
    private IDBHelper mDbHelper_;
    private final ThreadExecutors mExecutors_;

    @Inject
    public DataManager(INetworkHelper networkHelper, IDBHelper dbHelper, ISPHelper spHelper, ThreadExecutors executors) {
        mNetworkHelper_ = networkHelper;
        mDbHelper_ = dbHelper;
        mSPHelper_ = spHelper;
        this.mExecutors_ = executors;
    }

    @Override
    public void fetchQuestions(boolean forceLoad, LoadQuizCallback callback) {
        if (forceLoad) {
            fetchDataFromNetwork(callback);
        } else {
            fetchDataFromDB(callback);
        }
    }

    private void fetchDataFromDB(final LoadQuizCallback callback) {
        mExecutors_.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                loadDBData(callback);
            }
        });
    }

    private void fetchDataFromNetwork(final LoadQuizCallback callback) {
        mNetworkHelper_.fetchQuestions(new INetworkHelper.NetworkQuizCallback() {
            @Override
            public void onQuestionsLoaded(List<NetworkQuestionModel> networkModel) {
                modelMap(networkModel, callback);
            }

            @Override
            public void onDataNotAvailable(String message) {
                callback.onDataNotAvailable(message);
            }
        });
    }

    private void modelMap(final List<NetworkQuestionModel> networkModel, final LoadQuizCallback callback) {
        mExecutors_.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final ModelMapper.QuizDBModel quizDBModel = ModelMapper.convertNetworkToDBModel(networkModel);
                refreshLocalDataSource(quizDBModel);
                loadDBData(callback);
            }
        });
    }

    private void loadDBData(final LoadQuizCallback callback) {
        final List<QuizData> quizData = getQuizData();
        mExecutors_.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                callback.onQuestionsLoaded(quizData);
            }
        });
    }

    private List<QuizData> getQuizData() {
        return mDbHelper_.fetchQuizData();
    }

    private void refreshLocalDataSource(ModelMapper.QuizDBModel quizData) {
        mDbHelper_.deleteData();
        mDbHelper_.saveQuestions(quizData.getQuestions());
        mDbHelper_.saveOptions(quizData.getOptions());
        mDbHelper_.saveImages(quizData.getImages());
    }

    @Override
    public boolean isAppInitialised() {
        return mSPHelper_.isAppInitialized();
    }

    @Override
    public void setAppInitialised() {
        mSPHelper_.setAppInitialized();
    }
}
