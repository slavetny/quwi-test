package com.slavetny.quwitest.domain.model.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar_url")
    @Expose
    private Object avatarUrl;
    @SerializedName("is_online")
    @Expose
    private boolean isOnline;
    @SerializedName("dta_activity")
    @Expose
    private String dtaActivity;
    @SerializedName("dta_since")
    @Expose
    private Object dtaSince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(Object avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getDtaActivity() {
        return dtaActivity;
    }

    public void setDtaActivity(String dtaActivity) {
        this.dtaActivity = dtaActivity;
    }

    public Object getDtaSince() {
        return dtaSince;
    }

    public void setDtaSince(Object dtaSince) {
        this.dtaSince = dtaSince;
    }
}