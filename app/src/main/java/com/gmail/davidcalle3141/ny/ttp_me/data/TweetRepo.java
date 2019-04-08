package com.gmail.davidcalle3141.ny.ttp_me.data;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.LocationModel;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.network.TwitterNetworkDataSource;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TweetRepo {

    private static final Object LOCK = new Object();
    private static TweetRepo sInstance;
    private final TwitterNetworkDataSource mTwitterNDS;
    private final AppExecutors mExecutors;
    private final String mCount = "30";
    private boolean mLocationInitialized;
    private boolean mTweetsInitialized;

    private TweetRepo(TwitterNetworkDataSource twitterNetworkDataSource, AppExecutors executors){
        this.mExecutors = executors;
        this.mTwitterNDS = twitterNetworkDataSource;
        mTweetsInitialized=false;
        mLocationInitialized=false;

    }

    public synchronized static TweetRepo getInstance(TwitterNetworkDataSource twitterNetworkDataSource, AppExecutors executors){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new TweetRepo(twitterNetworkDataSource,executors);
            }
        }
        return sInstance;
    }

    private void initializeLocation(){
        if(mLocationInitialized) return;
        mLocationInitialized = true;
        mTwitterNDS.fetchLocation();
    }

    private void initializeTweets(String latitude,String longitude,String distance){
        if(mTweetsInitialized) return;
        mTweetsInitialized = true;
        mTwitterNDS.fetchTweetByLocation(latitude,longitude,distance, mCount);

    }


    public void fetchLocalTweets(String latitude,String longitude,String distance) {
        initializeTweets( latitude, longitude, distance);
    }
    public void fetchTweetsByUser(String str_id){
        mTwitterNDS.fetchTweetsByUser(str_id, mCount);
    }
    public void fetchTweetsByHashtag(String hashtag){
        mTwitterNDS.fetchTweetsByHashtag(hashtag, mCount);
    }


    public void refreshLocation(){
        mTweetsInitialized=false;
        mTwitterNDS.fetchLocation();
    }

    public LiveData<List<Tweet>> getLocationTweets(){
        return mTwitterNDS.getTweets();
    }

    public LiveData<List<Tweet>> getSearchTweets(){
        return mTwitterNDS.getSearchTweets();
    }

    public LiveData<List<Tweet>> getUserTimeline(){return mTwitterNDS.getUserTimeline();}

    public LiveData<LocationModel> getLocation(){
        initializeLocation();
        return mTwitterNDS.getLocation();}

}
