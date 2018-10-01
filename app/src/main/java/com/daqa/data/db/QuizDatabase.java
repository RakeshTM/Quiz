package com.daqa.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.daqa.data.db.dao.ImageDao;
import com.daqa.data.db.dao.OptionDao;
import com.daqa.data.db.dao.QuestionDao;
import com.daqa.data.db.dao.QuizDao;
import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.Option;
import com.daqa.data.db.model.Question;

@Database(entities = {Question.class, Option.class, Image.class}, version = 1)
public abstract class QuizDatabase extends RoomDatabase {

    abstract QuestionDao questionDao();

    abstract OptionDao optionDao();

    abstract ImageDao imageDao();

    abstract QuizDao quizDao();
}
