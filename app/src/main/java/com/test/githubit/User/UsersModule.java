package com.test.githubit.User;

import com.test.githubit.http.UserApiService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {

    @Provides
    public UsersActivityMVP.Presenter provideUsersActivityPresenter(UsersActivityMVP.Model usersModel) {
        return new UsersPresenter(usersModel);
    }

    @Provides
    public UsersActivityMVP.Model provideUsersActivityModel(Repository repository) {
        return new UsersModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(UserApiService userApiService) {
        return new UsersRepository(userApiService);
    }
}
