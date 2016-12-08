package migdonio1.sharedjapanese.endpoints;

import java.util.List;

import migdonio1.sharedjapanese.models.Site;
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
    Call<List<Site>> SitesList();

    @POST("sites")
    Call<Site> createSite(@Body Site site);

    @GET("sites/{site_id}")
    Call<Site> getSite(@Path("site_id") String site);
}
