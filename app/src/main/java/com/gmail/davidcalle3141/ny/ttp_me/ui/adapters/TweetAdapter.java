package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

public class TweetAdapter extends RecyclerView.Adapter<TweetViewHolder> {
    private Context context;
    private List<Tweet> tweetList;
    private NavController navHostFragment;
    private TweetViewHolder holder;
    private Boolean profileButtonClickable = true;

    public TweetAdapter(Context context, NavController navHostFragment) {
        this.context = context;
        this.navHostFragment = navHostFragment;
        tweetList = new ArrayList<>();
    }

    public void addTweetList(List<Tweet> tweetList) {
        this.tweetList.clear();
        this.tweetList.addAll(tweetList);
    }

    @NonNull
    @Override
    public TweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_view, parent, false);
        return new TweetViewHolder(view, navHostFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetViewHolder holder, int position) {
        this.holder = holder;
        String profilePic;
        holder.screenName.setText(tweetList.get(position).getUser().getScreen_name());
        holder.name.setText(tweetList.get(position).getUser().getName());
        holder.tweet.setText(tweetList.get(position).getText());
        holder.timeCreated.setText(tweetList.get(position).getCreated_at());
        profilePic = tweetList.get(position).getUser().getProfile_image_url_https()
                .replace("_normal", "");
        Picasso.get().load(profilePic).into(holder.profilePic);
        if (tweetList.get(position).getMedia_url_https() != null) {
            Picasso.get().load(tweetList.get(position).getMedia_url_https()).into(holder.media);
            holder.media.setVisibility(View.VISIBLE);
        } else {
            holder.media.setVisibility(View.GONE);
        }
        holder.userId = tweetList.get(position).getUser().getId_str();
        if (!profileButtonClickable) holder.disableButtons();
    }

    public void disableProfileButton() {
        profileButtonClickable = false;
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

}
