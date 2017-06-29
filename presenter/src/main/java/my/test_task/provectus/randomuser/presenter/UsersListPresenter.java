package my.test_task.provectus.randomuser.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.model.rest.RandomuserApi;
import my.test_task.provectus.randomuser.view.UsersListView;

public class UsersListPresenter {

    private static final String TAG = UsersListPresenter.class.getSimpleName();

    private UsersListView mView;
    private final List<RandomUser> mRandomUsers = new ArrayList<>();

    @Inject
    RandomuserApi api;

    @Inject
    UsersListPresenter() {
        System.out.println("UsersListPresenter constructor");
    }

    public void attach(UsersListView view) {
        if (mView != null) {
            detach();
        }
        mView = view;

        if (!mRandomUsers.isEmpty()) {
            mView.setRandomUsers(mRandomUsers);
        }

        api.getRandomUsers(2)
                .subscribeOn(Schedulers.io())
                .subscribe(response -> onUsersLoaded(response.getRandomUsers()));
    }

    private void onUsersLoaded(List<RandomUser> users) {
        mRandomUsers.addAll(users);

        mView.setRandomUsers(mRandomUsers);
    }

    public void detach() {
        mView = null;
    }
}
