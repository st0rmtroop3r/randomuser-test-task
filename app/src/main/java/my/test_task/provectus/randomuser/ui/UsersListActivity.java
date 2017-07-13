package my.test_task.provectus.randomuser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

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
import my.test_task.provectus.randomuser.ui.view.UserListItemView;
import my.test_task.provectus.randomuser.view.UsersListView;

/** View implementation of {@link UsersListView} */
public class UsersListActivity extends BaseActivity implements UsersListView {

    private static final String TAG = UsersListActivity.class.getSimpleName();

    /** RecyclerView for UserListItemView */
    @BindView(R.id.rcv_users_list)
    RecyclerView rcvUsersList;

    /** Presenter */
    @Inject
    UsersListPresenter presenter;

    /** {@link UserListItemView} adapter for {@link RecyclerView}*/
    private UsersListRecyclerAdapter adapter;

    /** {@link UserListItemView} OnClickListener*/
    private OnUserItemClickListener userItemClickListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvUsersList.setLayoutManager(new CustomLayoutManager(this));
        userItemClickListener = new OnUserItemClickListener(rcvUsersList);
        adapter = new UsersListRecyclerAdapter(userItemClickListener);
        rcvUsersList.setAdapter(adapter);
        presenter.attach(this);

        // TODO add tablet mode

    }

    @Override
    public void onBackPressed() {
        if (userItemClickListener.isItemExpanded()) {
            userItemClickListener.collapseItem();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void inject(NonConfigurationComponent injector) {
        injector.inject(this);
    }

    @Override
    public void setRandomUsers(Collection<RandomUser> randomUsers) {
//        Log.i(TAG, "randomUsers size " + randomUsers.size());
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
