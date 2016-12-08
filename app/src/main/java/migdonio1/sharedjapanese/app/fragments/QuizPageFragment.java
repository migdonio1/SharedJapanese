package migdonio1.sharedjapanese.app.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.models.Word;
import static  migdonio1.sharedjapanese.data.APIConstants.WORD;

/**
 * Created by migdonio1 on 12/7/16.
 */
public class QuizPageFragment extends Fragment {

    private static final String INDEX = "index";

    private int index;
    private String original;
    private String syllables;
    private String translates;
    private String types;
    private String notes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.original = (getArguments() != null) ? getArguments().getString(WORD.ORIGINAL) : "";
        this.syllables = (getArguments() != null) ? getArguments().getString(WORD.SYLLABLES) : "";
        this.types = (getArguments() != null) ? getArguments().getString(WORD.TYPES) : "";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_quiz_page, container, false);

        TextView originalTextView = (TextView) rootView.findViewById(R.id.card_view_original_text);
        TextView syllablesTextView = (TextView) rootView.findViewById(R.id.card_view_syllables_text);

        String syllablesFormated = " (" + syllables + ")";

        originalTextView.setText(original);
        syllablesTextView.setText(syllablesFormated);

        return rootView;

    }

    public static QuizPageFragment newInstance(Word word, int index) {
        QuizPageFragment fragment = new QuizPageFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putString(WORD.ORIGINAL, word.getOriginal());
        bundle.putString(WORD.SYLLABLES, word.getSyllables());
        bundle.putString(WORD.TRANSLATES, word.getTranslates().get(0));
        bundle.putString(WORD.TYPES, word.getTypes().get(0));
        bundle.putString(WORD.NOTES, word.getNotes());

        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;
    }

}
