package com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories;

import com.gmail.davidcalle3141.ny.ttp_me.data.GroupRepo;
import com.gmail.davidcalle3141.ny.ttp_me.data.TweetRepo;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.GroupsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GroupsVMFactory extends ViewModelProvider.NewInstanceFactory {
    private final GroupRepo mRepo;
    public GroupsVMFactory(GroupRepo repo){
        this.mRepo = repo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GroupsViewModel(mRepo);
    }
}
