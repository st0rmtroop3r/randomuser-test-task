package my.test_task.provectus.randomuser.model.entities;

import com.google.gson.annotations.SerializedName;

public class RandomUser {

    private boolean mIsSelected;

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
    private String dateRegistered;

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

    public void setSelected(boolean mIsSelected) {
        this.mIsSelected = mIsSelected;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public String getGender() {
        return gender;
    }

    public String getNameTitle() {
        if (name != null) {
            return name.getTitle();
        }
        return null;
    }

    public void setNameTitle(String title) {
        if (name != null) {
            name.setTitle(title);
        }
    }

    public String getFirstName() {
        if (name != null) {
            return name.getFirst();
        }
        return null;
    }

    public void setFirstName(String firstName) {
        if (name != null) {
            name.setFirst(firstName);
        }
    }

    public String getLastName() {
        if (name != null) {
            return name.getLast();
        }
        return null;
    }

    public void setLastName(String lastName) {
        if (name != null) {
            name.setLast(lastName);
        }
    }

    public UserLocation getLocation() {
        return location;
    }

    public String getLocationStreet() {
        if (location != null) {
            return location.getStreet();
        }
        return null;
    }

    public void setLocationStreet(String street) {
        if (location != null) {
            location.setStreet(street);
        }
    }

    public String getLocationCity() {
        if (location != null) {
            return location.getCity();
        }
        return null;
    }

    public void setLocationCity(String city) {
        if (location != null) {
            location.setCity(city);
        }
    }

    public String getLocationState() {
        if (location != null) {
            return location.getState();
        }
        return null;
    }

    public void setLocationState(String state) {
        if (location != null) {
            location.setState(state);
        }
    }

    public String getLocationPostCode() {
        if (location != null) {
            return location.getPostcode();
        }
        return null;
    }

    public String getEmail() {
        return email;
    }

    public UserLogin getLogin() {
        return login;
    }

    public String getLoginUsername() {
        if (login != null) {
            return login.getUsername();
        }
        return null;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String date) {
        dateRegistered = date;
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
//            System.out.println("userPic.getThumbnail() = " +userPic.getThumbnail());
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
                ", registered='" + dateRegistered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", userId=" + userId +
                ", userPic=" + userPic +
                ", nationality='" + nationality + '\'' +
                "} \n";
    }
}
