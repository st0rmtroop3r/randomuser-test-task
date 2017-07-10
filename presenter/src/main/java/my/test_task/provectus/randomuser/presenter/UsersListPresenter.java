package my.test_task.provectus.randomuser.presenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
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
    private Disposable randomuserApiDisposable;
    private final Collection<RandomUser> mRandomUsers = new TreeSet<>(new UserFirstNameComparator());
    private final Scheduler mMainScheduler;
    private final Scheduler mIoScheduler;
    private final DateFormat dateFormatJson = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final DateFormat dateFormatDob = new SimpleDateFormat("yyyy MMM dd");
    private final DateFormat dateFormatRegistration = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

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
        randomuserApiDisposable = restApi.getRandomUsers(20)
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
        if (randomuserApiDisposable != null) {
            randomuserApiDisposable.dispose();
        }

        mRandomUsers.addAll(users);

        if (mView == null) return;
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
        if (randomuserApiDisposable != null) {
            randomuserApiDisposable.dispose();
        }
    }

    private void fetchUser(RandomUser user) {
//        System.out.println(TAG + "fetchUser, currentThread = " + Thread.currentThread().getName());
//        System.out.println(TAG + "fetchUser, user = " + user);
        if (user == null) return;
        user.setNameTitle(capitalizeFirstLetters(user.getNameTitle()));
        user.setFirstName(capitalizeFirstLetters(user.getFirstName()));
        user.setLastName(capitalizeFirstLetters(user.getLastName()));
        user.setDateOfBirth(reformatDateOfBirth(user.getDateOfBirth()));
        user.setDateRegistered(reformatRegistrationDate(user.getDateRegistered()));
        user.setLocationCity(capitalizeFirstLetters(user.getLocationCity()));
        user.setLocationState(capitalizeFirstLetters(user.getLocationState()));
        user.setLocationStreet(capitalizeFirstLetters(user.getLocationStreet()));
    }

    private String reformatDateOfBirth(String date) {
        try {
            return dateFormatDob.format(dateFormatJson.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    private String reformatRegistrationDate(String date) {
        try {
            return dateFormatRegistration.format(dateFormatJson.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    private String capitalizeFirstLetters(String text) {
        StringBuilder capitalized = new StringBuilder();
        String[] words = text.split(" ");
        for (String word : words) {
            word = capitalizeFirstLetter(word);
            capitalized.append(word).append(" ");
        }
        words = capitalized.toString().split("-");
        capitalized = new StringBuilder();
        for (String word : words) {
            word = capitalizeFirstLetter(word);
            capitalized.append(word).append("-");
        }
        capitalized.deleteCharAt(capitalized.lastIndexOf("-"));
        return capitalized.toString().trim();
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
