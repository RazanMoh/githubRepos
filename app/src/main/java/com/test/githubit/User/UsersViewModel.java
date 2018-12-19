package com.test.githubit.User;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;

public class UsersViewModel extends ViewModel {

  private LiveData<List<ViewModelU>> viewModelList;
  private UsersRepository usersRepository;

  public LiveData<List<ViewModelU>> getUsers(){
    if(viewModelList == null){
      usersRepository = new UsersRepository();
      viewModelList = usersRepository.getUsers();

    }
    return viewModelList;
  }

}
