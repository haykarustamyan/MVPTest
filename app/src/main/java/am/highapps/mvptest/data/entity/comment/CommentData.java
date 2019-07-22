package am.highapps.mvptest.data.entity.comment;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentData {

    @SerializedName("content")
    private List<CommentContent> content = null;

    @SerializedName("pageable")
    private Pageable pageable;

    @SerializedName("totalElements")
    private int totalElements;

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("last")
    private boolean last;

    @SerializedName("first")
    private boolean first;

    @SerializedName("sort")
    private Sort sort;

    @SerializedName("numberOfElements")
    private int numberOfElements;

    @SerializedName("size")
    private int size;

    @SerializedName("number")
    private int number;

    public List<CommentContent> getContent() {
        return content;
    }

    public void setContent(List<CommentContent> content) {
        this.content = content;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
