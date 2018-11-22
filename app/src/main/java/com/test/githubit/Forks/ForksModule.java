package com.test.githubit.Forks;

import com.test.githubit.http.UserApiService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ForksModule {

    @Provides
    public ForksActivityMVP.Presenter provideUsersActivityPresenter(ForksActivityMVP.Model usersModel) {
        return new ForksPresenter(usersModel);
    }

    @Provides
    public ForksActivityMVP.Model provideUsersActivityModel(Repository repository) {
        return new ForksModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(UserApiService userApiService) {
        return new ForksRepository(userApiService);
    }


}
