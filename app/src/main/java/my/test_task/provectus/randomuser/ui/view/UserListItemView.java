package my.test_task.provectus.randomuser.ui.view;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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

    @BindView(R.id.txv_user_name)
    TextView txvUserName;

    @BindView(R.id.imv_close)
    ImageView imvClose;

    @BindView(R.id.scv_user_details)
    ScrollView scrollView;

    @BindView(R.id.txv_user_login)
    TextView txvLogin;

    @BindView(R.id.txv_user_login_label)
    TextView txvLoginLabel;

    @BindView(R.id.txv_email)
    TextView txvEmail;

    @BindView(R.id.txv_email_label)
    TextView txvEmailLabel;

    @BindView(R.id.txv_phone)
    TextView txvPhone;

    @BindView(R.id.txv_phone_label)
    TextView txvPhoneLabel;

    @BindView(R.id.txv_cell)
    TextView txvCell;

    @BindView(R.id.txv_cell_label)
    TextView txvCellLabel;

    @BindView(R.id.txv_dob)
    TextView txvDob;

    @BindView(R.id.txv_dob_label)
    TextView txvDobLabel;

    @BindView(R.id.txv_registered)
    TextView txvRegistered;

    @BindView(R.id.txv_registered_label)
    TextView txvRegisteredLabel;

    @BindView(R.id.txv_location)
    TextView txvLocation;

    @BindView(R.id.txv_location_label)
    TextView txvLocationLabel;

    private boolean mIsExpanded = false;
    private MarginLayoutParams mThisMarginParams;
    private LayoutParams collapsedImvCloseLayoutParams;
    private LayoutParams collapsedTxvUserNameLayoutParams;
    private LayoutParams collapsedTxvUserTitleLayoutParams;
    private LayoutParams collapsedScrollViewLayoutParams;
    private boolean isLandscape;
    private int mCommonMargin;

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

        mThisMarginParams = new MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(mThisMarginParams);
        mCommonMargin = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        isLandscape = screenHeight - screenWidth < 0;

        setExpanded(false);
        setBackground(getResources().getDrawable(R.drawable.rounded_corners_solid));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator animator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.selected_view_elevation);
            setStateListAnimator(animator);
        }

    }

    public void setTitle(CharSequence title) {
        txvUserTitle.setText(title);
    }

    public void setName(CharSequence name) {
        txvUserName.setText(name);
    }

    public void setUserPic(Drawable drawable) {
        imvUserPic.setImageDrawable(drawable);
    }

    public ImageView getUserPicImageView() {
        return imvUserPic;
    }

    public void setLogin(CharSequence login) {
        txvLogin.setText(login);
    }

    public void setEmail(CharSequence email) {
        txvEmail.setText(email);
    }

    public void setPhone(CharSequence phone) {
        txvPhone.setText(phone);
    }

    public void setCell(CharSequence cell) {
        txvCell.setText(cell);
    }

    public void setDob(CharSequence dob) {
        txvDob.setText(dob);
    }

    public void setRegistered(CharSequence registered) {
        txvRegistered.setText(registered);
    }

    public void setLocation(CharSequence location) {
        txvLocation.setText(location);
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

    public boolean isExpanded() {
        return mIsExpanded;
    }

    public void setExpanded(boolean expanded) {
        mIsExpanded = expanded;
        if (mIsExpanded) {
            expandView();
        } else {
            collapseView();
        }
    }

    private void expandView() {

        mThisMarginParams = (MarginLayoutParams) getLayoutParams();
        mThisMarginParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mThisMarginParams.setMargins(mCommonMargin, mCommonMargin, mCommonMargin, mCommonMargin);

        imvUserPic.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.userpic_size_expanded);
        imvUserPic.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.userpic_size_expanded);
        imvUserPic.setImageDrawable(null);

        collapsedTxvUserNameLayoutParams = (LayoutParams) txvUserName.getLayoutParams();

        LayoutParams txvUserNameLayoutParams = new LayoutParams(txvUserName.getLayoutParams());
        txvUserNameLayoutParams.addRule(BELOW, txvUserTitle.getId());
        txvUserNameLayoutParams.addRule(END_OF, imvUserPic.getId());
        txvUserNameLayoutParams.setMarginStart(mCommonMargin);
        txvUserNameLayoutParams.topMargin = 0;
        txvUserName.setLayoutParams(txvUserNameLayoutParams);

        setDetailsVisibility(View.VISIBLE);

        if (isLandscape) {
            expandViewInLandscape();
        }
    }

    private void expandViewInLandscape() {

        collapsedScrollViewLayoutParams = (LayoutParams) scrollView.getLayoutParams();
        collapsedTxvUserTitleLayoutParams = (LayoutParams) txvUserTitle.getLayoutParams();
        collapsedImvCloseLayoutParams = (LayoutParams) imvClose.getLayoutParams();

        LayoutParams scrollViewLayoutParams = new LayoutParams(scrollView.getLayoutParams());
        LayoutParams txvUserTitleLayoutParams = new LayoutParams(txvUserTitle.getLayoutParams());
        LayoutParams txvUserNameLayoutParams = new LayoutParams(txvUserName.getLayoutParams());
        LayoutParams imvCloseLayoutParams = new LayoutParams(imvClose.getLayoutParams());

        scrollViewLayoutParams.removeRule(BELOW);
        scrollViewLayoutParams.addRule(END_OF, imvUserPic.getId());

        txvUserTitleLayoutParams.removeRule(END_OF);
        txvUserTitleLayoutParams.addRule(BELOW, imvUserPic.getId());
        txvUserTitleLayoutParams.addRule(ALIGN_PARENT_START);
        txvUserTitleLayoutParams.addRule(START_OF, scrollView.getId());
        txvUserTitleLayoutParams.setMarginStart(mCommonMargin);

        txvUserNameLayoutParams.removeRule(END_OF);
        txvUserNameLayoutParams.addRule(BELOW, txvUserTitle.getId());
        txvUserNameLayoutParams.addRule(ALIGN_PARENT_START);
        txvUserNameLayoutParams.addRule(START_OF, scrollView.getId());
        txvUserNameLayoutParams.topMargin = 0;
        txvUserNameLayoutParams.setMarginStart(mCommonMargin);

        imvCloseLayoutParams.removeRule(ALIGN_PARENT_END);
        imvCloseLayoutParams.addRule(ALIGN_PARENT_START);
        imvClose.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_grey_24dp));

        scrollView.setLayoutParams(scrollViewLayoutParams);
        txvUserTitle.setLayoutParams(txvUserTitleLayoutParams);
        txvUserName.setLayoutParams(txvUserNameLayoutParams);
        imvClose.setLayoutParams(imvCloseLayoutParams);
    }

    private void collapseView() {

        mThisMarginParams = (MarginLayoutParams) getLayoutParams();
        mThisMarginParams.setMargins(
                mCommonMargin,
                getResources().getDimensionPixelSize(R.dimen.dimen_4dp),
                mCommonMargin,
                getResources().getDimensionPixelSize(R.dimen.dimen_4dp)
        );
        mThisMarginParams.height = RecyclerView.LayoutParams.WRAP_CONTENT;

        imvUserPic.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.userpic_size_collapsed);
        imvUserPic.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.userpic_size_collapsed);
        imvUserPic.setImageDrawable(getResources().getDrawable(R.drawable.round_frame));

        if (collapsedTxvUserNameLayoutParams != null) {
            txvUserName.setLayoutParams(collapsedTxvUserNameLayoutParams);
        }

        setDetailsVisibility(View.GONE);

        if (isLandscape) {
            collapseViewInLandscape();
        }
    }

    private void collapseViewInLandscape() {

        if (collapsedScrollViewLayoutParams != null) {
            scrollView.setLayoutParams(collapsedScrollViewLayoutParams);
        }

        if (collapsedTxvUserTitleLayoutParams != null) {
            txvUserTitle.setLayoutParams(collapsedTxvUserTitleLayoutParams);
        }

        if (collapsedImvCloseLayoutParams != null) {
            imvClose.setLayoutParams(collapsedImvCloseLayoutParams);
        }
    }

    private void setDetailsVisibility(int visibility) {

        imvClose.setVisibility(visibility);
        scrollView.setVisibility(visibility);
//        txvLogin.setVisibility(visibility);
//        txvLoginLabel.setVisibility(visibility);
//        txvEmail.setVisibility(visibility);
//        txvEmailLabel.setVisibility(visibility);
//        txvPhone.setVisibility(visibility);
//        txvPhoneLabel.setVisibility(visibility);
//        txvCell.setVisibility(visibility);
//        txvCellLabel.setVisibility(visibility);
//        txvDob.setVisibility(visibility);
//        txvDobLabel.setVisibility(visibility);
//        txvRegistered.setVisibility(visibility);
//        txvRegisteredLabel.setVisibility(visibility);
//        txvLocation.setVisibility(visibility);
//        txvLocationLabel.setVisibility(visibility);

    }

}
