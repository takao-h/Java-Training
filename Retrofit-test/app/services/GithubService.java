package services;

import repo.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * Created by ruth on 2017/07/17.
 */
public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listrepo(@Path("user") String user);
}
