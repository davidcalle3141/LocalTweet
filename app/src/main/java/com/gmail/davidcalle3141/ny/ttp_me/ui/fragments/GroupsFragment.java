package com.gmail.davidcalle3141.ny.ttp_me.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.ui.adapters.GroupAdapter;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.GroupsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.GroupsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.utils.InjectorUtils;

import java.util.Objects;


public class GroupsFragment extends Fragment implements GroupAdapter.GroupAdapterOnClickListener {
    private Context mContext;
    private View view;
    private GroupAdapter groupAdapter;
    private GroupsViewModel groupsViewModel;
    private GroupsVMFactory groupsVMFactory;



    @BindView(R.id.groups_rv)
    RecyclerView recyclerView;
    private NavController navController;
    private NavController navhostFragment;

    public GroupsFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this.getContext();

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_groups,container,false);
        ButterKnife.bind(this,view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);



        groupsVMFactory = InjectorUtils.provideGroupFactory(mContext.getApplicationContext());
        groupsViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()),groupsVMFactory).get(GroupsViewModel.class);
        groupAdapter = new GroupAdapter(mContext,this,groupsViewModel);
        recyclerView.setAdapter(groupAdapter);
        navhostFragment = NavHostFragment.findNavController(this);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        populateUI();
    }

    private void populateUI() {
        groupsViewModel.getGroupList().observe(this,groups -> {
            if(groups!= null){
                groupAdapter.addGroups(groups);
                groupAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Log.d("GroupFragment","clicked");
        Bundle bundle = new Bundle();
        bundle.putString("hashtag",groupAdapter.getGroup(position).getHashtag());
        navhostFragment.navigate(R.id.action_groupsFragment_to_groupDetailFragment,bundle);

    }
}
