package com.gmail.davidcalle3141.ny.ttp_me.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.ui.adapters.TweetAdapter;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.utils.InjectorUtils;

import java.util.Objects;

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


public class GroupDetailFragment extends Fragment {

    private Context mContext;
    private View mView;
    private TweetAdapter mTweetAdapter;
    private TweetsVMFactory mTweetVMFactory;
    private TweetsViewModel mTweetViewModel;


    @BindView(R.id.group_detail_header_text)
    TextView mHeaderTextView;
    @BindView(R.id.group_detail_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.group_detail_header_back_button)
    ImageButton mBackButton;
    private NavController mNavHostFragment;

    public GroupDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContext = getContext();
        this.mView = inflater.inflate(R.layout.fragment_group_detail, container, false);
        ButterKnife.bind(this, mView);
        mNavHostFragment = NavHostFragment.findNavController(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mTweetAdapter = new TweetAdapter(mContext, mNavHostFragment);
        mRecyclerView.setAdapter(mTweetAdapter);

        mTweetVMFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        mTweetViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mTweetVMFactory).get(TweetsViewModel.class);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateUI();
    }

    private void populateUI() {
        assert getArguments() != null;
        String hashTag = getArguments().getString("hashtag");
        mHeaderTextView.setText(hashTag);
        mTweetViewModel.fetchTweetsByHashtag(hashTag);
        mTweetViewModel.getSearchTweets().observe(this, tweetList -> {
            if (tweetList != null) {
                mTweetAdapter.addTweetList(tweetList);
                mTweetAdapter.notifyDataSetChanged();
            }
        });
    }


    @OnClick(R.id.group_detail_header_back_button)
    public void onBackButtonPressed() {
        mNavHostFragment.popBackStack();
    }
}
