package com.ivanliu.algorithm;

import java.util.*;

public class SuffixTree {

    public static class SuffixTreeNode {
        public char currentValue;
        public SuffixTreeNode suffixPointer;
        Map<Character, SuffixTreeNode> children;

        public SuffixTreeNode(char c) {
            this(c, null);
        }

        public SuffixTreeNode(char c, SuffixTreeNode pointer) {
            this.currentValue = c;
            this.suffixPointer = pointer;
            this.children = new HashMap<>();
        }
    }

    private final char HEAD = '*';
    private final char TAIL = '$';
    public SuffixTreeNode root;

    public SuffixTree(String s) {
        this.root = new SuffixTreeNode(HEAD);
        this.createSuffixTrie(s);
    }

//    public SuffixTree(String[] ss) {
//        this.root = new SuffixTreeNode(HEAD);
//        for (int i = 0; i < ss.length; ++i) {
//            this.createSuffixTrie(ss[i]);
//        }
//    }

    private void createSuffixTrie(String s) {
        s += TAIL;
        SuffixTreeNode current = root;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            current = addChild(current, c);
        }
    }

    private SuffixTreeNode addChild(SuffixTreeNode node, char c) {
        if (node.suffixPointer == null) {
            if (!node.children.containsKey(c)) {
                SuffixTreeNode newNode = new SuffixTreeNode(c, node);
                node.children.put(c, newNode);
            }
            return node.children.get(c);
        }
        SuffixTreeNode suffixNode = addChild(node.suffixPointer, c);
        if (!node.children.containsKey(c)) {
            SuffixTreeNode newNode = new SuffixTreeNode(c, suffixNode);
            node.children.put(c, newNode);
        }
        return node.children.get(c);
    }

    // Check if q is subString of S
    public boolean isSubString(String q) {
        SuffixTreeNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return true;
    }

    // Check if q is the suffix of s
    public boolean isSuffix(String q) {
        q += TAIL;
        SuffixTreeNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return false;
            }
            node = node.children.get(c);
        }
        return node.currentValue == TAIL;
    }

    // Get the frequency number of q in s
    public int frequency(String q) {
        SuffixTreeNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            if (!node.children.containsKey(c)) {
                return 0;
            }
            node = node.children.get(c);
        }
        return countLeafNode(node);
    }

    public int countLeafNode(SuffixTreeNode node) {
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
    private String lds;
    public String lds() {
        lds = "*";
        String str = Character.toString(root.currentValue);
        search(root, str);
        return lds.substring(1);
    }

    private void search(SuffixTreeNode node, String str) {
        if (node.children.size() == 0) {
            return;
        }
        if (node.children.size() >= 2 && str.length() > lds.length()) {
            lds = new String(str);
        }
        for (char c : node.children.keySet()) {
            SuffixTreeNode n = node.children.get(c);
            search(n, str + c);
        }

    }

    // Longest Common Substring
    private String lcs;
    public String lcs(String q) {
        lcs = "";
        String cs = "";
        SuffixTreeNode node = root;
        for (int i = 0; i < q.length(); ++i) {
            char c = q.charAt(i);
            cs += c;
            if (!node.children.containsKey(c)) {
                node = node.suffixPointer;
                cs = cs.substring(1);
            } else {
                node = node.children.get(c);
            }
            if (cs.length() > lcs.length()) {
                lcs = cs;
            }
        }
        return lcs;
    }

    // Find all substrings of s which contains q   ???
    private List<String> substringList;
    public String[] findAllSubstring(String q) {
        substringList = new ArrayList<>();
        SuffixTreeNode node = root;
//        while (node != null) {
            for (int i = 0; i < q.length(); ++i) {
                char c = q.charAt(i);
                if (!node.children.containsKey(c)) {
                    break;
                }
                node = node.children.get(c);
            }
            dfs(node, q);
//            node = node.suffixPointer;
//        }
        return substringList.stream().toArray(String[]::new);
    }
    private void dfs(SuffixTreeNode node, String str) {
        if (node.children.size() == 0) {
            return;
        }
        substringList.add(new String(str));
        for (char c : node.children.keySet()) {
            dfs(node.children.get(c), str + c);
        }
    }
}
