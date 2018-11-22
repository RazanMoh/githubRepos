package com.test.githubit.root;

import com.test.githubit.Forks.ForksActivity;
import com.test.githubit.Forks.ForksModule;
import com.test.githubit.Repo.ReposActivity;
import com.test.githubit.Repo.ReposModule;
import com.test.githubit.User.UsersActivity;
import com.test.githubit.User.UsersModule;
import com.test.githubit.http.ApiModuleForName;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModuleForName.class,
    UsersModule.class, ReposModule.class, ForksModule.class
})
public interface ApplicationComponent {

    void inject(UsersActivity target);
    void inject(ReposActivity target);
    void inject(ForksActivity target);

}
