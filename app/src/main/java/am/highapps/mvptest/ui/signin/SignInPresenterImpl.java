package am.highapps.mvptest.ui.signin;

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

public class SignInPresenterImpl implements SignInContract.SignInPresenter, SignInInteractor.OnSignInFinishedListener {

    private static final String TAG = SignInPresenterImpl.class.getSimpleName();

    private MVPTestDataSource mvpTestDataSource;
    private NetworkUtil networkUtil;
    private CompositeDisposable compositeDisposable;
    private SignInContract.SignInView signInView;
    private SignInInteractor signInInteractor;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public SignInPresenterImpl(MVPTestDataSource mvpTestDataSource,
                               NetworkUtil networkUtil,
                               CompositeDisposable compositeDisposable,
                               SignInInteractor signInInteractor,
                               SignInContract.SignInView signInView) {
        this.mvpTestDataSource = mvpTestDataSource;
        this.networkUtil = networkUtil;
        this.compositeDisposable = compositeDisposable;
        this.signInView = signInView;
        this.signInInteractor = signInInteractor;

        this.signInView.setPresenter(this);
    }

    @Override
    public boolean isUserAuthDone() {
        return sharedPreferences.getString(PREF_USER_TOKEN, null) != null;
    }

    @Override
    public void validateCredentials(String id, String password) {
        if (signInView != null) {
            signInView.showProgress();
        }

        signInInteractor.signIn(id, password, this);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        signInView = null;
    }

    @Override
    public void onIdError() {
        if (signInView != null) {
            signInView.setIdError();
            signInView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (signInView != null) {
            signInView.setPasswordError();
            signInView.hideProgress();
        }
    }

    @Override
    public void onValid(String id, String password) {
        if (signInView != null) {
            signIn(id, password);
        }
    }

    private void signIn(String username, String password) {
        Disposable disposable = mvpTestDataSource.signIn(new UserRequestBody(username, password))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscribe -> {
                    if (!networkUtil.isConnected()) {
                        signInView.hideProgress();
                        signInView.showMessage(Constant.Error.NO_NETWORK);
                    }
                })
                .subscribe(tokenData -> {
                            if (tokenData.getData() != null) {
                                String token = tokenData.getData().getToken();
                                sharedPreferences.edit().putString(PREF_USER_TOKEN, token).apply();
                                Log.d(TAG, "token: " + token);
                                Log.d(TAG, "shared token: " + sharedPreferences.getString(PREF_USER_TOKEN, null));
                                signInView.navigateToMain();
                            } else {
                                signInView.hideProgress();
                                signInView.showMessage(tokenData.getMessage());
                            }
                        },
                        error -> {
                            signInView.hideProgress();
                            signInView.showMessage(ExcUtil.readError(error));
                        }
                );
        compositeDisposable.add(disposable);
    }
}
