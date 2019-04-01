package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import android.content.Context;

import com.gmail.davidcalle3141.ny.ttp_me.data.Tweet.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;

import androidx.lifecycle.MutableLiveData;

public class TwitterNetworkDataSource {
    private static final Object LOCK = new Object();
    private static TwitterNetworkDataSource sInstance;
    private final Context mContext;
    private final MutableLiveData<Tweet[]> mDownloadedTweets;
    private final AppExecutors mExecutors;


    private TwitterNetworkDataSource(Context context, AppExecutors executors){
        this.mContext = context;
        this.mExecutors = executors;
        this.mDownloadedTweets = new MutableLiveData<Tweet[]>();
    }

    public static TwitterNetworkDataSource getInstance(Context context, AppExecutors executors){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new TwitterNetworkDataSource(context.getApplicationContext(),executors);

            }
        }
        return sInstance;
    }
    //TODO complete methods
    void fetchTweetsByLocation(){}
    void fetchTweetsByHashtag(){}
    void fetchTweetsByUser(){}


}
