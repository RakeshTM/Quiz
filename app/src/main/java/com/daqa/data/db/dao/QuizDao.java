package com.daqa.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.daqa.data.db.model.QuizData;

import java.util.List;

@Dao
public interface QuizDao {

    @Transaction @Query(value = "SELECT * from tblQuestion")
    List<QuizData> getQuizData();

}
