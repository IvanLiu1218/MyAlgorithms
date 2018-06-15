package com.ivanliu.algorithms.models;

import com.ivanliu.algorithms.models.SuffixTrie;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.ivanliu.algorithms.models.SuffixTrie.SuffixTrieNode;

public class SuffixTrieTest {

    @Test
    public void testCreateSuffixTrie() {
        SuffixTrie t = new SuffixTrie("abaaba");
    }

    @Test
    public void testIsSubString() {
        SuffixTrie t = new SuffixTrie("abaaba");

        assertTrue(t.isSubString("abaaba"));
        assertTrue(t.isSubString("abaab"));
        assertTrue(t.isSubString("baab"));
        assertTrue(t.isSubString("baa"));

        assertFalse(t.isSubString("aaaba"));
    }

    @Test
    public void testIsSuffix() {
        SuffixTrie t = new SuffixTrie("abaaba");
        assertTrue(t.isSuffix("abaaba"));
        assertTrue(t.isSuffix("baaba"));
        assertTrue(t.isSuffix("aaba"));
        assertTrue(t.isSuffix("aba"));
        assertTrue(t.isSuffix("ba"));
        assertTrue(t.isSuffix("a"));
        assertTrue(t.isSuffix(""));

        assertFalse(t.isSuffix("abaab"));
        assertFalse(t.isSuffix("baab"));
    }

    @Test
    public void testNumberOf() {
        SuffixTrie t = new SuffixTrie("abaaba");

        assertEquals(4, t.numberOf("a"));
        assertEquals(2, t.numberOf("b"));
        assertEquals(2, t.numberOf("aba"));
        assertEquals(1, t.numberOf("abaa"));
        assertEquals(1, t.numberOf("baa"));
        assertEquals(1, t.numberOf("aaba"));
        assertEquals(0, t.numberOf("babb"));
    }

    @Test
    public void testCountLeafNode() {
        SuffixTrie t = new SuffixTrie("abaaba");
        assertEquals(7, t.countLeafNode(t.root));
        assertEquals(4, t.countLeafNode(t.root.children.get('a')));
        assertEquals(2, t.countLeafNode(t.root.children.get('b')));
        assertEquals(1, t.countLeafNode(t.root.children.get('$')));
        SuffixTrieNode node = t.root.children.get('a');
        assertEquals(2, t.countLeafNode(node.children.get('b')));
        assertEquals(1, t.countLeafNode(node.children.get('a')));
        assertEquals(1, t.countLeafNode(node.children.get('$')));
        node = t.root.children.get('b');
        assertEquals(2, t.countLeafNode(node.children.get('a')));
    }

    @Test
    public void testLDS() {
        SuffixTrie t = new SuffixTrie("abaaba");
        assertEquals("aba", t.lds());

        t = new SuffixTrie("abaaaaba");
        assertEquals("aba", t.lds());
    }
}