package com.test.githubit.Forks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.test.githubit.R;
import com.test.githubit.http.apimodel.Repo.Repo;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<ViewModelF> list;
    private Context mContext;

    public ListAdapter(Context mContext, List<ViewModelF> list) {
        this.list = list;
        this.mContext = mContext;
    }

    public void updateDataSet(List<ViewModelF> list){
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_row, parent, false);
        return new ListItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {

        holder.userName.setText(list.get(position).getName());
        holder.noOfRepos.setText(list.get(position).getPublicRepos()+"");
        holder.noOfFollowers.setText(list.get(position).getFollowers()+"");
        holder.repos.setText(mContext.getResources().getString( R.string.repositories));
        holder.followers.setText(mContext.getResources().getString( R.string.followers));
        Glide.with(mContext).load(list.get(position).getAvatar()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView_user_name) TextView userName;
        @BindView(R.id.textView_num_of_repos) TextView noOfRepos;
        @BindView(R.id.textView_num_of_followers) TextView noOfFollowers;
        @BindView(R.id.textView_repos) TextView repos;
        @BindView(R.id.textView_followers) TextView followers;
        @BindView(R.id.imageView_user_avatar) ImageView avatar;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}