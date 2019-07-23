package am.highapps.mvptest.data.entity.user;

import com.google.gson.annotations.SerializedName;

public class UserDetailsResponseEntity {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private UserDetailsData data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserDetailsData getData() {
        return data;
    }

    public void setData(UserDetailsData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
