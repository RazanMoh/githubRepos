package com.test.githubit.User;

import com.test.githubit.http.ApiModuleForName;
import dagger.Component;

@UsersScope
@Component(modules = {UsersModule.class,ApiModuleForName.class})
public interface UsersComponent {
  void inject(UsersActivity target);
}