package com.daqa.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.daqa.data.db.model.Option;

import java.util.List;

@Dao
public interface OptionDao {

    @Insert
    void insertOptions(List<Option> options);

    @Query("DELETE FROM tblOption")
    void deleteOptions();
}
