package com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels;

import com.gmail.davidcalle3141.ny.ttp_me.data.database.Group;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GroupsViewModel extends ViewModel {
    private LiveData<List<Group>> mGroupList;
    private MutableLiveData<Group> mFocusedGroup;


}
