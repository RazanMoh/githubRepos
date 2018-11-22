package com.test.githubit.User;

import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.User;
import com.test.githubit.http.apimodel.UserDetails;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class UsersRepository implements Repository {

    private UserApiService userApiService;
    private List<UserDetails> userDetails;
    private List<User> users;
    private long timestamp;
    private String client_id= "d7b05bbfdcf1e4457fea";
    private String client_secret="ec564681514e3488f2e1917e403a8d240470fcbb";

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public UsersRepository(UserApiService userApiService) {
        this.timestamp = System.currentTimeMillis();
        this.userApiService = userApiService;
        userDetails = new ArrayList<>();
        users = new ArrayList<>();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<User> getUsersFromMemory() {
        if (isUpToDate()) {
            return Observable.fromIterable(users);
        } else {
            timestamp = System.currentTimeMillis();
            users.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<User> getUsersFromNetwork() {
        Observable<List<User>> topRatedObservable = userApiService.getUsers(client_id,client_secret);
        return topRatedObservable.concatMap(new Function<List<User>, Observable<User>>() {
            @Override
            public Observable<User> apply(List<User> users) throws Exception {
                return Observable.fromIterable(users);
            }
        }).doOnNext(new Consumer<User>() {
            @Override
            public void accept(User result) {
                users.add(result);
            }
        });
    }

    @Override
    public Observable<UserDetails> getUserDetailsFromMemory() {
        if (isUpToDate()) {
            return Observable.fromIterable(userDetails);
        } else {
            timestamp = System.currentTimeMillis();
            userDetails.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<UserDetails> getUserDetailsFromNetwork() {
        return getUsersFromNetwork().concatMap(new Function<User, Observable<UserDetails>>() {
            @Override
            public Observable<UserDetails> apply(User result) {
                return userApiService.getUserDetails(result.getLogin(),client_id,client_secret );
            }
        }).concatMap(new Function<UserDetails, Observable<UserDetails>>() {
            @Override
            public Observable<UserDetails> apply(UserDetails omdbApi) {
                return Observable.just(omdbApi);
            }
        }).doOnNext(new Consumer<UserDetails>() {
            @Override
            public void accept(UserDetails userDetails) throws Exception {
                UsersRepository.this.userDetails.add(userDetails);
            }
        });
    }

    @Override
    public Observable<UserDetails> getUserDetailsData() {
        return getUserDetailsFromMemory().switchIfEmpty(getUserDetailsFromNetwork());
    }

    @Override
    public Observable<User> getUsersData() {
        return getUsersFromMemory().switchIfEmpty(getUsersFromNetwork());
    }
}