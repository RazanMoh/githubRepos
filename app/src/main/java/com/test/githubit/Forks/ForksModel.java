package com.test.githubit.Forks;

import com.test.githubit.http.apimodel.Fork;
import com.test.githubit.http.apimodel.UserDetails;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ForksModel implements ForksActivityMVP.Model {

    private Repository repository;

    public ForksModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<ViewModel> result(String repoName, String username) {
        return Observable.zip(
            repository.getOwnerData(repoName, username),
            repository.getUserDetailsData(repoName, username), new BiFunction<Fork, UserDetails, ViewModel>() {
              @Override
              public ViewModel apply(Fork fork, UserDetails userDetails) throws Exception {
                return new ViewModel(fork.getOwner().getLogin(),userDetails.getPublicRepos(),fork.getOwner().getAvatarUrl(),userDetails.getFollowers());

              }
            }
        );
    }

}
