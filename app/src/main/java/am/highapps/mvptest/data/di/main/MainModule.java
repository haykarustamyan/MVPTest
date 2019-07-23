package am.highapps.mvptest.data.di.main;

import am.highapps.mvptest.data.di.FragmentScope;
import am.highapps.mvptest.ui.main.MainFragmentActions;
import am.highapps.mvptest.ui.main.MainFragmentContract;
import am.highapps.mvptest.ui.main.MainFragmentPresenterImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @Binds
    @FragmentScope
    abstract MainFragmentContract.MainFragmentView bindsMainView(MainFragmentActions mainActivity);

    @Binds
    @FragmentScope
    abstract MainFragmentContract.MainFragmentPresenter bindsMainPresenter(MainFragmentPresenterImpl mainPresenter);

}
