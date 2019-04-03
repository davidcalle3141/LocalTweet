package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import com.gmail.davidcalle3141.ny.ttp_me.data.Tweet.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TwitterJsonUtils {

    public List<Tweet> parseTwitterJson(String twitterJSONData) throws JSONException {
        JSONObject results = new JSONObject(twitterJSONData);
        JSONArray resultsJSONArray = results.getJSONArray("results");
        JSONObject tempObject;
        JSONObject nestedObject;
        List<Tweet> tweetList = new ArrayList<>();
        for(int i=0;i< results.length();i++){
            tweetList.add(new Tweet());
            tweetList.get(i).setNext(results.getString("next"));
            tempObject = resultsJSONArray.getJSONObject(i);
            tweetList.get(i).setId_str(tempObject.getString("id_str"));
            tweetList.get(i).setCreated_at(tempObject.getString("created_at"));
            tweetList.get(i).setText(tempObject.getString("text"));
            nestedObject = tempObject.getJSONObject("user");
            tweetList.get(i).setUser_id(nestedObject.getString("id_str"));
            nestedObject = tempObject.getJSONObject("entities");
            if(nestedObject.has("media")){
                nestedObject = nestedObject.getJSONObject("media");
                tweetList.get(i).setMedia_url_https(nestedObject.getString("media_url_https"));}
        }
        return tweetList;
    }
}
