package com.test.githubit.User;

import com.test.githubit.http.apimodel.User;
import com.test.githubit.http.apimodel.UserDetails;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;


public class UsersModel implements UsersActivityMVP.Model {

    private Repository repository;

    public UsersModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result() {
        return Observable.zip(
            repository.getUsersData(),
            repository.getUserDetailsData(), new BiFunction<User, UserDetails, ViewModel>() {
              @Override
              public ViewModel apply(User user, UserDetails userDetails) throws Exception {
                return new ViewModel(user.getLogin(),userDetails.getPublicRepos(),user.getAvatarUrl(),userDetails.getFollowers());

              }
            }
        );
    }

}
