package com.daqa.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.daqa.data.db.model.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Insert
    void insertQuestions(List<Question> questions);


    @Query("DELETE FROM tblQuestion")
    void deleteQuestions();
}
