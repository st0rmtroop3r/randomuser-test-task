package my.test_task.provectus.randomuser.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.model.rest.RandomuserApi;
import my.test_task.provectus.randomuser.view.UsersListView;

import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_IO;
import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_MAIN;

public class UsersListPresenter {

    private static final String TAG = UsersListPresenter.class.getSimpleName() + ": ";

    private UsersListView mView;
    private final List<RandomUser> mRandomUsers = new ArrayList<>();
    private final Scheduler mMainScheduler;
    private final Scheduler mIoScheduler;

    @Inject
    RandomuserApi api;

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
        }

        api.getRandomUsers(20)
                .subscribeOn(mIoScheduler)
                .observeOn(mMainScheduler)
                .subscribe(response -> onUsersLoaded(response.getRandomUsers()),
                        throwable -> onHttpRequestError(throwable));
    }

    private void onUsersLoaded(List<RandomUser> users) {

        mRandomUsers.addAll(users);

        mView.setRandomUsers(mRandomUsers);
    }

    private void onHttpRequestError(Throwable throwable) {
        System.out.println(TAG + "throwable = " + throwable);
    }

    public void detach() {
        mView = null;
    }
}
