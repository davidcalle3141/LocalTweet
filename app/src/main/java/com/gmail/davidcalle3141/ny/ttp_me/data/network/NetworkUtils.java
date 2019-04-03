package com.gmail.davidcalle3141.ny.ttp_me.data.network;

import android.content.Context;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import android.util.Base64;

import java.io.IOException;


public class NetworkUtils {
    private final String API_KEY;
    private final String API_SECRET;
    private final String ACCESS_KEY;
    private final String ACCESS_SECRET;
    private final String BEARER_TOKEN;
    private final String BASE_URL = "api.twitter.com";
    private final String QUERY_BASE_URL = "https://api.twitter.com/1.1/tweets/search/30day/TPPME.json";
    private final String USER_TIMELINE_BASE_URL = "https://api.twitter.com/1.1/statuses/user_timeline.json";
    private OkHttpClient client = new OkHttpClient();
    private HttpUrl.Builder urlBuilder;


    public NetworkUtils(Context context){
        API_KEY = context.getString(R.string.API_KEY);
        API_SECRET = context.getString(R.string.API_SECRET);
        ACCESS_KEY = context.getString(R.string.ACCESS_TOKEN);
        ACCESS_SECRET = context.getString(R.string.ACCESS_SECRET);
        BEARER_TOKEN = context.getString(R.string.BEARER_TOKEN);
    }

    public void buildURLByLocation(String latitude, String longitude, String distance, String maxResults ) {
        urlBuilder = HttpUrl.parse(QUERY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("query","point_radius:["+latitude+" "+longitude+" "+distance+"mi]");
        urlBuilder.addQueryParameter("maxResults",maxResults);

    }

    public void buildURLByHashtag(String hashtag,String maxResults){
        urlBuilder = HttpUrl.parse(QUERY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("query","#"+hashtag);
        urlBuilder.addQueryParameter("maxResults",maxResults);
    }

    public String buildURLByUserID(String userId, String count){
        urlBuilder = HttpUrl.parse(USER_TIMELINE_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("user_id",userId);
        urlBuilder.addQueryParameter("count",count);
        return null;
    }

    public void buildNextUrl(String next){
        urlBuilder.addQueryParameter("next",next);
    }
    public void buildNextTimelineUrl(String max_id){
        urlBuilder.addQueryParameter("max_id",max_id);
    }

    public String getResponse() throws IOException {
        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlBuilder.toString())
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+BEARER_TOKEN)
                .addHeader("cache-control", "no-cache")
                .build();
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        return string;

    }

    private String generateBearerToken() {
        String credentials = API_KEY+":"+API_SECRET;
        String base64 = android.util.Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP | Base64.URL_SAFE);
        urlBuilder =
                HttpUrl.parse("https://api.twitter.com/oauth2/token").newBuilder();
        urlBuilder.addQueryParameter("grant_type","client_credentials");
        client = new OkHttpClient();
        RequestBody body = RequestBody.create(null,"grant_type=client_credentials");
        Request request = new Request.Builder()
                .url("https://api.twitter.com/oauth2/token")
                .post(body)
                .addHeader("Authorization", "Basic "+base64)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();
        Call call = client.newCall(request);


        try {
            Response response = call.execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }
}
