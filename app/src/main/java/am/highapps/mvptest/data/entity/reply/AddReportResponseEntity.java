package am.highapps.mvptest.data.entity.reply;

import com.google.gson.annotations.SerializedName;

public class AddReportResponseEntity {

    @SerializedName("success")

    private boolean success;

    @SerializedName("data")
    private AddReportResponseData data;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public AddReportResponseData getData() {
        return data;
    }

    public void setData(AddReportResponseData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
