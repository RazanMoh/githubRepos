package com.test.githubit.User;

import com.test.githubit.Base.BasePresenter;
import com.test.githubit.http.apimodel.User.User;
import com.test.githubit.http.apimodel.User.UserDetails;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class UsersPresenter extends BasePresenter<ViewModel> implements UsersActivityMVP.Presenter {

    private UsersActivityMVP.View view;
    private Disposable subscription = null;
    private UsersActivityMVP.Model model;

    public UsersPresenter(UsersActivityMVP.Model model) {
      super(model);

      this.model = model;
    }

    @Override
    public void loadData() {

        subscription = result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                  @Override
                  protected void onStart() {
                    if (view != null) {
                        view.showProgressDialog();
                    }
                  }

                  @Override
                    public void onComplete() {
                    if (view != null) {
                        view.hideProgressDialog();
                    }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null) {
                            view.hideProgressDialog();
                            view.showSnackbar("Error getting users"+e.getMessage());
                        }
                    }

                    @Override
                    public void onNext(ViewModel viewModel) {
                        if (view != null) {
                            view.showProgressDialog();
                            view.updateData(viewModel);
                        }
                    }
                });
    }

   @Override
    public void setView(UsersActivity view) {
        this.view = view;
    }


  @Override
  public Observable<ViewModel> result() {
    return Observable.zip(
        model.getUsersData(),
        model.getUserDetailsData(), new BiFunction<User, UserDetails, ViewModel>() {
          @Override
          public ViewModel apply(User user, UserDetails userDetails) throws Exception {
            return new ViewModel(user.getLogin(),userDetails.getPublicRepos(),user.getAvatarUrl(),userDetails.getFollowers());

          }
        }
    );
  }

  @Override
  public void rxUnsubscribe() {
    super.rxUnsubscribe();
  }
}
