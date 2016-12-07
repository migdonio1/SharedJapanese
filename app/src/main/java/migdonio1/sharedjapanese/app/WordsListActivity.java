package migdonio1.sharedjapanese.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.endpoints.WordsEndpointInterface;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;

public class WordsListActivity extends AppCompatActivity {

    private TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WordsEndpointInterface apiWords = retrofit.create(WordsEndpointInterface.class);

        String id = "5844bdd3daeba5467053f36b";

        Call<Word> call = apiWords.getWord(id);
        call.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                int statusCode = response.code();
                Word word = response.body();
            }

            @Override
            public void onFailure(Call<Word> call, Throwable t) {
                Log.d("Error", "Can't complete the request");
            }
        });
    }
}
