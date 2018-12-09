package com.test.githubit.Forks;

import com.test.githubit.Base.BasePresenter;
import com.test.githubit.http.apimodel.Fork.Fork;
import com.test.githubit.http.apimodel.User.UserDetails;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ForksPresenter extends BasePresenter<ViewModel> implements ForksActivityMVP.Presenter {

    private ForksActivityMVP.View view;
    private Disposable subscription = null;
    private ForksActivityMVP.Model model;

    public ForksPresenter(ForksActivityMVP.Model model) {
      super(model);
      this.model = model;
    }

  @Override
    public void loadData(String repoName, String username) {

        subscription = result(repoName, username)
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
    public void setView(ForksActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public Observable<ViewModel> result(String repoName, String username) {
    return Observable.zip(
        model.getOwnerData(repoName, username),
        model.getUserDetailsData(repoName, username),
        new BiFunction<Fork, UserDetails, ViewModel>() {
          @Override
          public ViewModel apply(Fork fork, UserDetails userDetails) throws Exception {
            return new ViewModel(fork.getOwner().getLogin(),userDetails.getPublicRepos(),fork.getOwner().getAvatarUrl(),userDetails.getFollowers());
          }
        }
    );
    }

  @Override
  public void rxUnsubscribe() {
    super.rxUnsubscribe();
  }
}
