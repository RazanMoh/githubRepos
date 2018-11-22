package com.test.githubit.User;

import com.test.githubit.http.apimodel.User;
import com.test.githubit.http.apimodel.UserDetails;
import io.reactivex.Observable;

public interface Repository {

    Observable<User> getUsersFromMemory();

    Observable<User> getUsersFromNetwork();

    Observable<UserDetails> getUserDetailsFromMemory();

    Observable<UserDetails> getUserDetailsFromNetwork();

    Observable<UserDetails> getUserDetailsData();

    Observable<User> getUsersData();
}
