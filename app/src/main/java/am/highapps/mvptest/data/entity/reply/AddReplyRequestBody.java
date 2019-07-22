package am.highapps.mvptest.data.entity.reply;

public class AddReplyRequestBody {

    private String comment;
    private int commentId;

    public AddReplyRequestBody(String comment, int commentId) {
        this.comment = comment;
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
