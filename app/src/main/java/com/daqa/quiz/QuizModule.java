package com.daqa.quiz;

import dagger.Binds;
import dagger.Module;

@Module
public interface QuizModule {

    @Binds
    IQuizContract.IQuizPresenter bindPresenter(QuizPresenter presenter);

    @Binds
    IQuizContract.IQuizView bindView(QuizActivity activity);
}
