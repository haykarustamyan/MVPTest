package am.highapps.mvptest.data.entity.publisher;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Publisher {

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("positions")
    private List<Object> positions = null;

    @SerializedName("specializations")
    private List<Object> specializations = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Object> getPositions() {
        return positions;
    }

    public void setPositions(List<Object> positions) {
        this.positions = positions;
    }

    public List<Object> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Object> specializations) {
        this.specializations = specializations;
    }
}
