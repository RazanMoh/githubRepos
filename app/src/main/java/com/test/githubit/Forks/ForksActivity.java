package com.test.githubit.Forks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import com.test.githubit.root.App;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForksActivity extends AppCompatActivity implements ForksActivityMVP.View {

  private final String TAG = ForksActivity.class.getName();

  @BindView(R.id.recycler_view)
  RecyclerView recyclerView;

  @BindView(R.id.listActivity_rootView)
  ViewGroup rootView;

  @Inject
  ForksActivityMVP.Presenter presenter;
  @Inject
  UserApiService userApiService;

  private ListAdapter listAdapter;
  private List<ViewModel> usersList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.users_activity);

    ((App) getApplication()).getComponent().inject(this);

    ButterKnife.bind(this);

    listAdapter = new ListAdapter(getApplicationContext(),usersList);
    recyclerView.setAdapter(listAdapter);


    recyclerView.addItemDecoration(new DividerItemDecoration(this));

    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  protected void onStart() {
    super.onStart();
    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    presenter.setView(this);
    presenter.loadData(bundle.getString("repo"),bundle.getString("username"));
    presenter.setView(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
    presenter.rxUnsubscribe();
    usersList.clear();
    listAdapter.notifyDataSetChanged();
  }

  @Override
  public void updateData(ViewModel viewModel) {
    usersList.add(viewModel);
    listAdapter.notifyItemInserted(usersList.size() - 1);
  }

  @Override
  public void showSnackbar(String msg) {
    Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show();

  }
}
