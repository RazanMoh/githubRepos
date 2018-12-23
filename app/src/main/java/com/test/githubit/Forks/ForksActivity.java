package com.test.githubit.Forks;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.test.githubit.Base.BaseActivity;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForksActivity extends BaseActivity<ViewModelF> {

  private final String TAG = ForksActivity.class.getName();

  private ForksViewModel forksViewModel;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.listActivity_rootView)
  ViewGroup rootView;

  @Inject
  UserApiService userApiService;

  private ListAdapter listAdapter;
  private List<ViewModelF> usersList =new ArrayList<>();
  private String username, repo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.users_activity);

    forksViewModel = ViewModelProviders.of(this).get(ForksViewModel.class);
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    username = bundle.getString("username");
    repo = bundle.getString("repo");

    ButterKnife.bind(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    forksViewModel.getForkUsers(username, repo).observe(this, new Observer<List<ViewModelF>>() {
      @Override
      public void onChanged(@Nullable List<ViewModelF> viewModelList) {
        //update ui
        listAdapter.updateDataSet(viewModelList);
        hideProgressDialog();

      }
    });

  }
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
