package com.test.githubit.Repo;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.test.githubit.Base.BaseActivity;
import com.test.githubit.R;
import com.test.githubit.User.ViewModelU;
import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.Repo.Repo;
import com.test.githubit.root.App;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends BaseActivity<Repo> {

  private final String TAG = ReposActivity.class.getName();

  private ReposViewModel reposViewModel;

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.listActivity_rootView)
  ViewGroup rootView;

  @Inject
  UserApiService userApiService;

  ProgressDialog progressDialog;

  private ListAdapter listAdapter;
  private List<Repo> reposList =new ArrayList<>();
  private String login;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.users_activity);

    reposViewModel = ViewModelProviders.of(this).get(ReposViewModel.class);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    login = bundle.getString("login");

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ButterKnife.bind(this);

    initProgressDialog();

    initRecyclerView();

    showProgressDialog();

    subscribeUsersObserver();
  }

  @Override
  protected void initRecyclerView() {
    listAdapter = new ListAdapter(getApplicationContext(),reposList,login);
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

    reposViewModel.getRepos(login).observe(this, new Observer<List<Repo>>() {
      @Override
      public void onChanged(@Nullable List<Repo> reposList) {
        listAdapter.updateDataSet(reposList);
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
