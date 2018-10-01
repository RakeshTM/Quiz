
package com.daqa.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkOptionModel {

    @SerializedName("correct")
    @Expose
    private String correct;
    @SerializedName("value")
    @Expose
    private String value;

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
