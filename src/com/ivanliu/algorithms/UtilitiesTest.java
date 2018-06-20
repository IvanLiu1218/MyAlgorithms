package com.ivanliu.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class UtilitiesTest {

    @Test
    public void testRandomPermutation() {
        int[] array = Utilities.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = Utilities.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = Utilities.randomPermutation(9);
        System.out.println(Arrays.toString(array));

        array = Utilities.randomPermutation(9);
        System.out.println(Arrays.toString(array));
    }

}