package com.daqa.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.daqa.data.db.dao.ImageDao;
import com.daqa.data.db.dao.OptionDao;
import com.daqa.data.db.dao.QuestionDao;
import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.Option;
import com.daqa.data.db.model.Question;
import com.daqa.data.db.model.QuizData;
import com.daqa.di.annotations.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class DBHelper implements IDBHelper {

    private final QuizDatabase mDatabase_;

    @Inject
    DBHelper(@ApplicationContext Context context) {
        this.mDatabase_ = Room.databaseBuilder(context, QuizDatabase.class, context.getPackageName() + ".db").build();
    }

    @Override
    public void saveQuestions(List<Question> questions) {
        mDatabase_.questionDao().insertQuestions(questions);
    }

    @Override
    public void saveOptions(List<Option> options) {
        mDatabase_.optionDao().insertOptions(options);
    }

    @Override
    public void saveImages(List<Image> images) {
        mDatabase_.imageDao().insertImage(images);
    }

    @Override
    public void deleteData() {
        QuestionDao questionDao = mDatabase_.questionDao();
        ImageDao imageDao = mDatabase_.imageDao();
        OptionDao optionDao = mDatabase_.optionDao();
        questionDao.deleteQuestions();
        imageDao.deleteImage();
        optionDao.deleteOptions();
    }

    @Override
    public List<QuizData> fetchQuizData() {
        return mDatabase_.quizDao().getQuizData();
    }
}