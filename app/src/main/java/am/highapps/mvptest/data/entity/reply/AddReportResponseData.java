package am.highapps.mvptest.data.entity.reply;

import com.google.gson.annotations.SerializedName;

import am.highapps.mvptest.data.entity.publisher.Publisher;

public class AddReportResponseData {

    @SerializedName("reasonMessage")
    private String reasonMessage;

    @SerializedName("seen")
    private boolean seen;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("commentOwner")
    private CommentOwner commentOwner;

    @SerializedName("typeId")
    private int typeId;

    @SerializedName("reportId")
    private int reportId;

    @SerializedName("topicId")
    private int topicId;

    @SerializedName("commentOrReply")
    private String commentOrReply;

    @SerializedName("reportType")
    private int reportType;

    @SerializedName("reportStatus")
    private int reportStatus;

    @SerializedName("date")
    private String date;

    public String getReasonMessage() {
        return reasonMessage;
    }

    public void setReasonMessage(String reasonMessage) {
        this.reasonMessage = reasonMessage;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public CommentOwner getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(CommentOwner commentOwner) {
        this.commentOwner = commentOwner;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getCommentOrReply() {
        return commentOrReply;
    }

    public void setCommentOrReply(String commentOrReply) {
        this.commentOrReply = commentOrReply;
    }

    public int getReportType() {
        return reportType;
    }

    public void setReportType(int reportType) {
        this.reportType = reportType;
    }

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
