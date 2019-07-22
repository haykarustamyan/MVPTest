package am.highapps.mvptest.data.di.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

import javax.inject.Singleton;

import am.highapps.mvptest.data.api.ApiFactory;
import am.highapps.mvptest.data.api.MVPTestAPI;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public MVPTestAPI ProvidesService(Retrofit mvpRetrofit) {
        return mvpRetrofit.create(MVPTestAPI.class);
    }

    @Provides
    @Singleton
    public Gson ProvidesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ApiFactory.Url.BASE_URL)
                .client(okHttpClient)
                //.callbackExecutor(Executors.newFixedThreadPool(5))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

}
