package migdonio1.sharedjapanese.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Site {

    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("syllables")
    @Expose
    private String syllables;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("position")
    @Expose
    private Position position;
    @SerializedName("translates")
    @Expose
    private List<String> translates = null;

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
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

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
     * The position
     */
    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param position
     * The position
     */
    public void setPosition(Position position) {
        this.position = position;
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