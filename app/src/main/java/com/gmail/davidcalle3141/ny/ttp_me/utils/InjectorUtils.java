package com.gmail.davidcalle3141.ny.ttp_me.utils;

import android.content.Context;

import com.gmail.davidcalle3141.ny.ttp_me.data.GroupRepo;
import com.gmail.davidcalle3141.ny.ttp_me.data.TweetRepo;
import com.gmail.davidcalle3141.ny.ttp_me.data.database.Database;
import com.gmail.davidcalle3141.ny.ttp_me.data.network.TwitterNetworkDataSource;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.GroupsVMFactory;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.ViewModelFactories.TweetsVMFactory;

public class InjectorUtils {
    private static TweetRepo provideTweetRepo(Context context){
        AppExecutors executors = AppExecutors.getInstance();
        TwitterNetworkDataSource twitterNetworkDataSource =
                TwitterNetworkDataSource.getInstance(context.getApplicationContext(),executors);
        return TweetRepo.getInstance(twitterNetworkDataSource,executors);
    }

    private static GroupRepo provideGroupRepo(Context context){
        Database database = Database.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return GroupRepo.getInstance(database.groupDao(),executors);
    }

    public static TwitterNetworkDataSource provideNetworkDataSource(Context context){
        provideTweetRepo(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        return TwitterNetworkDataSource.getInstance(context.getApplicationContext(),executors);
    }
    public static GroupsVMFactory provideGroupFactory(Context context){
        GroupRepo repo = provideGroupRepo(context.getApplicationContext());
        return new GroupsVMFactory(repo);
    }

    public static TweetsVMFactory provideTweetFactory(Context context){
        TweetRepo repo = provideTweetRepo(context.getApplicationContext());
        return new TweetsVMFactory(repo);
    }
}
