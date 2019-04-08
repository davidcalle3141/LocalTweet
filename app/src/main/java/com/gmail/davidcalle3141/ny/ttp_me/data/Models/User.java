package com.gmail.davidcalle3141.ny.ttp_me.data.Models;

public class User {
    private String id_str;
    private String name;
    private String screen_name;
    private String profile_image_url;
    private String profile_image_url_https;
    private String friends_count;
    private String followers_count;
    private String location;

    public User(String id_str, String name, String screen_name, String location,
                String profile_image_url,String profile_image_url_https,String friends_count,String followers_count){
        this.id_str = id_str;
        this.name = name;
        this.screen_name = "@"+screen_name;
        this.profile_image_url = profile_image_url;
        this.profile_image_url_https = profile_image_url_https;
        this.friends_count = friends_count;
        this.followers_count = followers_count;
        this.location = location;

    }

    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public String getId_str() {
        return id_str;
    }

    public String getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(String friends_count) {
        this.friends_count = friends_count;
    }

    public String getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(String followers_count) {
        this.followers_count = followers_count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
