package migdonio1.sharedjapanese.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import migdonio1.sharedjapanese.R;

/**
 * Created by migdonio1 on 12/7/16.
 */
public class QuizPageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_quiz_page, container, false);
        return rootView;

    }
}
