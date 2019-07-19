package am.highapps.mvptest.data.api;

import am.highapps.mvptest.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static class Url {
        public static final String BASE_URL = "https://mcapi.armlon.co.uk/";
        public static final String SIGN_IN = "api/token/signIn";
        public static final String GET_USER_DETAILS = "patient/getUserDetails";
        public static final String GET_TOPIC_BY_ID = "patient/getTopicById";
        public static final String GET_TOPIC_COMENTS = "patient/getTopicComments";
        public static final String ADD_COMMENT = "forumComment/addComment";
        public static final String MAKE_COMMENT_HELPFUL = "forumComment/makeCommentHelpful";
        public static final String REMOVE_COMMENT_HELPFUL = "forumComment/removeHelpfulComment";
        public static final String ADD_REPORT = "forumReport/addReport";
        public static final String MAKE_REPLY_HELPFUL = "forumComment/makeReplyHelpful";
        public static final String REMOVE_REPLY_HELPFUL = "forumComment/removeHelpfulReply";
    }

    public static MVPTestAPI getMVPtestService(String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }

        OkHttpClient okHttpClient = httpClient.build();

        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .build();

            return chain.proceed(request);
        });


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                //.callbackExecutor(Executors.newFixedThreadPool(5))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MVPTestAPI.class);
    }

}
