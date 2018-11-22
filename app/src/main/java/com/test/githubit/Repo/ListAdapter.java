package com.test.githubit.Repo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.test.githubit.Forks.ForksActivity;
import com.test.githubit.R;
import com.test.githubit.http.apimodel.Repo;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {

    private List<Repo> list;
    private Context mContext;
    private String username;

    public ListAdapter(Context mContext, List<Repo> reposList, String username) {
        this.list = reposList;
        this.mContext = mContext;
        this.username = username;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_row, parent, false);
        return new ListItemViewHolder(itemView,username);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {

        holder.repoName.setText(list.get(position).getName());
        if(list.get(position).getDescription()!=null)
        holder.repoDescription.setText(list.get(position).getDescription());
        else{
            holder.repoDescription.setText(mContext.getResources().getString( R.string.not_found));
        }
        if(list.get(position).getLicense()==null)
            holder.licenseName.setText(mContext.getResources().getString( R.string.not_found));
        else{
            holder.licenseName.setText(list.get(position).getLicense().getName());
        }

        holder.noOfFork.setText(String.valueOf(list.get(position).getForks()));
        holder.fork.setText(mContext.getResources().getString( R.string.forks));
        holder.license.setText(mContext.getResources().getString( R.string.license));
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ListItemViewHolder extends RecyclerView.ViewHolder {

        private String username;
        @BindView(R.id.textView_repo_name) TextView repoName;
        @BindView(R.id.textView_repo_description) TextView repoDescription;
        @BindView(R.id.textView_fork) TextView fork;
        @BindView(R.id.textView_num_of_fork) TextView noOfFork;
        @BindView(R.id.textView_license) TextView license;
        @BindView(R.id.textView_license_name) TextView licenseName;

        @OnClick
        void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("repo", repoName.getText().toString());
            bundle.putString("username", username);
            Intent intent = new Intent(view.getContext(), ForksActivity.class);
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            view.getContext().startActivity(intent);
        }

        public ListItemViewHolder(View itemView, String username) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.username = username;
        }
    }
}

