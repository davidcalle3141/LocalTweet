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
    private View view;
    private TweetAdapter tweetAdapter;
    private TweetsViewModel mTweetViewModel;
    private TweetsVMFactory mTweetVMFactory;

    private boolean mCanSave = false;

    private GroupsViewModel mGroupsViewModel;
    private GroupsVMFactory mGroupsVMFactory;

    @BindView(R.id.search_fragment_search_bar)
    SearchView searchView;
    @BindView(R.id.Search_rv)
    RecyclerView recyclerView;
    @BindView(R.id.search_follow_button)
    MaterialButton button;
    private NavController navhostFragment;


    public SearchFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this.getContext();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_search,container,false);
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);

        navhostFragment = NavHostFragment.findNavController(this);

        tweetAdapter = new TweetAdapter(mContext,navhostFragment);
        searchView.setOnQueryTextListener(this);
        recyclerView.setAdapter(tweetAdapter);


        mTweetVMFactory = InjectorUtils.provideTweetFactory(mContext.getApplicationContext());
        mTweetViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()), mTweetVMFactory).get(TweetsViewModel.class);

        mGroupsVMFactory = InjectorUtils.provideGroupFactory(mContext.getApplicationContext());
        mGroupsViewModel = ViewModelProviders.of(getActivity(),mGroupsVMFactory).get(GroupsViewModel.class);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateUI();
    }

    private void populateUI() {
        mTweetViewModel.getSearchTweets().observe(this, tweetList -> {
            if(tweetList!=null&&tweetList.size()>0){
                if(!mTweetViewModel.getHashtag().equals("")) searchView.setQueryHint(mTweetViewModel.getHashtag());
                tweetAdapter.addTweetList(tweetList);
                tweetAdapter.notifyDataSetChanged();
                button.setVisibility(View.VISIBLE);

            }else{
                button.setVisibility(View.INVISIBLE);
            }


        });
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        mTweetViewModel.fetchTweetsByHashtag(s);
        mGroupsViewModel.setFocusedGroup(s);
        mCanSave = true;
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        //TODO autocomplete or suggestions
        return false;
    }

    @OnClick(R.id.search_follow_button)
    public void follow(View view) {
        mGroupsViewModel.getFocusedGroup().observe(this, group -> mGroupsViewModel.saveGroup(group));
    }
}
