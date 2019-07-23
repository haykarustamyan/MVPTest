package am.highapps.mvptest.data.entity.comment;

import com.google.gson.annotations.SerializedName;

public class MakeCommentHelpfulResponseEntity {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private int data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
