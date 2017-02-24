package com.ivanliu.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SortingTest extends Sorting {
	
	private int[] input  = null;
	private int[] output = null;

	@Before
	public void before() {
		input  = new int[] {11,3,5,1,9,7,6,2,8};
		output = new int[] {1,2,3,5,6,7,8,9,11};
	}
	
	@Test
	public void testDirectInsertSort() {
		assertArrayEquals(output, InsertSort.directInsertSort(input));
	}
	
	@Test
	public void testBinaryInsertSort() {
		assertArrayEquals(output, InsertSort.binaryInsertSort(input));
	}
	
	@Test
	public void testShellSort() {
		assertArrayEquals(output, InsertSort.shellSort(input));
	}
	
	@Test
	public void testDirectSelectSort() {
		assertArrayEquals(output, SelectSort.directSelectSort(input)); 
	}
	
	@Test
	public void testHeapSort() {
		assertArrayEquals(output, SelectSort.heapSort(input));
	}
	
	@Test
	public void testBubbleSort() {
		assertArrayEquals(output, ChangeSort.bubbleSort(input));
	}
	
	@Test
	public void testQuickSort() {
		assertArrayEquals(output, ChangeSort.quickSort(input));
	}
}
