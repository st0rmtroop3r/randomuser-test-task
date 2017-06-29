package my.test_task.provectus.randomuser.model.entities;

public class UserLogin {

    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }
}
