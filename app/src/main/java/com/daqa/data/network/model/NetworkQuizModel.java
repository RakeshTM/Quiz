
package com.daqa.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkQuizModel {

    @SerializedName("status")
    @Expose
    private NetworkStatusModel status;
    @SerializedName("questions")
    @Expose
    private List<NetworkQuestionModel> questions = null;

    public NetworkStatusModel getStatus() {
        return status;
    }

    public void setStatus(NetworkStatusModel status) {
        this.status = status;
    }

    public List<NetworkQuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<NetworkQuestionModel> questions) {
        this.questions = questions;
    }

}
