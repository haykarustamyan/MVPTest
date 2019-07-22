package am.highapps.mvptest.data.entity.topic;

import com.google.gson.annotations.SerializedName;

public class TopicResponseEntity {
    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private TopicData data;
    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public TopicData getData() {
        return data;
    }

    public void setData(TopicData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
