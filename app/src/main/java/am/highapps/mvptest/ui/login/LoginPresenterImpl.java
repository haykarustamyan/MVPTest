package am.highapps.mvptest.ui.login;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;

import am.highapps.mvptest.data.entity.signin.UserRequestBody;
import am.highapps.mvptest.data.repository.MVPTestDataSource;
import am.highapps.mvptest.util.Constant;
import am.highapps.mvptest.util.ExcUtil;
import am.highapps.mvptest.util.NetworkUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static am.highapps.mvptest.util.Constant.Pref.PREF_USER_TOKEN;

public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private static final String TAG = "LoginPresenterImpl";

    private MVPTestDataSource mvpTestDataSource;
    private NetworkUtil networkUtil;
    private CompositeDisposable compositeDisposable;
    private LoginContract.LoginView loginView;
    private LoginInteractor loginInteractor;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public LoginPresenterImpl(MVPTestDataSource mvpTestDataSource,
                              NetworkUtil networkUtil,
                              CompositeDisposable compositeDisposable,
                              LoginInteractor loginInteractor,
                              LoginContract.LoginView loginView) {
        this.mvpTestDataSource = mvpTestDataSource;
        this.networkUtil = networkUtil;
        this.compositeDisposable = compositeDisposable;
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;

        this.loginView.setPresenter(this);
    }

    @Override
    public boolean isUserAuthDone() {
        return sharedPreferences.getString(PREF_USER_TOKEN, null)!= null;
    }

    @Override
    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }

        loginInteractor.login(username, password, this);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setIdError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onValid(String username, String password) {
        if (loginView != null) {
            signIn(username, password);
        }
    }

    private void signIn(String username, String password) {
        Disposable disposable = mvpTestDataSource.signIn(new UserRequestBody(username, password))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        loginView.hideProgress();
                        loginView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(tokenData -> {
                            if (tokenData.getData() != null) {
                                String token = tokenData.getData().getToken();
                                sharedPreferences.edit().putString(PREF_USER_TOKEN, token).apply();
                                Log.d(TAG, "token: " + token);
                                Log.d(TAG, "shared token: " + sharedPreferences.getString(PREF_USER_TOKEN, null));
                                loginView.navigateToMain();
                            } else {
                                loginView.hideProgress();
                                loginView.showMessage(tokenData.getMessage());
                            }
                        },
                        error -> {
                            loginView.hideProgress();
                            loginView.showMessage(ExcUtil.readError(error));
                        }
                );
        compositeDisposable.add(disposable);
    }
}
