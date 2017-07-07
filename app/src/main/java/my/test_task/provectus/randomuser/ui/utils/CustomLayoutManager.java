package my.test_task.provectus.randomuser.ui.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class CustomLayoutManager extends LinearLayoutManager {

    private boolean mIsScrollEnabled = true;

    public CustomLayoutManager(Context context) {
        super(context);
    }

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean enabled) {
        this.mIsScrollEnabled = enabled;
    }

    @Override
    public boolean canScrollVertically() {
        return mIsScrollEnabled && super.canScrollVertically();
    }
}
