package com.gmail.davidcalle3141.ny.ttp_me.data.Tweet;

public class Tweet {
    private String id_str;
    private String created_at;
    private String text;
    private String source;
    private User user;

    public Tweet(String id_str,String created_at,String text,String source, User user){
        this.id_str = id_str;
        this.created_at = created_at;
        this.text = text;
        this.source = source;
        this.user = user;
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

    public String getSource() {
        return source;
    }

    public User getUser() {
        return user;
    }
}
