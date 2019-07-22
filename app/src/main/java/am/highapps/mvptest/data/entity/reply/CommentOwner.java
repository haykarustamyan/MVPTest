package am.highapps.mvptest.data.entity.reply;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentOwner {

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("username")
    private String username;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("patronymic")
    private Object patronymic;

    @SerializedName("address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName("gender")
    private int gender;

    @SerializedName("role")
    private String role;

    @SerializedName("imageUrl")
    private Object imageUrl;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(Object patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
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
