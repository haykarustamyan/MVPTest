package am.highapps.mvptest.ui.adapter;

public class CommentContentItem {

    private int position;
    private Object item;

    public CommentContentItem(int position, Object item) {
        this.position = position;
        this.item = item;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
