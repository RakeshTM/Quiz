package com.daqa.data.db.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

public class QuizData {

    @Embedded
    private Question question;

    @Relation(parentColumn = "questionId", entityColumn = "questionId", entity = Image.class)
    private List<Image> image;

    @Relation(parentColumn = "questionId", entityColumn = "questionId", entity = Option.class)
    private List<Option> options;

    @Ignore
    private boolean answered;

    @Ignore
    private boolean correct;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered() {
        this.answered = true;
    }

    public void setAnswer(int selectedOptionId) {
        for (Option option : options) {
            if (option.isCorrect()) {
                correct = selectedOptionId == option.getOptionId();
                break;
            }
        }
    }

    public boolean isCorrect() {
        return correct;
    }

}
