package com.gmail.davidcalle3141.ny.ttp_me.data.Tweet;

public class Tweet {
    private String id_str;
    private String created_at;
    private String text;
    private String user_id;
    private String media_url_https;
    private String next;


    public Tweet(){};


    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getUser() {
        return user_id;
    }

    public String getMedia_url_https() {
        return media_url_https;
    }

    public void setMedia_url_https(String media_url_https) {
        this.media_url_https = media_url_https;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
