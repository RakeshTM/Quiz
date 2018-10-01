package com.daqa.di;

import com.daqa.di.annotations.ActivityScoped;
import com.daqa.quiz.QuizActivity;
import com.daqa.quiz.QuizModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = QuizModule.class)
    QuizActivity bindQuizActivity();

}
