package com.slumdev.farol.classes;


import java.io.Serializable;


// Serializable pra fazer o putExtra e pegar o id do usuário...
public class User implements Serializable {

    private String uuid;
    private  String username;
    private  String language;
    private String profileUrl; // url da foto do storage

    public User() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
