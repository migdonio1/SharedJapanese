package migdonio1.sharedjapanese.app.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.models.Word;

/**
 * Created by migdonio1 on 12/7/16.
 */
public class CardsListRecyclerViewAdapter extends RecyclerView.Adapter<CardsListRecyclerViewAdapter.WordHolder> {

    private static String LOG = "CardsListRecyclerViewAdapter";
    private List<Word> mWordset;
    private static CardClickListener cardClickListener;

    @Override
    public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);
        WordHolder dataWordHolder = new WordHolder(view);

        return dataWordHolder;
    }

    @Override
    public void onBindViewHolder(WordHolder holder, int position) {
        String syllables = " (" + mWordset.get(position).getSyllables() + ")";

        holder.original.setText(mWordset.get(position).getOriginal());
        holder.syllables.setText(syllables);
        holder.translates.setText(mWordset.get(position).getTranslates().get(0));
        holder.types.setText(mWordset.get(position).getTypes().get(0));
    }

    public void addItem(Word word, int index) {
        mWordset.add(index, word);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mWordset.remove(index);
    }

    @Override
    public int getItemCount() {
        return mWordset.size();
    }

    public static class WordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView original;
        TextView syllables;
        TextView translates;
        TextView types;

        public WordHolder (View itemView){
            super(itemView);
            original = (TextView) itemView.findViewById(R.id.card_view_original_text);
            syllables = (TextView) itemView.findViewById(R.id.card_view_syllables_text);
            translates = (TextView) itemView.findViewById(R.id.card_view_translates_text);
            types = (TextView) itemView.findViewById(R.id.card_view_types_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            cardClickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(CardClickListener cardClickListener){
        this.cardClickListener = cardClickListener;
    }

    public CardsListRecyclerViewAdapter(List<Word> mWordset){
        this.mWordset = mWordset;
    }

    public interface CardClickListener {
        public void onItemClick(int position, View view);
    }
}
