package com.test.githubit.User;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.support.annotation.Nullable;
import android.util.Log;
import com.test.githubit.http.ApiModuleForName;
import com.test.githubit.http.UserApiService;
import com.test.githubit.http.apimodel.User.User;
import com.test.githubit.http.apimodel.User.UserDetails;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
  private UserApiService userApiService;
  private List<ViewModelU> viewModelUList;
  private ApiModuleForName apiModuleForName;
  private MediatorLiveData<List<ViewModelU>> viewModelUMediatorLiveData;
  private MutableLiveData<List<User>> usersData;
  private MutableLiveData<UserDetails> userDetailsMutableLiveData;


  public UsersRepository() {
    apiModuleForName = new ApiModuleForName();
    viewModelUList=  new ArrayList<>();
    userApiService = apiModuleForName.provideApiService();
  }


  public LiveData<List<User>> getUsersList() {

    usersData = new MutableLiveData<>();

    Call<List<User>> userListCall = userApiService.getUsers();

    userListCall.enqueue(new Callback<List<User>>() {
      @Override
      public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful())
          usersData.setValue(response.body());
      }

      @Override
      public void onFailure(Call<List<User>> call, Throwable t) {
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

  public LiveData<List<ViewModelU>> getUsers() {
    LiveData<List<User>> usersLiveData = getUsersList();

    LiveData<List<ViewModelU>> viewModelULiveData = Transformations.switchMap(usersLiveData, new android.arch.core.util.Function<List<User>, LiveData<List<ViewModelU>>>() {
      @Override
      public LiveData<List<ViewModelU>> apply(List<User> usersList) {
        viewModelUMediatorLiveData = new MediatorLiveData<>();
        for (User user : usersList) {
          viewModelUMediatorLiveData.addSource(getUserDetails(user.getLogin()), new Observer<UserDetails>() {
            @Override
            public void onChanged(@Nullable UserDetails userDetails) {
              viewModelUList.add(new ViewModelU(userDetails.getLogin(), userDetails.getPublicRepos(),
                  userDetails.getAvatarUrl(), userDetails.getFollowers()));
              viewModelUMediatorLiveData.postValue(viewModelUList);
            }
          });

        }
        return viewModelUMediatorLiveData;
      }
    });
    return viewModelULiveData;
    }

}
