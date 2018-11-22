package com.test.githubit.Repo;

import com.test.githubit.http.apimodel.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class ReposPresenter implements ReposActivityMVP.Presenter {

    private ReposActivityMVP.View view;
    private Disposable subscription = null;
    private ReposActivityMVP.Model model;

    public ReposPresenter(ReposActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData(String username) {

        subscription = model
                .result(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Repo>() {
                    @Override
                    public void onComplete() {
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.showSnackbar("Error getting users"+e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(Repo repo) {
                        if (view != null) {
                            view.updateData(repo);
                        }
                    }
                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

    @Override
    public void setView(ReposActivityMVP.View view) {
        this.view = view;
    }

}
