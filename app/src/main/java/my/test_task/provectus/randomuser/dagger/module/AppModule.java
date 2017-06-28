package my.test_task.provectus.randomuser.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import my.test_task.provectus.randomuser.App;

@Module
public class AppModule {

    /** Application instance. */
    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    public Context context() {
        return app;
    }

    @Provides
    public App app() {
        return app;
    }
}
