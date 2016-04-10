package rtk.lab.predictor;

import java.util.HashMap;

/**
 * Created by ransom on 4/8/16.
 */
class Node {
    private char character;
    private HashMap<Character, Node> children = new HashMap<>();
    private Node parent;
    private boolean sentinel;
    private int occurrence = 0;

    Node() {
    }

    Node(char character) {
        this.character = character;
        this.sentinel = false;
    }

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

    void incrementOccurrence() {
        this.setOccurrence(this.getOccurrence() + 1);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
