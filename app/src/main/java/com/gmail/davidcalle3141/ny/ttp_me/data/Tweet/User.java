package com.gmail.davidcalle3141.ny.ttp_me.data.Tweet;

public class User {
    private String id_str;
    private String name;
    private String screen_name;
    private String location;
    private String description;
    private Boolean verified;
    private String profile_image_url;
    private String profile_image_url_https;

    public User(String id_str, String name, String screen_name, String location,
                String description, Boolean verified, String profile_image_url,String profile_image_url_https){
        this.id_str = id_str;
        this.name = name;
        this.screen_name = screen_name;
        this.location = location;
        this.description = description;
        this.verified = verified;
        this.profile_image_url = profile_image_url;
        this.profile_image_url_https = profile_image_url_https;

    }

    public String getProfile_image_url_https() {
        return profile_image_url_https;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public Boolean getVerified() {
        return verified;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
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
}
