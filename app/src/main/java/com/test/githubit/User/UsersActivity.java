package com.test.githubit.User;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.test.githubit.Base.BaseActivity;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import android.arch.lifecycle.Observer;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends BaseActivity<ViewModelU> {

    private final String TAG = UsersActivity.class.getName();

    private UsersViewModel usersViewModel;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @Inject
    UserApiService userApiService;

    ProgressDialog progressDialog;

    private ListAdapter listAdapter;
    private List<ViewModelU> usersList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        ButterKnife.bind(this);

        initProgressDialog();

        initRecyclerView();

        showProgressDialog();

        subscribeUsersObserver();
    }

    @Override
    protected void initRecyclerView() {
        listAdapter = new ListAdapter(getApplicationContext(),usersList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void subscribeUsersObserver() {

        usersViewModel.getUsers().observe(this, new Observer<List<ViewModelU>>() {
            @Override
            public void onChanged(@Nullable List<ViewModelU> viewModelList) {
                listAdapter.updateDataSet(viewModelList);
                hideProgressDialog();

            }
        });

    }

}
