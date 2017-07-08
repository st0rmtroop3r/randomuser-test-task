package my.test_task.provectus.randomuser.ui.listener;

import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import my.test_task.provectus.R;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import my.test_task.provectus.randomuser.ui.utils.CustomLayoutManager;
import my.test_task.provectus.randomuser.ui.view.UserListItemView;

public class OnUserItemClickListener implements View.OnClickListener {

    private static final String TAG = OnUserItemClickListener.class.getSimpleName();

    private final RecyclerView mRecyclerView;
    private final CustomLayoutManager mLayoutManager;
    private final int mDuration;
    private int mFirstVisibleItemPosition;
    private UserListItemView mItemView;

    public OnUserItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mDuration = mRecyclerView.getResources().getInteger(R.integer.item_elevation_duration);
        mLayoutManager = (CustomLayoutManager) mRecyclerView.getLayoutManager();
    }

    @Override
    public void onClick(View view) {

        if (!(view instanceof UserListItemView)) return;

        mItemView = (UserListItemView) view;

        if (mItemView.isExpanded()) {
            collapseView(mItemView);
        } else {
            expandView(mItemView);
        }
    }

    private void expandView(final UserListItemView view) {
//        Log.i(TAG, "expandView, first name = " + ((RandomUser)view.getTag()).getFirstName());
        mFirstVisibleItemPosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        view.setSelected(true);
        ((RandomUser)view.getTag()).setSelected(true);

        // set delay to animate expanding after elevation finished
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionManager.beginDelayedTransition(view,
                            new TransitionSet()
                                    .addTransition(new Fade().setDuration(mDuration))
                                    .setStartDelay(mDuration / 2)
                            );
                    TransitionManager.beginDelayedTransition(mRecyclerView,
                            new TransitionSet()
                                    .addTransition(new ChangeBounds()
                                            .setDuration(mDuration)
                                            .setInterpolator(new DecelerateInterpolator()))
                    );
                }

                view.setExpanded(true);

                int position = mLayoutManager.getPosition(view);
                mLayoutManager.scrollToPositionWithOffset(position, 0);
                mLayoutManager.setScrollEnabled(false);
            }
        }, mDuration);
    }

    private void collapseView(final UserListItemView view) {
//        Log.i(TAG, "collapseView, first name = " + ((RandomUser)view.getTag()).getFirstName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(mRecyclerView,
                    new TransitionSet()
                            .addTransition(new ChangeBounds()
                                    .setDuration(mDuration)
                                    .setInterpolator(new DecelerateInterpolator()))
            );
        }

        view.setExpanded(false);
        mLayoutManager.setScrollEnabled(true);
        mLayoutManager.scrollToPositionWithOffset(mFirstVisibleItemPosition, 0);

        // set delay to animate elevation after expanding finished
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        view.setSelected(false);
                        ((RandomUser)view.getTag()).setSelected(false);
                    }
                }, mDuration);
    }

    public boolean isItemExpanded() {
        return mItemView != null && mItemView.isExpanded();
    }

    public void collapseItem() {
        if (mItemView != null) {
            collapseView(mItemView);
        }
    }
}
