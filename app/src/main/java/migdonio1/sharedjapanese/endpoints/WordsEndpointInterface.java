package migdonio1.sharedjapanese.endpoints;

import java.util.List;

import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by migdonio1 on 12/4/16.
 */
public interface WordsEndpointInterface {
    @GET("words")
    Call<List<Word>> WordsList();

    @GET("words/{word_id}")
    Call<Word> getWord(@Path("word_id") String word);
}
