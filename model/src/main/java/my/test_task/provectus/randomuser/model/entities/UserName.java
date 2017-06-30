package my.test_task.provectus.randomuser.model.entities;

public class UserName {

    private String title;
    private String first;
    private String last;

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return "UserName{" +
                "title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
