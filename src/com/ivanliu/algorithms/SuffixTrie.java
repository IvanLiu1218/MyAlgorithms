package com.ivanliu.algorithms;

import java.util.HashMap;
import java.util.Map;

public class SuffixTrie {

    public static class SuffixTrieNode {
        public char currentValue;
        public SuffixTrieNode suffixPointer;
        Map<Character, SuffixTrieNode> children;

        public SuffixTrieNode(char c) {
            this(c, null);
        }

        public SuffixTrieNode(char c, SuffixTrieNode pointer) {
            this.currentValue = c;
            this.suffixPointer = pointer;
            this.children = new HashMap<>();
        }
    }

    private final char HEAD = '*';
    private final char TAIL = '$';
    public SuffixTrieNode root;

    public SuffixTrie(String s) {
        this.root = new SuffixTrieNode(HEAD);
        this.createSuffixTrie(s);
    }

    private void createSuffixTrie(String s) {
        s += TAIL;
        SuffixTrieNode current = root;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            current = addChild(current, c);
        }
    }

    private SuffixTrieNode addChild(SuffixTrieNode node, char c) {
        if (node.suffixPointer == null) {
            if (!node.children.containsKey(c)) {
                SuffixTrieNode newNode = new SuffixTrieNode(c, node);
                node.children.put(c, newNode);
            }
            return node.children.get(c);
        }
        SuffixTrieNode suffixNode = addChild(node.suffixPointer, c);
        if (!node.children.containsKey(c)) {
            SuffixTrieNode newNode = new SuffixTrieNode(c, suffixNode);
            node.children.put(c, newNode);
        }
        return node.children.get(c);
    }

    public boolean isSubString(String q) {
        SuffixTrieNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return true;
    }

    public boolean isSuffix(String q) {
        q += TAIL;
        SuffixTrieNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.currentValue == TAIL;
    }

    public int numberOf(String q) {
        SuffixTrieNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return 0;
            }
            node = node.children.get(c);
        }
        return countLeafNode(node);
    }

    public int countLeafNode(SuffixTrieNode node) {
        if (node.children.size() == 0) {
            return 1;
        }
        int num = 0;
        for (char c : node.children.keySet()) {
            num += countLeafNode(node.children.get(c));
        }
        return num;
    }

    //  longest duplicated substring
    private String lds = "";
    public String lds() {
        lds = "";
        search(root, lds);
        return lds.substring(1);
    }

    private void search(SuffixTrieNode node, String str) {
        if (node.children.size() == 0) {
            return;
        }
        String temp = str + node.currentValue;
        int num = countLeafNode(node);
        if (num >= 2) {
            if (temp.length() > lds.length()) {
                lds = temp;
            }
        }
        for (char c : node.children.keySet()) {
            search(node.children.get(c), temp);
        }
    }

}
