package com.test.githubit.Forks;

import android.content.Context;
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
    public ForksRepository provideUsersActivityModel() {
        return new ForksRepository();
    }

}
