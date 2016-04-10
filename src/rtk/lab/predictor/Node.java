package rtk.lab.predictor;

import java.util.HashMap;

/**
 * Created by ransom on 4/8/16.
 * Node structure for the trie
 */
class Node {
    // The char stored in this node
    private char character;
    // A map of children to this node - enables O(1) lookups on this level
    private HashMap<Character, Node> children = new HashMap<>();
    // Reference to parent node lets us construct a word from a node by traversing upwards from the node.
    private Node parent;
    // If this nodes represents the end of a word, we mark it as a sentinel node
    private boolean sentinel;
    // Every time a sentinel is created on a node, we increment the number of occurrences. This gives us frequency
    private int occurrence = 0;

    /**
     * Empty constructor - default 0 occurrences, and false sentinel
     */
    Node() {
    }

    /**
     * Constructor that sets character and defaults
     * @param character Node char
     */
    Node(char character) {
        this.character = character;
        this.sentinel = false;
    }

    /**
     * Increment the number of occurrences on a node by 1
     */
    void incrementOccurrence() {
        this.setOccurrence(this.getOccurrence() + 1);
    }

    // Getters and Setters
    HashMap<Character, Node> getChildren() {
        return this.children;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isSentinel() {
        return sentinel;
    }

    void setSentinel(boolean isSentinel) {
        this.sentinel = isSentinel;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
