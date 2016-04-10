package rtk.lab.predictor;

import java.util.*;

/**
 * Created by ransom on 4/8/16.
 * This is a trie data structure. It makes looking up words, their roots, and prefixes very efficient. It does this by
 * storing a letter to each word at an incrementing level of the tree. So, the maximum depth of the tree will be the
 * longest word inserted into it, with children off of nodes for words of common roots.
 */
public class RanTrie {
    private Node root;

    /**
     * Creates an empty trie
     */
    public RanTrie() {
        // Empty root node
        this.root = new Node();
    }

    /**
     * Adds a word to the trie
     * @param word Word to store
     */
    public void add(String word) {
        // Get the root children for first iteration
        HashMap<Character, Node> children = root.getChildren();
        Node current = root;
        // For each letter of the word, we see if it exists in the nodes children.
        // This is where the hashmap makes a difference.
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node;
            // If this level has the character, continue down the path
            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                // This level doesn't have the character as a child, so add it and continue
                node = new Node(c);
                node.setParent(current);
                children.put(c, node);
            }

            // Update references to continue descending tree
            current = node;
            children = node.getChildren();
            // If we're on the last letter of the word, mark it is a sentinel and increment the occurrences on it
            if (i == (word.length() - 1)) {
                node.setSentinel(true);
                node.incrementOccurrence();
            }
        }
    }

    /**
     * Returns true if the trie has an exact word in it - never used, but worth having
     * @param word Search word
     * @return if word is in trie
     */
    public boolean hasWord(String word) {
        Node node = this.search(word);
        // Make sure the node was found for the last letter of the word and its actually a word marker (sentinel)
        return (node != null && node.isSentinel());
    }

    /**
     * Descend the trie, searching for a root node based on the needle query. Does not verify if it is a word. This
     * allows for prefix searching
     * @param needle Search query
     * @return Node to needle
     */
    public Node search(String needle) {
        HashMap<Character, Node> children = root.getChildren();
        Node result = null;
        char current;

        // Descend the trie letter by letter
        for (int i = 0; i < needle.length(); i++) {
            current = needle.charAt(i);
            // Again, fast lookups from hashmap!
            if (children.containsKey(current)) {
                result = children.get(current);
                children = result.getChildren();
            } else {
                // Letter wasn't in the trie, so return null. Might be fun to return the closest we could get for
                // another implementation
                return null;
            }
        }
        return result;
    }

    /**
     * Gets the word candidates for a node. Essentially, we're traversing down the trie, assembling the words
     * @param node Prefix node
     * @return List of ICandidate objects
     */
    public List<ICandidate> getCandidates(Node node) {
        List<ICandidate> result = new ArrayList<>();

        HashMap<Character, Node> children = node.getChildren();
        // If the node supplied is a word, we need to add it as a possibility.
        if (node.isSentinel()) result.add(this.nodeToCandidate(node));

        // We need to iterate over the hashmap of children, recursively calling getCandidates on it and adding the
        // result set to our current result set.
        Iterator it = children.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            result.addAll(this.getCandidates((Node) pair.getValue()));
            it.remove();
        }

        // Return the result set, used recursively
        return result;
    }

    /**
     * Converts a node to a candidate word
     * @param node Input
     * @return ICandidate word
     */
    private ICandidate nodeToCandidate(Node node) {
        // Create a new concrete candidate, set the word and confidence on it
        ICandidateImpl candidate = new ICandidateImpl();
        candidate.setWord(this.getWord(node));
        candidate.setConfidence(node.getOccurrence());

        return candidate;
    }

    /**
     * Get the candidates from a string
     * @param string search query
     * @return List of candidates
     */
    public List<ICandidate> getCandidates(String string) {
        return getCandidates(this.search(string));
    }

    /**
     * Converts a node to its word by traversing upwards
     * @param node Base node
     * @return String word
     */
    public String getWord(Node node) {
        StringBuilder sb = new StringBuilder();
        Node current = node;
        // While we're not at the root of the tree, append the character and continue upwards
        while (current != this.root) {
            sb.append(current.getCharacter());
            current = current.getParent();
        }
        // Reverse the string, since we were appending characters with string builder rather than prepending
        return sb.reverse().toString();
    }
}
