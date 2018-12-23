package com.test.githubit.Forks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.test.githubit.User.UsersRepository;
import com.test.githubit.User.ViewModelU;

import java.util.List;

public class ForksViewModel extends ViewModel {

  private LiveData<List<ViewModelF>> viewModelList;
  private ForksRepository forksRepository;

  public LiveData<List<ViewModelF>> getForkUsers(String login, String repo){
    if(viewModelList == null){
      forksRepository = new ForksRepository();
      viewModelList = forksRepository.getForkUsers(login,repo);

    }
    return viewModelList;
  }

}
