package com.test.githubit.Repo;

import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.Repo.Repo;
import com.test.githubit.http.apimodel.Repo.RepoMemory;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ReposModel implements ReposActivityMVP.Model {

    private UserApiService userApiService;
    private List<Repo> repos;
    private RepoMemory repoMemory;
    private long timestamp;

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds

    public ReposModel(UserApiService userApiService) {
        this.timestamp = System.currentTimeMillis();
        this.userApiService = userApiService;
        repos = new ArrayList<>();
        repoMemory = new RepoMemory();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable <Repo> getReposFromMemory(String username) {
        if (isUpToDate()&& repoMemory.getName().equals(username)) {
            return Observable.fromIterable(repos);
        } else {
            timestamp = System.currentTimeMillis();
            repos.clear();
            repoMemory.setRepos(repos);
            repoMemory.setName(username);
            return Observable.empty();
        }
    }

    @Override
    public Observable<Repo> getReposFromNetwork(String username) {
        repoMemory.setName(username);
        Observable<List<Repo>> topRatedObservable = userApiService.getRepos(username);
        return topRatedObservable.concatMap(new Function<List<Repo>, Observable<Repo>>() {
            @Override
            public Observable<Repo> apply(List<Repo> repos) throws Exception {
                repoMemory.setRepos(repos);
                return Observable.fromIterable(repos);
            }
        }).doOnNext(new Consumer<Repo>() {
            @Override
            public void accept(Repo result) {
                repos.add(result);
            }
        });
    }

    @Override
    public Observable<Repo> getReposData(String username) {
        return getReposFromMemory(username).switchIfEmpty(getReposFromNetwork(username));

    }

}
