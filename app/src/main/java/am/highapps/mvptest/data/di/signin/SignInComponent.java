package am.highapps.mvptest.data.di.signin;

import am.highapps.mvptest.data.di.ActivityScope;
import am.highapps.mvptest.data.di.AppComponent;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {SignInModule.class})
public interface SignInComponent {

    @Component.Builder
    interface Builder {

        Builder appComponent(AppComponent component);

        SignInComponent build();
    }
}