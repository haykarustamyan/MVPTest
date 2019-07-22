package am.highapps.mvptest.data.entity.comment;

public class CommentRequestBody {
    private String comment;
    private int topicId;

    public CommentRequestBody(String comment, int topicId) {
        this.comment = comment;
        this.topicId = topicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
