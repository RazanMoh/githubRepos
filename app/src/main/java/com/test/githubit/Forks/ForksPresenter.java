package com.test.githubit.Forks;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ForksPresenter implements ForksActivityMVP.Presenter {

    private ForksActivityMVP.View view;
    private Disposable subscription = null;
    private ForksActivityMVP.Model model;

    public ForksPresenter(ForksActivityMVP.Model model) {
        this.model = model;
    }


  @Override
    public void loadData(String repoName, String username) {

        subscription = model
                .result(repoName, username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
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
    public void setView(ForksActivityMVP.View view) {
        this.view = view;
    }

}
