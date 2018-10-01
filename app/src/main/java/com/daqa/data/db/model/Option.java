package com.daqa.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tblOption", foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "questionId", childColumns = "questionId", onDelete = ForeignKey.CASCADE))
public class Option {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "optionId")
    private int optionId;

    @ColumnInfo(name = "questionId")
    private int questionId;

    @ColumnInfo(name = "value")
    private String value;

    @ColumnInfo(name = "correct")
    private boolean correct;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getValue() {
        return value;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
