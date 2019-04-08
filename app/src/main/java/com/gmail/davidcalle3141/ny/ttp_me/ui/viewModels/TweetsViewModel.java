package com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.LocationModel;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.User;
import com.gmail.davidcalle3141.ny.ttp_me.data.TweetRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TweetsViewModel extends ViewModel {
    private final TweetRepo mRepo;
    private MutableLiveData<User> mFocusedUser;
    private LiveData<List<Tweet>> mLocationTweetList;
    private LiveData<List<Tweet>> mSearchTweetList;
    private LiveData<List<Tweet>> mUserTimeline;
    private LiveData<LocationModel> mLocation;
    private String hashtag;


    public TweetsViewModel(TweetRepo mRepo) {
        this.mRepo = mRepo;
        this.mLocationTweetList = mRepo.getLocationTweets();
        this.mSearchTweetList = mRepo.getSearchTweets();
        this.mFocusedUser = new MutableLiveData<>();
        this.mUserTimeline = mRepo.getUserTimeline();
        this.mLocation = mRepo.getLocation();
    }


    public LiveData<List<Tweet>> getLocationTweets() {
        return mLocationTweetList;
    }

    public LiveData<List<Tweet>> getSearchTweets() {
        return mSearchTweetList;
    }

    public LiveData<List<Tweet>> getUserTimeline() {
        return mUserTimeline;
    }

    public void fetchLocalTweets(String latitude, String longitude, String distance) {
        mRepo.fetchLocalTweets(longitude, latitude, distance);
    }


    public void fetchTweetsByHashtag(String hashtag) {
        this.hashtag = hashtag;
        mRepo.fetchTweetsByHashtag(hashtag);
    }

    public void fetchTweetsByUser(String str_id) {
        mRepo.fetchTweetsByUser(str_id);
    }

    public void setFocusedUser(User user) {
        mFocusedUser.postValue(user);
    }

    public LiveData<User> getFocusedUser() {
        return mFocusedUser;
    }

    public void updateLocation() {
        mRepo.refreshLocation();
    }

    public LiveData<LocationModel> getLocation() {
        return mLocation;
    }

    public String getHashtag() {
        return hashtag;
    }

}
