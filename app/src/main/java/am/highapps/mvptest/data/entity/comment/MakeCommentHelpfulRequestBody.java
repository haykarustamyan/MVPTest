package am.highapps.mvptest.data.entity.comment;

public class MakeCommentHelpfulRequestBody {
    private int commentId;

    public MakeCommentHelpfulRequestBody(int commentId) {
        this.commentId = commentId;
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
