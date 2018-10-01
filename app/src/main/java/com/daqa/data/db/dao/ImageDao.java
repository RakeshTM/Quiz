package com.daqa.data.db.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.daqa.data.db.model.Image;

import java.util.List;

@Dao
public interface ImageDao {

    @Insert
    void insertImage(List<Image> image);

    @Query("DELETE FROM tblImage")
    void deleteImage();
}
