package com.test.githubit.User;

import android.content.Context;
import com.test.githubit.http.UserApiService;
import dagger.Module;
import dagger.Provides;

@Module
public class UsersModule {
    Context context;
    public UsersModule(Context context) {
        this.context = context;
    }
    @UsersScope
    @Provides
    public UsersActivityMVP.Presenter provideUsersActivityPresenter(UsersActivityMVP.Model usersModel) {
        return new UsersPresenter(usersModel);
    }

    @UsersScope
    @Provides
    public UsersActivityMVP.Model provideUsersActivityModel(UserApiService userApiService) {
        return new UsersModel(userApiService);
    }
}
