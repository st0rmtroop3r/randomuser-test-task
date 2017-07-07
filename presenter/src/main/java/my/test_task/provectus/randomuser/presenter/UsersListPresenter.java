package my.test_task.provectus.randomuser.presenter;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.model.rest.RandomuserApi;
import my.test_task.provectus.randomuser.model.utils.UserFirstNameComparator;
import my.test_task.provectus.randomuser.view.UsersListView;

import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_IO;
import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_MAIN;

@Singleton
public class UsersListPresenter {

    private static final String TAG = UsersListPresenter.class.getSimpleName() + ": ";

    private UsersListView mView;
    private final Collection<RandomUser> mRandomUsers = new TreeSet<>(new UserFirstNameComparator());
    private final Scheduler mMainScheduler;
    private final Scheduler mIoScheduler;

    @Inject
    RandomuserApi restApi;

    @Inject
    UsersListPresenter(
            @Named(SCHEDULER_MAIN) Scheduler mainScheduler,
            @Named(SCHEDULER_IO) Scheduler ioScheduler) {
        mMainScheduler = mainScheduler;
        mIoScheduler = ioScheduler;
    }

    public void attach(UsersListView view) {

        if (mView != null) {
            detach();
        }
        mView = view;

        if (!mRandomUsers.isEmpty()) {
            mView.setRandomUsers(mRandomUsers);
        } else {
            requestRandomUsers();
        }

    }

    private void requestRandomUsers() {
        restApi.getRandomUsers(20)
                .subscribeOn(mIoScheduler)
                .flatMapIterable(response -> response.getRandomUsers())
                .filter(user -> user != null)
                .doOnNext(user -> fetchUser(user))
                .toList()
                .observeOn(mMainScheduler)
                .subscribe(
                        users -> onUsersLoaded(users),
                        throwable -> onHttpRequestError(throwable));
    }

    private void onUsersLoaded(List<RandomUser> users) {

        mRandomUsers.addAll(users);

        mView.setRandomUsers(mRandomUsers);
    }

    private void onHttpRequestError(Throwable throwable) {
        System.err.println(TAG + "throwable = " + throwable);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

    public void detach() {
        mView = null;
    }

    private void fetchUser(RandomUser user) {
//        System.out.println(TAG + "fetchUser, currentThread = " + Thread.currentThread().getName());
//        System.out.println(TAG + "fetchUser, user = " + user);
        if (user == null) return;
        user.setNameTitle(capitalizeFirstLetter(user.getNameTitle()));
        user.setFirstName(capitalizeFirstLetter(user.getFirstName()));
        user.setLastName(capitalizeFirstLetter(user.getLastName()));
    }

    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) return text;
        if (text.length() > 1) {
            return String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
        } else {
            return String.valueOf(text.charAt(0)).toUpperCase();
        }
    }
}
