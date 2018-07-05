package com.ivanliu.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testReverse() {
        int[] nums = new int[4];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = i;
        }
        System.out.println(Arrays.toString(nums));
        Utilities.reverse(nums, 0, nums.length);
        System.out.println(Arrays.toString(nums));

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Utilities.reverse(nums, 1, nums.length);
        System.out.println(Arrays.toString(nums));
    }
    @Test
    public void testNextPermutation() {
        int size = 4;
        int[] nums = new int[size];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = i;
        }
        do {
            System.out.println(Arrays.toString(nums));
        } while (Utilities.nextPermutation(nums, 0, nums.length));
    }

    @Test
    public void testAllPermutation() {
        int[] nums = new int[] {1,2,0,3};
        List<int[]> all = Utilities.allPermutation(nums);
        System.out.println(all.size());
        for (int[] array : all) {
            System.out.println(Arrays.toString(array));
        }
    }

    @Test
    public void testPrevPermutation() {
        int[] nums = new int[] {3,2,1,0};
        do {
            System.out.println(Arrays.toString(nums));
        } while (Utilities.prevPermutation(nums, 0, nums.length));
    }

    @Test
    public void testIntegerPartition1() {
        assertEquals(627, Utilities.integerPartition(20, 20));
    }

    @Test
    public void testIntegerPartition2() {
        List<List<Integer>> all = Utilities.integerPartition(1);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        all = Utilities.integerPartition(2);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        all = Utilities.integerPartition(3);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        all = Utilities.integerPartition(4);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        all = Utilities.integerPartition(5);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));

        all = Utilities.integerPartition(6);
        all.stream().forEach(list -> System.out.println(Arrays.toString(list.toArray())));
    }

    @Test
    public void testSetPartition1() {
        assertEquals( 1, Utilities.setPartition(4, 1));
        assertEquals( 7, Utilities.setPartition(4, 2));
        assertEquals( 6, Utilities.setPartition(4, 3));
        assertEquals( 1, Utilities.setPartition(4, 4));
    }

    @Test
    public void testSetPartition2() {
        assertEquals(15, Utilities.setPartition(4));
    }

    @Test
    public void testSetPartition3() {
        int[] nums = new int[] {1,2,3,4};
        List<List<List<Integer>>> all = Utilities.setPartition(nums, 2);
        for (int i = 0; i < all.size(); ++i) {
            System.out.println(Arrays.toString(all.get(i).toArray()));
        }

//        nums = new int[] {1,2,3,4};
//        all = Utilities.setPartition(nums, 3);
//        for (int i = 0; i < all.size(); ++i) {
//            System.out.println(Arrays.toString(all.get(i).toArray()));
//        }
    }

    @Test
    public void testSetPartition4() {
        int[] nums = new int[] {1,2};
        List<List<List<Integer>>> all = Utilities.setPartition(nums);
        for (int i = 0; i < all.size(); ++i) {
            System.out.println(Arrays.toString(all.get(i).toArray()));
        }
    }

    @Test
    public void testGCD() {
        assertEquals(1, Utilities.gcd(5, 9));
        assertEquals(1, Utilities.gcd(9, 5));
        assertEquals(3, Utilities.gcd(12, 9));
        assertEquals(3, Utilities.gcd(9, 12));
        assertEquals(4, Utilities.gcd(16, 12));
        assertEquals(4, Utilities.gcd(12, 16));
        assertEquals(12, Utilities.gcd(12, 12));
    }

    @Test
    public void testLCM() {
        assertEquals(2, Utilities.lcm(1, 2));
        assertEquals(6, Utilities.lcm(3, 2));
        assertEquals(6, Utilities.lcm(3, 6));
        assertEquals(6, Utilities.lcm(2, 6));
        assertEquals(18, Utilities.lcm(9, 6));
        assertEquals(18, Utilities.lcm(9, 2));
        assertEquals(9, Utilities.lcm(9, 3));
        assertEquals(36, Utilities.lcm(9, 12));
    }
}