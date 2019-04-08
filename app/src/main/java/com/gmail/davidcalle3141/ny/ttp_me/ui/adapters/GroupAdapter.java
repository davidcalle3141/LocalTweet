package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.database.Group;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.GroupsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {
    private final GroupAdapterOnClickListener mOnGroupClickListener;
    private Context mContext;
    private GroupsViewModel mGroupsViewModel;
    private List<Group> mGroupList;

    public GroupAdapter(Context context, GroupAdapterOnClickListener listener, GroupsViewModel groupsViewModel){
        this.mGroupsViewModel = groupsViewModel;
        this.mContext = context;
        this.mOnGroupClickListener = listener;
        mGroupList = new ArrayList<>();
    }
    public void addGroups(List<Group> groups){
        this.mGroupList.clear();
        this.mGroupList.addAll(groups);
    }

    public Group getGroup(int index){
        return mGroupList.get(index);
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_view,parent,false);
        return new GroupViewHolder(view, mOnGroupClickListener, mGroupsViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.mGroup = mGroupList.get(position);
        holder.mName.setText(mGroupList.get(position).getHashtag());
        holder.mDate.setText(mGroupList.get(position).getDate_created());

    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }



    public interface GroupAdapterOnClickListener {
        void onItemClick(int position);
    }
}
