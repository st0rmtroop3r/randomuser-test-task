package my.test_task.provectus.randomuser.model.rest;

import java.util.List;

import io.reactivex.Observable;
import my.test_task.provectus.randomuser.model.entities.RandomUser;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomuserApi {

    @GET("api/")
    Observable<Response> getRandomUsers(@Query("results") int number);

}
