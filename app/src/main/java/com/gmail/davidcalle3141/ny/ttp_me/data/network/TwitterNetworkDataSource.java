package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import android.content.Context;

import com.gmail.davidcalle3141.ny.ttp_me.data.Tweet.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;
import com.google.android.exoplayer2.BuildConfig;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TwitterNetworkDataSource {
    private static final Object LOCK = new Object();
    private static TwitterNetworkDataSource sInstance;
    private final Context mContext;
    private final MutableLiveData<List<Tweet>> mDownloadedTweets;
    private final AppExecutors mExecutors;
    private final NetworkUtils networkUtils;



    public TwitterNetworkDataSource(Context context, AppExecutors executors){
        this.mContext = context;
        this.mExecutors = executors;
        this.mDownloadedTweets = new MutableLiveData<List<Tweet>>();
        this.networkUtils = new NetworkUtils(context);
    }

    public static TwitterNetworkDataSource getInstance(Context context, AppExecutors executors){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new TwitterNetworkDataSource(context.getApplicationContext(),executors);

            }
        }
        return sInstance;
    }
    public void fetchTweetByLocation(String latitude, String longitude, String distance, String maxResults){
        mExecutors.networkIO().execute(()->{
            try{
                List<Tweet> twitterResponse;
                String twitterJSONData;
                networkUtils.buildURLByLocation(latitude,longitude,distance,maxResults);
                twitterJSONData = networkUtils.getResponse();
                twitterResponse = new TwitterJsonUtils().parseTwitterJson(twitterJSONData);
                mDownloadedTweets.postValue(twitterResponse);

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    void fetchTweetsByHashtag(String hashtag,String maxResults){
        mExecutors.networkIO().execute(()->{
            try{
                List<Tweet> twitterResponse;
                String twitterJSONData;
                networkUtils.buildURLByHashtag(hashtag,maxResults);
                twitterJSONData = networkUtils.getResponse();
                twitterResponse = new TwitterJsonUtils().parseTwitterJson(twitterJSONData);
                mDownloadedTweets.postValue(twitterResponse);
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }
    void fetchTweetsByUser(String str_id,String count){
        mExecutors.networkIO().execute(()->{
            try{
                List<Tweet> twitterResponse;
                String twitterJSONData;
                networkUtils.buildURLByUserID(str_id,count);
                twitterJSONData = networkUtils.getResponse();
                twitterResponse = new TwitterJsonUtils().parseTwitterJson(twitterJSONData);
                mDownloadedTweets.postValue(twitterResponse);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }


}
