package migdonio1.sharedjapanese.data;

/**
 * Created by migdonio1 on 12/6/16.
 */
public class APIConstants {
    public static final String BASE_URL = "http://192.168.0.18:3001";
    public static final String API_PATH = "/api/v1/";

    public static final String API_ENDPOINT = BASE_URL + API_PATH;

    public static class WORD {
        public static final String TOKEN = "token";
        public static final String ID = "_id";
        public static final String ORIGINAL = "original";
        public static final String SYLLABLES = "syllables";
        public static final String NOTES = "notes";
        public static final String TYPES = "types";
        public static final String TRANSLATES = "translates";
    }

    public static class SITE {
        public static final String ID = "_id";
        public static final String ORIGINAL = "original";
        public static final String SYLLABLES = "syllables";
        public static final String DESCRIPTION = "description";
        public static final String COUNTRY = "country";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String TRANSLATES = "translates";
    }
}
