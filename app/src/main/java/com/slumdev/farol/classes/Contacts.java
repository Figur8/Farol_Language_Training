package com.slumdev.farol.classes;

public class Contacts {
    private String uuid;
    private String username;
    private String lastMessage;
    private long timestamp;
    private String photoUrl;
    private String language;
    private String optTeach;

    public Contacts() {
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOptTeach() {
        return optTeach;
    }

    public void setOptTeach(String optTeach) {
        this.optTeach = optTeach;
    }
}
