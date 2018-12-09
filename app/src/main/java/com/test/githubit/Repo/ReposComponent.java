package com.test.githubit.Repo;

import com.test.githubit.http.ApiModuleForName;
import dagger.Component;

@ReposScope
@Component(modules = {ReposModule.class,ApiModuleForName.class})
public interface ReposComponent {
    void inject(ReposActivity target);
}
