package my.test_task.provectus.randomuser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import my.test_task.provectus.randomuser.dagger.component.NonConfigurationComponent;
import my.test_task.provectus.randomuser.model.rest.RandomuserApi;

public class UsersListActivity extends BaseActivity {

    private static final String TAG = UsersListActivity.class.getSimpleName();

    @Inject
    RandomuserApi api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "api = " + api);
        api.getRandomUsers(2)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    @Override
    protected void inject(NonConfigurationComponent injector) {
        injector.inject(this);
    }
}
