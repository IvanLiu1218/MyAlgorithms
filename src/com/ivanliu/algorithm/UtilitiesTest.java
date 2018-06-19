package com.ivanliu.algorithm;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UtilitiesTest {

    @Test
    public void testQuickSort1() {
        int[] nums = new int[] {3,2,5,1,0,4};
        Utilities.quickSort(nums, 0, nums.length);
        assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(nums));

        nums = new int[] {5,2,3,1,0,4};
        Utilities.quickSort(nums, 0, nums.length);
        assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(nums));

        nums = new int[] {2,0,2,3,1};
        Utilities.quickSort(nums, 0, nums.length);
        assertEquals("[0, 1, 2, 2, 3]", Arrays.toString(nums));
    }

    @Test
    public void testQuickSort2() {
        int[] nums = new int[] {3,2,5,1,0,4};
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        Utilities.quickSort(nums, 0, nums.length, index);
        assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(nums));
        assertEquals("[4, 3, 1, 0, 5, 2]", Arrays.toString(index));

        nums = new int[] {5,2,3,1,0,4};
        index = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        Utilities.quickSort(nums, 0, nums.length, index);
        assertEquals("[0, 1, 2, 3, 4, 5]", Arrays.toString(nums));
        assertEquals("[4, 3, 1, 2, 5, 0]", Arrays.toString(index));

        nums = new int[] {2,0,2,3,1};
        index = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        Utilities.quickSort(nums, 0, nums.length, index);
        assertEquals("[0, 1, 2, 2, 3]", Arrays.toString(nums));
//        assertEquals("[1, 4, 2, 0, 3]", Arrays.toString(index));
        assertThat(Arrays.toString(index), anyOf(equalTo("[1, 4, 2, 0, 3]"), equalTo("[1, 4, 0, 2, 3]")));
    }
}