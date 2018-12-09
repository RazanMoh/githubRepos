package com.test.githubit.Base;

import io.reactivex.disposables.Disposable;


public abstract class BasePresenter<T> implements BaseActivityMVP.Presenter<T> {

    private BaseActivityMVP.View<T> view;
    private Disposable subscription = null;
    private BaseActivityMVP.Model<T> model;

    public BasePresenter(BaseActivityMVP.Model<T> model) {
        this.model = model;
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }

}
