package am.highapps.mvptest.data.di.signin;

import am.highapps.mvptest.data.di.ActivityScope;
import am.highapps.mvptest.ui.signin.SignInActivity;
import am.highapps.mvptest.ui.signin.SignInContract;
import am.highapps.mvptest.ui.signin.SignInInteractor;
import am.highapps.mvptest.ui.signin.SignInInteractorImpl;
import am.highapps.mvptest.ui.signin.SignInPresenterImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class SignInModule {

    @Binds
    @ActivityScope
    abstract SignInInteractor bindsSignInInteractorImpl(SignInInteractorImpl signInInteractor);

    @Binds
    @ActivityScope
    abstract SignInContract.SignInView bindsSignInView(SignInActivity signInActivity);

    @Binds
    @ActivityScope
    abstract SignInContract.SignInPresenter bindsSignInPresenter(SignInPresenterImpl signInPresenter);

}
