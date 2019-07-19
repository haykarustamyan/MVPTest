package am.highapps.mvptest.data.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;



import javax.inject.Singleton;

import am.highapps.mvptest.MVPApp;
import am.highapps.mvptest.data.api.ApiFactory;
import am.highapps.mvptest.data.api.MVPTestAPI;
import am.highapps.mvptest.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;

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
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context);
    }

    @Singleton
    @Provides
    MVPTestAPI provideGitHubService() {
        return ApiFactory.getMVPtestService(BASE_URL);
    }

//    @Singleton
//    @Provides
//    GitHubRemoteDataSource provideGitHubRemoteDataSource(GitHubAPI gitHubService) {
//        return new GitHubRemoteDataSource(gitHubService);
//    }
//
//    @Singleton
//    @Provides
//    GitHubLocalDataSource provideGitHubLocalDataSource() {
//        return new GitHubLocalDataSource();
//    }
//
//    @Singleton
//    @Provides
//    GitHubDataSource provideGitHubRepository(GitHubLocalDataSource gitHubLocalDataSource,
//                                             GitHubRemoteDataSource gitHubRemoteDataSource) {
//        return new GitHubRepository(gitHubLocalDataSource, gitHubRemoteDataSource);
//    }

}
