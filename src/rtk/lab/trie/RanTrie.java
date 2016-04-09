package rtk.lab.trie;

import java.util.HashMap;

/**
 * Created by ransom on 4/8/16.
 */
public class RanTrie {
    private Node root;

    public RanTrie() {
        this.root = new Node();
    }


    public void add(String word) {
        HashMap<Character, Node> children = root.getChildren();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            Node node;
            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new Node(c);
                children.put(c, node);
            }

            children = node.getChildren();
            if (i == (word.length() - 1)) {
                node.setSentinel(true);
                node.incrementOccurrence();
            }
        }
    }

    public String[] searchPrefix(String prefix) {
        return null;
    }

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
}
