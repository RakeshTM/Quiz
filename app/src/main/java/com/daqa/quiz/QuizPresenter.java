package com.daqa.quiz;

import com.daqa.data.IDataManager;
import com.daqa.data.db.model.QuizData;
import com.daqa.di.annotations.ActivityScoped;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@ActivityScoped
public class QuizPresenter implements IQuizContract.IQuizPresenter {

    private IDataManager mDataManager_;
    private IQuizContract.IQuizView mView_;
    private List<QuizData> mQuizData_ = new ArrayList<>();

    private int mCurrentQuestionId_ = 0;

    @Inject
    QuizPresenter(IDataManager dataManager) {
        mDataManager_ = dataManager;
    }


    @Inject
    @Override
    public void bindView(IQuizContract.IQuizView view) {
        mView_ = view;
    }

    @Override
    public void setScore() {
        mView_.showScore(getScore(), mQuizData_.size());
    }

    private int getScore() {
        int score = 0;
        for (QuizData q :
                mQuizData_) {
            if (q.isCorrect()) {
                score++;
            }
        }
        return score;
    }

    @Override
    public void initialise() {
        boolean appInitialised = mDataManager_.isAppInitialised();
        fetchQuestions(!appInitialised);
    }

    private void fetchQuestions(boolean forceLoad) {
        mView_.showLoadingScreen();
        mCurrentQuestionId_ = 0;
        mDataManager_.fetchQuestions(forceLoad, new IDataManager.LoadQuizCallback() {
            @Override
            public void onQuestionsLoaded(List<QuizData> quizData) {
                mDataManager_.setAppInitialised();
                mView_.showQuizScreen();
                mQuizData_.clear();
                mQuizData_.addAll(quizData);
                showQuiz(quizData.get(0));
            }

            @Override
            public void onDataNotAvailable(String message) {
                mView_.showLoadingError(message);
            }
        });
    }

    private void showQuiz(QuizData quizData){
        mView_.showQuiz(quizData);
        if(!quizData.getImage().isEmpty()){
            mView_.showImage(quizData.getImage().get(0));
        }
    }

    @Override
    public void nextQuestion() {
        ++mCurrentQuestionId_;
        if (mCurrentQuestionId_ < mQuizData_.size()) {
            showQuiz(mQuizData_.get(mCurrentQuestionId_));
        } else {
            int scoreperc = (getScore() * 100 / mQuizData_.size());
            mView_.showFinalScore(scoreperc);
        }
    }

    @Override
    public void reset() {
        fetchQuestions(true);
    }
}
