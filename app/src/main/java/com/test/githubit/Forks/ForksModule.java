package com.test.githubit.Forks;

import android.content.Context;
import com.test.githubit.http.UserApiService;
import dagger.Module;
import dagger.Provides;

@Module
public class ForksModule {

    Context context;
    public ForksModule(Context context) {
        this.context = context;
    }
    @ForksScope
    @Provides
    public ForksActivityMVP.Presenter provideUsersActivityPresenter(ForksActivityMVP.Model usersModel) {
        return new ForksPresenter(usersModel);
    }

    @ForksScope
    @Provides
    public ForksActivityMVP.Model provideUsersActivityModel(UserApiService userApiService) {
        return new ForksModel(userApiService);
    }

}
