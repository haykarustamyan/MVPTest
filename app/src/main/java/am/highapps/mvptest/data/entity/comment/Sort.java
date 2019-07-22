package am.highapps.mvptest.data.entity.comment;

import com.google.gson.annotations.SerializedName;

public class Sort {

    @SerializedName("sorted")
    private boolean sorted;

    @SerializedName("unsorted")
    private boolean unsorted;

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }

    public boolean isUnsorted() {
        return unsorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.unsorted = unsorted;
    }

}
