package am.highapps.mvptest.data.entity.topic;

import com.google.gson.annotations.SerializedName;

import am.highapps.mvptest.data.entity.publisher.Publisher;

public class TopicData {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("privatePost")
    private boolean privatePost;

    @SerializedName("createdDate")
    private String createdDate;

    @SerializedName("category")
    private String category;

    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("publisher")
    private Publisher publisher;

    @SerializedName("viewsCount")
    private int viewsCount;

    @SerializedName("participantsCount")
    private int participantsCount;

    @SerializedName("answersCount")
    private int answersCount;

    @SerializedName("status")
    private int status;

    @SerializedName("bookmarkedByUser")
    private boolean bookmarkedByUser;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivatePost() {
        return privatePost;
    }

    public void setPrivatePost(boolean privatePost) {
        this.privatePost = privatePost;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public int getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(int participantsCount) {
        this.participantsCount = participantsCount;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isBookmarkedByUser() {
        return bookmarkedByUser;
    }

    public void setBookmarkedByUser(boolean bookmarkedByUser) {
        this.bookmarkedByUser = bookmarkedByUser;
    }

    @Override
    public String toString() {
        return "TopicData{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", privatePost=" + privatePost +
                ", createdDate='" + createdDate + '\'' +
                ", category='" + category + '\'' +
                ", categoryId=" + categoryId +
                ", publisher=" + publisher +
                ", viewsCount=" + viewsCount +
                ", participantsCount=" + participantsCount +
                ", answersCount=" + answersCount +
                ", status=" + status +
                ", bookmarkedByUser=" + bookmarkedByUser +
                '}';
    }
}
