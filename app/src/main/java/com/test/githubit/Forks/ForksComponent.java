package com.test.githubit.Forks;


import com.test.githubit.http.ApiModuleForName;
import dagger.Component;

@ForksScope
@Component(modules = {ForksModule.class,ApiModuleForName.class})
public interface ForksComponent {
    void inject(ForksActivity target);
}