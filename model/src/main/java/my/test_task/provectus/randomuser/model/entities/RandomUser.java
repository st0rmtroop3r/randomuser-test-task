package my.test_task.provectus.randomuser.model.entities;

import com.google.gson.annotations.SerializedName;

public class RandomUser {

    @SerializedName("gender")
    private String gender;

    @SerializedName("name")
    private UserName name;

    @SerializedName("location")
    private UserLocation location;

    @SerializedName("email")
    private String email;

    @SerializedName("login")
    private UserLogin login;

    @SerializedName("dob")
    private String dateOfBirth;

    @SerializedName("registered")
    private String registered;

    @SerializedName("phone")
    private String phone;

    @SerializedName("cell")
    private String cell;

    @SerializedName("id")
    private UserId userId;

    @SerializedName("picture")
    private UserPicture userPic;

    @SerializedName("nat")
    private String nationality;

    @Override
    public String toString() {
        return "RandomUser{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", login=" + login +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", userId=" + userId +
                ", userPic=" + userPic +
                ", nationality='" + nationality + '\'' +
                "} \n";
    }
}
