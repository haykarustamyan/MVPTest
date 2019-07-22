package am.highapps.mvptest.data.entity.signin;

import com.google.gson.annotations.SerializedName;

public class TokenResponseData {

    @SerializedName("token")
    private String token;

    @SerializedName("role")
    private String role;

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }


}
