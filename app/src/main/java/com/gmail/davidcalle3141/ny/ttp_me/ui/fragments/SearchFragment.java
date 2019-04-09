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
import android.widget.SearchView;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.ui.adapters.TweetAdapter;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.GroupsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.GroupsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.utils.InjectorUtils;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;


public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{
    private Context mContext;
    private View mView;
    private TweetAdapter mTweetAdapter;
    private TweetsViewModel mTweetViewModel;
    private TweetsVMFactory mTweetVMFactory;

    private GroupsViewModel mGroupsViewModel;
    private GroupsVMFactory mGroupsVMFactory;

    @BindView(R.id.search_fragment_search_bar)
    SearchView mSearchView;
    @BindView(R.id.Search_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_follow_button)
    MaterialButton mMaterialButton;
    private NavController mNavHostFragment;


    public SearchFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this.getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_search,container,false);
        ButterKnife.bind(this, mView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mNavHostFragment = NavHostFragment.findNavController(this);

        mTweetAdapter = new TweetAdapter(mContext, mNavHostFragment);
        mSearchView.setOnQueryTextListener(this);
        mRecyclerView.setAdapter(mTweetAdapter);


        mTweetVMFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        mTweetViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mTweetVMFactory).get(TweetsViewModel.class);

        mGroupsVMFactory = InjectorUtils.provideGroupFactory(mContext.getApplicationContext());
        mGroupsViewModel = ViewModelProviders.of(getActivity(),mGroupsVMFactory).get(GroupsViewModel.class);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateUI();
    }

    private void populateUI() {
        mTweetViewModel.getSearchTweets().observe(this, tweetList -> {
            if(tweetList!=null&&tweetList.size()>0){
                if(!mTweetViewModel.getHashtag().equals("")||mTweetViewModel.getHashtag()!=null) mSearchView.setQueryHint(mTweetViewModel.getHashtag());
                mTweetAdapter.addTweetList(tweetList);
                mTweetAdapter.notifyDataSetChanged();
                mMaterialButton.setVisibility(View.VISIBLE);

            }else{
                mMaterialButton.setVisibility(View.INVISIBLE);
            }


        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        mTweetViewModel.fetchTweetsByHashtag(s);
        mGroupsViewModel.setFocusedGroup(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //TODO autocomplete or suggestions
        return false;
    }

    @OnClick(R.id.search_follow_button)
    public void follow(View view) {
        mGroupsViewModel.getFocusedGroup().observe(this, group -> {
            mGroupsViewModel.saveGroup(group);
        });
    }
}
