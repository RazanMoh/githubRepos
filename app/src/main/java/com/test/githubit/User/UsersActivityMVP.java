package com.test.githubit.User;

import com.test.githubit.Base.BaseActivityMVP;
import com.test.githubit.http.apimodel.User.User;
import com.test.githubit.http.apimodel.User.UserDetails;
import io.reactivex.Observable;

public interface UsersActivityMVP {

    interface View extends BaseActivityMVP.View<ViewModel> {

        void updateData(ViewModel viewModel);

    }

    interface Presenter extends BaseActivityMVP.Presenter<ViewModel> {

        void loadData();

        void setView(UsersActivity view);

        Observable<ViewModel> result();
    }

    interface Model extends BaseActivityMVP.Model<ViewModel> {

        Observable<User> getUsersFromMemory();

        Observable<User> getUsersFromNetwork();

        Observable<UserDetails> getUserDetailsFromMemory();

        Observable<UserDetails> getUserDetailsFromNetwork();

        Observable<UserDetails> getUserDetailsData();

        Observable<User> getUsersData();

    }
}
