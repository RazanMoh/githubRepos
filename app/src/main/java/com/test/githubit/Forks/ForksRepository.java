package com.test.githubit.Forks;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.githubit.http.ApiModuleForName;
import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.Fork.Fork;
import com.test.githubit.http.apimodel.User.UserDetails;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForksRepository {

  private UserApiService userApiService;
  private List<ViewModelF> viewModelUList;
  private ApiModuleForName apiModuleForName;
  private MediatorLiveData<List<ViewModelF>> viewModelFMediatorLiveData;
  private MutableLiveData<List<Fork>> usersData;
  private MutableLiveData<UserDetails> userDetailsMutableLiveData;


  public ForksRepository() {
    apiModuleForName = new ApiModuleForName();
    userApiService = apiModuleForName.provideApiService();
    viewModelUList=  new ArrayList<>();
  }


  public LiveData<List<Fork>> getUsersList(String login, String repo) {

    usersData = new MutableLiveData<>();

    Call<List<Fork>> userListCall = userApiService.getForkUsers(login,repo);

    userListCall.enqueue(new Callback<List<Fork>>() {
      @Override
      public void onResponse(Call<List<Fork>> call, Response<List<Fork>> response) {
        if (response.isSuccessful())
          usersData.setValue(response.body());
      }

      @Override
      public void onFailure(Call<List<Fork>> call, Throwable t) {
        Log.e("Failed", t.getMessage().toString());
        usersData.setValue(null);
      }

    });
    return usersData;
  }

  public LiveData<UserDetails> getUserDetails(String login) {

    userDetailsMutableLiveData = new MutableLiveData<>();
    Call<UserDetails> userDetails = userApiService.getUserDetails(login);
    userDetails.enqueue(new Callback<UserDetails>() {
      @Override
      public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
        if (response.isSuccessful())
          userDetailsMutableLiveData.setValue(response.body());
      }
      @Override
      public void onFailure(Call<UserDetails> call, Throwable t) {
        Log.e("Failed", t.getMessage().toString());

        userDetailsMutableLiveData.setValue(null);
      }

    });
    return userDetailsMutableLiveData;
  }

  public LiveData<List<ViewModelF>> getForkUsers(String login, String repo) {
    LiveData<List<Fork>> usersLiveData = getUsersList(login,repo);

    LiveData<List<ViewModelF>> viewModelULiveData = Transformations.switchMap(usersLiveData, new android.arch.core.util.Function<List<Fork>, LiveData<List<ViewModelF>>>() {
      @Override
      public LiveData<List<ViewModelF>> apply(List<Fork> usersList) {
        viewModelFMediatorLiveData = new MediatorLiveData<>();
        for (Fork fork : usersList) {
          viewModelFMediatorLiveData.addSource(getUserDetails(fork.getOwner().getLogin()), new Observer<UserDetails>() {
            @Override
            public void onChanged(@Nullable UserDetails userDetails) {
              viewModelUList.add(new ViewModelF(userDetails.getLogin(), userDetails.getPublicRepos(),
                  userDetails.getAvatarUrl(), userDetails.getFollowers()));
              viewModelFMediatorLiveData.postValue(viewModelUList);
            }
          });

        }
        return viewModelFMediatorLiveData;
      }
    });
    return viewModelULiveData;
  }

}
