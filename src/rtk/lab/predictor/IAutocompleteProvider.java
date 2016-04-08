package rtk.lab.predictor;

import java.util.List;

/**
 * Created by ransom on 4/8/16.
 */
public interface IAutocompleteProvider {
    /**
     * Returns list of autocomplete candidates ordered by confidence
     * @param fragment partial text
     * @return List of candidates
     */
    List<ICandidate> getWords(String fragment);

    /**
     * Trains the algorithm with the provided passage
     * @param passage Seed text
     */
    void train(String passage);
}
