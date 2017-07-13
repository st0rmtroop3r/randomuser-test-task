package my.test_task.provectus.randomuser.view;

import java.util.Collection;

import my.test_task.provectus.randomuser.model.entities.RandomUser;

/** Interface for Random user list View. Provides access to View from Presenter. */
public interface UsersListView {

    /**
     * Passes a collection of {@link RandomUser} to View
     * @param randomUsers a collection of {@link RandomUser} to display
     */
    void setRandomUsers(Collection<RandomUser> randomUsers);

}
