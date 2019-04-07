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
import androidx.recyclerview.widget.RecyclerView;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {
    private final TweetAdapterOnClickListener onTweetClickListener;
    private Context context;
    private List<Tweet> tweetList;

    public TweetAdapter(Context context, TweetAdapterOnClickListener listener){
        this.context = context;
        this.onTweetClickListener = listener;
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
        return new TweetViewHolder(view,onTweetClickListener);
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
        profilePic = profilePic.replace("normal","bigger");
        Picasso.get().load(profilePic).into(holder.profilePic);
        if(media!= null){
            Picasso.get().load(media).into(holder.media);
            holder.media.setVisibility(View.VISIBLE);
        } else{
            holder.media.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount(){
        return tweetList.size();
    }

    public interface TweetAdapterOnClickListener{
        void onItemClick(int position);
    }
}
