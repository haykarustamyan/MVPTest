package am.highapps.mvptest.data.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


import javax.inject.Singleton;

import am.highapps.mvptest.MVPApp;
import am.highapps.mvptest.data.api.ApiFactory;
import am.highapps.mvptest.data.api.MVPTestAPI;
import am.highapps.mvptest.data.repository.MVPTestDataSource;
import am.highapps.mvptest.data.repository.remote.MVPTestRemoteDataSource;
import am.highapps.mvptest.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static am.highapps.mvptest.data.api.ApiFactory.Url.BASE_URL;


@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(MVPApp application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Singleton
    @Provides
    MVPTestDataSource provideMvpTestRemoteDataSource(MVPTestAPI mvpTestAPI) {
        return new MVPTestRemoteDataSource(mvpTestAPI);
    }

    @Provides
    CompositeDisposable bindsLoginCompositeDisposable() {
        return new CompositeDisposable();
    }


//    @Singleton
//    @Provides
//    GitHubLocalDataSource provideGitHubLocalDataSource() {
//        return new GitHubLocalDataSource();
//    }
//
//    @Singleton
//    @Provides
//    GitHubDataSource provideGitHubRepository(GitHubLocalDataSource gitHubLocalDataSource,
//                                             MVPTestRemoteDataSource gitHubRemoteDataSource) {
//        return new GitHubRepository(gitHubLocalDataSource, gitHubRemoteDataSource);
//    }

}
