package am.highapps.mvptest.data.di.login;

import am.highapps.mvptest.data.di.ActivityScope;
import am.highapps.mvptest.data.di.AppComponent;
import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {LoginModule.class})
public interface LoginComponent {

    @Component.Builder
    interface Builder {

        Builder appComponent(AppComponent component);

        LoginComponent build();
    }
}
