package am.highapps.mvptest.data.di;


import am.highapps.mvptest.data.di.login.LoginModule;
import am.highapps.mvptest.data.di.main.MainModule;
import am.highapps.mvptest.ui.login.LoginActivity;
import am.highapps.mvptest.ui.main.MainActivity;
import am.highapps.mvptest.ui.main.MainFragmentActions;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginActivity bindLoginActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

    @FragmentScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainFragmentActions bindMainFragment();

}
