package com.daqa.quiz;

import com.daqa.base.IBasePresenter;
import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.QuizData;

public interface IQuizContract {

    public interface IQuizPresenter extends IBasePresenter<IQuizView> {

        void initialise();

        void setScore();

        void nextQuestion();

        void reset();
    }

    public interface IQuizView {
        void showLoadingScreen();

        void showLoadingError(String message);

        void showQuizScreen();

        void showQuiz(QuizData quizData);

        void showScore(int score, int total);

        void showFinalScore(int scorePerc);

        void showImage(Image image);
    }
}
