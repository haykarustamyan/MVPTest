package am.highapps.mvptest.data.entity.reply;

import com.google.gson.annotations.SerializedName;

import am.highapps.mvptest.data.entity.comment.CommentContent;

public class AddReplyResponseEntity {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private Reply data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Reply getData() {
        return data;
    }

    public void setData(Reply data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
