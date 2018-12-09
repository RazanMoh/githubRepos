package com.test.githubit.http;

import com.test.githubit.http.apimodel.Fork.Fork;
import com.test.githubit.http.apimodel.Repo.Repo;
import com.test.githubit.http.apimodel.User.User;
import com.test.githubit.http.apimodel.User.UserDetails;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApiService {

    @GET("users")
    Observable<List<User>> getUsers();

    @GET("users/{user}")
    Observable<UserDetails> getUserDetails(@Path("user") String user);

    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepos(@Path("user") String user);

    @GET("repos/{user}/{repo}/forks")
    Observable<List<Fork>> getUser(@Path("user") String user,
                                   @Path("repo") String repo);
}
