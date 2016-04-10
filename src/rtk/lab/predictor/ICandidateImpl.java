package rtk.lab.predictor;

/**
 * Created by Ransom on 4/8/16.
 */
public class ICandidateImpl implements ICandidate {
    private String word;
    private int confidece;

    @Override
    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int getConfidence() {
        return this.confidece;
    }

    public void setConfidece(int confidece) {
        this.confidece = confidece;
    }

    public String toString() {
        return "\"" + this.getWord() + "\" (" + this.getConfidence() + ")";
    }
}
