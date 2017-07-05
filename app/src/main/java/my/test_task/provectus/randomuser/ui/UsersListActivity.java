package my.test_task.provectus.randomuser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import my.test_task.provectus.R;
import my.test_task.provectus.randomuser.adapter.UsersListRecyclerAdapter;
import my.test_task.provectus.randomuser.dagger.component.NonConfigurationComponent;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.presenter.UsersListPresenter;
import my.test_task.provectus.randomuser.ui.listener.OnUserItemClickListener;
import my.test_task.provectus.randomuser.ui.utils.CustomLayoutManager;
import my.test_task.provectus.randomuser.view.UsersListView;

public class UsersListActivity extends BaseActivity implements UsersListView {

    private static final String TAG = UsersListActivity.class.getSimpleName();

    @BindView(R.id.rcv_users_list)
    RecyclerView rcvUsersList;

    @Inject
    UsersListPresenter presenter;

    private UsersListRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUsersList.setLayoutManager(new CustomLayoutManager(this));
        adapter = new UsersListRecyclerAdapter(new OnUserItemClickListener(rcvUsersList));
        rcvUsersList.setAdapter(adapter);
        presenter.attach(this);

        Log.d(TAG, "savedInstanceState == " + savedInstanceState);
        if (savedInstanceState == null) {
        } else {

            Log.d(TAG, "savedInstanceState.size = " + savedInstanceState.size());
            for (String s : savedInstanceState.keySet()) {
                Log.d(TAG, "s = " + s);
            }

            Bundle bundle = savedInstanceState.getBundle("android:viewHierarchyState");
            Log.d(TAG, "bundle.size = " + bundle.size());
            for (String s : bundle.keySet()) {
                Log.d(TAG, "bundle s = " + s + ", " + bundle.getSparseParcelableArray(s));
            }
        }


        // TODO add tablet and landscape mode

    }

    @Override
    protected void inject(NonConfigurationComponent injector) {
        injector.inject(this);
    }

    @Override
    public void setRandomUsers(Collection<RandomUser> randomUsers) {
//        Log.i(TAG, "randomUsers = " + randomUsers);
        Log.i(TAG, "randomUsers size " + randomUsers.size());
        for (RandomUser user : randomUsers) {
            adapter.addUser(user);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }
}
