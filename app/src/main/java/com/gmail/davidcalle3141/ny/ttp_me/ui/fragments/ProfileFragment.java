package com.gmail.davidcalle3141.ny.ttp_me.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.User;
import com.gmail.davidcalle3141.ny.ttp_me.ui.adapters.TweetAdapter;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.utils.InjectorUtils;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private Context mContext;
    private View mView;
    private TweetAdapter mTweetAdapter;
    private TweetsViewModel mTweetsViewModel;
    private TweetsVMFactory mTweetsFactory;

    @BindView(R.id.profile_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.profile_header_name)
    TextView mHeaderName;
    @BindView(R.id.profile_header_followers)
    TextView mHeaderFollowers;
    @BindView(R.id.profile_header_following)
    TextView mHeaderFollowing;
    @BindView(R.id.profile_header_profile_pic)
    ImageView mHeaderProfilePic;
    @BindView(R.id.profile_header_location)
    TextView mHeaderLocation;
    @BindView(R.id.profile_header_screen_name)
    TextView mHeaderScreenName;
    private NavController mNavHostFragment;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_profile, container, false);
        this.mContext = getContext();
        ButterKnife.bind(this, mView);
        mNavHostFragment = NavHostFragment.findNavController(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTweetAdapter = new TweetAdapter(mContext, mNavHostFragment);
        mRecyclerView.setAdapter(mTweetAdapter);

        mTweetsFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        mTweetsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mTweetsFactory).get(TweetsViewModel.class);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateUI();
    }

    private void populateUI() {


        assert getArguments() != null;
        String userID = getArguments().getString("user_id");
        mTweetsViewModel.fetchTweetsByUser(userID);
        mTweetsViewModel.getUserTimeline().observe(this, tweetList -> {
            User user;
            if (tweetList != null) {
                mTweetAdapter.disableProfileButton();
                mTweetAdapter.addTweetList(tweetList);
                mTweetAdapter.notifyDataSetChanged();

                user = tweetList.get(0).getUser();
                String profilePic = user.getProfile_image_url_https();
                profilePic = profilePic.replace("_normal", "");
                Picasso.get().load(profilePic)
                        .into(mHeaderProfilePic);
                mHeaderFollowers.setText(user.getFollowers_count());
                mHeaderFollowing.setText(user.getFriends_count());
                mHeaderName.setText(user.getName());
                mHeaderScreenName.setText(user.getScreen_name());
                mHeaderLocation.setText(user.getLocation());


            }
        });
    }


    @OnClick(R.id.profile_header_back_button)
    public void onBackButtonPressed() {
        mNavHostFragment.popBackStack();
    }
}
