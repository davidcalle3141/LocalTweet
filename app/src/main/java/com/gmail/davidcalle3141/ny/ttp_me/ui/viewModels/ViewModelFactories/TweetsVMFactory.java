package com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories;

import com.gmail.davidcalle3141.ny.ttp_me.data.TweetRepo;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.TweetsViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TweetsVMFactory extends ViewModelProvider.NewInstanceFactory {
    private final TweetRepo mRepo;
    public TweetsVMFactory(TweetRepo repo){
        this.mRepo = repo;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TweetsViewModel(mRepo);
    }
}
