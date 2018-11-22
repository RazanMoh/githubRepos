package com.test.githubit.Repo;

import com.test.githubit.http.apimodel.Repo;

import io.reactivex.Observable;

public interface ReposActivityMVP {

    interface View {

        void updateData(Repo viewModel);

        void showSnackbar(String s);

    }

    interface Presenter {

        void loadData(String username);

        void rxUnsubscribe();

        void setView(ReposActivityMVP.View view);

    }

    interface Model {

        Observable<Repo> result(String username);

    }
}
