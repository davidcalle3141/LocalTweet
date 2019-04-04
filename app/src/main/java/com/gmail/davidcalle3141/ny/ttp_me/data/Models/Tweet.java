package com.gmail.davidcalle3141.ny.ttp_me.data.Models;

public class Tweet {
    private String id_str;
    private String created_at;
    private String text;
    private String media_url_https;
    private User user;



    public Tweet(){};
    public Tweet(String id_str, String created_at, String text, String media_url_https){
        this.id_str = id_str;
        this.created_at = created_at;
        this.text = text;
        this.media_url_https = media_url_https;
    }


    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId_str() {
        return id_str;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getText() {
        return text;
    }


    public String getMedia_url_https() {
        return media_url_https;
    }

    public void setMedia_url_https(String media_url_https) {
        this.media_url_https = media_url_https;
    }

}
