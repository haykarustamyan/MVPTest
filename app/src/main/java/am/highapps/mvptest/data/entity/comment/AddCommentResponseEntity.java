package am.highapps.mvptest.data.entity.comment;

import com.google.gson.annotations.SerializedName;

public class AddCommentResponseEntity {

    @SerializedName("success")
    private boolean success;

    @SerializedName("data")
    private CommentContent data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CommentContent getData() {
        return data;
    }

    public void setData(CommentContent data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
