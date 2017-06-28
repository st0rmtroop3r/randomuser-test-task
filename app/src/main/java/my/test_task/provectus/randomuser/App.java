package my.test_task.provectus.randomuser;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import my.test_task.provectus.randomuser.dagger.component.AppComponent;
import my.test_task.provectus.randomuser.dagger.module.AppModule;
import my.test_task.provectus.randomuser.dagger.module.NetworkModule;

/** Start point of application. */
public class App extends Application {
    /** Application Dagger component. */
    protected AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = AppComponent.Initializer.init(this);
//        refreshComponent(new ServiceModule());
    }

    /** Creates component. <b>CAUTION! Use for testing only</b> */
//    @VisibleForTesting
//    public void refreshComponent(ServiceModule serviceModule) {
//        final DaggerAppComponent.Builder builder = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .networkModule(new NetworkModule())
//                .imageLoadingModule(new ImageLoadingModule())
//                .serviceModule(serviceModule);
//        component = builder.build();
//    }

    /** Application component. */
    public AppComponent component() {
        return component;
    }
}
