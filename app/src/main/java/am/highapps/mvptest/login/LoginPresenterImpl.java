package am.highapps.mvptest.login;

import android.util.Log;

import am.highapps.mvptest.data.api.ApiFactory;
import am.highapps.mvptest.data.entity.login.SignInRequestBody;
import am.highapps.mvptest.data.entity.login.UserResponseEntity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private static final String TAG = "LoginPresenterImpl";

    private LoginContract.LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginContract.LoginView  loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();

        this.loginView.setPresenter(this);

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
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
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
//            loginView.navigateToHome();
            ApiFactory.getMVPtestService(ApiFactory.Url.BASE_URL)
                    .getSignInData(new UserResponseEntity(username,password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<SignInRequestBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d(TAG, "onNext: signInData");

                        }

                        @Override
                        public void onNext(SignInRequestBody signInRequestBody) {
                            Log.d(TAG, "onNext: signInRequestBody");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: signInData" + e.getMessage() + "   err "+e.toString());

                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onNext: signInData");

                        }
                    });
        }
    }
}
