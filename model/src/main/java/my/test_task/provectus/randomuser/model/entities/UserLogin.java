package my.test_task.provectus.randomuser.model.entities;

public class UserLogin {

    private String username;
    private String password;
    private String salt;
    private String md5;
    private String sha1;
    private String sha256;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getMd5() {
        return md5;
    }

    public String getSha1() {
        return sha1;
    }

    public String getSha256() {
        return sha256;
    }

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
