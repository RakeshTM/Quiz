package com.daqa.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tblImage", foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "questionId", onDelete = ForeignKey.CASCADE))
public class Image {

    @PrimaryKey
    @ColumnInfo(name = "questionId")
    private int questionId;

    @ColumnInfo(name = "imgUrl")
    private String imgUrl;

    @ColumnInfo(name = "localUtl")
    private String localUrl;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
}
