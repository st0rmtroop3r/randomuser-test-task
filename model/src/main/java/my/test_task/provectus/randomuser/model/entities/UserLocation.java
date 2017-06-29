package my.test_task.provectus.randomuser.model.entities;

public class UserLocation {

    private String street;
    private String city;
    private String state;
    private String postcode;


    @Override
    public String toString() {
        return "UserLocation{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}
