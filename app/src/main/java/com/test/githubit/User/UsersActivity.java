package com.test.githubit.User;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import com.test.githubit.root.App;
import android.arch.lifecycle.Observer;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends AppCompatActivity {

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

    public void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
    }

    private void initRecyclerView() {
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

    private void subscribeUsersObserver() {

        usersViewModel.getUsers().observe(this, new Observer<List<ViewModelU>>() {
            @Override
            public void onChanged(@Nullable List<ViewModelU> viewModelList) {
                //update ui
                listAdapter.updateDataSet(viewModelList);
                hideProgressDialog();

            }
        });

    }

    public void showProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage("Loading...");
        } else {
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);

            try {
                progressDialog.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void hideProgressDialog() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog.hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
