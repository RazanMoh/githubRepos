package com.test.githubit.Repo;

import com.test.githubit.http.apimodel.Repo;
import io.reactivex.Observable;


public interface Repository {

    Observable<Repo> getReposFromMemory(String username);

    Observable<Repo> getReposFromNetwork(String username);

    Observable<Repo> getReposData(String username);

}
