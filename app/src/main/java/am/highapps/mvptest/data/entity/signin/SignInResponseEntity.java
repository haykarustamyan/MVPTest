package am.highapps.mvptest.data.entity.signin;

import com.google.gson.annotations.SerializedName;

public class SignInResponseEntity {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private TokenResponseData data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public TokenResponseData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
