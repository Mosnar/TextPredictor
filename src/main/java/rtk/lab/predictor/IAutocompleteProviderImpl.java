package rtk.lab.predictor;

import java.util.List;

/**
 * Created by Ransom on 4/8/16.
 * The purpose of this class is to provide autocomplete functionality based on a trie
 */
public class IAutocompleteProviderImpl implements IAutocompleteProvider {
    private RanTrie trie;

    /**
     * Empty constructor, creates empty trie
     */
    public IAutocompleteProviderImpl() {
        this.trie = new RanTrie();
    }

    /**
     * Constructor that creates the trie and trains it on a passage
     * @param passage training string
     */
    public IAutocompleteProviderImpl(String passage) {
        this.trie = new RanTrie();
        this.train(passage);
    }

    /**
     * Gets candidate words from a string fragment
     * @param fragment partial text
     * @return List of words as ICandidates
     */
    @Override
    public List<ICandidate> getWords(String fragment) {
        return this.trie.getCandidates(fragment);
    }

    /**
     * Parses a passage of text into the trie datastructure
     * @param passage Seed text
     */
    @Override
    public void train(String passage) {
        // Split the string at whitespace, add each fragment into the trie
        String[] words = passage.split("\\s+");
        for (String word : words) {
            // Convert to lowercase and strip non-alpha characters
            this.trie.add(word.toLowerCase().replaceAll("[^a-z]", ""));
        }
    }
}

