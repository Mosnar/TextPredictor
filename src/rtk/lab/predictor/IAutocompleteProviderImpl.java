package rtk.lab.predictor;

import java.util.List;

/**
 * Created by Ransom on 4/8/16.
 */
public class IAutocompleteProviderImpl implements IAutocompleteProvider {
    private RanTrie trie;

    public IAutocompleteProviderImpl() {
        this.trie = new RanTrie();
    }

    public IAutocompleteProviderImpl(String passage) {
        this.trie = new RanTrie();
        this.train(passage);
    }

    @Override
    public List<ICandidate> getWords(String fragment) {
        return this.trie.getCandidates(fragment);
    }

    @Override
    public void train(String passage) {
        String[] words = passage.split("\\s+");
        for (String word : words) {
            this.trie.add(word.toLowerCase().replaceAll("[^a-z]", ""));
        }
    }
}

