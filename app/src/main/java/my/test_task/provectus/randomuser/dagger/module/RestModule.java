package my.test_task.provectus.randomuser.dagger.module;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import my.test_task.provectus.BuildConfig;
import my.test_task.provectus.randomuser.model.rest.RandomuserApi;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestModule {

    private static final String BASE_URL = "https://randomuser.me/";

    @Provides
    public RandomuserApi getApi(Retrofit retrofit) {
        return retrofit.create(RandomuserApi.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient client, String baseUrl, CallAdapter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(client)
                .addCallAdapterFactory(factory)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public CallAdapter.Factory getRxCallFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    public static String getBaseUrl() {
        return BASE_URL;
    }
}
