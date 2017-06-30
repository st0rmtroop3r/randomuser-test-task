package my.test_task.provectus.randomuser.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.test_task.provectus.R;

public class UserListItem extends RelativeLayout{

    @BindView(R.id.imv_user_pic)
    ImageView imvUserPic;

    @BindView(R.id.txv_user_title)
    TextView txvUserTitle;

    public UserListItem(Context context) {
        super(context);
        init(context);
    }

    public UserListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.widget_user_item, this);
        ButterKnife.bind(this, this);
    }

    public void setTitle(CharSequence title) {
        txvUserTitle.setText(title);
    }

    public void setUserPic(Drawable drawable) {
        imvUserPic.setImageDrawable(drawable);
    }

    public ImageView getImvUserPic() {
        return imvUserPic;
    }
}
