package com.test.githubit.root;

import android.app.Application;
import com.test.githubit.Forks.DaggerForksComponent;
import com.test.githubit.Forks.ForksComponent;
import com.test.githubit.Forks.ForksModule;
import com.test.githubit.Repo.DaggerReposComponent;
import com.test.githubit.Repo.ReposComponent;
import com.test.githubit.Repo.ReposModule;
import com.test.githubit.User.DaggerUsersComponent;
import com.test.githubit.User.UsersComponent;
import com.test.githubit.User.UsersModule;
import com.test.githubit.http.ApiModuleForName;

public class App extends Application {

    private ApplicationComponent component;
    private UsersComponent usersComponent;
    private ReposComponent reposComponent;
    private ForksComponent forksComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();


        usersComponent = DaggerUsersComponent.builder()
                .apiModuleForName(new ApiModuleForName())
                .usersModule(new UsersModule(this))
                .build();

        reposComponent = DaggerReposComponent.builder()
            .apiModuleForName(new ApiModuleForName())
            .reposModule(new ReposModule(this))
            .build();

        forksComponent = DaggerForksComponent.builder()
            .apiModuleForName(new ApiModuleForName())
            .forksModule(new ForksModule(this))
            .build();
    }


    public ApplicationComponent getComponent() {
        return component;
    }
    public UsersComponent getUsersComponent() {
        return usersComponent;
    }
    public ReposComponent getReposComponent() {
        return reposComponent;
    }
    public ForksComponent getForksComponent() {
        return forksComponent;
    }
}
