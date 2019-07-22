package am.highapps.mvptest.ui.login;

import android.text.TextUtils;

import javax.inject.Inject;

//@ActivityScope
public class LoginInteractorImpl implements LoginInteractor {

    @Inject
    public LoginInteractorImpl() {
    }

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
            return;
        }
        if (!error) {
            listener.onValid(username, password);
        }
    }

}
