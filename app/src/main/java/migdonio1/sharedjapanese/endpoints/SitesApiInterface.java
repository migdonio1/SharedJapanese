package migdonio1.sharedjapanese.endpoints;

import java.util.List;

import migdonio1.sharedjapanese.models.Site;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by migdonio1 on 12/8/16.
 */
public interface SitesApiInterface {
    @GET("sites")
    Call<List<Word>> SitesList();

    @POST("sites")
    Call<Word> createSite(@Body Site site);

    @GET("sites/{site_id}")
    Call<Word> getSite(@Path("site_id") String site);
}
