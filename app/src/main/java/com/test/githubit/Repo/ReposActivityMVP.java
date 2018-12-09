package com.test.githubit.Repo;

import com.test.githubit.Base.BaseActivityMVP;
import com.test.githubit.http.apimodel.Repo.Repo;
import io.reactivex.Observable;

public interface ReposActivityMVP {

    interface View extends BaseActivityMVP.View<ViewModel>{

        void updateData(ViewModel viewModel);

    }

    interface Presenter extends BaseActivityMVP.Presenter<ViewModel> {

        void loadData(String username);

        void setView(ReposActivityMVP.View view);

        Observable<Repo> result(String username);

    }

    interface Model extends BaseActivityMVP.Model<ViewModel>{

        Observable<Repo> getReposFromMemory(String username);

        Observable<Repo> getReposFromNetwork(String username);

        Observable<Repo> getReposData(String username);


    }
}
