package rtk.lab.predictor;

import java.util.*;

/**
 * Created by ransom on 4/8/16.
 */
public class RanTrie {
    private Node root;

    public RanTrie() {
        this.root = new Node();
    }

    /**
     *
     * @param word
     */
    public void add(String word) {
        HashMap<Character, Node> children = root.getChildren();
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node;
            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new Node(c);
                node.setParent(current);
                children.put(c, node);
            }

            current = node;
            children = node.getChildren();
            if (i == (word.length() - 1)) {
                node.setSentinel(true);
                node.incrementOccurrence();
            }
        }
    }

    /**
     *
     * @param word
     * @return if word is in trie
     */
    public boolean hasWord(String word) {
        Node node = this.search(word);
        return (node != null && node.isSentinel());
    }

    /**
     *
     * @param needle
     * @return Node to needle
     */
    public Node search(String needle) {
        HashMap<Character, Node> children = root.getChildren();
        Node result = null;
        char current;

        for (int i = 0; i < needle.length(); i++) {
            current = needle.charAt(i);
            if (children.containsKey(current)) {
                result = children.get(current);
                children = result.getChildren();
            } else {
                return null;
            }
        }
        return result;
    }

    /**
     *
     * @param node
     * @return
     */
    public List<ICandidate> getCandidates(Node node) {
        List<ICandidate> result = new ArrayList<>();

        HashMap<Character, Node> children = node.getChildren();
        if (node.isSentinel()) result.add(this.nodeToCandidate(node));

        Iterator it = children.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            result.addAll(this.getCandidates((Node)pair.getValue()));
            it.remove();
        }

        return result;
    }

    /**
     *
     * @param node
     * @return
     */
    private ICandidate nodeToCandidate(Node node) {
        ICandidateImpl c = new ICandidateImpl();
        c.setWord(this.getWord(node));
        c.setConfidence(node.getOccurrence());

        return c;
    }

    /**
     *
     * @param string
     * @return
     */
    public List<ICandidate> getCandidates(String string) {
        return getCandidates(this.search(string));
    }

    /**
     *
     * @param node
     * @return
     */
    public String getWord(Node node) {
        StringBuilder sb = new StringBuilder();
        Node current = node;
        while (current != this.root) {
            sb.append(current.getCharacter());
            current = current.getParent();
        }
        return sb.reverse().toString();
    }
}
