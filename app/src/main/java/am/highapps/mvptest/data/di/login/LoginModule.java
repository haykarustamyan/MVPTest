package am.highapps.mvptest.data.di.login;

import am.highapps.mvptest.data.di.ActivityScope;
import am.highapps.mvptest.ui.login.LoginActivity;
import am.highapps.mvptest.ui.login.LoginContract;
import am.highapps.mvptest.ui.login.LoginInteractor;
import am.highapps.mvptest.ui.login.LoginInteractorImpl;
import am.highapps.mvptest.ui.login.LoginPresenterImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginModule {

    @Binds
    @ActivityScope
    abstract LoginInteractor bindsLoginInteractorImpl(LoginInteractorImpl loginInteractor);

    @Binds
    @ActivityScope
    abstract LoginContract.LoginView bindsLoginView(LoginActivity loginActivity);

    @Binds
    @ActivityScope
    abstract LoginContract.LoginPresenter bindsLoginPresenter(LoginPresenterImpl loginPresenter);

}
