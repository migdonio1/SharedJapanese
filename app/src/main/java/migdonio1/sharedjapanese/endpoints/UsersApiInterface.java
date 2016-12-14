package migdonio1.sharedjapanese.endpoints;

import migdonio1.sharedjapanese.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by ordon on 14/12/2016.
 */

public interface UsersApiInterface {

    @POST("users")
    Call<User> tryLogin(@Body User user);
}
