package my.test_task.provectus.randomuser.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.ui.view.UserListItem;

public class UsersListRecyclerAdapter extends RecyclerView.Adapter {

    private static final String TAG = UsersListRecyclerAdapter.class.getSimpleName();

    private final List<RandomUser> randomUsers = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new UserListItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        Log.i(TAG, "onBindViewHolder, position = " + position);
        UserListItem item = (UserListItem) holder.itemView;
        RandomUser user = randomUsers.get(position);
        item.setTitle(user.getNameTitle() + " " + user.getFirstName() + " " + user.getLastName());
        Picasso.with(item.getContext())
                .load(user.getUserPicThumbnail())
                .fit()
                .into(item.getImvUserPic());
    }

    @Override
    public int getItemCount() {
        return randomUsers.size();
    }

    public void addUser(RandomUser user) {
        randomUsers.add(user);
    }

    public void clearAll() {
        randomUsers.clear();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        UserListItem view;

        ViewHolder(View view) {
            super(view);
            this.view = (UserListItem) view;
        }
    }
}
