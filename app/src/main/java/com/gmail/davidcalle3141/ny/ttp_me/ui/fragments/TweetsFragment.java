package com.gmail.davidcalle3141.ny.ttp_me.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.Models.LocationModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.adapters.TweetAdapter;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.utils.InjectorUtils;


import java.util.Objects;


public class TweetsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private View mView;
    private Context mContext;
    private TweetsViewModel mViewModel;
    private TweetsVMFactory mFactory;
    private NavController mNavHostFragment;

    private TweetAdapter mTweetAdapter;

    @BindView(R.id.tweets_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.tweet_fragment_swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;


    public TweetsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_tweets, container, false);
        ButterKnife.bind(this, mView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mNavHostFragment = NavHostFragment.findNavController(this);

        mTweetAdapter = new TweetAdapter(mContext, mNavHostFragment);
        mRecyclerView.setAdapter(mTweetAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        return mView;
    }

    //callback to location permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mFactory).get(TweetsViewModel.class);
        mViewModel.getLocation().observe(this, locationModel -> {
            if (locationModel != null) {
                populateUI(locationModel);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        checkPermission();


    }

    private void populateUI(LocationModel locationModel) {
        mViewModel.fetchLocalTweets(locationModel.getLatitude(), locationModel.getLongitude(), locationModel.getDistance());
        mViewModel.getLocationTweets().observe(this, tweets -> {
            if (tweets != null) {
                mTweetAdapter.addTweetList(tweets);
                mTweetAdapter.notifyDataSetChanged();

            }
        });

    }

    //checks location permissions if not available or if denied finds location via IP
    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            mViewModel = ViewModelProviders.of(this, mFactory).get(TweetsViewModel.class);
            mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mFactory).get(TweetsViewModel.class);
            mViewModel.getLocation().observe(this, locationModel -> {
                if (locationModel != null) {
                    populateUI(locationModel);
                }
            });
        }
    }


    //on swipe to refresh location is refreshed
    @Override
    public void onRefresh() {
        mViewModel.updateLocation();
        mSwipeRefreshLayout.setRefreshing(false);

    }


}

