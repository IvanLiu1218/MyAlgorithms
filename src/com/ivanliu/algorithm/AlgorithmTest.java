package com.ivanliu.algorithm;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AlgorithmTest {

    private Algorithm algorithm = new Algorithm();

    @Test
    public void testLIS() {  // Longest Increasing Sequences
        int[] nums = new int[]{2,4,3,5,1,7,6,9,8};
        int[] seq = algorithm.longestIncSequence(nums);
        assertEquals(5, seq.length);
        assertEquals("[2, 4, 5, 7, 9]", Arrays.toString(seq));

    }

    @Test
    public void testEditDistance() {
        String s = "thou shalt not";
        String t = "you should not";
        assertEquals(5, algorithm.compareString(s, t));
    }

}