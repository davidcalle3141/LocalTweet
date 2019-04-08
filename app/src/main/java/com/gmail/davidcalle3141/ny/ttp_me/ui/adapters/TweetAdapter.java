package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;

import com.bumptech.glide.Glide;
import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {
    private Context context;
    private List<Tweet> tweetList;
    private NavController navHostFragment;

    public TweetAdapter(Context context, NavController navHostFragment){
        this.context = context;
        this.navHostFragment=navHostFragment;
        tweetList = new ArrayList<>();
    }
    public void addTweetList(List<Tweet> tweetList){
        this.tweetList.clear();
        this.tweetList.addAll(tweetList);
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_view,parent,false);
        return new TweetViewHolder(view,navHostFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder,int position){
        String profilePic = tweetList.get(position).getUser().getProfile_image_url_https();
        String authorName = tweetList.get(position).getUser().getName();
        String screenName = tweetList.get(position).getUser().getScreen_name();
        String timeSincePosted = tweetList.get(position).getCreated_at();
        String tweet = tweetList.get(position).getText();
        String media = tweetList.get(position).getMedia_url_https();
        tweet = tweet.replace("&amp;","&");
        holder.screenName.setText(screenName);
        holder.name.setText(authorName);
        holder.tweet.setText(tweet);
        holder.timeCreated.setText(timeSincePosted);
        profilePic = profilePic.replace("_normal","");
        Picasso.get().load(profilePic).into(holder.profilePic);
        if(media!= null){
            Picasso.get().load(media).into(holder.media);
            holder.media.setVisibility(View.VISIBLE);
        } else{
            holder.media.setVisibility(View.GONE);
        }

        holder.userId = tweetList.get(position).getUser().getId_str();

    }

    @Override
    public int getItemCount(){
        return tweetList.size();
    }

}
