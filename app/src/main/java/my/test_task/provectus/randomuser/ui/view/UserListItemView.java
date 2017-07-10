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

    private MarginLayoutParams mThisMarginParams;
    private static LayoutParams scrollViewLayoutParamsCollapsed;
    private static LayoutParams scrollViewLayoutParamsExpanded;
    private static LayoutParams txvUserNameLayoutParamsCollapsed;
    private static LayoutParams txvUserNameLayoutParamsExpanded;
    private static LayoutParams txvUserNameLayoutParamsExpandedLandscape;
    private static LayoutParams txvUserTitleLayoutParamsCollapsed;
    private static LayoutParams txvUserTitleLayoutParamsExpanded;
    private Drawable mUserPicFrameCollapsed;
    private Drawable mUserPicFrameExpanded;
    private boolean mIsExpanded = false;
    private boolean mIsLandscape;
    private int mCommonMargin;
    private int mUserPicExpandedPadding;
    private int mUserPicSizeExpanded;
    private int mUserPicSizeCollapsed;

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
        mUserPicExpandedPadding = getResources().getDimensionPixelSize(R.dimen.dimen_minus_1dp);
        mUserPicSizeExpanded = getResources().getDimensionPixelSize(R.dimen.userpic_size_expanded);
        mUserPicSizeCollapsed = getResources().getDimensionPixelSize(R.dimen.userpic_size_collapsed);
        mUserPicFrameExpanded = getResources().getDrawable(R.drawable.rounded_corners_stroke_whie_2dp);
        mUserPicFrameCollapsed = getResources().getDrawable(R.drawable.round_frame);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        mIsLandscape = screenHeight - screenWidth < 0;

        if (mIsLandscape) {
            imvClose.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_grey_24dp));
            ((LayoutParams)imvClose.getLayoutParams()).removeRule(ALIGN_PARENT_END);
            ((LayoutParams)imvClose.getLayoutParams()).addRule(ALIGN_PARENT_START);
        }

        setExpanded(false);
        setBackground(getResources().getDrawable(R.drawable.rounded_corners_solid_white));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StateListAnimator animator = AnimatorInflater
                    .loadStateListAnimator(context, R.animator.selected_view_elevation);
            setStateListAnimator(animator);
        }

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

        imvUserPic.getLayoutParams().height = mUserPicSizeExpanded;
        imvUserPic.getLayoutParams().width = mUserPicSizeExpanded;
        imvUserPic.setPadding(mUserPicExpandedPadding, mUserPicExpandedPadding,
                mUserPicExpandedPadding, mUserPicExpandedPadding);
        imvUserPic.setImageDrawable(mUserPicFrameExpanded);

        txvUserNameLayoutParamsCollapsed = (LayoutParams) txvUserName.getLayoutParams();



        setDetailsVisibility(View.VISIBLE);

        if (mIsLandscape) {
            expandViewInLandscape();
        } else {
            if (txvUserNameLayoutParamsExpanded == null) {
                txvUserNameLayoutParamsExpanded = new LayoutParams(txvUserName.getLayoutParams());
                txvUserNameLayoutParamsExpanded.addRule(BELOW, txvUserTitle.getId());
                txvUserNameLayoutParamsExpanded.addRule(END_OF, imvUserPic.getId());
                txvUserNameLayoutParamsExpanded.addRule(START_OF, imvClose.getId());
                txvUserNameLayoutParamsExpanded.setMarginStart(mCommonMargin);
                txvUserNameLayoutParamsExpanded.topMargin = 0;
            }
            txvUserName.setLayoutParams(txvUserNameLayoutParamsExpanded);
        }
    }

    private void expandViewInLandscape() {

        scrollViewLayoutParamsCollapsed = (LayoutParams) scrollView.getLayoutParams();
        txvUserTitleLayoutParamsCollapsed = (LayoutParams) txvUserTitle.getLayoutParams();

        if (scrollViewLayoutParamsExpanded == null) {
            scrollViewLayoutParamsExpanded = new LayoutParams(scrollView.getLayoutParams());
            scrollViewLayoutParamsExpanded.removeRule(BELOW);
            scrollViewLayoutParamsExpanded.addRule(END_OF, imvUserPic.getId());
        }

        if (txvUserTitleLayoutParamsExpanded == null) {
            txvUserTitleLayoutParamsExpanded = new LayoutParams(txvUserTitle.getLayoutParams());
            txvUserTitleLayoutParamsExpanded.removeRule(END_OF);
            txvUserTitleLayoutParamsExpanded.addRule(BELOW, imvUserPic.getId());
            txvUserTitleLayoutParamsExpanded.addRule(ALIGN_PARENT_START);
            txvUserTitleLayoutParamsExpanded.addRule(START_OF, scrollView.getId());
            txvUserTitleLayoutParamsExpanded.setMarginStart(mCommonMargin);
        }

        if (txvUserNameLayoutParamsExpandedLandscape == null) {
            txvUserNameLayoutParamsExpandedLandscape = new LayoutParams(txvUserName.getLayoutParams());
            txvUserNameLayoutParamsExpandedLandscape.removeRule(END_OF);
            txvUserNameLayoutParamsExpandedLandscape.addRule(BELOW, txvUserTitle.getId());
            txvUserNameLayoutParamsExpandedLandscape.addRule(ALIGN_PARENT_START);
            txvUserNameLayoutParamsExpandedLandscape.addRule(START_OF, scrollView.getId());
            txvUserNameLayoutParamsExpandedLandscape.topMargin = 0;
            txvUserNameLayoutParamsExpandedLandscape.setMarginStart(mCommonMargin);
        }

        scrollView.setLayoutParams(scrollViewLayoutParamsExpanded);
        txvUserTitle.setLayoutParams(txvUserTitleLayoutParamsExpanded);
        txvUserName.setLayoutParams(txvUserNameLayoutParamsExpandedLandscape);
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

        imvUserPic.getLayoutParams().height = mUserPicSizeCollapsed;
        imvUserPic.getLayoutParams().width = mUserPicSizeCollapsed;
        imvUserPic.setPadding(0, 0, 0, 0);
        imvUserPic.setImageDrawable(mUserPicFrameCollapsed);

        if (txvUserNameLayoutParamsCollapsed != null) {
            txvUserName.setLayoutParams(txvUserNameLayoutParamsCollapsed);
        }

        if (mIsLandscape) {
            collapseViewInLandscape();
        }

        setDetailsVisibility(View.GONE);
    }

    private void collapseViewInLandscape() {

        if (scrollViewLayoutParamsCollapsed != null) {
            scrollView.setLayoutParams(scrollViewLayoutParamsCollapsed);
        }

        if (txvUserTitleLayoutParamsCollapsed != null) {
            txvUserTitle.setLayoutParams(txvUserTitleLayoutParamsCollapsed);
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

}
