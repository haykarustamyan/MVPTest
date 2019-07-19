package am.highapps.mvptest.login;

import am.highapps.mvptest.base.BasePresenter;
import am.highapps.mvptest.base.BaseView;

public interface LoginContract {

    interface LoginPresenter extends BasePresenter {

        void validateCredentials(String username, String password);

        void onDestroy();
    }

    interface LoginView extends BaseView<LoginPresenter> {

        void setUsernameError();

        void setPasswordError();

        void navigateToHome();
    }
}
