package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;

import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class TweetViewHolder extends RecyclerView.ViewHolder  {
    @BindView(R.id.tweet_author_profile_pic) public CircleImageView mProfilePic;
    @BindView(R.id.tweet_author_handle) public TextView mScreenName;
    @BindView(R.id.tweet_author_name)   public TextView mName;
    @BindView(R.id.tweet_tweet) public TextView mTweet;
    @BindView(R.id.tweet_time_created)  public TextView mTimeCreated;
    @BindView(R.id.tweet_media_image_or_gif) public ImageView mMedia;
    String mUserID;
    private Boolean mProfileButtonsClickable =true;
    private NavController mNavHostFragment;

    public TweetViewHolder(View itemView, NavController navHostFragment){
        super(itemView);
        ButterKnife.bind(this,itemView);
        this.mNavHostFragment = navHostFragment;
    }

    //disables onProfileClick and onName click when in the profileFragment
    public void disableButtons(){
        mProfileButtonsClickable = false;
    }


    @OnClick(R.id.tweet_author_profile_pic)
    public void onProfileClick(){
        if(mProfileButtonsClickable){
        Bundle bundle = new Bundle();
        bundle.putString("user_id", mUserID);
        mNavHostFragment.navigate(R.id.action_global_profileFragment,bundle);}

    }
    @OnClick(R.id.tweet_author_name)
    public void onNameClick(){
        if(mProfileButtonsClickable){

            Bundle bundle = new Bundle();
        bundle.putString("user_id", mUserID);
        mNavHostFragment.navigate(R.id.action_global_profileFragment,bundle);}
    }
}
