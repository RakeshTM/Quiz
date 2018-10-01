
package com.daqa.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkQuestionModel {

    @SerializedName("options")
    @Expose
    private List<NetworkOptionModel> options = null;
    @SerializedName("questionText")
    @Expose
    private String questionText;

    public List<NetworkOptionModel> getOptions() {
        return options;
    }

    public void setOptions(List<NetworkOptionModel> options) {
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

}
