package my.test_task.provectus.randomuser.ui.listener;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.ChangeScroll;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import my.test_task.provectus.R;
import my.test_task.provectus.randomuser.ui.view.UserListItemView;

public class OnUserItemClickListener implements View.OnClickListener {

    private static final String TAG = OnUserItemClickListener.class.getSimpleName();

    private final RecyclerView mRecyclerView;
    private final int duration;
    private float y;

    public OnUserItemClickListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        duration = mRecyclerView.getResources().getInteger(R.integer.item_elevation_duration);
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");

        if (!(view instanceof UserListItemView)) return;

        UserListItemView itemView = (UserListItemView) view;

        if (itemView.isExpanded()) {
            collapseView(itemView);
        } else {
            expandView(itemView);
        }

        itemView.setExpanded(!itemView.isExpanded());
    }

    private void expandView(final View view) {

        StateListAnimator animator = AnimatorInflater
                .loadStateListAnimator(view.getContext(), R.animator.elevation_increase);
        view.setStateListAnimator(animator);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    TransitionManager.beginDelayedTransition((ViewGroup) view,
                            new TransitionSet()
                                    .addTransition(new ChangeBounds().setDuration(duration).setInterpolator(new DecelerateInterpolator()))
                                    .addTransition(new ChangeScroll().setDuration(duration))
                    );
                }

                RecyclerView.MarginLayoutParams marginParams = (RecyclerView.MarginLayoutParams) view.getLayoutParams();
                marginParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                int margin = view.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
                marginParams.setMargins(margin, margin, margin, margin);
                view.setLayoutParams(marginParams);

                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                int position = layoutManager.getPosition(view);
                layoutManager.scrollToPositionWithOffset(position, 0);
            }
        }, duration);
    }

    private void collapseView(final View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(mRecyclerView,
                    new TransitionSet()
                            .addTransition(new ChangeBounds().setDuration(duration).setInterpolator(new DecelerateInterpolator()))
                            .addTransition(new ChangeScroll().setDuration(duration))
//                            .addTransition(new ChangeClipBounds().setDuration(duration))
            );
        }

        RecyclerView.MarginLayoutParams marginParams = (RecyclerView.MarginLayoutParams) view.getLayoutParams();
        marginParams.setMargins(
                view.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                view.getResources().getDimensionPixelSize(R.dimen.dimen_4dp),
                view.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                view.getResources().getDimensionPixelSize(R.dimen.dimen_4dp)
        );
        marginParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(marginParams);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        view.setStateListAnimator(
                                AnimatorInflater.loadStateListAnimator(view.getContext(),
                                        R.animator.elevation_decrease));
                    }
                },
                duration
        );

    }

}
