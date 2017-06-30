package my.test_task.provectus.randomuser.dagger.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_IO;
import static my.test_task.provectus.randomuser.presenter.PresenterConfig.SCHEDULER_MAIN;

@Module
public class SchedulersModule {

    @Named(SCHEDULER_MAIN)
    @Provides
    public Scheduler mainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Named(SCHEDULER_IO)
    @Provides
    public Scheduler iocheduler() {
        return Schedulers.io();
    }
}
