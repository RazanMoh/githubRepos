package com.test.githubit.User;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class UsersPresenter implements UsersActivityMVP.Presenter {

    private UsersActivityMVP.View view;
    private Disposable subscription = null;
    private UsersActivityMVP.Model model;

    public UsersPresenter(UsersActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void loadData() {

        subscription = model
                .result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                    @Override
                    public void onComplete() {
                      Log.d("3333","333||");
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.showSnackbar("Error getting users"+e.getMessage());
                          Log.d("3333","333||"+e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ViewModel viewModel) {
                        if (view != null) {
                            view.updateData(viewModel);
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
    public void setView(UsersActivityMVP.View view) {
        this.view = view;
    }

}
