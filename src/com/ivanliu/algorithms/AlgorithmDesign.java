package com.ivanliu.algorithms;

import java.util.Random;

public class AlgorithmDesign {

    public int[] randomPermutation(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; ++i) {
            array[i] = i;
        }
        Random r = new Random();
        for (int i = 0; i < size - 1; ++i) {
            int x = i;
            int y = r.nextInt(size - i) + i;  // random[i, n)
            swap(array, x, y);
        }
        return array;
    }

    private void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}
