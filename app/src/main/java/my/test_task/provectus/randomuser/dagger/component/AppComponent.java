package my.test_task.provectus.randomuser.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import my.test_task.provectus.randomuser.App;
import my.test_task.provectus.randomuser.dagger.module.AppModule;
import my.test_task.provectus.randomuser.dagger.module.NetworkModule;
import my.test_task.provectus.randomuser.dagger.module.RestModule;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        RestModule.class
})
/**
 * Application component. Source component for all sub-components.
 */
public interface AppComponent {

    void inject(App app);

    NonConfigurationComponent getActivityComponent();

    final class Initializer {

        private Initializer() {}

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule())
                    .restModule(new RestModule())
                    .build();
        }
    }

}