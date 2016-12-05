package migdonio1.sharedjapanese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import migdonio1.sharedjapanese.endpoints.WordsEndpointInterface;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://192.168.0.17:3001/api/v1/";
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WordsEndpointInterface apiWords = retrofit.create(WordsEndpointInterface.class);
        textView = (TextView) findViewById(R.id.textfield);

        String id = "5844bdd3daeba5467053f36b";

        Call<Word> call = apiWords.getWord(id);
        call.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                int statusCode = response.code();
                Word word = response.body();
                textView.setText(word.getOriginal());
            }

            @Override
            public void onFailure(Call<Word> call, Throwable t) {
                Log.d("Error", "Can't complete the request");
            }
        });
    }
}
