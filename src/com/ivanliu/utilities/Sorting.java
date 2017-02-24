package com.ivanliu.utilities;

public class Sorting {
	
	private static void swap(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	public static class InsertSort {
		
		public static int[] directInsertSort(int[] array) {
			for (int i = 1; i < array.length; ++i) {
				int toInsert = array[i];
				int j = i - 1;
				for (; j >= 0 && array[j] > toInsert; --j) {
					array[j + 1] = array[j];
				}
				array[j + 1] = toInsert;
			}
			return array;
		}
		
		public static int[] binaryInsertSort(int[] array) {
			for (int i = 1; i < array.length; ++i) {
				int toInsert = array[i];
				int low = 0;
				int high = i - 1;
				int mid = 0;
				while (low <= high) {
					mid = (low + high) / 2;
					if (array[mid] > toInsert) {
						high = mid - 1;
					} else {
						low = mid + 1;
					}
				}
				for (int j = i - 1; j >= low; --j) {
					array[j + 1] = array[j];
				}
				array[low] = toInsert;
			}
			return array;
		}
		
		public static int[] shellSort(int[] array) {
			int d = array.length;
			while (d >= 1) {
				d = d / 2;
				for (int x = 0; x < d; ++x) {
					for (int i = x + d; i < array.length; i = i + d) {
						int toInsert = array[i];
						int j = i - d;
						for (; j >= 0 && array[j] > toInsert; j = j - d) {
							array[j + d] = array[j];
						}
						array[j + d] = toInsert;
					}
				}
			}
			return array;
		}
	}
	
	public static class SelectSort {
		
		public static int[] directSelectSort(int[] array) {
			for (int i = 0; i < array.length; ++i) {
				int minValue = array[i];
				int minIndex = i;
				for (int j = i + 1; j < array.length; ++j) {
					if (array[j] < minValue) {
						minValue = array[j];
						minIndex = j;
					}
				}
				array[minIndex] = array[i];
				array[i] = minValue;
			}
			return array;
		}
		
		public static int[] heapSort(int[] array) {
			for (int i = array.length - 1; i >= 1; --i) {
				buildMaxHeap(array, i);
				swap(array, 0, i);
			}
			return array;
		}
		
		private static int getParentIndex(int index) {
			return (index - 1) / 2;
		}
		
		private static int getLeftIndex(int index) {
			return index * 2 + 1;
		}
		
		private static int getRightIndex(int index) {
			return index * 2 + 2;
		}
		
		/* 
		 * The structure of heap:
		 * 
		 *        [0]
		 *       /   \
		 *     [1]    [2]
		 *    /  \    /  \
		 *   [3] [4] [5] [6]
		 *  
		 *  [i] represents the index of array
		 */
		private static void buildMaxHeap(int[] data, int lastIndex) {
			int i = getParentIndex(lastIndex);
			for (; i >= 0; --i) {
				int current = i;
				int left = getLeftIndex(current);
				while (left <= lastIndex) {
					int biggerIndex = left;
					if (left < lastIndex) { // the right node does exist
						int right = getRightIndex(current);
						if (data[left] < data[right]) {
							biggerIndex = right;
						}
					}
					if (data[current] < data[biggerIndex]) {
						swap(data, current, biggerIndex);
						current = biggerIndex;
					} else {
						break;
					}
					left = getLeftIndex(current);
				}
			}
		}
	}
	
	public static class ChangeSort {
		
		public static int[] bubbleSort(int[] array) {
			for (int i = 0; i < array.length - 1; ++i) {
				for (int j = i + 1; j < array.length; ++j) {
					if (array[i] > array[j]) {
						swap(array, i, j);
					}
				}
			}
			return array;
		}
		
		public static int[] quickSort(int[] array) {
			quickSort(array, 0, array.length - 1);
			return array;
		}
		
		private static void quickSort(int[] data, int low, int high) {
			if (low > high) return;
			int keyIndex = getKeyIndex(data, low, high);
			quickSort(data, low, keyIndex - 1);
			quickSort(data, keyIndex + 1, high);
		}
		
		private static int getKeyIndex(int[] data, int low, int high) {
			int i = low;
			int j = high;
			int keyValue = data[i];
			while (i < j) {
				while (i < j && data[j] >= keyValue) --j;
				data[i] = data[j];
				while (i < j && data[i] <= keyValue) ++i;
				data[j] = data[i];
			}
			data[i] = keyValue;
			return i;
		}
	}
}
