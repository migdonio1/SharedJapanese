package migdonio1.sharedjapanese.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import migdonio1.sharedjapanese.R;

public class QuizMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_menu);
    }

    public void onClickLevel10Button(View view){
        Intent intent = new Intent(this, QuizMenuActivity.class);
        intent.putExtra("quizz", 10);
        startActivity(intent);
    }

    public void onClickLevel20Button(View view){
        Intent intent = new Intent(this, QuizMenuActivity.class);
        intent.putExtra("quizz", 20);
        startActivity(intent);
    }

    public void onClickLevel30Button(View view){
        Intent intent = new Intent(this, QuizMenuActivity.class);
        intent.putExtra("quizz", 30);
        startActivity(intent);
    }
}
