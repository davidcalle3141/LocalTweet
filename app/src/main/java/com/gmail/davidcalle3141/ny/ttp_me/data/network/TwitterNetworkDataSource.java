package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import com.gmail.davidcalle3141.ny.ttp_me.data.Models.LocationModel;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class TwitterNetworkDataSource {
    private static final Object LOCK = new Object();
    private static TwitterNetworkDataSource sInstance;
    private final Context mContext;
    private final MutableLiveData<List<Tweet>> mDownloadedTweets;
    private final MutableLiveData<List<Tweet>> mDownloadedTweetsByHashtag;
    private final MutableLiveData<LocationModel> mLocation;
    private final AppExecutors mExecutors;
    private final NetworkUtils networkUtils;
    private final FusedLocationProviderClient mFusedLocationClient;


    public TwitterNetworkDataSource(Context context, AppExecutors executors){
        this.mContext = context;
        this.mExecutors = executors;
        this.mDownloadedTweets = new MutableLiveData<>();
        this.mDownloadedTweetsByHashtag = new MutableLiveData<>();
        this.networkUtils = new NetworkUtils(context);
        this.mLocation = new MutableLiveData<>();
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);

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
                twitterResponse = new TwitterJsonUtils().parseTwitterJson(twitterJSONData).getTweets();
                mDownloadedTweets.postValue(twitterResponse);

            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
    public void fetchTweetsByHashtag(String hashtag,String maxResults){
        mExecutors.networkIO().execute(()->{
            try{
                List<Tweet> twitterResponse;
                String twitterJSONData;
                networkUtils.buildURLByHashtag(hashtag,maxResults);
                twitterJSONData = networkUtils.getResponse();
                twitterResponse = new TwitterJsonUtils().parseTwitterJson(twitterJSONData).getTweets();
                mDownloadedTweetsByHashtag.postValue(twitterResponse);
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }
    public void fetchTweetsByUser(String str_id,String count){
        mExecutors.networkIO().execute(()->{
            try{
                List<Tweet> twitterResponse;
                String twitterJSONData;
                networkUtils.buildURLByUserID(str_id,count);
                twitterJSONData = networkUtils.getResponse();
                twitterResponse = new TwitterJsonUtils().parseUserTimeline(twitterJSONData).getTweets();
                mDownloadedTweets.postValue(twitterResponse);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }





    public LiveData<List<Tweet>> getTweets(){
        return mDownloadedTweets;
    }
    public LiveData<List<Tweet>> getSearchTweets(){
        return mDownloadedTweetsByHashtag;
    }



    public void fetchLocation(){
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(
                    location -> {
                        if(location != null){
                            LocationModel tempLocationModel = new LocationModel(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),"5");
                            mLocation.postValue(tempLocationModel);
                        }else{
                            fetchIPLocation();
                        }

                    }
            );

        }else {
            fetchIPLocation();
        }


    }

    private void fetchIPLocation(){
        mExecutors.networkIO().execute(()->{
            try {
                String response = networkUtils.getLocationViaIP();
                LocationModel locationModel = new LocationModel(locationFromIP(response,"latitude"),locationFromIP(response,"longitude"),"20");
                mLocation.postValue(locationModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private String locationFromIP(String string, String label) {
        double answer = 0;
        try {
            JSONObject jsonObject = new JSONObject(string);
            answer =  (double) jsonObject.get(label);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return String.valueOf(answer);
    }

    public LiveData<LocationModel> getLocation(){

        return mLocation;
    }

}
