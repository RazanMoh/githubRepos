package com.test.githubit.Repo;

import android.content.Context;
import com.test.githubit.http.UserApiService;
import dagger.Module;
import dagger.Provides;

@Module
public class ReposModule {

    Context context;
    public ReposModule(Context context) {
        this.context = context;
    }
    @ReposScope
    @Provides
    public ReposActivityMVP.Presenter provideReposActivityPresenter(ReposActivityMVP.Model reposModel) {
        return new ReposPresenter(reposModel);
    }

    @ReposScope
    @Provides
    public ReposActivityMVP.Model provideReposActivityModel(UserApiService userApiService) {
        return new ReposModel(userApiService);
    }
}
