package my.test_task.provectus.randomuser.model.rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import my.test_task.provectus.randomuser.model.entities.RandomUser;

/**
 * Created by Stormtrooper on 28.06.2017.
 */

public class Response {

    @SerializedName("results")
    private List<RandomUser> randomUsers;

    public List<RandomUser> getRandomUsers() {
        return randomUsers;
    }

    @Override
    public String toString() {
        return "Response{" +
                "randomUsers=" + randomUsers +
                '}';
    }
}
