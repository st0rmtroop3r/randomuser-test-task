package my.test_task.provectus.randomuser.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/** Network module. Used to provide HTTP client. */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    public OkHttpClient httpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor loggingInterceptor(HttpLoggingInterceptor.Level level) {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(level);
        return interceptor;
    }

    @Provides
    public HttpLoggingInterceptor.Level loggingLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }

}