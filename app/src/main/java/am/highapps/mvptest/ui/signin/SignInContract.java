package am.highapps.mvptest.ui.signin;

import am.highapps.mvptest.base.BasePresenter;
import am.highapps.mvptest.base.BaseView;

public interface SignInContract {

    interface SignInPresenter extends BasePresenter {

        boolean isUserAuthDone();

        void validateCredentials(String id, String password);

    }

    interface SignInView extends BaseView<SignInPresenter> {

        void setIdError();

        void setPasswordError();

        void navigateToMain();

        void showMessage(String message);
    }
}
