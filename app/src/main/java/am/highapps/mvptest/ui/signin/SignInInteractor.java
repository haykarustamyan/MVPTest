package am.highapps.mvptest.ui.signin;

public interface SignInInteractor {

    interface OnSignInFinishedListener {
        void onIdError();

        void onPasswordError();

        void onValid(String id, String password);
    }

    void signIn(String id, String password, OnSignInFinishedListener listener);

}
