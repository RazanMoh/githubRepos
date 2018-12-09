package com.test.githubit.Forks;

import com.test.githubit.Base.BaseActivityMVP;
import com.test.githubit.http.apimodel.Fork.Fork;
import com.test.githubit.http.apimodel.User.UserDetails;
import io.reactivex.Observable;

public interface ForksActivityMVP {

    interface View extends BaseActivityMVP.View<ViewModel>{

        void updateData(ViewModel viewModel);
    }

    interface Presenter extends BaseActivityMVP.Presenter<ViewModel> {

        void loadData(String username, String repoName);

        void setView(ForksActivityMVP.View view);

        Observable<ViewModel> result(String repoName, String username);

    }

    interface Model extends BaseActivityMVP.Model<ViewModel>{

        Observable<Fork> getUsersFromMemory(String repoName, String username);

        Observable<Fork> getUsersFromNetwork(String repoName, String username);

        Observable<UserDetails> getUserDetailsFromMemory(String repoName, String username);

        Observable<UserDetails> getUserDetailsFromNetwork(String repoName, String username);

        Observable<UserDetails> getUserDetailsData(String repoName, String username);

        Observable<Fork> getOwnerData(String repoName, String username);
    }
}
