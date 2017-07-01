package my.test_task.provectus.randomuser.model.utils;


import java.util.Comparator;

import my.test_task.provectus.randomuser.model.entities.RandomUser;

public class UserFirstNameComparator implements Comparator<RandomUser> {

    @Override
    public int compare(RandomUser u1, RandomUser u2) {
        return u1.getFirstName().compareToIgnoreCase(u2.getFirstName());
    }
}
