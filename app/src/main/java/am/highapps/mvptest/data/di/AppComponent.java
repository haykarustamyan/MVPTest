package am.highapps.mvptest.data.di;

import javax.inject.Singleton;

import am.highapps.mvptest.MVPApp;
import am.highapps.mvptest.data.di.network.NetworkModule;
import am.highapps.mvptest.data.di.network.ServiceModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ServiceModule.class,
        BuildersModule.class})
public interface AppComponent extends AndroidInjector<MVPApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MVPApp application);

        AppComponent build();
    }

}
