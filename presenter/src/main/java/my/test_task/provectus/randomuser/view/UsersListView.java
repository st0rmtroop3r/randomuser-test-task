package my.test_task.provectus.randomuser.view;

import java.util.Collection;

import my.test_task.provectus.randomuser.model.entities.RandomUser;

public interface UsersListView {
    void setRandomUsers(Collection<RandomUser> randomUsers);
}
