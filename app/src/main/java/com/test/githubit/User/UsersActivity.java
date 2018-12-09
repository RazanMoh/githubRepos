package com.test.githubit.User;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.test.githubit.Base.BaseActivity;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import com.test.githubit.root.App;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity extends BaseActivity<ViewModel> implements UsersActivityMVP.View {

    private final String TAG = UsersActivity.class.getName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    @Inject
    UsersActivityMVP.Presenter presenter;
    @Inject
    UserApiService userApiService;

    private static final String STATE_LIST = "State Adapter Data";
    private ArrayListAdapter arrayListAdapter;
    private ArrayList<ViewModel> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);

        initInjector();

        initViews();

        ButterKnife.bind(this);

        initProgressDialog();

        if (savedInstanceState == null) {
            presenter.loadData();
        }
        else {
            usersList = savedInstanceState.getParcelableArrayList(STATE_LIST);
        }

        arrayListAdapter = new ArrayListAdapter(getApplicationContext(),usersList);
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
        outState.putParcelableArrayList(STATE_LIST, usersList);
    }

    @Override
    public void updateData(ViewModel viewModel) {
        usersList.add(viewModel);
        arrayListAdapter.notifyItemInserted(usersList.size() - 1);
    }

    @Override
    protected void initInjector() {
        ((App) getApplication()).getUsersComponent().inject(this);
    }

    @Override
    protected void initViews() {
        presenter.setView(this);
    }
}
