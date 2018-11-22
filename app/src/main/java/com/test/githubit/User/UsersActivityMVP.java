package com.test.githubit.User;

import io.reactivex.Observable;

public interface UsersActivityMVP {

    interface View {

        void updateData(ViewModel viewModel);

        void showSnackbar(String s);

    }

    interface Presenter {

        void loadData();

        void rxUnsubscribe();

        void setView(UsersActivityMVP.View view);

    }

    interface Model {

        Observable<ViewModel> result();

    }
}
