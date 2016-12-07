package migdonio1.sharedjapanese;

/**
 * Created by ordon on 06/12/2016.
 */

public class DataObject {

    private String mText1;
    private String mText2;

    DataObject(String Text1, String Text2) {
        mText1 = Text1;
        mText2 = Text2;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }
}
