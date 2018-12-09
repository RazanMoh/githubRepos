package com.test.githubit.Base;

public interface BaseActivityMVP<T> {

    interface View<T> {

        void showSnackbar(String s);

        void hideProgressDialog();

        void showProgressDialog();

        void initProgressDialog();

    }

    interface Presenter<T>  {

        void rxUnsubscribe();

    }

    interface Model<T> {

    }
}
