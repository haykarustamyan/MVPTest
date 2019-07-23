package am.highapps.mvptest.data.di;


import am.highapps.mvptest.data.di.signin.SignInModule;
import am.highapps.mvptest.data.di.main.MainModule;
import am.highapps.mvptest.ui.signin.SignInActivity;
import am.highapps.mvptest.ui.main.MainActivity;
import am.highapps.mvptest.ui.main.MainFragmentActions;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract SignInActivity bindSignInActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainFragmentActions bindMainFragment();

}
