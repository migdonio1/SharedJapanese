package migdonio1.sharedjapanese.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.app.fragments.QuizPageFragment;

public class QuizActivity extends FragmentActivity {

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new QuizPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private class QuizPagerAdapter extends FragmentStatePagerAdapter {
        public QuizPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new QuizPageFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
