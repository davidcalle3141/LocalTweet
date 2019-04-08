package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class TweetViewHolder extends RecyclerView.ViewHolder  {
    @BindView(R.id.tweet_author_profile_pic) public CircleImageView profilePic;
    @BindView(R.id.tweet_author_handle) public TextView screenName;
    @BindView(R.id.tweet_author_name)   public TextView name;
    @BindView(R.id.tweet_tweet) public TextView tweet;
    @BindView(R.id.tweet_time_created)  public TextView timeCreated;
    @BindView(R.id.tweet_media_image_or_gif) public ImageView media;
    String userId;
    Boolean ProfileButtonsClickable =true;
    private NavController navHostFragment;

    public TweetViewHolder(View itemView, NavController navHostFragment){
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.navHostFragment = navHostFragment;
    }

    public void disableButtons(){
        ProfileButtonsClickable = false;
    }


    @OnClick(R.id.tweet_author_profile_pic)
    public void onProfileClick(){
        if(ProfileButtonsClickable){
        Bundle bundle = new Bundle();
        bundle.putString("user_id",userId);
        navHostFragment.navigate(R.id.action_global_profileFragment,bundle);}

    }
    @OnClick(R.id.tweet_author_name)
    public void onNameClick(){
        if(ProfileButtonsClickable){

            Bundle bundle = new Bundle();
        bundle.putString("user_id",userId);
        navHostFragment.navigate(R.id.action_global_profileFragment,bundle);}
    }
}
