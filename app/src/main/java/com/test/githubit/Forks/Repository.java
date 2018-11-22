package com.test.githubit.Forks;

import com.test.githubit.http.apimodel.Fork;
import com.test.githubit.http.apimodel.UserDetails;
import io.reactivex.Observable;

public interface Repository {

    Observable<Fork> getUsersFromMemory(String repoName, String username);

    Observable<Fork> getUsersFromNetwork(String repoName, String username);

    Observable<UserDetails> getUserDetailsFromMemory(String repoName, String username);

    Observable<UserDetails> getUserDetailsFromNetwork(String repoName, String username);

    Observable<UserDetails> getUserDetailsData(String repoName, String username);

    Observable<Fork> getOwnerData(String repoName, String username);
}
