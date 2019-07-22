package am.highapps.mvptest.data.di.network;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Named;
import javax.inject.Singleton;

import am.highapps.mvptest.util.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

import static am.highapps.mvptest.util.Constant.Pref.PREF_USER_TOKEN;

@Module
public class NetworkModule {


    @Singleton
    @Provides
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@Named("logging") HttpLoggingInterceptor loggingInterceptor,
                                     SharedPreferences preferences) {

        OkHttpClient okhttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addNetworkInterceptor(chain -> {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("Content-Type", "application/json;charset=utf-8");
                    if (preferences.getString(PREF_USER_TOKEN, null) != null) {
                        builder.addHeader("Authorization", "Bearer " + preferences.getString(PREF_USER_TOKEN, ""));
                    }
                    return chain.proceed(builder.build());
                })
                .build();
        return okhttpClient;
    }




    @Provides
    @Named("logging")
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


}