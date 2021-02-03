package com.slavetny.quwitest.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Token {

    private String token;

    @SerializedName("first_errors")
    private Map<String, String> error;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}