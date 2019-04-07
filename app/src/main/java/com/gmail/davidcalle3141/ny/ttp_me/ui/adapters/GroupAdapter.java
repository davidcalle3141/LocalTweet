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
    private final GroupAdapterOnClickListener onGroupClickListener;
    private Context context;
    private GroupsViewModel groupsViewModel;
    private List<Group> groupList;

    public GroupAdapter(Context context, GroupAdapterOnClickListener listener, GroupsViewModel groupsViewModel){
        this.groupsViewModel = groupsViewModel;
        this.context = context;
        this.onGroupClickListener = listener;
        groupList = new ArrayList<>();
    }
    public void addGroups(List<Group> groups){
        this.groupList.clear();
        this.groupList.addAll(groups);
    }

    public Group getGroup(int index){
        return groupList.get(index);
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_view,parent,false);
        return new GroupViewHolder(view, onGroupClickListener,groupsViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.group = groupList.get(position);
        holder.name.setText(groupList.get(position).getHashtag());
        holder.date.setText(groupList.get(position).getDate_created());

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }



    public interface GroupAdapterOnClickListener {
        void onItemClick(int position);
    }
}
