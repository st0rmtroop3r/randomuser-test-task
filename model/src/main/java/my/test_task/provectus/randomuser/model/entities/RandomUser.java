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

    public String getGender() {
        return gender;
    }

    public String getNameTitle() {
        if (name != null) {
            return name.getTitle();
        }
        return null;
    }

    public String getFirstName() {
        if (name != null) {
            return name.getFirst();
        }
        return null;
    }

    public String getLastName() {
        if (name != null) {
            return name.getLast();
        }
        return null;
    }

    public UserLocation getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public UserLogin getLogin() {
        return login;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRegistered() {
        return registered;
    }

    public String getPhone() {
        return phone;
    }

    public String getCell() {
        return cell;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserPicLarge() {
        if (userPic != null) {
            return userPic.getLarge();
        }
        return null;
    }

    public String getUserPicMedium() {
        if (userPic != null) {
            return userPic.getMedium();
        }
        return null;
    }

    public String getUserPicThumbnail() {
        if (userPic != null) {
            System.out.println("userPic.getThumbnail() = " +userPic.getThumbnail());
            return userPic.getThumbnail();
        }
        return null;
    }

    public String getNationality() {
        return nationality;
    }

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
