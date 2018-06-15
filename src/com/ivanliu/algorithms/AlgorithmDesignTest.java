package com.ivanliu.algorithms;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AlgorithmDesignTest {

    private AlgorithmDesign ad = new AlgorithmDesign();

    @Test
    public void testRandomPermutation() {
        int[] array = ad.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = ad.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = ad.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = ad.randomPermutation(9);
        System.out.println(Arrays.toString(array));
    }
}