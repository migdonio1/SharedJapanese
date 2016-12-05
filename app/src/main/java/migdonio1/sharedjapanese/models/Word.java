
package migdonio1.sharedjapanese.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Word {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("syllables")
    @Expose
    private String syllables;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("translates")
    @Expose
    private List<String> translates = null;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @param original
     * The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     *
     * @return
     * The syllables
     */
    public String getSyllables() {
        return syllables;
    }

    /**
     *
     * @param syllables
     * The syllables
     */
    public void setSyllables(String syllables) {
        this.syllables = syllables;
    }

    /**
     *
     * @return
     * The notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     * The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     *
     * @return
     * The v
     */
    public Integer getV() {
        return v;
    }

    /**
     *
     * @param v
     * The __v
     */
    public void setV(Integer v) {
        this.v = v;
    }

    /**
     *
     * @return
     * The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    /**
     *
     * @return
     * The translates
     */
    public List<String> getTranslates() {
        return translates;
    }

    /**
     *
     * @param translates
     * The translates
     */
    public void setTranslates(List<String> translates) {
        this.translates = translates;
    }

}