package my.test_task.provectus.randomuser.dagger.component;

import dagger.Subcomponent;
import my.test_task.provectus.randomuser.ui.UsersListActivity;

@ActivityScope
@Subcomponent
public interface NonConfigurationComponent {
    void inject(UsersListActivity activity);
}
