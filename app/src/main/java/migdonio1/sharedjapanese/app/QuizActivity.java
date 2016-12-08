package migdonio1.sharedjapanese.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.app.fragments.CardsListRecyclerViewAdapter;
import migdonio1.sharedjapanese.app.fragments.QuizPageFragment;
import migdonio1.sharedjapanese.endpoints.WordsEndpointInterface;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;

public class QuizActivity extends FragmentActivity {

    private ViewPager mPager;
    private QuizPagerAdapter mPagerAdapter;

    private List<Word> words;

    private WordsEndpointInterface apiWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new QuizPagerAdapter(getSupportFragmentManager());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiWords = retrofit.create(WordsEndpointInterface.class);

        getWords();
    }

    public void getWords(){
        Call<List<Word>> call = apiWords.WordsList();
        call.enqueue(new Callback<List<Word>>() {
            @Override
            public void onResponse(Call<List<Word>> call, Response<List<Word>> response) {
                words = response.body();

                for(int i=0; i<words.size(); i++) {
                    mPagerAdapter.addFragment(QuizPageFragment.newInstance(words.get(i), i+1));
                }
                mPager.setAdapter(mPagerAdapter);
            }

            @Override
            public void onFailure(Call<List<Word>> call, Throwable t) {
                Toast.makeText(QuizActivity.this, "Error: El servidor no responde, intentelo mas tarde.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class QuizPagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments;

        public QuizPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<Fragment>();
        }

        public void addFragment(Fragment fragment) {
            this.fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
