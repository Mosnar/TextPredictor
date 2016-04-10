package rtk.lab.predictor;

/**
 * Created by Ransom on 4/8/16.
 */
public interface ICandidate {
    /**
     * Returns the autocomplete candidate
     *
     * @return String candidate
     */
    String getWord();

    /**
     * Returns the confidence for the candidate
     *
     * @return int confidence
     */
    int getConfidence();
}
