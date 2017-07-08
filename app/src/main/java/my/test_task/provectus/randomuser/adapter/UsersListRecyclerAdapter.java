package my.test_task.provectus.randomuser.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.ui.utils.CustomLayoutManager;
import my.test_task.provectus.randomuser.ui.view.UserListItemView;

public class UsersListRecyclerAdapter extends RecyclerView.Adapter {

    private static final String TAG = UsersListRecyclerAdapter.class.getSimpleName();

    private final List<RandomUser> mRandomUsers = new ArrayList<>();
    private final View.OnClickListener mListener;
    private CustomLayoutManager mLayoutManager;
    Random random = new Random();

    public UsersListRecyclerAdapter(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mLayoutManager = (CustomLayoutManager) recyclerView.getLayoutManager();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.i(TAG, "onCreateViewHolder, viewType = " + viewType);
        return new ViewHolder(new UserListItemView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.i(TAG, "onBindViewHolder, position = " + position + ", first name = " + mRandomUsers.get(position).getFirstName());
        UserListItemView item = (UserListItemView) holder.itemView;
        RandomUser user = mRandomUsers.get(position);
        item.setTitle(user.getNameTitle() + " ");
        item.setName(user.getFirstName() + " " + user.getLastName());
        item.setLogin(user.getLoginUsername());
        item.setEmail(user.getEmail());
        item.setPhone(user.getPhone());
        item.setCell(user.getCell());
        item.setDob(user.getDateOfBirth());
        item.setRegistered(user.getDateRegistered());
        item.setLocation(user.getLocationStreet() + "\n" +
                user.getLocationCity() + "\n" +
                user.getLocationState() + "\n" +
                user.getLocationPostCode());

        if (random.nextBoolean()) {
            Picasso.with(item.getContext())
                    .load(user.getUserPicLarge())
                    .into(item);
        }
        item.setTag(user);

        item.setOnClickListener(mListener);

        // on restore: check if user was selected
        if (user.isSelected()) {
            item.setSelected(true);
            item.setExpanded(true);
            mLayoutManager.setScrollEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return mRandomUsers.size();
    }

    public void addUser(RandomUser user) {
        mRandomUsers.add(user);
    }

    public void clearAll() {
        mRandomUsers.clear();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        UserListItemView view;

        ViewHolder(View view) {
            super(view);
            this.view = (UserListItemView) view;
        }
    }
}
