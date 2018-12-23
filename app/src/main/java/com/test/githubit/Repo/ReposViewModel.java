package com.test.githubit.Repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.test.githubit.http.apimodel.Repo.Repo;

import java.util.List;

public class ReposViewModel extends ViewModel {

  private LiveData<List<Repo>> repoList;
  private ReposRepository reposRepository;

  public LiveData<List<Repo>> getRepos(String login){
    if(repoList == null){
      reposRepository = new ReposRepository();
      repoList = reposRepository.getRepos(login);

    }
    return repoList;
  }

}
