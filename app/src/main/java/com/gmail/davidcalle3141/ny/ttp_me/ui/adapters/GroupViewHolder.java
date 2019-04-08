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
    TextView mName;
    @BindView(R.id.gourp_view_date)
    TextView mDate;
    Group mGroup;
    private GroupsViewModel mGroupViewModel;

    private GroupAdapter.GroupAdapterOnClickListener mGroupAdapterOnClickListener;

    public GroupViewHolder(View itemView, GroupAdapter.GroupAdapterOnClickListener listener, GroupsViewModel groupsViewModel){
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
        this.mGroupViewModel = groupsViewModel;
        this.mGroupAdapterOnClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if(mGroupAdapterOnClickListener != null){
            mGroupAdapterOnClickListener.onItemClick(getAdapterPosition());
        }

    }
    @OnClick(R.id.group_view_button)
    public void unFollow(View view) {
        mGroupViewModel.deleteGroup(mGroup);
    }
}
