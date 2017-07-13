package my.test_task.provectus.randomuser.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import my.test_task.provectus.randomuser.App;
import my.test_task.provectus.randomuser.dagger.component.NonConfigurationComponent;

/** BaseActivity provides Dagger injection and ButterKnife binding */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    private NonConfigurationComponent injector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injector = restoreInjector();
        inject(injector);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    /**
     * Request to execute injection of itself.
     * @param injector injector
     */
    protected abstract void inject(NonConfigurationComponent injector);

    /** Restores presenter while configuration change. */
    private NonConfigurationComponent restoreInjector() {
        Object o = getLastCustomNonConfigurationInstance();
        if (o == null) {
            return getApp().component().getActivityComponent();
        } else {
            return (NonConfigurationComponent) o;
        }
    }

    public App getApp() {
        return (App) super.getApplication();
    }

}
