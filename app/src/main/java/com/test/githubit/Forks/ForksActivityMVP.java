package com.test.githubit.Forks;

import io.reactivex.Observable;

public interface ForksActivityMVP {

    interface View {

        void updateData(ViewModel viewModel);

        void showSnackbar(String s);

    }

    interface Presenter {

        void loadData(String username, String repoName);

        void rxUnsubscribe();

        void setView(ForksActivityMVP.View view);

    }

    interface Model {

        Observable<ViewModel> result(String repoName, String username);
    }
}
