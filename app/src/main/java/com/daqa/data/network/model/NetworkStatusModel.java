
package com.daqa.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetworkStatusModel {

    @SerializedName("isError")
    @Expose
    private boolean isError;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
