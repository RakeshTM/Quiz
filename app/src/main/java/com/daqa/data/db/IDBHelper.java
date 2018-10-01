package com.daqa.data.db;

import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.Option;
import com.daqa.data.db.model.Question;
import com.daqa.data.db.model.QuizData;

import java.util.List;

public interface IDBHelper {
    void deleteData();

    void saveQuestions(List<Question> questions);

    void saveOptions(List<Option> options);

    void saveImages(List<Image> images);

    List<QuizData> fetchQuizData();
}
