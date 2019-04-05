package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TwitterJsonUtils {
    private User user;
    private Tweet tweet;
    private TwitterResponse twitterResponse;
    private JSONObject results;
    private JSONArray resultsJSONArray;
    private JSONObject tempObject;
    private JSONObject nestedObject;

    public TwitterResponse parseTwitterJson(String twitterJSONData) throws JSONException {
        twitterResponse = new TwitterResponse();
        results = new JSONObject(twitterJSONData);
        if (results.length() == 0) return new TwitterResponse();
        twitterResponse.setNext(results.getString("next"));
        resultsJSONArray = results.getJSONArray("results");
        for(int i=0;i< resultsJSONArray.length();i++){
            tweet = parseTweet(i);
            user = parseUser(i);
            twitterResponse.addTweet(tweet,user);
        }
        return twitterResponse;
    }

    public TwitterResponse parseUserTimeline(String twitterJSONData) throws JSONException {
        twitterResponse = new TwitterResponse();
        resultsJSONArray = new JSONArray(twitterJSONData);
        for(int i =0; i<resultsJSONArray.length();i++){
            tweet = parseTweet(i);
            twitterResponse.addTweet(tweet);
        }
        return twitterResponse;
    }


    private User parseUser(int index) throws JSONException {
        tempObject = resultsJSONArray.getJSONObject(index);
        nestedObject = tempObject.getJSONObject("user");
        String id_str = nestedObject.getString("id_str");
        String name = nestedObject.getString("name");
        String screen_name = nestedObject.getString("screen_name");
        String profile_image_url = nestedObject.getString("profile_image_url");
        String profile_image_url_http = nestedObject.getString("profile_image_url_https");
        String friends_count = nestedObject.getString("friends_count");
        String followers_count = nestedObject.getString("followers_count");
        String location = nestedObject.getString("location");

        return new User(id_str,name,screen_name,location,profile_image_url,profile_image_url_http,friends_count,followers_count);

    }

    private Tweet parseTweet(int index) throws JSONException {
        tempObject = resultsJSONArray.getJSONObject(index);
        String media_url_https = null;
        String id_str = tempObject.getString("id_str");
        String created_at = tempObject.getString("created_at");
        String text = tempObject.getString("text");
        nestedObject = tempObject.getJSONObject("entities");
        if(nestedObject.has("media")){
            media_url_https = nestedObject.getJSONArray("media").getJSONObject(0).get("media_url_https").toString();
        }
        if(tempObject.has("extended_tweet")){
        tempObject = tempObject.getJSONObject("extended_tweet");
        if(tempObject.has("full_text")){
            text = tempObject.getString("full_text");
        }}

        return new Tweet(id_str,created_at,text, media_url_https);

    }


}
