package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class TweetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tweet_author_profile_pic) public CircleImageView profilePic;
    @BindView(R.id.tweet_author_handle) public TextView screenName;
    @BindView(R.id.tweet_author_name)   public TextView name;
    @BindView(R.id.tweet_tweet) public TextView tweet;
    @BindView(R.id.tweet_time_created)  public TextView timeCreated;
    @BindView(R.id.tweet_media_image_or_gif) public ImageView media;
    private TweetAdapter.TweetAdapterOnClickListener tweetAdapterOnClickListener;

    public TweetViewHolder(View itemView, TweetAdapter.TweetAdapterOnClickListener listener){
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
        this.tweetAdapterOnClickListener = listener;
    }

    @Override
    public void onClick(View v){
        if(tweetAdapterOnClickListener != null){
            tweetAdapterOnClickListener.onItemClick(getAdapterPosition());
        }
    }
}
