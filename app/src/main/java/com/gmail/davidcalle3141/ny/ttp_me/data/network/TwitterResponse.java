package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.User;

import java.util.ArrayList;
import java.util.List;

public class TwitterResponse {
    private final List<Tweet> mTweets;
    private String next;

    TwitterResponse() {
        mTweets = new ArrayList<>();
    }

    public void addTweet(Tweet tweet, User user) {
        mTweets.add(tweet);
        tweet.setUser(user);
    }

    public void addTweet(Tweet tweet) {
        mTweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        return mTweets;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
