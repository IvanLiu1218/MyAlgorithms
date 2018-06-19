package com.ivanliu.algorithm;

import java.util.Random;

public class Utilities {

    public static void quickSort(int[] nums, int from, int to) {
        if (from >= to) return;
        int flagIndex = random(from, to);
        int flag = nums[flagIndex];
        swap(nums, from, flagIndex);
        int i = from;
        int j = to - 1;
        while (i < j) {
            while (i < j && nums[j] > flag) --j;
            nums[i] = nums[j];
            while (i < j && nums[i] <= flag) ++i;
            nums[j] = nums[i];
        }
        nums[i] = flag;
        quickSort(nums, from, i);
        quickSort(nums, i + 1, to);
        return;
    }

    public static void quickSort(int[] nums, int from, int to, int[] index) {
        if (from >= to) return;
        int flagIndex = random(from, to);
        int flag = nums[flagIndex];
        swap(nums, from, flagIndex);
        swap(index, from, flagIndex);
        flagIndex = index[from];  //<<--- !!!
        int i = from;
        int j = to - 1;
        while (i < j) {
            while (i < j && nums[j] > flag) --j;
            nums[i] = nums[j];
            index[i] = index[j];
            while (i < j && nums[i] <= flag) ++i;
            nums[j] = nums[i];
            index[j] = index[i];
        }
        nums[i] = flag;
        index[i] = flagIndex;
        quickSort(nums, from, i, index);
        quickSort(nums, i + 1, to, index);
        return;
    }

    public static int random(int from, int to) {
        return new Random().nextInt(to - from) + from;
    }

    public static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
