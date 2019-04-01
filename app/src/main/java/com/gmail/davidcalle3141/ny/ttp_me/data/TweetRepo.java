package com.gmail.davidcalle3141.ny.ttp_me.data;

import com.gmail.davidcalle3141.ny.ttp_me.data.Tweet.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.data.network.TwitterNetworkDataSource;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;

public class TweetRepo {

    private static final Object LOCK = new Object();
    private static TweetRepo sInstance;
    private final TwitterNetworkDataSource mTwitterNDS;
    private final AppExecutors mExecutors;

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

    //TODO add network methods


}
