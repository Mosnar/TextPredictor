package rtk.lab.predictor;

import rtk.lab.predictor.RanTrie;

import java.util.List;

/**
 * Created by Ransom on 4/8/16.
 */
public class IAutocompleteProviderImpl implements IAutocompleteProvider {
    private RanTrie trie;

    void IAutocompleteProvider() {
        this.trie = new RanTrie();
    }

    @Override
    public List<ICandidate> getWords(String fragment) {
        return this.trie.getCandidates(fragment);
    }

    @Override
    public void train(String passage) {
        String[] words = passage.split("\\s+");
        for (String word : words) {
            trie.add(word);
        }
    }
}

