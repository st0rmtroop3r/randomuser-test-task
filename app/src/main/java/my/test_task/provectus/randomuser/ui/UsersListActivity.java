package my.test_task.provectus.randomuser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import my.test_task.provectus.randomuser.dagger.component.NonConfigurationComponent;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.presenter.UsersListPresenter;
import my.test_task.provectus.randomuser.view.UsersListView;

public class UsersListActivity extends BaseActivity implements UsersListView {

    private static final String TAG = UsersListActivity.class.getSimpleName();

    @Inject
    UsersListPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter.attach(this);

    }

    @Override
    protected void inject(NonConfigurationComponent injector) {
        injector.inject(this);
    }

    @Override
    public void setRandomUsers(List<RandomUser> randomUsers) {
        Log.i(TAG, "randomUsers = " + randomUsers);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
