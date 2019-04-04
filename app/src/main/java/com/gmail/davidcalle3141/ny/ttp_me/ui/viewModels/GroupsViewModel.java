package com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels;

import com.gmail.davidcalle3141.ny.ttp_me.data.GroupRepo;
import com.gmail.davidcalle3141.ny.ttp_me.data.TweetRepo;
import com.gmail.davidcalle3141.ny.ttp_me.data.database.Group;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroupsViewModel extends ViewModel {
    private final GroupRepo mRepo;
    private LiveData<List<Group>> mGroupList;
    private MutableLiveData<Group> mFocusedGroup;



    public GroupsViewModel(GroupRepo repo) {
        this.mRepo = repo;
        this.mGroupList = mRepo.getGroupList();
        this.mFocusedGroup = new MutableLiveData<>();
    }
    public LiveData<List<Group>> getGroupList(){
        return mGroupList;
    }
    public void setFocusedGroup(String hashtag){
        DateFormat df = new SimpleDateFormat("dd MM yyyy, HH:mm", Locale.US);
        String date = df.format(Calendar.getInstance().getTime());
        mFocusedGroup.setValue(new Group(hashtag,date));
    }
    public MutableLiveData<Group> getFocusedGroup(){
        return mFocusedGroup;
    }
    public void saveGroup(Group group){
        mRepo.addGroup(group);
    }
    public void deleteGroup(Group group){
        mRepo.deleteGroup(group);
    }
}
