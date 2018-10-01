package com.daqa.mapper;

import android.text.TextUtils;

import com.daqa.data.db.model.Image;
import com.daqa.data.db.model.Option;
import com.daqa.data.db.model.Question;
import com.daqa.data.network.model.NetworkOptionModel;
import com.daqa.data.network.model.NetworkQuestionModel;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class ModelMapper {

    public static QuizDBModel convertNetworkToDBModel(List<NetworkQuestionModel> networkQuestionModel) {

        QuizDBModel model = new QuizDBModel();

        List<Question> questions = new ArrayList<>();
        List<Option> options = new ArrayList<>();
        List<Image> images = new ArrayList<>();


        for (int i = 0; i < networkQuestionModel.size(); i++) {

            NetworkQuestionModel n = networkQuestionModel.get(i);
            int questionId = i + 1;

            String questionText = n.getQuestionText();
            questions.add(getQuestion(questionId, questionText));

            Image image = getImage(questionId, questionText);
            if (image != null) {
                images.add(image);
            }

            options.addAll(getOptions(questionId, n.getOptions()));

        }
        model.setQuestions(questions);
        model.setImages(images);
        model.setOptions(options);
        return model;

    }

    private static List<Option> getOptions(int questionId, List<NetworkOptionModel> options) {
        List<Option> opts = new ArrayList<>();
        for (NetworkOptionModel o : options) {
            Option opt = new Option();
            opt.setQuestionId(questionId);
            opt.setValue(getParsedHTMLParaText(o.getValue()));
            opt.setCorrect(Boolean.parseBoolean(o.getCorrect()));
            opts.add(opt);
        }
        return opts;
    }

    private static Image getImage(int questionId, String questionText) {
        String imgUrl = getParsedHTMLImageAttr(questionText);
        if (!TextUtils.isEmpty(imgUrl)) {
            Image image = new Image();
            image.setQuestionId(questionId);
            image.setImgUrl(imgUrl);
            return image;
        }
        return null;
    }

    private static Question getQuestion(int questionId, String questionText) {
        Question question = new Question();
        question.setQuestionId(questionId);
        String q = getParsedHTMLParaText(questionText);
        question.setQuestion(q.trim());
        return question;
    }

    private static String getParsedHTMLImageAttr(String text) {
        return isHTML(text) ? getElementsByTag("img", text).attr("src"): null;
    }

    private static boolean isHTML(String text) {
        Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
        return htmlPattern.matcher(text).matches();
    }

    private static Elements getElementsByTag(String tag, String text) {
        return Jsoup.parse(text).select(tag);
    }

    private static String getParsedHTMLParaText(String text) {
        return isHTML(text) ? getElementsByTag("p", text).text() : text;
    }

    public static class QuizDBModel {
        private List<Question> questions;
        private List<Option> options;
        private List<Image> images;

        public List<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Question> questions) {
            this.questions = questions;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }
    }

}
