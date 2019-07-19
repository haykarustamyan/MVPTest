package am.highapps.mvptest.data.entity.login;

import com.google.gson.annotations.SerializedName;

public class SignInRequestBody {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private TokenResponseEntity data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public TokenResponseEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

}
