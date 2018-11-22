package com.test.githubit.Repo;

import com.test.githubit.http.UserApiService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ReposModule {

    @Provides
    public ReposActivityMVP.Presenter provideReposActivityPresenter(ReposActivityMVP.Model reposModel) {
        return new ReposPresenter(reposModel);
    }

    @Provides
    public ReposActivityMVP.Model provideReposActivityModel(Repository repository) {
        return new ReposModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(UserApiService userApiService) {
        return new ReposRepository(userApiService);
    }
}
