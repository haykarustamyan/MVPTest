package am.highapps.mvptest.data.entity.reply;

import com.google.gson.annotations.SerializedName;

import am.highapps.mvptest.data.entity.publisher.Publisher;

public class Reply {

    @SerializedName("id")
    private int id;

    @SerializedName("comment")
    private String comment;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("helpfulAnswersCount")
    private int helpfulAnswersCount;

    @SerializedName("currentUserVote")
    private boolean currentUserVote;

    @SerializedName("currentUserReport")
    private boolean currentUserReport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getHelpfulAnswersCount() {
        return helpfulAnswersCount;
    }

    public void setHelpfulAnswersCount(int helpfulAnswersCount) {
        this.helpfulAnswersCount = helpfulAnswersCount;
    }

    public boolean isCurrentUserVote() {
        return currentUserVote;
    }

    public void setCurrentUserVote(boolean currentUserVote) {
        this.currentUserVote = currentUserVote;
    }

    public boolean isCurrentUserReport() {
        return currentUserReport;
    }

    public void setCurrentUserReport(boolean currentUserReport) {
        this.currentUserReport = currentUserReport;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", publisher=" + publisher +
                ", helpfulAnswersCount=" + helpfulAnswersCount +
                ", currentUserVote=" + currentUserVote +
                ", currentUserReport=" + currentUserReport +
                '}';
    }
}
