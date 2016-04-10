package rtk.lab.predictor;

/**
 * Created by Ransom on 4/8/16.
 * Stores a word candidate and its confidence level
 */
public class ICandidateImpl implements ICandidate {
    private String word;
    private int confidence;

    @Override
    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int getConfidence() {
        return this.confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    /**
     * Creates a pretty output for this candidate similar to the output from the Asymmetrik example
     * @return String with word and confidence
     */
    public String toString() {
        return "\"" + this.getWord() + "\" (" + this.getConfidence() + ")";
    }
}
