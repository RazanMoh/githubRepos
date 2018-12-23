package com.test.githubit.Repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.test.githubit.http.ApiModuleForName;
import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.Repo.Repo;
import com.test.githubit.http.apimodel.Repo.RepoMemory;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposRepository {
  private UserApiService userApiService;
  private ApiModuleForName apiModuleForName;
  private MutableLiveData<List<Repo>> reposData;


    public ReposRepository() {
      apiModuleForName = new ApiModuleForName();
      userApiService = apiModuleForName.provideApiService();
    }

  public LiveData<List<Repo>> getRepos(String login) {

    reposData = new MutableLiveData<>();

    Call<List<Repo>> repoListCall = userApiService.getRepos(login);

    repoListCall.enqueue(new Callback<List<Repo>>() {
      @Override
      public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
        if (response.isSuccessful())
          reposData.setValue(response.body());
        Log.d("4444","4444|||"+response.body().size());
      }

      @Override
      public void onFailure(Call<List<Repo>> call, Throwable t) {
        Log.e("Failed", t.getMessage().toString());
        reposData.setValue(null);
      }

    });
    return reposData;
  }

}
