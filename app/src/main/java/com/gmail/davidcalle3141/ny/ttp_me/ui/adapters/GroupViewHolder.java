package com.gmail.davidcalle3141.ny.ttp_me.ui.adapters;

import android.view.View;
import android.widget.TextView;

import com.gmail.davidcalle3141.ny.ttp_me.R;
import com.gmail.davidcalle3141.ny.ttp_me.data.database.Group;
import com.gmail.davidcalle3141.ny.ttp_me.ui.viewModels.GroupsViewModel;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.group_view_name)
    TextView name;
    @BindView(R.id.gourp_view_date)
    TextView date;
    Group group;
    private GroupsViewModel groupsViewModel;

    private GroupAdapter.GroupAdapterOnClickListener groupAdapterOnClickListener;

    public GroupViewHolder(View itemView, GroupAdapter.GroupAdapterOnClickListener listener, GroupsViewModel groupsViewModel){
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
        this.groupsViewModel = groupsViewModel;
        this.groupAdapterOnClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(groupAdapterOnClickListener != null){
            groupAdapterOnClickListener.onItemClick(getAdapterPosition());
        }

    }
    @OnClick(R.id.group_view_button)
    public void unfollow(View view) {
        groupsViewModel.deleteGroup(group);
    }
}
