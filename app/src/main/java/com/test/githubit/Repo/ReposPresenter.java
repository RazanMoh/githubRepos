package com.test.githubit.Repo;

import com.test.githubit.Base.BasePresenter;
import com.test.githubit.http.apimodel.Repo.Repo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ReposPresenter extends BasePresenter<ViewModel> implements ReposActivityMVP.Presenter {

    private ReposActivityMVP.View view;
    private Disposable subscription = null;
    private ReposActivityMVP.Model model;

    public ReposPresenter(ReposActivityMVP.Model model) {
      super(model);
      this.model = model;
    }

    @Override
    public void loadData(String username) {

        subscription = result(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Repo>() {
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
                    public void onNext(Repo repo) {
                        if (view != null) {
                          if(repo.getLicense()!=null)
                            view.updateData(new ViewModel(repo.getOwner().getLogin(),repo.getName(),repo.getDescription(),repo.getForksCount(),repo.getLicense().getName()));
                          else
                          view.updateData(new ViewModel(repo.getOwner().getLogin(),repo.getName(),repo.getDescription(),repo.getForksCount()));
                        }
                    }
                });
    }

  @Override
    public void setView(ReposActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public Observable<Repo> result(String username) {

    return model.getReposData(username);
    }

  @Override
  public void rxUnsubscribe() {
    super.rxUnsubscribe();
  }
}
