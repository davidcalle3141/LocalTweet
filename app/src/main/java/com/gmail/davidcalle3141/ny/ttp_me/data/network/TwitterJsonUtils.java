package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TwitterJsonUtils {
    private User mUser;
    private Tweet mTweet;
    private TwitterResponse mTwitterResponse;
    private JSONObject mResults;
    private JSONArray mResultsJSONArray;
    private JSONObject mTempObject;
    private JSONObject mNestedObject;

    public TwitterResponse parseTwitterJson(String twitterJSONData) throws JSONException {
        mTwitterResponse = new TwitterResponse();
        mResults = new JSONObject(twitterJSONData);
        if (mResults.length() == 0) return new TwitterResponse();
        mTwitterResponse.setNext(mResults.getString("next"));
        mResultsJSONArray = mResults.getJSONArray("results");
        for(int i = 0; i< mResultsJSONArray.length(); i++){
            mTweet = parseTweet(i);
            mUser = parseUser(i);
            mTwitterResponse.addTweet(mTweet, mUser);
        }
        return mTwitterResponse;
    }

    public TwitterResponse parseUserTimeline(String twitterJSONData) throws JSONException {
        mTwitterResponse = new TwitterResponse();
        mResultsJSONArray = new JSONArray(twitterJSONData);
        for(int i = 0; i< mResultsJSONArray.length(); i++){
            mTweet = parseTweet(i);
            mUser = parseUser(i);
            mTwitterResponse.addTweet(mTweet, mUser);
        }
        return mTwitterResponse;
    }


    private User parseUser(int index) throws JSONException {
        mTempObject = mResultsJSONArray.getJSONObject(index);
        mNestedObject = mTempObject.getJSONObject("user");
        String id_str = mNestedObject.getString("id_str");
        String name = mNestedObject.getString("name");
        String screen_name = mNestedObject.getString("screen_name");
        String profile_image_url = mNestedObject.getString("profile_image_url");
        String profile_image_url_http = mNestedObject.getString("profile_image_url_https");
        String friends_count = mNestedObject.getString("friends_count");
        String followers_count = mNestedObject.getString("followers_count");
        String location = mNestedObject.getString("location");

        return new User(id_str,name,screen_name,location,profile_image_url,profile_image_url_http,friends_count,followers_count);

    }

    private Tweet parseTweet(int index) throws JSONException {
        mTempObject = mResultsJSONArray.getJSONObject(index);
        String media_url_https = null;
        String id_str = mTempObject.getString("id_str");
        String created_at = mTempObject.getString("created_at");
        String text = mTempObject.getString("text");
        mNestedObject = mTempObject.getJSONObject("entities");
        if(mNestedObject.has("media")){
            media_url_https = mNestedObject.getJSONArray("media").getJSONObject(0).get("media_url_https").toString();
        }
        if(mTempObject.has("extended_tweet")){
        mTempObject = mTempObject.getJSONObject("extended_tweet");
        if(mTempObject.has("full_text")){
            text = mTempObject.getString("full_text");
        }}

        return new Tweet(id_str,created_at,text, media_url_https);

    }


}
