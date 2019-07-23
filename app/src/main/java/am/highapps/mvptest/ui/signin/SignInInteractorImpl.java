package am.highapps.mvptest.ui.signin;

import android.text.TextUtils;

import javax.inject.Inject;

public class SignInInteractorImpl implements SignInInteractor {

    @Inject
    public SignInInteractorImpl() {
    }

    @Override
    public void signIn(final String id, final String password, final OnSignInFinishedListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(id)) {
            listener.onIdError();
            error = true;
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
            error = true;
            return;
        }
        if (!error) {
            listener.onValid(id, password);
        }
    }

}
