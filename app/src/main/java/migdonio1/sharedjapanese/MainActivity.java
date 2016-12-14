package migdonio1.sharedjapanese;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import migdonio1.sharedjapanese.app.QuizActivity;
import migdonio1.sharedjapanese.app.QuizMenuActivity;
import migdonio1.sharedjapanese.app.SitesListActivity;
import migdonio1.sharedjapanese.app.WordCreateActivity;
import migdonio1.sharedjapanese.app.WordsListActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickWordCreateButton(View v) {
        Intent intent = new Intent(this, WordCreateActivity.class);
        startActivity(intent);
    }

    public void onClickWordsListButton(View v) {
        Intent intent = new Intent(this, WordsListActivity.class);
        startActivity(intent);
    }

    public void onClickQuizButton(View v) {
        Intent intent = new Intent(this, QuizMenuActivity.class);
        startActivity(intent);
    }

    public void onClickSitesListButton(View v) {
        Intent intent = new Intent(this, SitesListActivity.class);
        startActivity(intent);
    }
}
