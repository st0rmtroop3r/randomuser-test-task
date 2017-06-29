package my.test_task.provectus.randomuser.model.entities;

public class UserPicture {

    private String large;
    private String medium;
    private String thumbnail;

    @Override
    public String toString() {
        return "UserPicture{" +
                "large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
