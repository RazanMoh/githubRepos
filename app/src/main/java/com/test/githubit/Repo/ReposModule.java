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
    public ReposRepository provideUsersActivityModel() {
        return new ReposRepository();
    }
}
