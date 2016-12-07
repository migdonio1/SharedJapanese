package migdonio1.sharedjapanese.app;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.app.fragments.CardsListRecyclerViewAdapter;
import migdonio1.sharedjapanese.endpoints.WordsEndpointInterface;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;

public class WordsListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG = "WordsListActivity";

    private List<Word> words;

    private WordsEndpointInterface apiWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiWords = retrofit.create(WordsEndpointInterface.class);

        getWords();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getWords(){
        Call<List<Word>> call = apiWords.WordsList();
        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                words = response.body();
                mAdapter = new CardsListRecyclerViewAdapter(words);
                mRecyclerView.setAdapter(mAdapter);

                ((CardsListRecyclerViewAdapter) mAdapter)
                        .setOnItemClickListener(new CardsListRecyclerViewAdapter.CardClickListener(){

                            @Override
                            public void onItemClick(int position, View view) {
                                Log.d(LOG, "Clicked card " + position);
                            }
                        });

                for(int i=0; i<words.size(); i++){
                    Log.d(LOG, "Word: " + words.get(i).getOriginal() + " (" + words.get(i).getSyllables() + ")");
                }
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                Toast.makeText(WordsListActivity.this, "Error: El servidor no responde, intentelo mas tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
