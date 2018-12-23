package com.test.githubit.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.test.githubit.R;
import com.test.githubit.http.UserApiService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<T> extends AppCompatActivity {

    private final String TAG = BaseActivity.class.getName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.listActivity_rootView)
    ViewGroup rootView;

    ProgressDialog progressDialog;

    @Inject
    UserApiService userApiService;

    private List<T> tArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_activity);

        ButterKnife.bind(this);

        initProgressDialog();
    }

    protected abstract void subscribeUsersObserver();
    protected abstract void initRecyclerView();

    public void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
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
