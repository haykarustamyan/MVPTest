package am.highapps.mvptest.ui.login;

import am.highapps.mvptest.base.BasePresenter;
import am.highapps.mvptest.base.BaseView;

public interface LoginContract {

    interface LoginPresenter extends BasePresenter {

        boolean isUserAuthDone();

        void validateCredentials(String username, String password);

        void onDestroy();
    }

    interface LoginView extends BaseView<LoginPresenter> {

        void setIdError();

        void setPasswordError();

        void navigateToMain();

        void showMessage(String message);
    }
}
