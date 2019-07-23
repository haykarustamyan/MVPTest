package am.highapps.mvptest.ui.adapter;

public class Item {

    private int viewType;
    private int commentPos;
    private int replyPos;

    public Item(int viewType, int commentPos, int replyPos) {
        this.viewType = viewType;
        this.commentPos = commentPos;
        this.replyPos = replyPos;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getCommentPos() {
        return commentPos;
    }

    public void setCommentPos(int commentPos) {
        this.commentPos = commentPos;
    }

    public int getReplyPos() {
        return replyPos;
    }

    public void setReplyPos(int replyPos) {
        this.replyPos = replyPos;
    }
}
