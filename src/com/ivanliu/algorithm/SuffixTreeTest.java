package com.ivanliu.algorithm;

import com.ivanliu.algorithm.SuffixTree;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.*;
import static com.ivanliu.algorithm.SuffixTree.SuffixTreeNode;

public class SuffixTreeTest {

    @Test
    public void testCreateSuffixTrie() {
        SuffixTree t = new SuffixTree("abaaba");
    }

    @Test
    public void testIsSubString() {
        SuffixTree t = new SuffixTree("abaaba");

        assertTrue(t.isSubString("abaaba"));
        assertTrue(t.isSubString("abaab"));
        assertTrue(t.isSubString("baab"));
        assertTrue(t.isSubString("baa"));

        assertFalse(t.isSubString("aaaba"));
        assertFalse(t.isSubString("abbaaba"));
    }

    @Test
    public void testIsSuffix() {
        SuffixTree t = new SuffixTree("abaaba");
        assertTrue(t.isSuffix("abaaba"));
        assertTrue(t.isSuffix("baaba"));
        assertTrue(t.isSuffix("aaba"));
        assertTrue(t.isSuffix("aba"));
        assertTrue(t.isSuffix("ba"));
        assertTrue(t.isSuffix("a"));
        assertTrue(t.isSuffix(""));

        assertFalse(t.isSuffix("abaab"));
        assertFalse(t.isSuffix("baab"));
        assertFalse(t.isSuffix("bba"));
    }

    @Test
    public void testrFequency() {
        SuffixTree t = new SuffixTree("abaaba");
        assertEquals(4, t.frequency("a"));
        assertEquals(2, t.frequency("b"));
        assertEquals(2, t.frequency("aba"));
        assertEquals(1, t.frequency("abaa"));
        assertEquals(1, t.frequency("baa"));
        assertEquals(1, t.frequency("aaba"));
        assertEquals(0, t.frequency("babb"));

        t = new SuffixTree("abaaaaba");
        assertEquals(1, t.frequency("aaaa"));
        assertEquals(2, t.frequency("aaa"));  //?

        t = new SuffixTree("abababa");
        assertEquals(3, t.frequency("aba"));
    }

    @Test
    public void testCountLeafNode() {
        SuffixTree t = new SuffixTree("abaaba");
        assertEquals(7, t.countLeafNode(t.root));
        assertEquals(4, t.countLeafNode(t.root.children.get('a')));
        assertEquals(2, t.countLeafNode(t.root.children.get('b')));
        assertEquals(1, t.countLeafNode(t.root.children.get('$')));
        SuffixTreeNode node = t.root.children.get('a');
        assertEquals(2, t.countLeafNode(node.children.get('b')));
        assertEquals(1, t.countLeafNode(node.children.get('a')));
        assertEquals(1, t.countLeafNode(node.children.get('$')));
        node = t.root.children.get('b');
        assertEquals(2, t.countLeafNode(node.children.get('a')));
    }

    @Test
    public void testLDS() {
        SuffixTree t = new SuffixTree("abaaba");
        assertEquals("aba", t.lds());

        t = new SuffixTree("abaaaba");
        assertEquals("aba", t.lds());

        t = new SuffixTree("abaacaaba");
        assertEquals("aba", t.lds());
//        assertThat(t.lds(), anyOf(equalTo("aaa"), equalTo("aba")));
    }

    @Test
    public void testLCS() {
        SuffixTree t = new SuffixTree("abaaba");
        assertEquals("baa", t.lcs("bbaa"));
        assertEquals("ba", t.lcs("bba"));
        assertEquals("aab", t.lcs("aabb"));
    }

    @Test
    public void testAllSub() {
        SuffixTree t = new SuffixTree("abaaba");
        String[] result = t.findAllSubstring("aba");
        //assertEquals(4, result.length);
        System.out.println(Arrays.toString(result));
    }
}