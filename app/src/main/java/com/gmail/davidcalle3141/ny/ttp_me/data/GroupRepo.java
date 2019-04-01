package com.gmail.davidcalle3141.ny.ttp_me.data;

import com.gmail.davidcalle3141.ny.ttp_me.data.database.Group;
import com.gmail.davidcalle3141.ny.ttp_me.data.database.GroupDao;
import com.gmail.davidcalle3141.ny.ttp_me.utils.AppExecutors;

import java.util.List;

import androidx.lifecycle.LiveData;

public class GroupRepo {
    private static final Object LOCK = new Object();
    private static GroupRepo sInstance;
    private final GroupDao mGroupDao;
    private final AppExecutors mExecutors;


    private GroupRepo(GroupDao groupDao, AppExecutors executors){
        this.mExecutors = executors;
        this.mGroupDao = groupDao;

    }

    public synchronized static GroupRepo getInstance(GroupDao groupDao,
                                                     AppExecutors executors){
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = new GroupRepo(groupDao,executors);
            }
        }
        return sInstance;
    }

    public LiveData<List<Group>> getGroupList(){
        return mGroupDao.getAll();
    }

    public void deleteGroup(Group group){
        mGroupDao.delete(group);
    }
    public void addGroup(Group group){
        mExecutors.diskIO().execute(()-> mGroupDao.insert(group));
    }



}
