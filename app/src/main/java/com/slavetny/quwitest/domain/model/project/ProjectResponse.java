package com.slavetny.quwitest.domain.model.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ProjectResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("logo_url")
    @Expose
    private Object logoUrl;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("is_active")
    @Expose
    private int isActive;
    @SerializedName("is_owner_watcher")
    @Expose
    private boolean isOwnerWatcher;
    @SerializedName("dta_user_since")
    @Expose
    private Object dtaUserSince;
    @SerializedName("users")
    @Expose
    private List<User> users = null;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Object getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(Object logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public boolean isIsOwnerWatcher() {
        return isOwnerWatcher;
    }

    public void setIsOwnerWatcher(boolean isOwnerWatcher) {
        this.isOwnerWatcher = isOwnerWatcher;
    }

    public Object getDtaUserSince() {
        return dtaUserSince;
    }

    public void setDtaUserSince(Object dtaUserSince) {
        this.dtaUserSince = dtaUserSince;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}