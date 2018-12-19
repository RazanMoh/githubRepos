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
  public UsersRepository provideUsersActivityModel() {
    return new UsersRepository();
  }
}
