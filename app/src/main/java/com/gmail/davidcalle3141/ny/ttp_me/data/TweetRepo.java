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
    private final String count = "10";
    private boolean mLocationInitialized;

    private TweetRepo(TwitterNetworkDataSource twitterNetworkDataSource, AppExecutors executors){
        this.mExecutors = executors;
        this.mTwitterNDS = twitterNetworkDataSource;

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


    public void fetchLocalTweets(String latitude,String longitude,String distance) {
        mTwitterNDS.fetchTweetByLocation(latitude,longitude,distance,count);
    }
    public void fetchTweetsByUser(String str_id){
        mTwitterNDS.fetchTweetsByUser(str_id,count);
    }
    public void fetchTweetsByHashtag(String hashtag){
        mTwitterNDS.fetchTweetsByHashtag(hashtag,count);
    }
    public void fetchLocation(){
        mTwitterNDS.fetchLocation();
    }

    public LiveData<List<Tweet>> getTweets(){
        return mTwitterNDS.getTweets();
    }

    public LiveData<LocationModel> getLocation(){
        initializeLocation();
        return mTwitterNDS.getLocation();}




}
