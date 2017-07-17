package api;

import repo.Repo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.GithubService;

import java.util.List;

/**
 * Created by ruth on 2017/07/17.
 */
public class MainActivity {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GithubService githubService = retrofit.create(GithubService.class);


    Call<List<Repo>> call = githubService.listrepo("ユーザ名");


}
