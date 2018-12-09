package com.test.githubit.Repo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.test.githubit.Base.BaseActivity;
import com.test.githubit.R;
import com.test.githubit.root.App;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposActivity extends BaseActivity<ViewModel> implements ReposActivityMVP.View {

  private final String TAG = ReposActivity.class.getName();
  private static final String STATE_LIST = "State Adapter Data";

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.listActivity_rootView)
  ViewGroup rootView;

  ProgressDialog progressDialog;

  @Inject
  ReposActivityMVP.Presenter presenter;

  private ArrayListAdapter arrayListAdapter;
  private ArrayList<ViewModel> reposList = new ArrayList<>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.repos_activity);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    //((App) getApplication()).getReposComponent().inject(this);
    initInjector();

    ButterKnife.bind(this);

    //progressDialog = new ProgressDialog(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    //presenter.setView(this);

    initViews();

    if (savedInstanceState == null) {
      presenter.loadData(bundle.getString("login"));
    }
    else {
      reposList = savedInstanceState.getParcelableArrayList(STATE_LIST);
    }

    arrayListAdapter = new ArrayListAdapter(getApplicationContext(),reposList,bundle.getString("login"));
    recyclerView.setAdapter(arrayListAdapter);
    recyclerView.addItemDecoration(new DividerItemDecoration(this));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.rxUnsubscribe();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList(STATE_LIST, reposList);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void updateData(ViewModel viewModel) {
    reposList.add(viewModel);
    arrayListAdapter.notifyItemInserted(reposList.size() - 1);
  }

  @Override
  protected void initInjector() {
    ((App) getApplication()).getReposComponent().inject(this);
  }

  @Override
  protected void initViews() {
    presenter.setView(this);
  }



}
