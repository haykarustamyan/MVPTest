package am.highapps.mvptest.data.api;


import am.highapps.mvptest.data.entity.login.SignInRequestBody;
import am.highapps.mvptest.data.entity.login.UserResponseEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MVPTestAPI {

    @POST(ApiFactory.Url.SIGN_IN)
    Observable<SignInRequestBody> getSignInData(@Body UserResponseEntity userResponseEntity);
}
