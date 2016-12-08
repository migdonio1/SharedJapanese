package migdonio1.sharedjapanese.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.endpoints.WordsEndpointInterface;
import migdonio1.sharedjapanese.models.Word;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;

public class WordCreateActivity extends AppCompatActivity {

    private String original;
    private String syllables;
    private String translates;
    private String types;
    private String notes;

    private EditText originalEditText;
    private EditText syllablesEditText;
    private EditText translatesEditText;
    private EditText typesEditText;
    private EditText notesEditText;
    private Button wordSubmit;

    private WordsEndpointInterface apiWords;

    private Word word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_create);

        originalEditText = (EditText) findViewById(R.id.wordOriginalEditText);
        syllablesEditText = (EditText) findViewById(R.id.wordSyllablesEditText);
        translatesEditText = (EditText) findViewById(R.id.wordTranslatesEditText);
        typesEditText = (EditText) findViewById(R.id.wordTypesEditText);
        notesEditText = (EditText) findViewById(R.id.wordNotesEditText);

    }

    public void onClickWordSubmitButton(View view) {
        wordSubmit = (Button) findViewById(R.id.wordCreateButton);
        wordSubmit.setEnabled(false);

        original = String.valueOf(originalEditText.getText()).trim();
        syllables = String.valueOf(syllablesEditText.getText()).trim();
        translates = String.valueOf(translatesEditText.getText()).trim();
        types = String.valueOf(typesEditText.getText()).trim();
        notes = String.valueOf(notesEditText.getText()).trim();

        if(original.length() == 0
                || syllables.length() == 0
                || translates.length() == 0
                || types.length() == 0
                || notes.length() == 0){
            Toast.makeText(WordCreateActivity.this, "Llene todos los campos para poder crear una palabra", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(WordCreateActivity.this, "Todos los campos se llenaron correctamente", Toast.LENGTH_SHORT).show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiWords = retrofit.create(WordsEndpointInterface.class);
            createWordModel();
            createWord();
        }
    }

    public void createWordModel() {
        word = new Word();

        List<String> translatesList = new ArrayList<String>();
        translatesList.add(translates);

        List<String> typesList = new ArrayList<String>();
        typesList.add(types);

        word.setOriginal(original);
        word.setSyllables(syllables);
        word.setTranslates(translatesList);
        word.setTypes(typesList);
        word.setNotes(notes);
    }

    public void createWord() {
        Call<Word> call = apiWords.createWord(word);
        call.enqueue(new Callback<Word>() {
            @Override
            public void onResponse(Call<Word> call, Response<Word> response) {
                Word wordResponse = response.body();

                String toastText = "Se agrego la palabra " + wordResponse.getOriginal();
                Toast.makeText(WordCreateActivity.this, toastText, Toast.LENGTH_SHORT).show();
                emptyInputs();
                wordSubmit.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Word> call, Throwable t) {
                Toast.makeText(WordCreateActivity.this, "Hubo un error creando la palabra", Toast.LENGTH_SHORT).show();
                wordSubmit.setEnabled(true);
            }
        });
    }

    public void emptyInputs() {
        originalEditText.setText("");
        syllablesEditText.setText("");
        translatesEditText.setText("");
        typesEditText.setText("");
        notesEditText.setText("");
    }
}
