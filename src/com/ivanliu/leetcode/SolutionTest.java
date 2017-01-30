package com.ivanliu.leetcode;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.ivanliu.leetcode.Solution.MinStack;
import com.ivanliu.leetcode.Solution.MyQueue;
import com.ivanliu.leetcode.Solution.MyStack;
import com.ivanliu.leetcode.Solution.NumArray;
import com.ivanliu.leetcode.Utility.ListNode;
import com.ivanliu.leetcode.Utility.TreeLinkNode;
import com.ivanliu.leetcode.Utility.TreeNode;

public class SolutionTest {
	private Solution solution = new Solution();
	
	@Test
	public void test001() {
		assertArrayEquals(new int[] {0,1}, solution.twoSum(new int[] {2,7,11,15}, 9));
		assertArrayEquals(new int[] {0,3}, solution.twoSum(new int[] {2,7,11,4}, 6));
		assertArrayEquals(new int[] {1,2}, solution.twoSum(new int[] {3,2,4}, 6));
	}
	
	@Test
	public void test002() {
		ListNode l1 = Utility.buildListNode(new int[] {2,4,3});
		ListNode l2 = Utility.buildListNode(new int[] {5,6,4});
		ListNode result = solution.addTwoNumbers(l1, l2);
		assertEquals("[7,0,8]", Utility.toString(result));
		
		l1 = Utility.buildListNode(new int[] {9,9,9,9,9,9,9});
		l2 = Utility.buildListNode(new int[] {1});
		result = solution.addTwoNumbers(l1, l2);
		assertEquals("[0,0,0,0,0,0,0,1]", Utility.toString(result));
		
		l1 = Utility.buildListNode(new int[] {1});
		l2 = Utility.buildListNode(new int[] {9,9,9,9,9,9,9});
		result = solution.addTwoNumbers(l1, l2);
		assertEquals("[0,0,0,0,0,0,0,1]", Utility.toString(result));
		
		l1 = Utility.buildListNode(new int[] {});
		l2 = Utility.buildListNode(new int[] {1,2,3});
		result = solution.addTwoNumbers(l1, l2);
		assertEquals("[1,2,3]", Utility.toString(result));
	}
	
	@Test
	public void test003() {
		assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
		assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
		assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
		assertEquals(1, solution.lengthOfLongestSubstring("c"));
		assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
		assertEquals(2, solution.lengthOfLongestSubstring("abba"));
		// Wrong Answer
		assertEquals(5, solution.lengthOfLongestSubstring("tmmzuxt"));
	}
	
	@Test
	public void test004() {
		// odd
//		assertEquals(2, solution.findMedianSortedArrays(new int[]{1,3}, new int[] {2}), 0);
//		assertEquals(3, solution.findMedianSortedArrays(new int[]{1,3,5,6}, new int[] {2}), 0);
//		assertEquals(5, solution.findMedianSortedArrays(new int[]{1,3,5,6}, new int[] {2,7,8}), 0);
		// even
//		assertEquals(2.5d, solution.findMedianSortedArrays(new int[]{1,3}, new int[] {2,4}), 0);
//		assertEquals(3.5d, solution.findMedianSortedArrays(new int[]{1,3,5,6}, new int[] {2,4}), 0);
//		assertEquals(4d, solution.findMedianSortedArrays(new int[]{1,3,5,6,7}, new int[] {2}), 0);
	}
	
	@Test
	public void test006() {
		assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
		assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
		assertEquals("PAYPALISHIRING", solution.convert("PAYPALISHIRING", 1));
		assertEquals("A", solution.convert("A", 1));
	}
	
	@Test
	public void test007() {
		assertEquals(123, solution.reverse(321));
		assertEquals(-123, solution.reverse(-321));
		assertEquals(0, solution.reverse(1534236469));
		assertEquals(0, solution.reverse(2147483643));
		assertEquals(7654321, solution.reverse(1234567));
	}
	
	@Test
	public void test008() {
		assertEquals(123459, solution.myAtoi("+123459skeo3"));
		assertEquals(123459, solution.myAtoi("   +123459skeo3"));
		assertEquals(Integer.MAX_VALUE, solution.myAtoi("+2147483649"));
		assertEquals(Integer.MIN_VALUE, solution.myAtoi("-2147483649"));
	}
	
	@Test
	public void test009() {
		assertEquals(false, solution.isPalindrome(-12321));
		assertEquals(true, solution.isPalindrome(0));
		assertEquals(true, solution.isPalindrome(1234554321));
		assertEquals(true, solution.isPalindrome(123454321));
	}
	
	@Test
	public void test012() {
		assertEquals("I", solution.intToRoman(1));
		assertEquals("IV", solution.intToRoman(4));
		assertEquals("VII", solution.intToRoman(7));
		assertEquals("XIX", solution.intToRoman(19));
		assertEquals("MMMCMXCIX", solution.intToRoman(3999));
	}
	
	@Test
	public void test013() {
		assertEquals(4, solution.romanToInt("IV"));
		assertEquals(3999, solution.romanToInt("MMMCMXCIX"));
	}
	
	@Test
	public void test014() {
		assertEquals("ab", solution.longestCommonPrefix(new String[] {"abc","ab","abcd"}));
		assertEquals("a", solution.longestCommonPrefix(new String[] {"abc","ad","abcd"}));
		assertEquals("", solution.longestCommonPrefix(new String[] {}));
	}
	
	@Test
	public void test019() {
		ListNode head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		ListNode result = solution.removeNthFromEnd(head, 1);
		assertEquals("[1,2,3,4,5]", Utility.toString(result));
		
		head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		result = solution.removeNthFromEnd(head, 2);
		assertEquals("[1,2,3,4,6]", Utility.toString(result));
		
		head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		result = solution.removeNthFromEnd(head, 3);
		assertEquals("[1,2,3,5,6]", Utility.toString(result));
		
		head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		result = solution.removeNthFromEnd(head, 4);
		assertEquals("[1,2,4,5,6]", Utility.toString(result));
		
		head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		result = solution.removeNthFromEnd(head, 5);
		assertEquals("[1,3,4,5,6]", Utility.toString(result));
		
		head = Utility.buildListNode(new int[] {1,2,3,4,5,6});
		result = solution.removeNthFromEnd(head, 6);
		assertEquals("[2,3,4,5,6]", Utility.toString(result));
	}
	
	@Test
	public void test020() {
		assertEquals(true, solution.isValid("({[]})"));
		assertEquals(true, solution.isValid("({[][]}())"));
		assertEquals(false, solution.isValid("({[][}]())"));
		assertEquals(false, solution.isValid("]"));
		assertEquals(false, solution.isValid("(])"));
	}
	
	@Test
	public void test021() {
		ListNode l1 = new ListNode(1, new ListNode(6, new ListNode(7, null)));
		ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(5, new ListNode(8, null))));
		assertEquals("[1,2,3,5,6,7,8]", Utility.toString(solution.mergeTwoLists(l1, l2)));
	}
	
	@Test
	public void test022() {
		List<String> result = solution.generateParenthesis(3);
		assertEquals(5, result.size());
		assertEquals("[((())), (()()), (())(), ()(()), ()()()]", Arrays.toString(result.toArray()));
	}
	
	@Test
	public void test023() {
		ListNode[] lists = new ListNode[4];
		lists[0] = new ListNode(1, new ListNode(9, null));
		lists[1] = new ListNode(2, new ListNode(6, new ListNode(7, null)));
		lists[2] = new ListNode(3, null);
		lists[3] = new ListNode(4, new ListNode(5, new ListNode(8, new ListNode(11, null))));
		assertEquals("[1,2,3,4,5,6,7,8,9,11]", Utility.toString(solution.mergeKLists(lists)));
	}
	
	@Test
	public void test024() {
		ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
		assertEquals("[2,1,4,3]", Utility.toString(solution.swapPairs(list)));
		list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
		assertEquals("[2,1,4,3,5]", Utility.toString(solution.swapPairs(list)));
		list = new ListNode(1, null);
		assertEquals("[1]", Utility.toString(solution.swapPairs(list)));
		list = null;
		assertEquals("[]", Utility.toString(solution.swapPairs(list)));
	}
	
	@Test
	public void test026() {
		int[] array = new int[] {1,1,2};
		assertEquals(2, solution.removeDuplicates(array));
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		array = new int[]{1,1,2,2,2,2,2,2,3,3};
		assertEquals(3, solution.removeDuplicates(array));
		assertEquals(1, array[0]);
		assertEquals(2, array[1]);
		assertEquals(3, array[2]);
	}
	
	@Test
	public void test027() {
		assertEquals(2, solution.removeElement(new int[] {3,2,1,3}, 3));
		assertEquals(3, solution.removeElement(new int[] {3,2,1,0}, 3));
		assertEquals(3, solution.removeElement(new int[] {0,2,1,3}, 3));
		// Runtime Error
		assertEquals(0, solution.removeElement(new int[] {3,3}, 3));
		// Wrong Answer
		assertEquals(0, solution.removeElement(new int[] {1}, 1));
		assertEquals(1, solution.removeElement(new int[] {2,2,3}, 2));
		assertEquals(0, solution.removeElement(new int[] {2,2,2}, 2));
	}
	
	@Test
	public void test028() {
		assertEquals(3, solution.strStr("abcdefg", "def"));
		assertEquals(0, solution.strStr("abcdefg", "abc"));
		assertEquals(1, solution.strStr("aabcdefg", "abc"));
		assertEquals(-1, solution.strStr("abcdefg", "abd"));
		assertEquals(0, solution.strStr("a", "a"));
		assertEquals(-1, solution.strStr("a", "ab"));
		assertEquals(0, solution.strStr("ab", "ab"));
		assertEquals(0, solution.strStr("", ""));
		assertEquals(-1, solution.strStr("aaa", "aaaa"));
		assertEquals(-1, solution.strStr("aaaa", "aaab"));
	}
	@Test
	public void test033() {
		assertEquals(-1, solution.search(new int[]{5,6,7,0,1,2,3,4}, 8));
		assertEquals(7, solution.search(new int[]{5,6,7,0,1,2,3,4}, 4));
	}
	
	@Test
	public void test035() {
		assertEquals(2, solution.searchInsert(new int[]{1,3,5,6}, 5));
		assertEquals(1, solution.searchInsert(new int[]{1,3,5,6}, 2));
		assertEquals(4, solution.searchInsert(new int[]{1,3,5,6}, 7));
		assertEquals(0, solution.searchInsert(new int[]{1,3,5,6}, 0));
	}
	
	@Test
	public void test036() {
		char[][] board = new char[][] { {'5','3','.','.','7','.','.','.','.'},
			                            {'6','.','.','1','9','5','.','.','.'},
			                            {'.','9','8','.','.','.','.','.','.'},
			                            {'8','.','.','.','6','.','.','.','3'},
			                            {'4','.','.','8','.','3','.','.','1'},
			                            {'7','.','.','.','2','.','.','.','6'},
			                            {'.','6','.','.','.','.','2','8','.'},
			                            {'.','.','.','4','1','9','.','.','5'},
			                            {'.','.','.','.','8','.','.','7','9'},
			                          };
		assertEquals(true, solution.isValidSudoku(board));
	}
	
	@Test
	public void test038() {
		assertEquals("1", solution.countAndSay(1));
		assertEquals("11", solution.countAndSay(2));
		assertEquals("21", solution.countAndSay(3));
		assertEquals("1211", solution.countAndSay(4));
		assertEquals("111221", solution.countAndSay(5));
		assertEquals("312211", solution.countAndSay(6));
		assertEquals("13112221", solution.countAndSay(7));
		assertEquals("1113213211", solution.countAndSay(8));
	}
	
	@Test
	public void test046() {
		List<List<Integer>> llist = solution.permute(new int[]{1,2,3});
		assertEquals(6, llist.size());
	}
	
	@Test
	public void test058() {
		assertEquals(5, solution.lengthOfLastWord("Hello World"));
		assertEquals(10, solution.lengthOfLastWord("HelloWorld"));
		assertEquals(5, solution.lengthOfLastWord("Hello World "));
	}
	
	@Test
	public void test059() {
		int[][] matrix = solution.generateMatrix(3);
		assertEquals("[1, 2, 3]", Arrays.toString(matrix[0]));
		assertEquals("[8, 9, 4]", Arrays.toString(matrix[1]));
		assertEquals("[7, 6, 5]", Arrays.toString(matrix[2]));
		matrix = solution.generateMatrix(4);
		assertEquals("[1, 2, 3, 4]", Arrays.toString(matrix[0]));
		assertEquals("[12, 13, 14, 5]", Arrays.toString(matrix[1]));
		assertEquals("[11, 16, 15, 6]", Arrays.toString(matrix[2]));
		assertEquals("[10, 9, 8, 7]", Arrays.toString(matrix[3]));
	}
	
	@Test
	public void test062() {
		assertEquals(28, solution.uniquePaths(3, 7));
		assertEquals(21, solution.uniquePaths(3, 6));
	}
	
	@Test
	public void test064() {
		assertEquals(12, solution.minPathSum(new int[][]{{2,2,2,2},{2,1,1,2},{2,2,2,2},{2,2,2,2}}));
		assertEquals(16, solution.minPathSum(new int[][]{{2,5,7,8},{5,5,6,1},{7,1,7,1},{8,1,1,1}}));
	}
	
	@Test
	public void test066() {
		assertEquals("[1, 0, 0, 0, 0, 0]", Arrays.toString(solution.plusOne(new int[]{9,9,9,9,9})));
	}

	@Test
	public void test067() {
		assertEquals("100", solution.addBinary("1", "11"));
		assertEquals("100", solution.addBinary("11", "1"));
		assertEquals("110", solution.addBinary("101", "1"));
	}
	
	@Test
	public void test070() {
		assertEquals(1, solution.climbStairs(1));
		assertEquals(2, solution.climbStairs(2));
		assertEquals(3, solution.climbStairs(3));
		assertEquals(5, solution.climbStairs(4));
	}
	
	@Test
	public void test073() {
		int[][] matrix = new int[4][5];
		matrix[0] = new int[] {1,1,1,1,1};
		matrix[1] = new int[] {1,0,1,1,1};
		matrix[2] = new int[] {1,1,1,1,1};
		matrix[3] = new int[] {1,1,1,1,1};
		solution.setZeroes(matrix);
		assertEquals("[1, 0, 1, 1, 1]", Arrays.toString(matrix[0]));
		assertEquals("[0, 0, 0, 0, 0]", Arrays.toString(matrix[1]));
		assertEquals("[1, 0, 1, 1, 1]", Arrays.toString(matrix[2]));
		assertEquals("[1, 0, 1, 1, 1]", Arrays.toString(matrix[3]));
		matrix[0] = new int[] {0,1,1,1,1};
		matrix[1] = new int[] {1,1,1,1,1};
		matrix[2] = new int[] {1,1,1,1,1};
		matrix[3] = new int[] {1,1,1,1,1};
		solution.setZeroes(matrix);
		assertEquals("[0, 0, 0, 0, 0]", Arrays.toString(matrix[0]));
		assertEquals("[0, 1, 1, 1, 1]", Arrays.toString(matrix[1]));
		assertEquals("[0, 1, 1, 1, 1]", Arrays.toString(matrix[2]));
		assertEquals("[0, 1, 1, 1, 1]", Arrays.toString(matrix[3]));
		matrix[0] = new int[] {1,1,1,1,1};
		matrix[1] = new int[] {1,1,1,1,1};
		matrix[2] = new int[] {1,1,1,1,1};
		matrix[3] = new int[] {1,1,1,1,0};
		solution.setZeroes(matrix);
		assertEquals("[1, 1, 1, 1, 0]", Arrays.toString(matrix[0]));
		assertEquals("[1, 1, 1, 1, 0]", Arrays.toString(matrix[1]));
		assertEquals("[1, 1, 1, 1, 0]", Arrays.toString(matrix[2]));
		assertEquals("[0, 0, 0, 0, 0]", Arrays.toString(matrix[3]));
	}
	
	@Test
	public void test074() {
		int[][] matrix = new int[3][4];
		matrix[0] = new int[] { 1, 2, 3, 4};
		matrix[1] = new int[] { 6, 7, 8, 9};
		matrix[2] = new int[] {12,15,18,20};
		assertEquals(true, solution.searchMatrix(matrix, 3));
		assertEquals(false, solution.searchMatrix(matrix, 5));
		assertEquals(false, solution.searchMatrix(matrix, 10));
		assertEquals(false, solution.searchMatrix(matrix, 16));
		assertEquals(false, solution.searchMatrix(matrix, 21));
	}
	
	@Test
	public void test075() {
		int[] nums = new int[] {2,1,1,1,2,0,2,1};
		solution.sortColors(nums);
		assertEquals("[0, 1, 1, 1, 1, 2, 2, 2]", Arrays.toString(nums));
		nums = new int[] {1,1,1,1,1,1,1,1};
		solution.sortColors(nums);
		assertEquals("[1, 1, 1, 1, 1, 1, 1, 1]", Arrays.toString(nums));
	}
	
	@Test
	public void test077() {
		List<List<Integer>> llist = solution.combine(4, 2);
		assertEquals(6, llist.size());
		assertEquals("[1, 2]", Arrays.toString(llist.get(0).toArray()));
		assertEquals("[1, 3]", Arrays.toString(llist.get(1).toArray()));
		assertEquals("[1, 4]", Arrays.toString(llist.get(2).toArray()));
		assertEquals("[2, 3]", Arrays.toString(llist.get(3).toArray()));
		assertEquals("[2, 4]", Arrays.toString(llist.get(4).toArray()));
		assertEquals("[3, 4]", Arrays.toString(llist.get(5).toArray()));
		llist = solution.combine(4, 3);
		assertEquals(4, llist.size());
		assertEquals("[1, 2, 3]", Arrays.toString(llist.get(0).toArray()));
		assertEquals("[1, 2, 4]", Arrays.toString(llist.get(1).toArray()));
		assertEquals("[1, 3, 4]", Arrays.toString(llist.get(2).toArray()));
		assertEquals("[2, 3, 4]", Arrays.toString(llist.get(3).toArray()));
	}
	
	@Test
	public void test078() {
		List<List<Integer>> llist = solution.subsets(new int[] {1, 2, 3});
		assertEquals(8, llist.size());
		assertEquals("[]", Arrays.toString(llist.get(0).toArray()));
		assertEquals("[3]", Arrays.toString(llist.get(1).toArray()));
		assertEquals("[2]", Arrays.toString(llist.get(2).toArray()));
		assertEquals("[1]", Arrays.toString(llist.get(3).toArray()));
		assertEquals("[2, 3]", Arrays.toString(llist.get(4).toArray()));
		assertEquals("[1, 3]", Arrays.toString(llist.get(5).toArray()));
		assertEquals("[1, 2]", Arrays.toString(llist.get(6).toArray()));
		assertEquals("[1, 2, 3]", Arrays.toString(llist.get(7).toArray()));
	}
	
	@Test
	public void test080() {
		int[] nums = new int[]{1,1,1,2,2,3};
		assertEquals(5, solution.removeDuplicatesII(nums));
		assertEquals(2, nums[2]);
	}
	
	@Test
	public void test081() {
		assertEquals(true,  solution.searchII(new int[]{5,5,6,7,8,8,1,1,2,3,4}, 5));
		assertEquals(false, solution.searchII(new int[]{5,5,6,7,8,8,1,2,3,3,4}, 9));
	}
	
	@Test
	public void test082() {
		// 1->2->3->3->4->4->5
		ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
		ListNode resList = solution.deleteDuplicatesII(list);
		assertEquals("[1,2,5]", Utility.toString(resList));
		// 1->1->1->2->3
		list = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
		resList = solution.deleteDuplicatesII(list);
		assertEquals("[2,3]", Utility.toString(resList));
		// 1->1->1
		list = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1))));
		resList = solution.deleteDuplicatesII(list);
		assertEquals("[]", Utility.toString(resList));
	}
	
	@Test
	public void test083() {
		// 1->1->2
		ListNode list = new ListNode(1, new ListNode(1, new ListNode(2)));
		ListNode resList = solution.deleteDuplicates(list);
		assertEquals("[1,2]", Utility.toString(resList));
		// 1->1->2->3->3
		list = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
		resList = solution.deleteDuplicates(list);
		assertEquals("[1,2,3]", Utility.toString(resList));
	}
	
	@Test
	public void test088() {
		int[] nums1 = new int[] {1,4,7,8,0,0,0};
		int[] nums2 = new int[] {2,3,5};
		solution.merge(nums1, 4, nums2, 3);
		assertEquals("[1, 2, 3, 4, 5, 7, 8]", Arrays.toString(nums1));
	}
	
	@Test
	public void test089() {
		assertEquals("[0, 1, 3, 2]", Arrays.toString(solution.grayCode(2).toArray()));
		assertEquals("[0, 1, 3, 2, 6, 7, 5, 4]", Arrays.toString(solution.grayCode(3).toArray()));
	}
	
	@Test
	public void test090() {
		List<List<Integer>> llist = solution.subsetsWithDup(new int[] {1,2,2});
		assertEquals(6, llist.size());
		assertEquals("[]", Arrays.toString(llist.get(0).toArray()));
		assertEquals("[2]", Arrays.toString(llist.get(1).toArray()));
		assertEquals("[1]", Arrays.toString(llist.get(2).toArray()));
		assertEquals("[2, 2]", Arrays.toString(llist.get(3).toArray()));
		assertEquals("[1, 2]", Arrays.toString(llist.get(4).toArray()));
		assertEquals("[1, 2, 2]", Arrays.toString(llist.get(5).toArray()));
	}
	
	@Test
	public void test092() {
		// 1->2->3->4->5->NULL, m = 2 and n = 4
		ListNode list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		ListNode resList = solution.reverseBetween(list, 2, 4);
		assertEquals("[1,4,3,2,5]", Utility.toString(resList));
	}
	
	@Test
	public void test094() {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(3);
		List<Integer> list = solution.inorderTraversal(root);
		assertEquals("[1, 3, 2]", Arrays.toString(list.toArray()));
	}
	
	@Test
	public void test100() {
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(1);
		root1.left.left = new TreeNode(1);
		root1.right = new TreeNode(1);
		root1.right.left = new TreeNode(1);
		root1.right.right = new TreeNode(1);
		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(1);
		root2.left.left = new TreeNode(1);
		root2.right = new TreeNode(1);
		root2.right.left = new TreeNode(1);
		root2.right.right = new TreeNode(1);
		
		assertEquals(true, solution.isSameTree(root1, root2));
		
		root2 = new TreeNode(1);
		root2.left = new TreeNode(1);
		root2.left.left = new TreeNode(1);
		root2.left.right = new TreeNode(1);
		root2.right = new TreeNode(1);
		root2.right.right = new TreeNode(1);
		assertEquals(false, solution.isSameTree(root1, root2));
		
		// Runtime Error
		assertEquals(true, solution.isSameTree(null, null));
		// Wrong Answer
		root1 = new TreeNode(1);
		root1.left = new TreeNode(1);
		root2 = new TreeNode(1);
		root2.right = new TreeNode(1);
		assertEquals(false, solution.isSameTree(root1, root2));
	}
	
	@Test
	public void test101() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(2);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		assertEquals(true, solution.isSymmetric(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(1);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(1);
		assertEquals(true, solution.isSymmetric(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(1);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(1);
		assertEquals(false, solution.isSymmetric(root));
		
		// Wrong Answer: [5,4,1,null,1,null,4,2,null,2,null]
		root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(1);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(2);
		assertEquals(false, solution.isSymmetric(root));
	}
	
	@Test
	public void test102() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> resList = solution.levelOrder(root);
		assertEquals(3, resList.size());
		assertEquals("[3]", Arrays.toString(resList.get(0).toArray()));
		assertEquals("[9, 20]", Arrays.toString(resList.get(1).toArray()));
		assertEquals("[15, 7]", Arrays.toString(resList.get(2).toArray()));
	}
	
	@Test
	public void test103() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> resList = solution.zigzagLevelOrder(root);
		assertEquals(3, resList.size());
		assertEquals("[3]", Arrays.toString(resList.get(0).toArray()));
		assertEquals("[20, 9]", Arrays.toString(resList.get(1).toArray()));
		assertEquals("[15, 7]", Arrays.toString(resList.get(2).toArray()));
	}
	
	@Test
	public void test104() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		assertEquals(3, solution.maxDepth(root));
	}
	
	@Test
	public void test105() {
		int[] preorder = new int[] {1,2,4,5,3,6,7};
		int[] inorder = new int[] {4,2,5,1,6,3,7};
		assertEquals("[1,2,3,4,5,6,7,null,null,null,null,null,null,null,null]", Utility.toString(solution.buildTree(preorder, inorder)));
	}
	
	@Test
	public void test106() {
		int[] inorder = new int[] {4,2,5,1,6,3,7};
		int[] postorder = new int[] {4,5,2,6,7,3,1};
		assertEquals("[1,2,3,4,5,6,7,null,null,null,null,null,null,null,null]", Utility.toString(solution.buildTreeII(inorder, postorder)));
	}
	
	@Test
	public void test107() {
		TreeNode head = new TreeNode(3);
		head.left = new TreeNode(9);
		head.right = new TreeNode(20);
		head.right.left = new TreeNode(15);
		head.right.right = new TreeNode(7);
		List<List<Integer>> result = solution.levelOrderBottom(head);
		assertEquals(3, result.size());
		assertEquals("[15, 7]", Arrays.toString(result.get(0).toArray()));
		assertEquals("[9, 20]", Arrays.toString(result.get(1).toArray()));
		assertEquals("[3]", Arrays.toString(result.get(2).toArray()));
		
		head = new TreeNode(3);
		head.left = new TreeNode(9);
		head.right = new TreeNode(20);
		head.left.left = new TreeNode(15);
		head.right.right = new TreeNode(7);
		result = solution.levelOrderBottom(head);
		assertEquals(3, result.size());
		assertEquals("[15, 7]", Arrays.toString(result.get(0).toArray()));
		assertEquals("[9, 20]", Arrays.toString(result.get(1).toArray()));
		assertEquals("[3]", Arrays.toString(result.get(2).toArray()));
		
		head = new TreeNode(3);
		head.right = new TreeNode(20);
		head.right.left = new TreeNode(15);
		head.right.right = new TreeNode(7);
		result = solution.levelOrderBottom(head);
		assertEquals(3, result.size());
		assertEquals("[15, 7]", Arrays.toString(result.get(0).toArray()));
		assertEquals("[20]", Arrays.toString(result.get(1).toArray()));
		assertEquals("[3]", Arrays.toString(result.get(2).toArray()));
	}
	
	@Test
	public void test110() {
		TreeNode head = new TreeNode(0);
		head.left = new TreeNode(1);
		head.right = new TreeNode(1);
		head.right.left = new TreeNode(2);
		head.right.right = new TreeNode(2);
		head.right.right.right = new TreeNode(3);
		assertEquals(false, solution.isBalanced(head));
		
		head = new TreeNode(0);
		head.left = new TreeNode(1);
		head.right = new TreeNode(1);
		head.right.left = new TreeNode(2);
		head.right.right = new TreeNode(2);
		assertEquals(true, solution.isBalanced(head));
		
		head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.left.left = new TreeNode(3);
		head.left.left.left = new TreeNode(4);
		head.right = new TreeNode(2);
		head.right.right = new TreeNode(3);
		head.right.right.right = new TreeNode(4);
		assertEquals(false, solution.isBalanced(head));
	}
	
	@Test
	public void test111() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		assertEquals(2, solution.minDepth(root));
	}
	
	@Test
	public void test112() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(1);
		assertEquals(true, solution.hasPathSum(root, 22));
	}
	
	@Test
	public void test113() {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		root.right.right.left = new TreeNode(5);
		root.right.right.right = new TreeNode(1);
		List<List<Integer>> resList = solution.pathSumII(root, 22);
		assertEquals(2, resList.size());
		assertEquals("[5, 4, 11, 2]", Arrays.toString(resList.get(0).toArray()));
		assertEquals("[5, 8, 4, 5]", Arrays.toString(resList.get(1).toArray()));
	}
	
	@Test
	public void test114() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		solution.flatten(root);
		TreeNode node = root;
		int i = 1;
		while (node != null) {
			assertEquals(i++, node.val);
			node = node.right;
		}
	}
	
	@Test
	public void test115() {
		assertEquals(3, solution.numDistinct("rabbbit", "rabbit"));
		assertEquals(6, solution.numDistinct("rabbbbit", "rabbit"));
		assertEquals(6, solution.numDistinct("iirabbbit", "irabbit"));
	}
	
	@Test
	public void test116() {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.left.left = new TreeLinkNode(4);
		root.left.right = new TreeLinkNode(5);
		root.right = new TreeLinkNode(3);
		root.right.left = new TreeLinkNode(6);
		root.right.right = new TreeLinkNode(7);
		solution.connect(root);
		assertEquals(3, root.left.next.val);
		assertEquals(7, root.left.left.next.next.next.val);
	}
	
	@Test
	public void test117() {
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.left.left = new TreeLinkNode(4);
		root.left.right = new TreeLinkNode(5);
		root.right = new TreeLinkNode(3);
		root.right.right = new TreeLinkNode(7);
		solution.connectII(root);
		assertEquals(1, root.val);
		assertEquals(3, root.left.next.val);
		assertEquals(7, root.left.left.next.next.val);
	}
	
	@Test
	public void test118() {
		List<List<Integer>> resList = solution.generate(5);
		assertEquals(5, resList.size());
		assertEquals("[1]", Arrays.toString(resList.get(0).toArray()));
		assertEquals("[1, 1]", Arrays.toString(resList.get(1).toArray()));
		assertEquals("[1, 2, 1]", Arrays.toString(resList.get(2).toArray()));
		assertEquals("[1, 3, 3, 1]", Arrays.toString(resList.get(3).toArray()));
		assertEquals("[1, 4, 6, 4, 1]", Arrays.toString(resList.get(4).toArray()));
	}
	
	@Test
	public void test119() {
		List<Integer> resList = solution.getRow(3);
		assertEquals("[1, 3, 3, 1]", Arrays.toString(resList.toArray()));
	}
	
	@Test
	public void test120() {
		List<List<Integer>> llist = new ArrayList<>();
		llist.add(new ArrayList<>());
		llist.add(new ArrayList<>());
		llist.add(new ArrayList<>());
		llist.add(new ArrayList<>());
		llist.get(0).add(2);
		llist.get(1).add(3);
		llist.get(1).add(4);
		llist.get(2).add(6);
		llist.get(2).add(5);
		llist.get(2).add(7);
		llist.get(3).add(4);
		llist.get(3).add(1);
		llist.get(3).add(8);
		llist.get(3).add(3);
		assertEquals(11, solution.minimumTotal(llist));
	}
	
	@Test
	public void test121() {
		assertEquals(5, solution.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
		assertEquals(0, solution.maxProfit(new int[] {7, 6, 4, 3, 1}));
		// Time Limit Exceeded
	}
	
	@Test
	public void test125() {
		assertEquals(true, solution.isPalindrome("A man, a plan, a canal: Panama"));
		assertEquals(false, solution.isPalindrome("race a car"));
		assertEquals(false, solution.isPalindrome("0P"));
	}
	
	@Test
	public void test129() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		assertEquals(25, solution.sumNumbers(root));
		root.right.right = new TreeNode(3);
		assertEquals(145, solution.sumNumbers(root));
	}
	
	@Test
	public void test134() {
		int[] gas = new int[] {2,4};
		int[] cost = new int[] {3,4};
		assertEquals(-1, solution.canCompleteCircuit(gas, cost));
		// Time Limit Exceeded
		gas = new int[] {2,0,1,2,3,4,0};
		cost = new int[] {0,1,0,0,0,0,11};
		assertEquals(0, solution.canCompleteCircuit(gas, cost));
	}
	
	@Test
	public void test136() {
		assertEquals(4, solution.singleNumber(new int[] {3,6,7,4,3,7,6}));
	}
	
	@Test
	public void test141() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
		assertEquals(false, solution.hasCycle(head));
		ListNode joint = new ListNode(7, new ListNode(8, new ListNode(9)));
		joint.next.next.next = joint;
		head.next.next.next = joint;
		assertEquals(true, solution.hasCycle(head));
	}
	
	@Test
	public void test142() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
		assertEquals(null, solution.detectCycle(head));
		ListNode joint = new ListNode(7, new ListNode(8, new ListNode(9)));
		joint.next.next.next = joint;
		head.next.next.next = joint;
		assertEquals(joint, solution.detectCycle(head));
	}
	
	@Test
	public void test143() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
		solution.reorderList(head);
		assertEquals("[1,6,2,5,3,4]", Utility.toString(head));
	}
	
	@Test
	public void test144() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		assertEquals("[1, 2, 4, 3, 6, 7]", Arrays.toString(solution.preorderTraversal(root).toArray()));
	}
	
	@Test
	public void test145() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		assertEquals("[4, 2, 6, 7, 3, 1]", Arrays.toString(solution.postorderTraversal(root).toArray()));
	}
	
	@Test
	public void test147() {
		ListNode list = new ListNode(4, new ListNode(2, new ListNode(8, new ListNode(1))));
		list = solution.insertionSortList(list);
		assertEquals("[1,2,4,8]", Utility.toString(list));
		list = new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1))));
		list = solution.insertionSortList(list);
		assertEquals("[1,2,3,4]", Utility.toString(list));
		list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		list = solution.insertionSortList(list);
		assertEquals("[1,2,3,4]", Utility.toString(list));
		list = new ListNode(2, new ListNode(2, new ListNode(2, new ListNode(2))));
		list = solution.insertionSortList(list);
		assertEquals("[2,2,2,2]", Utility.toString(list));
		// Runtime Error
		list = solution.insertionSortList(null);
		assertEquals("[]", Utility.toString(list));
		// Wrong Answer: [-2147483647,-2147483648]
		list = new ListNode(-2147483647, new ListNode(-2147483648));
		list = solution.insertionSortList(list);
		assertEquals("[-2147483648,-2147483647]", Utility.toString(list));
	}
	
	@Test
	public void test148() {
		ListNode list = new ListNode(4, new ListNode(2, new ListNode(8, new ListNode(1))));
		list = solution.sortList(list);
		assertEquals("[1,2,4,8]", Utility.toString(list)); 
	}
	
	@Test
	public void test150() {
		assertEquals(9, solution.evalRPN(new String[]{"2","1","+","3","*"}));
		assertEquals(6, solution.evalRPN(new String[]{"4","13","5","/","+"}));
	}
	
	@Test
	public void test151() {
		assertEquals("World Hello", solution.reverseWords("Hello World"));
		assertEquals("Ivan is name My", solution.reverseWords("My name is Ivan"));
		assertEquals("Monday is Today", solution.reverseWords("Today  is Monday  "));
		assertEquals("sad so am I", solution.reverseWords("    I am so sad   "));
	}
	
	@Test
	public void test155() {
		MinStack minStack = solution.new MinStack();
		minStack.push(4);
		assertEquals(4, minStack.top());
		assertEquals(4, minStack.getMin());
		minStack.push(-1);
		assertEquals(-1, minStack.top());
		assertEquals(-1, minStack.getMin());
		minStack.push(2);
		assertEquals(2, minStack.top());
		assertEquals(-1, minStack.getMin());
		minStack.push(0);
		assertEquals(0, minStack.top());
		assertEquals(-1, minStack.getMin());
		minStack.pop();
		assertEquals(2, minStack.top());
		assertEquals(-1, minStack.getMin());
		minStack.pop();
		assertEquals(-1, minStack.top());
		assertEquals(-1, minStack.getMin());
		minStack.pop();
		assertEquals(4, minStack.top());
		assertEquals(4, minStack.getMin());
		
	}
	
	@Test
	public void test160() {
		ListNode com = Utility.buildListNode(new int[] {1,2,3});
		ListNode headA = new ListNode(1, com);
		ListNode headB = new ListNode(1, new ListNode(1, com));
		assertEquals(com, solution.getIntersectionNode(headA, headB));
		
		headA = new ListNode(1, new ListNode(1, new ListNode(1, com)));
		headB = new ListNode(1, com);
		assertEquals(com, solution.getIntersectionNode(headA, headB));
		
		headA = new ListNode(1, new ListNode(1, new ListNode(1, com)));
		headB = new ListNode(1, new ListNode(1, com.next));
		assertEquals(com.next, solution.getIntersectionNode(headA, headB));
		
		// no intersection
		headA = Utility.buildListNode(new int[] {1,2,3});
		headB = Utility.buildListNode(new int[] {4,5,6});
		assertEquals(null, solution.getIntersectionNode(headA, headB));
	}
	
	@Test
	public void test165() {
		assertEquals(1, solution.compareVersion("1.1", "0.1"));
		assertEquals(0, solution.compareVersion("1.1", "1.1"));
		assertEquals(-1, solution.compareVersion("0.1", "1.1"));
		assertEquals(1, solution.compareVersion("1.21.1", "1.11.34"));
		assertEquals(1, solution.compareVersion("1.11.33", "1.11.3"));
		assertEquals(-1, solution.compareVersion("1.11.3", "1.12.32"));
		assertEquals(-1, solution.compareVersion("1", "1.1"));
		assertEquals(0, solution.compareVersion("1", "1.0"));
		assertEquals(0, solution.compareVersion("1.0.0", "1"));
		assertEquals(1, solution.compareVersion("1.0.0.1", "1"));
		assertEquals(-1, solution.compareVersion("1", "1.0.1"));
	}
	
	@Test
	public void test168() {
		assertEquals("A", solution.convertToTitle(1));
		assertEquals("Z", solution.convertToTitle(26));
		assertEquals("AA", solution.convertToTitle(27));
		assertEquals("AZ", solution.convertToTitle(52));
		assertEquals("BA", solution.convertToTitle(53));
		assertEquals("AAA", solution.convertToTitle(703));
		assertEquals("AAZ", solution.convertToTitle(728));
		assertEquals("ZY", solution.convertToTitle(701));
	}
	
	@Test
	public void test169() {
		assertEquals(1, solution.majorityElement(new int[] {1,1,1,2,3}));
		assertEquals(2, solution.majorityElement(new int[] {1,2,4,2,2}));
	}
	
	@Test
	public void test171() {
		assertEquals(1, solution.titleToNumber("A"));
		assertEquals(26, solution.titleToNumber("Z"));
		assertEquals(28, solution.titleToNumber("AB"));
		assertEquals(701, solution.titleToNumber("ZY"));
		assertEquals(703, solution.titleToNumber("AAA"));
	}
	
	@Test
	public void test172() {
		assertEquals(1, solution.trailingZeroes(6));
		assertEquals(2, solution.trailingZeroes(10));
		assertEquals(3, solution.trailingZeroes(19));
		assertEquals(4, solution.trailingZeroes(22));
		assertEquals(6, solution.trailingZeroes(26));
	}
	
	@Test
	public void test189() {
		int[] nums = new int[] {1,2,3,4,5,6,7};
		solution.rotate(nums, 3);
		assertEquals("[5, 6, 7, 1, 2, 3, 4]", Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6,7,8};
		solution.rotate(nums, 3);
		assertEquals("[6, 7, 8, 1, 2, 3, 4, 5]", Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6};
		solution.rotate(nums, 2);
		assertEquals("[5, 6, 1, 2, 3, 4]", Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6,7,8,9};
		solution.rotate(nums, 3);
		assertEquals("[7, 8, 9, 1, 2, 3, 4, 5, 6]", Arrays.toString(nums));
		
		nums = new int[] {1,2,3,4,5,6};
		solution.rotate(nums, 4);
		assertEquals("[3, 4, 5, 6, 1, 2]", Arrays.toString(nums));
	}
	
	@Test
	public void test190() {
		assertEquals(964176192, solution.reverseBits(43261596));
		assertEquals(1073741824, solution.reverseBits(2));
		assertEquals(2, solution.reverseBits(1073741824));
		long value = 2147483648L;
		assertEquals(1, solution.reverseBits((int)value));
		assertEquals((int)value, solution.reverseBits(1));
		value = 4294967295L;
		assertEquals((int)value, solution.reverseBits((int)value));
	}
	
	@Test
	public void test191() {
		assertEquals(3, solution.hammingWeight(11));
		long value = 2147483648L;  // 0x7FFF_FFFF
		assertEquals(1, solution.hammingWeight((int)value));
		value = 4294967295L;  // 0xFFFF_FFFF
		assertEquals(32, solution.hammingWeight((int)value));
	}
	
	@Test
	public void test202() {
		assertEquals(true, solution.isHappy(19));
		assertEquals(true, solution.isHappy(1));
		assertEquals(true, solution.isHappy(1000));
		assertEquals(false, solution.isHappy(17));
	}
	
	@Test
	public void test203() {
		ListNode list = null;
		assertEquals("[]", Utility.toString(solution.removeElements(list, 2)));
		list = new ListNode(1, new ListNode(2, new ListNode(3, null)));
		assertEquals("[1,3]", Utility.toString(solution.removeElements(list, 2)));
		list = new ListNode(1, new ListNode(2, new ListNode(3, null)));
		assertEquals("[2,3]", Utility.toString(solution.removeElements(list, 1)));
		list = new ListNode(2, new ListNode(2, new ListNode(3, null)));
		assertEquals("[3]", Utility.toString(solution.removeElements(list, 2)));
		list = new ListNode(2, new ListNode(2, new ListNode(2, null)));
		assertEquals("[]", Utility.toString(solution.removeElements(list, 2)));
	}
	
	@Test
	public void test204() {
		// test countPrimes_isPrime():
//		assertEquals(false, solution.countPrimes_isPrime(0));
//		assertEquals(false, solution.countPrimes_isPrime(1));
//		assertEquals(true, solution.countPrimes_isPrime(2));
//		assertEquals(true, solution.countPrimes_isPrime(3));
//		assertEquals(false, solution.countPrimes_isPrime(4));
//		assertEquals(true, solution.countPrimes_isPrime(5));
//		assertEquals(true, solution.countPrimes_isPrime(11));
//		assertEquals(false, solution.countPrimes_isPrime(12));
//		assertEquals(true, solution.countPrimes_isPrime(13));
		// test countPrimes()
		assertEquals(0, solution.countPrimes(0));
		assertEquals(0, solution.countPrimes(1));
		assertEquals(0, solution.countPrimes(2));
		assertEquals(1, solution.countPrimes(3));
		assertEquals(2, solution.countPrimes(4));
		assertEquals(2, solution.countPrimes(5));
		assertEquals(3, solution.countPrimes(6));
		assertEquals(3, solution.countPrimes(7));
		assertEquals(4, solution.countPrimes(8));
		assertEquals(8, solution.countPrimes(20));
		assertEquals(7, solution.countPrimes(19));
		// Time Limit Exceeded
		assertEquals(41537, solution.countPrimes(499979));
		assertEquals(78497, solution.countPrimes(999983));
	}
	
	@Test
	public void test205() {
		assertEquals(true, solution.isIsomorphic("egg", "add"));
		assertEquals(false, solution.isIsomorphic("foo", "bar"));
		assertEquals(true, solution.isIsomorphic("paper", "title"));
		assertEquals(true, solution.isIsomorphic("abbcddeff", "tyyuii899"));
		assertEquals(false, solution.isIsomorphic("aa", "ab"));
		assertEquals(false, solution.isIsomorphic("ab", "aa"));
	}
	
	@Test
	public void test206() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		assertEquals("[4,3,2,1]", Utility.toString(solution.reverseList(head)));
		head = new ListNode(1);
		assertEquals("[1]", Utility.toString(solution.reverseList(head)));
		assertEquals("[]", Utility.toString(solution.reverseList(null)));
	}
	
	@Test
	public void test217() {
		assertEquals(false, solution.containsDuplicate(new int[] {1,2,3,4,5,6}));
		assertEquals(true, solution.containsDuplicate(new int[] {1,2,3,1,5,6}));
		assertEquals(false, solution.containsDuplicate(new int[] {}));
		assertEquals(false, solution.containsDuplicate(null));
	}
	
	@Test
	public void test219() {
		assertEquals(true, solution.containsNearbyDuplicate(new int[]{1,2,3,1,1}, 2));
		assertEquals(false, solution.containsNearbyDuplicate(new int[]{1,2,3,1,0}, 2));
		assertEquals(false, solution.containsNearbyDuplicate(new int[]{}, 2));
		assertEquals(false, solution.containsNearbyDuplicate(null, 2));
		assertEquals(true, solution.containsNearbyDuplicate(new int[]{0,1,2,3,4,5,6,7,7}, 2));
	}
	
	@Test
	public void test223() {
		assertEquals(26, solution.computeArea(-1, -1, 3, 4, 1, -2, 5, 0));
		assertEquals(26, solution.computeArea(1, -2, 5, 0, -1, -1, 3, 4));
		assertEquals(4, solution.computeArea(0, 0, 0, 0, -1, -1, 1, 1));
		assertEquals(4, solution.computeArea(-1, -1, 1, 1,0, 0, 0, 0));
		assertEquals(17, solution.computeArea(-2,-2, 2, 2, 3, 3, 4, 4));
	}
	
	@Test
	public void test225() {
		MyStack stack = solution.new MyStack();
		stack.push(5);
		stack.push(51);
		assertEquals(51, stack.top());
		assertEquals(false, stack.empty());
		stack.pop();
		assertEquals(5, stack.top());
		assertEquals(false, stack.empty());
		stack.pop();
		assertEquals(true, stack.empty());
	}
	
	@Test
	public void test226() {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		root = solution.invertTree(root);
		assertEquals(4, root.val);
		assertEquals(7, root.left.val);
		assertEquals(9, root.left.left.val);
		assertEquals(6, root.left.right.val);
		assertEquals(2, root.right.val);
		assertEquals(3, root.right.left.val);
		assertEquals(1, root.right.right.val);
		// Runtime Error
		root = solution.invertTree(null);
		assertEquals(null, root);
	}
	
	@Test
	public void test231() {
		assertEquals(true, solution.isPowerOfTwo(1));
		assertEquals(true, solution.isPowerOfTwo(2));
		assertEquals(false, solution.isPowerOfTwo(3));
		assertEquals(true, solution.isPowerOfTwo(4));
		assertEquals(false, solution.isPowerOfTwo(6));
		assertEquals(true, solution.isPowerOfTwo(8));
		// Time Limit Exceeded
		assertEquals(false, solution.isPowerOfTwo(0));
	}
	
	@Test
	public void test232() {
		MyQueue queue = solution.new MyQueue();
		queue.push(1);
		queue.push(2);
		queue.push(3);
		assertEquals(1, queue.peek());
		assertEquals(false, queue.empty());
		queue.pop();
		assertEquals(2, queue.peek());
		queue.pop();
		assertEquals(3, queue.peek());
		queue.pop();
		assertEquals(true, queue.empty());
	}
	
	@Test
	public void test234() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
		assertEquals(true, solution.isPalindrome(head));
		head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(1)))));
		assertEquals(true, solution.isPalindrome(head));
		head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(1))));
		assertEquals(false, solution.isPalindrome(head));
		// wrong answer
		head = new ListNode(-1, new ListNode(-1));
		assertEquals(true, solution.isPalindrome(head));
		head = null;
		assertEquals(true, solution.isPalindrome(head));
	}
	
	@Test
	public void test235() {
		TreeNode p = new TreeNode(3);
		TreeNode q = new TreeNode(9);
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = p;
		root.left.right.right = new TreeNode(5);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = q;
		assertEquals(root, solution.lowestCommonAncestor(root, p, q));
		
		root = new TreeNode(6);
		root.right = new TreeNode(8);
		assertEquals(root, solution.lowestCommonAncestor(root, root, root.right));
	}
	
	@Test
	public void test237() {
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
		solution.deleteNode(head.next.next);
		assertEquals("[1,2,4]", Utility.toString(head));
	}
	
	@Test
	public void test242() {
		String s = "anagram";
		String t = "nagaram";
		assertEquals(true, solution.isAnagram(s, t));
		
		s = "rat";
		t = "car";
		assertEquals(false, solution.isAnagram(s, t));
		
		s = "aaaaabb";
		t = "baabaaa";
		assertEquals(true, solution.isAnagram(s, t));
	}
	
	@Test
	public void test257() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(5);
		root.right = new TreeNode(3);
		List<String> result = solution.binaryTreePaths(root);
		assertEquals("1->2->5", result.get(0));
		assertEquals("1->3", result.get(1));
		
		root = new TreeNode(6);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(0);
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(9);
		result = solution.binaryTreePaths(root);
		assertEquals("6->2->0", result.get(0));
		assertEquals("6->2->4->3", result.get(1));
		assertEquals("6->2->4->5", result.get(2));
		assertEquals("6->8->7", result.get(3));
		assertEquals("6->8->9", result.get(4));
		
		// Runtime Error
		result = solution.binaryTreePaths(null);
		assertEquals(0, result.size());
	}
	
	@Test
	public void test258() {
		assertEquals(0, solution.addDigits(0));
		assertEquals(2, solution.addDigits(11));
		assertEquals(7, solution.addDigits(1231));
		assertEquals(9, solution.addDigits(1242));
	}
	
	@Test
	public void test263() {
		assertEquals(true, solution.isUgly(1));
		assertEquals(true, solution.isUgly(2));
		assertEquals(true, solution.isUgly(3));
		assertEquals(true, solution.isUgly(5));
		assertEquals(true, solution.isUgly(6));
		assertEquals(true, solution.isUgly(8));
		assertEquals(false, solution.isUgly(14));
		assertEquals(false, solution.isUgly(33));
		assertEquals(false, solution.isUgly(77));
		// Time Limit Exceeded
		assertEquals(false, solution.isUgly(0));
	}
	
	@Test
	public void test278() {
		solution.firstBadVer = 1;
		assertEquals(1, solution.firstBadVersion(1));
		solution.firstBadVer = 1;
		assertEquals(1, solution.firstBadVersion(2));
		solution.firstBadVer = 4;
		assertEquals(4, solution.firstBadVersion(7));
		solution.firstBadVer = 1702766719;
		assertEquals(1702766719, solution.firstBadVersion(2126753390));
	}
	
	@Test
	public void test283() {
		int[] nums = new int[] {0,1,0,3,12};
		solution.moveZeroes(nums);
		assertEquals("[1, 3, 12, 0, 0]", Arrays.toString(nums));
		nums = new int[] {0,0,0,0,0};
		solution.moveZeroes(nums);
		assertEquals("[0, 0, 0, 0, 0]", Arrays.toString(nums));
		nums = new int[] {1,2,3,4,5};
		solution.moveZeroes(nums);
		assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(nums));
		nums = new int[] {0,0,3,4,5};
		solution.moveZeroes(nums);
		assertEquals("[3, 4, 5, 0, 0]", Arrays.toString(nums));
		nums = new int[] {3,4,5,0,0};
		solution.moveZeroes(nums);
		assertEquals("[3, 4, 5, 0, 0]", Arrays.toString(nums));
	}
	
	@Test
	public void test290() {
		assertEquals(true, solution.wordPattern("abba", "dog cat cat dog"));
		assertEquals(false, solution.wordPattern("abba", "dog cat cat fish"));
		assertEquals(false, solution.wordPattern("aaaa", "dog cat cat dog"));
		assertEquals(false, solution.wordPattern("abba", "dog dog dog dog"));
	}
	
	@Test
	public void test292() {
		assertEquals(true, solution.canWinNim(1));
		assertEquals(true, solution.canWinNim(2));
		assertEquals(true, solution.canWinNim(3));
		assertEquals(false, solution.canWinNim(4));
		assertEquals(true, solution.canWinNim(5));
		assertEquals(true, solution.canWinNim(6));
		assertEquals(true, solution.canWinNim(7));
		assertEquals(false, solution.canWinNim(8));
	}
	
	@Test
	public void test299() {
		assertEquals("1A3B", solution.getHint("1807", "7810"));
		assertEquals("1A1B", solution.getHint("1123", "0111"));
		assertEquals("1A0B", solution.getHint("1111111111", "0010000000"));
		assertEquals("1A1B", solution.getHint("1111111101", "0010000020"));
		// Wrong Answer
		assertEquals("0A4B", solution.getHint("1122", "2211"));
	}
	
	@Test
	public void test303() {
		NumArray nums = solution.new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
		assertEquals(-4, nums.sumRange(0, 3));
		assertEquals(-3, nums.sumRange(0, 5));
		assertEquals( 0, nums.sumRange(1, 4));
		assertEquals(-1, nums.sumRange(1, 5));
		assertEquals( 3, nums.sumRange(2, 2));
		assertEquals(-1, nums.sumRange(2, 5));
		assertEquals(-4, nums.sumRange(3, 5));
		assertEquals( 1, nums.sumRange(4, 5));
		// Time Limit Exceeded
		// Runtime Error if nums is empty;
		//nums = solution.new NumArray(new int[] {});
		//assertEquals(0, nums.sumRange(0, 0));
	}
	
	@Test
	public void test326() {
		assertEquals(true, solution.isPowerOfThree(1));
		assertEquals(false, solution.isPowerOfThree(2));
		assertEquals(true, solution.isPowerOfThree(3));
		assertEquals(true, solution.isPowerOfThree(243));
		assertEquals(true, solution.isPowerOfThree(2187));
		assertEquals(false, solution.isPowerOfThree(2188));
		assertEquals(true, solution.isPowerOfThree(59049));
		assertEquals(false, solution.isPowerOfThree(59149));
		// Time Limit Exceeded
		assertEquals(false, solution.isPowerOfThree(2147483647));
		assertEquals(true, solution.isPowerOfThree(1162261467));
		assertEquals(false, solution.isPowerOfThree(0));
	}
	
	@Test
	public void test338() {
		assertArrayEquals(new int[]{0,1,1,2,1,2}, solution.countBits(5));
		assertArrayEquals(new int[]{0,1,1,2,1,2,2,3}, solution.countBits(7));
		assertArrayEquals(new int[]{0,1,1,2,1,2,2,3,1}, solution.countBits(8));
		// wrong answer: 16
		assertArrayEquals(new int[]{0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1}, solution.countBits(16));
		
	}
	
	@Test
	public void test344() {
		assertEquals("olleh", solution.reverseString("hello"));
		assertEquals("nmlkjihgfedcba", solution.reverseString("abcdefghijklmn"));
	}
	
	@Test
	public void test345() {
		assertEquals("holle", solution.reverseVowels("hello"));
		assertEquals("leotcede", solution.reverseVowels("leetcode"));
		assertEquals("ae", solution.reverseVowels("ea"));
		assertEquals("o", solution.reverseVowels("o"));
		assertEquals("abc", solution.reverseVowels("abc"));
		assertEquals("bac", solution.reverseVowels("bac"));
		assertEquals("bca", solution.reverseVowels("bca"));
		assertEquals("", solution.reverseVowels(""));
		// Wrong Answer
		assertEquals("Aa", solution.reverseVowels("aA"));
	}
	
	@Test
	public void test349() {
		assertEquals("[2]", Arrays.toString(solution.intersection(new int[] {1,2,2,1}, new int[] {2,2})));
		assertEquals("[1, 2]", Arrays.toString(solution.intersection(new int[] {1,2,2,1}, new int[] {2,1})));
		assertEquals("[]", Arrays.toString(solution.intersection(new int[] {}, new int[] {2,2})));
		assertEquals("[]", Arrays.toString(solution.intersection(new int[] {1,2,2,1}, new int[] {})));
	}
	
	@Test
	public void test350() {
		assertEquals("[2, 2]", Arrays.toString(solution.intersect(new int[] {1,2,2,1}, new int[] {2,2})));
		assertEquals("[2, 2]", Arrays.toString(solution.intersect(new int[] {1,2,2,1,2}, new int[] {2,2})));
		assertEquals("[2, 2]", Arrays.toString(solution.intersect(new int[] {1,2,2,1,3}, new int[] {2,2,2})));
	}
	
	@Test
	public void test371() {
		assertEquals(3, solution.getSum(1, 2));
		assertEquals(5, solution.getSum(3, 2));
		assertEquals(32, solution.getSum(12, 20));
	}
	
	@Test
	public void test374() {
		solution.guessNumber_i_pick = 65;
		assertEquals(65, solution.guessNumber(100));
		solution.guessNumber_i_pick = 1;
		assertEquals(1, solution.guessNumber(1));
		solution.guessNumber_i_pick = 66;
		assertEquals(66, solution.guessNumber(66));
		//  Wrong Answer
		solution.guessNumber_i_pick = 1;
		assertEquals(1, solution.guessNumber(2));
		solution.guessNumber_i_pick = 2;
		assertEquals(2, solution.guessNumber(2));
		solution.guessNumber_i_pick = 6;
		assertEquals(6, solution.guessNumber(10));
		solution.guessNumber_i_pick = 1702766719;
		assertEquals(1702766719, solution.guessNumber(2126753390)); // over the Integer.MAX_VALUE
	}
	
	@Test
	public void test383() {
		assertEquals(false, solution.canConstruct("a", "b"));
		assertEquals(false, solution.canConstruct("aa", "ab"));
		assertEquals(true, solution.canConstruct("aa", "aab"));
		assertEquals(true, solution.canConstruct("aa", "aabb"));
		assertEquals(false, solution.canConstruct("aabb", "aab"));
	}
	
	@Test
	public void test387() {
		assertEquals(0, solution.firstUniqChar("leetcode"));
		assertEquals(2, solution.firstUniqChar("loveleetcode"));
		assertEquals(7, solution.firstUniqChar("loveleetcodev"));
		assertEquals(8, solution.firstUniqChar("loveleetcodevt"));
		assertEquals(-1, solution.firstUniqChar("loveleetcodevtcd"));
	}
	
	@Test
	public void test389() {
		assertEquals('e', solution.findTheDifference("abcd", "abcde"));
		assertEquals('e', solution.findTheDifference("abcd", "dceba"));
	}
	
	@Test
	public void test396() {
		assertEquals(26, solution.maxRotateFunction(new int[] {4,3,2,6}));
		// Wrong Answer
		assertEquals(0, solution.maxRotateFunction(new int[] {}));
		assertEquals(-2147483648, solution.maxRotateFunction(new int[] {-2147483648,-2147483648}));
		// Time Limit Exceeded
	}
	
	@Test
	public void test400() {
		assertEquals(8, solution.findNthDigit(8));
		assertEquals(0, solution.findNthDigit(11));
		assertEquals(2, solution.findNthDigit(6890));  // 9 + 180 + 2700 + 4001 => 2000
		assertEquals(0, solution.findNthDigit(6891));  // 9 + 180 + 2700 + 4002 => 2001
		assertEquals(9, solution.findNthDigit(10889)); // 9 + 180 + 2700 + 8000 => 2999
		// Wrong Answer
		assertEquals(3, solution.findNthDigit(3));
		assertEquals(1, solution.findNthDigit(12));
		assertEquals(1, solution.findNthDigit(1000000000));
	}
	
	@Test
	public void test401() {
		assertEquals(10, solution.readBinaryWatch(1).size());
		assertEquals("[0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]", Arrays.toString(solution.readBinaryWatch(1).toArray()));
		assertEquals(44, solution.readBinaryWatch(2).size());
		assertEquals("[0:03, 0:05, 0:09, 0:17, 0:33, 0:06, 0:10, 0:18, 0:34, 0:12, 0:20, 0:36, 0:24, 0:40, 0:48,"
				   + " 1:01, 1:02, 1:04, 1:08, 1:16, 1:32,"
				   + " 2:01, 2:02, 2:04, 2:08, 2:16, 2:32,"
				   + " 4:01, 4:02, 4:04, 4:08, 4:16, 4:32,"
				   + " 8:01, 8:02, 8:04, 8:08, 8:16, 8:32,"
				   + " 3:00, 5:00, 9:00, 6:00, 10:00]", 
				Arrays.toString(solution.readBinaryWatch(2).toArray()));
	}
	
	@Test
	public void test404() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		assertEquals(24, solution.sumOfLeftLeaves(root));
		
		root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		root.right.right.left = new TreeNode(9);
		assertEquals(33, solution.sumOfLeftLeaves(root));
		
		root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		root.right.right.right = new TreeNode(9);
		assertEquals(24, solution.sumOfLeftLeaves(root));
		
		// Wrong Answer
		root = new TreeNode(1);
		assertEquals(0, solution.sumOfLeftLeaves(root));
	}
	
	@Test
	public void test405() {
		assertEquals("1a", solution.toHex(26));
		assertEquals("305", solution.toHex(773));
		assertEquals("315", solution.toHex(789));
		assertEquals("13482", solution.toHex(78978));
		assertEquals("ffffffff", solution.toHex(-1));
		assertEquals("fffffffe", solution.toHex(-2));
		// Wrong Answer
		assertEquals("0", solution.toHex(0));
	}
	
	@Test
	public void test409() {
		assertEquals(7, solution.longestPalindrome("abccccdd"));
		// Wrong Answer
		assertEquals(3, solution.longestPalindrome("ccc"));
		assertEquals(3, solution.longestPalindrome("ccca"));
		assertEquals(5, solution.longestPalindrome("cccaaa"));
	}
	
	@Test
	public void test412() {
		assertEquals("[1]", Arrays.toString(solution.fizzBuzz(1).toArray()));
		assertEquals("[1, 2]", Arrays.toString(solution.fizzBuzz(2).toArray()));
		assertEquals("[1, 2, Fizz]", Arrays.toString(solution.fizzBuzz(3).toArray()));
		assertEquals("[1, 2, Fizz, 4, Buzz]", Arrays.toString(solution.fizzBuzz(5).toArray()));
		assertEquals("[1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]", Arrays.toString(solution.fizzBuzz(15).toArray()));
	}
	
	@Test
	public void test413() {
		assertEquals(3, solution.numberOfArithmeticSlices(new int[]{1,2,3,4}));
		assertEquals(6, solution.numberOfArithmeticSlices(new int[]{1,3,5,7,9}));
		assertEquals(3, solution.numberOfArithmeticSlices(new int[]{7,7,7,7}));
		assertEquals(3, solution.numberOfArithmeticSlices(new int[]{3,-1,-5,-9}));
		// Wrong Answer: [1,2,3,4,5,6]
		// Misunderstanding: 1,3,5 is not!!!!
		assertEquals(10, solution.numberOfArithmeticSlices(new int[]{1,2,3,4,5,6}));
		// Wrong Answer: [2,1,3,4,2,3] 
		assertEquals(0, solution.numberOfArithmeticSlices(new int[]{2,1,3,4,2,3}));
		// Wrong Answer: [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
		assertEquals(120, solution.numberOfArithmeticSlices(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}));
	}
	
	@Test
	public void test414() {
		assertEquals(1, solution.thirdMax(new int[] {3, 2, 1}));
		assertEquals(2, solution.thirdMax(new int[] {1, 2}));
		assertEquals(1, solution.thirdMax(new int[] {2, 2, 3, 1}));
		assertEquals(2, solution.thirdMax(new int[] {2, 2, 2, 1}));
		assertEquals(2, solution.thirdMax(new int[] {2, 2, 2, 2}));
		assertEquals(Integer.MIN_VALUE, solution.thirdMax(new int[] {Integer.MAX_VALUE, 1, Integer.MIN_VALUE}));
		assertEquals(Integer.MAX_VALUE, solution.thirdMax(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
		assertEquals(Integer.MAX_VALUE, solution.thirdMax(new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}));
	}
	
	@Test
	public void test415() {
		assertEquals("3", solution.addStrings("1", "2"));
		assertEquals("30", solution.addStrings("1", "29"));
		assertEquals("100", solution.addStrings("91", "9"));
		assertEquals("10000000000", solution.addStrings("9999999999", "1"));
	}
	
	@Test
	public void test419() {
		char[][] board = new char[][]{{'X','.','.','X'}, {'.','.','.','X'}, {'.','.','.','X'}};
		assertEquals(2, solution.countBattleships(board));
		board = new char[][]{{'X','.','.','X'}, {'X','.','.','X'}, {'.','.','.','X'}};
		assertEquals(2, solution.countBattleships(board));
		board = new char[][]{{'X','.','.','.','X'}, {'.','X','.','X','.'}, {'.','X','.','.','X'},{'X','.','.','.','X'}};
		assertEquals(6, solution.countBattleships(board));
	}
	
	@Test
	public void test437() {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.left.left = new TreeNode(3);
		root.left.left.right = new TreeNode(-2);
		root.left.right = new TreeNode(2);
		root.left.right.right = new TreeNode(1);
		root.right = new TreeNode(-3);
		root.right.right = new TreeNode(11);
		assertEquals(3, solution.pathSum(root, 8));
		
		root.right.right.left = new TreeNode(0);
		assertEquals(4, solution.pathSum(root, 8));
		
		root.right.right.right = new TreeNode(0);
		assertEquals(5, solution.pathSum(root, 8));
		
		assertEquals(0, solution.pathSum(null, 8));
	}
	
	@Test
	public void test438() {
		assertEquals("[0, 6]", Arrays.toString(solution.findAnagrams("cbaebabacd", "abc").toArray()));
		assertEquals("[0, 1, 2]", Arrays.toString(solution.findAnagrams("abab", "ab").toArray()));
		assertEquals("[1, 7]", Arrays.toString(solution.findAnagrams("ecbaebabacd", "abc").toArray()));
		// Time Limit Exceeded
	}
	
	@Test
	public void test441() {
		assertEquals(0, solution.arrangeCoins(0));
		assertEquals(1, solution.arrangeCoins(2));
		assertEquals(2, solution.arrangeCoins(5));
		assertEquals(3, solution.arrangeCoins(8));
	}
	
	@Test
	public void test447() {
		assertEquals(2, solution.numberOfBoomerangs(new int[][] {{0,0}, {1,0}, {2,0}}));
		// Wrong Answer
		assertEquals(2, solution.numberOfBoomerangs(new int[][] {{1,1},{2,2},{3,3}}));
		assertEquals(20, solution.numberOfBoomerangs(new int[][] {{0,0},{1,0},{-1,0},{0,1},{0,-1}}));
		// Time Limit Exceeded
	}
	
	@Test
	public void test448() {
		List<Integer> list = solution.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1});
		assertEquals("[5, 6]", Arrays.toString(list.toArray()));
		list = solution.findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1,7});
		assertEquals("[5, 6, 9]", Arrays.toString(list.toArray()));
	}
	
	@Test
	public void test453() {
		assertEquals(1, solution.minMoves(new int[] {1,2}));
		assertEquals(3, solution.minMoves(new int[] {1,2,3}));
		assertEquals(4, solution.minMoves(new int[] {1,2,4}));
		assertEquals(6, solution.minMoves(new int[] {1,2,3,4}));
		assertEquals(10, solution.minMoves(new int[] {1,2,3,4,5}));
		assertEquals(11, solution.minMoves(new int[] {1,2,3,4,6}));
		assertEquals(15, solution.minMoves(new int[] {1,2,3,4,5,6}));
		// Wrong Answer
		assertEquals(0, solution.minMoves(new int[] {0}));
		assertEquals(0, solution.minMoves(new int[] {0,0}));
		// Time Limit Exceeded
		assertEquals(2147483646, solution.minMoves(new int[] {1,2147483647}));
	}
	
	@Test
	public void test455() {
		assertEquals(1, solution.findContentChildren(new int[] {1,2,3}, new int[] {1,1}));
		assertEquals(1, solution.findContentChildren(new int[] {3,2,1}, new int[] {1,1}));
		assertEquals(2, solution.findContentChildren(new int[] {1,2}, new int[] {1,2,3}));
		assertEquals(2, solution.findContentChildren(new int[] {2,1}, new int[] {2,3,1}));
		assertEquals(0, solution.findContentChildren(new int[] {1,2,3}, new int[] {}));
		assertEquals(0, solution.findContentChildren(new int[] {}, new int[] {1,1}));
		assertEquals(3, solution.findContentChildren(new int[] {1,2,3}, new int[] {1,3,3}));
		// Wrong Answer
		assertEquals(12, solution.findContentChildren(new int[] {250,490,328,149,495,325,314,360,333,418,430,458},
				new int[] {376,71,228,110,215,410,363,135,508,268,494,288,24,362,20,5,247,118,152,393,458,354,201,188,425,167,220,114,148,43,403,385,512,459,71,425,142,102,361,102,232,203,25,461,298,437,252,364,171,240,233,257,305,346,307,408,163,216,243,261,137,319,33,91,116,390,139,283,174,409,191,338,123,231,101,458,497,306,400,513,175,454,273,88,169,250,196,109,505,413,371,448,12,193,396,321,466,526,276,276,198,260,131,322,65,381,204,32,83,431,81,108,366,188,443,331,102,72,496,521,502,165,439,161,257,324,348,176,272,341,230,323,124,13,51,241,186,329,70,387,93,126,159,370,292,16,211,327,431,26,70,239,379,368,215,501,382,299,481,163,100,488,259,524,481,87,118,112,110,425,295,352,62,162,19,404,301,163,389,13,383,43,397,165,385,274,59,499,136,309,301,345,381,124,394,492,96,243,4,297,153,9,210,291,33,450,202,313,138,214,308,239,129,154,354,289,484,388,351,339,337,161,97,185,190,498,348,242,38,217,343,170,269,465,514,89,366,447,166,52,33,436,268,3,74,505,403,302,513,69,439,68,72,403,33,130,466,417,186,339,328,237,138,427,392,496,430,442,260,229,372,217,399,203,170,246,153,137,358,138,22,19,110,304,399,458,165,372,254,358,364,345,52,150,121,226,156,231,83,377,237,342,184,27,73,392,238,366,258,434,498,184,309,394,110,246,430,437,33,488,520,69,24,18,221,146,19,147,283,407,437,185,399,238,471,117,110,266,507,263,293,94,314,31,217,224,36,515,147,432,270,327,521,113,153,14,160,435,396,501,13,461,103,441,461,68,55,510,380,291,305,365,511,218,515,148,324,136,291,519,201,192,97,183,448,294,242,379,52,154,224,183,344,452,240,380,338,337,437,92,206,490,405,396,274,41,305,170,423,437,92,480,477,260,224,176,239,466,525,458,226,189,251,516,479,305,463,116,126,88,490,93,389,246,480,139,193,303,205,270,83,89,461,492,209,311,368,457,478,188,484,4,501,513,18,2,90,39,205,500,391,191,229,32,147,438,123,493,71,363,143,163,110,199,305,476,430,86,378,416,444,325,207,519,380,81,116,503,13,211,290,327,510,141,37,242,370,117,208,58,336,432,19,474,488,74,472,63,287,11,470,221,349,211,191,497,50,442,315,376,355,302,206,291,376,499,405,498,202,40,115,178,66,438,446,498,443,292,123,493,505,205,490,368,349,341,107,290,428,141,271,117,54,410,172,92,450,524,427,371,69,77,35,234,25,152,365,509,154,61,143,111,188,101,327,21,378,186,57,241,351,136,213,143,86,325,83,358,79,427,406,491,192,248,360,428,478,385,252,270,106,524,343,92,483,9,15,54,511,296,238,392,106,198,64,394,122,187,14,481,50,221,226,63,50,449,504,357,499,120,448,275,363,465,451,68,25,233,124,520,415,90,302,246,19,63,335,308,235,297,410,349,78,324,210,327,199,202,455,387,159,148,344,375,127,368,305,347,307,451,412,323,188,16,139,143,362,228,493,334,341,406,113,368,234,439,193,211,500,231,311,204,99,82,52,66,286,142,27,445,12,410,370,118,104,358,330,96,351,93,469,63,450,14,455,309,84,101,58,166,224,34,158,322,388,345,328,329,509,168,292,367,5,309,477,75,306,524,416,35,417,229,448,513,99,179,526,147,390,260,459,394,503,414,221,429,469,160,415,417,435,139,277,195,340,526,7,369,177,324,132,505,36,239,354,414,144,221,378,441,13,93,70,104,449,387,288,492,329,257,489,501,308,376,289,421,320,226,407,294,463,209,322,34,72,310,2,293,11,196,411,136,455,106,432,193,475,518,243,306,410,14,273,145,492,290,33,345,108,75,271,115,517,456,326,108,319,470,40,429,408,380,271,423,475,100,402,408,379,428,512,340,8,172,43,383,72,422,35,57,281,185,304,442,224,376,163,478,210,146,266,139,309,263,210}));
	}
	
	@Test
	public void test459() {
		assertEquals(true,  solution.repeatedSubstringPattern("abab"));
		assertEquals(false, solution.repeatedSubstringPattern("aba"));
		assertEquals(true,  solution.repeatedSubstringPattern("abcabcabcabc"));
		assertEquals(true,  solution.repeatedSubstringPattern("aaaaa"));
		assertEquals(true,  solution.repeatedSubstringPattern("abcabcabc"));
	}
	
	@Test
	public void test461() {
		assertEquals(2, solution.hammingDistance(1, 4));
		assertEquals(5, solution.hammingDistance(27, 4));
		assertEquals(4, solution.hammingDistance(27, 12));
	}
	
	@Test
	public void test463() {
		assertEquals(16, solution.islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}));
		assertEquals(16, solution.islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,1,0},{1,1,0,0}}));
		assertEquals(20, solution.islandPerimeter(new int[][]{{0,1,0,0,0},{1,1,1,1,1},{0,1,1,0,0},{1,1,0,0,0}}));
	}
}
