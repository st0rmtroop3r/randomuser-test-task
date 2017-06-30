package my.test_task.provectus.randomuser.ui.view;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.test_task.provectus.R;
import my.test_task.provectus.randomuser.view.UsersListView;

public class UserListItemView extends RelativeLayout implements Target {

    private static final String TAG = UsersListView.class.getSimpleName();

    @BindView(R.id.imv_user_pic)
    ImageView imvUserPic;

    @BindView(R.id.txv_user_title)
    TextView txvUserTitle;

    public UserListItemView(Context context) {
        super(context);
        init(context);
    }

    public UserListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.widget_user_item, this);
        ButterKnife.bind(this, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                getResources().getDimensionPixelSize(R.dimen.dimen_4dp),
                getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                getResources().getDimensionPixelSize(R.dimen.dimen_4dp));
        setLayoutParams(layoutParams);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator animator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.user_item_elevation);
            setStateListAnimator(animator);
        }

        setBackground(getResources().getDrawable(R.drawable.rounded_corners_solid));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d(TAG, "onClick, view =" + view);
            }
        });
    }

    public void setTitle(CharSequence title) {
        txvUserTitle.setText(title);
    }

    public void setUserPic(Drawable drawable) {
        imvUserPic.setImageDrawable(drawable);
    }

    public ImageView getUserPicImageView() {
        return imvUserPic;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        imvUserPic.setBackground(new BitmapDrawable(bitmap));
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {
        imvUserPic.setBackground(errorDrawable);
    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {
        imvUserPic.setBackground(placeHolderDrawable);
    }
}
