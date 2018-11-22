package com.test.githubit.http;

import com.test.githubit.http.apimodel.Fork;
import com.test.githubit.http.apimodel.Repo;
import com.test.githubit.http.apimodel.User;
import com.test.githubit.http.apimodel.UserDetails;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApiService {

    @GET("users")
    Observable<List<User>> getUsers(@Query("client_id") String client_id,
                                    @Query("client_secret") String client_secret);

    @GET("users/{user}")
    Observable<UserDetails> getUserDetails(@Path("user") String user,
                                           @Query("client_id") String client_id,
                                           @Query("client_secret") String client_secret);

    @GET("repos/{user}/{repo}/forks")
    Observable<List<Fork>> getUser(@Path("user") String user,
                                   @Path("repo") String repo,
                                   @Query("client_id") String client_id,
                                   @Query("client_secret") String client_secret);

    @GET("users/{user}/repos")
    Observable<List<Repo>> getRepos(@Path("user") String user,
                                    @Query("client_id") String client_id,
                                    @Query("client_secret") String client_secret);

}
