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
    private final int duration;
    private int firstVisibleItemPosition;
    private final CustomLayoutManager layoutManager;

    public OnUserItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        duration = mRecyclerView.getResources().getInteger(R.integer.item_elevation_duration);
        layoutManager = (CustomLayoutManager) mRecyclerView.getLayoutManager();
    }

    @Override
    public void onClick(View view) {

        if (!(view instanceof UserListItemView)) return;

        UserListItemView itemView = (UserListItemView) view;

        if (itemView.isExpanded()) {
            collapseView(itemView);
        } else {
            expandView(itemView);
        }
    }

    private void expandView(final UserListItemView view) {
//        Log.i(TAG, "expandView, first name = " + ((RandomUser)view.getTag()).getFirstName());
        firstVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
        view.setSelected(true);
        ((RandomUser)view.getTag()).setSelected(true);

        // set delay to animate expanding after elevation finished
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionManager.beginDelayedTransition(view,
                            new TransitionSet()
                                    .addTransition(new Fade().setDuration(duration))
                                    .setStartDelay(duration / 2)
                            );
                    TransitionManager.beginDelayedTransition(mRecyclerView,
                            new TransitionSet()
                                    .addTransition(new ChangeBounds()
                                            .setDuration(duration)
                                            .setInterpolator(new DecelerateInterpolator()))
                    );
                }

                view.setExpanded(true);

                int position = layoutManager.getPosition(view);
                layoutManager.scrollToPositionWithOffset(position, 0);
                layoutManager.setScrollEnabled(false);
            }
        }, duration);
    }

    private void collapseView(final UserListItemView view) {
//        Log.i(TAG, "collapseView, first name = " + ((RandomUser)view.getTag()).getFirstName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(mRecyclerView,
                    new TransitionSet()
                            .addTransition(new ChangeBounds()
                                    .setDuration(duration)
                                    .setInterpolator(new DecelerateInterpolator()))
            );
        }

        view.setExpanded(false);
        layoutManager.setScrollEnabled(true);
        layoutManager.scrollToPositionWithOffset(firstVisibleItemPosition, 0);

        // set delay to animate elevation after expanding finished
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        view.setSelected(false);
                        ((RandomUser)view.getTag()).setSelected(false);
                    }
                }, duration);
    }

}
