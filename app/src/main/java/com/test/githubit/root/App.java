package com.test.githubit.root;

import android.app.Application;
import com.test.githubit.Forks.ForksModule;
import com.test.githubit.Repo.ReposModule;
import com.test.githubit.User.UsersModule;
import com.test.githubit.http.ApiModuleForName;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModuleForName(new ApiModuleForName())
                .usersModule(new UsersModule())
                .forksModule(new ForksModule())
                .reposModule(new ReposModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
