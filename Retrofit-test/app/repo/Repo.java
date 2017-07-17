package repo;

import com.google.gson.annotations.Expose;

/**
 * Created by ruth on 2017/07/17.
 */
public class Repo {
    @Expose
    public Long id;
    @Expose
    public String name;

    public String toString(){
        return "id:" + id + "name:" + name;
    }
}
