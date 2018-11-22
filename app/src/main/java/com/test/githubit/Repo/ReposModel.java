package com.test.githubit.Repo;

import com.test.githubit.http.apimodel.Repo;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;


public class ReposModel implements ReposActivityMVP.Model {

    private Repository repository;
    private List<ViewModel> viewModelList = new ArrayList<>();

    public ReposModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Repo> result(String username) {

      return repository.getReposData(username);
    }
}
