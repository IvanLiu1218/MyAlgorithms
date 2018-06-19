package com.ivanliu.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import com.ivanliu.leetcode.Utility.ListNode;
import com.ivanliu.leetcode.Utility.TreeLinkNode;
import com.ivanliu.leetcode.Utility.TreeNode;

public class Solution {
	
	/**
	 *  [Easy]
	 *  #001. Two Sum
	 *  
	 *  Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	 *  You may assume that each input would have exactly one solution.
	 *  
	 *  Example:
	 *  Given nums = [2, 7, 11, 15], target = 9,
	 *  Because nums[0] + nums[1] = 2 + 7 = 9,
	 *  return [0, 1].
	 */
	public int[] twoSum(int[] nums, int target) {  // Accepted
		return this.twoSum_solution3(nums, target);
		//return this.twoSum_solution2(nums, target);
    }
	// One-pass Hash Table
	// Time complexity:  O(n)
	// Space complexity: O(n)
	private int[] twoSum_solution3(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
        	if (!map.containsKey(nums[i])) {
        		map.put(nums[i], i);
        	}
        	int leftValue = target - nums[i];
        	if (map.containsKey(leftValue) && map.get(leftValue) != i) {
        		return new int[] { map.get(leftValue), i };
        	}
        }
        return null;
	}
	// Two-pass Hash Table
	// Time complexity:  O(2n)
	// Space complexity: O(n)
	private int[] twoSum_solution2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], i);
			}
		}
		for (int i = 0; i < nums.length; ++i) {
			int leftValue = target - nums[i];
			if (map.containsKey(leftValue) && map.get(leftValue) != i) {
				return new int[] { i, map.get(leftValue) };
			}
		}
		return null;
	}
	// Time complexity:  O(n^2)
	// Space complexity: O(1)
	private int[] twoSum_solution1(int[] nums, int target) {  // Time Limit Exceeded
		for (int i = 0; i < nums.length; ++i) {
			int leftTarget = target - nums[i];
			for (int j = i + 1; j < nums.length; ++j) {
				if (nums[j] == leftTarget) {
					return new int[] { i, j };
				}
			}
		}
		return null;
    }
	
	/**
	 *  [Medium]
	 *  #002. Add Two Numbers
	 *  
	 *  You are given two linked lists representing two non-negative numbers. 
	 *  The digits are stored in reverse order and each of their nodes contain a single digit. 
	 *  Add the two numbers and return it as a linked list.
	 *  
	 *  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 *  Output: 7 -> 0 -> 8
	 */
	// Time complexity : O(max(m,n))
	// Space complexity: O(max(m,n)). The length of the new list is at most max(m,n) + 1
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode n1 = l1;
		ListNode n2 = l2;
		int carry = 0;
		ListNode result = new ListNode(carry);
		ListNode node = result;
		while (n1 != null || n2 != null) {
			int val1 = n1 != null ? n1.val : 0;
			int val2 = n2 != null ? n2.val : 0;
			int total = val1 + val2 + node.val;
			node.val = total % 10;
			carry = total / 10;
			
			if (n1 != null) n1 = n1.next;
			if (n2 != null) n2 = n2.next;
			if (n1 != null || n2 != null || carry != 0) {
				node.next = new ListNode(carry);
				node = node.next;
			}
		}
        return result;
    }
	
	/**
	 *  [Medium]
	 *  #003. Longest Substring Without Repeating Characters
	 *  
	 *  Given a string, find the length of the longest substring without repeating characters.
	 *  
	 *  Examples:
	 *  Given "abcabcbb", the answer is "abc", which the length is 3.
	 *  Given "bbbbb", the answer is "b", with the length of 1.
	 *  Given "pwwkew", the answer is "wke", with the length of 3. 
	 *   - Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 */
	public int lengthOfLongestSubstring(String s) {
		char[] cArray = s.toCharArray();
		int maxLength = 0;
		int startIndex = 0;
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < cArray.length; ++i) {
			if (map.containsKey(cArray[i])) {
				int length = i - startIndex;
				if (length > maxLength) {
					maxLength = length;
				}
				int index = map.get(cArray[i]) + 1;
				if (index > startIndex) {
					startIndex = index;
				}
			}
			map.put(cArray[i], i);
		}
		int length = cArray.length - startIndex;
		if (length > maxLength) maxLength = length;
		
        return maxLength;
		//return this.lengthOfLongestSubstring_answer1(s);
		//return this.lengthOfLongestSubstring_answer2(s);
		//return this.lengthOfLongestSubstring_answer3(s);
    }
	/*
	 *  Java (Assuming ASCII 128)
	 *  
	 *  The previous implements all have no assumption on the charset of the string s.
	 *  If we know that the charset is rather small, we can replace the Map with an integer array as direct access table.
	 *  
	 *  Commonly used tables are:
	 *  int[26] for Letters 'a' - 'z' or 'A' - 'Z'
	 *  int[128] for ASCII
	 *  int[256] for Extended ASCII
	 */
	public int lengthOfLongestSubstring_answer3(String s) {
		int length = s.length();
		int[] index = new int[128];  // default value is 0
		int maxLength = 0;
		int i = 0;
		int j = 0;
		while (i < length && j < length) {
			char c = s.charAt(j);
			i = Math.max(index[c], i);
			maxLength = Math.max(maxLength, j - i + 1);
			index[c] = ++j; // record the next index of current char
		}
		return maxLength;
	}
	/*
	 *  Sliding Window Optimized [Accepted]
	 *  Time complexity: O(n). Index jj will iterate nn times.
	 *  Space complexity (HashMap): O(min(m,n)). Same as the previous approach.
	 *  Space complexity (Table): O(m). m is the size of the charset.
	 */
	public int lengthOfLongestSubstring_answer2(String s) {
		int length = s.length();
		int maxLength = 0;
		Map<Character, Integer> map = new HashMap<>();
		int i = 0;
		int j = 0;
		while (i < length && j < length) {
			char c = s.charAt(j);
			if (map.containsKey(c)) {
				i = Math.max(map.get(c) + 1, i);
			}
			maxLength = Math.max(maxLength, j - i + 1);
			map.put(c, j);
			++j;
		}
		return maxLength;
	}
	/*
	 *  Sliding Window [Accepted]
	 *  Time complexity :  O(2n) = O(n). In the worst case each character will be visited twice by i and j.
	 *  Space complexity : O(min(m,n)). Same as the previous approach. We need O(k) space for the sliding window, 
	 *                     where k is the size of the Set. The size of the Set is upper bounded by the size of the string n 
	 *                     and the size of the charset/alphabet m.
	 */
	public int lengthOfLongestSubstring_answer1(String s) {
		int length = s.length();
		int maxLength = 0;
		int i = 0;
		int j = 0;
		Set<Character> set = new HashSet<>();
		while (i < length && j < length) {
			char c = s.charAt(j);
			if (!set.contains(c)) {
				set.add(c);
				++j;
				maxLength = Math.max(maxLength, j - i);
			} else {
				set.remove(s.charAt(i));
				++i;
			}
		}
		return maxLength;
	}
	
	/**
	 *  [Hard]
	 *  #004. Median of Two Sorted Arrays
	 *  
	 *  There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 *  Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
	 *  
	 *  Example 1:
	 *  nums1 = [1, 3]
	 *  nums2 = [2]
	 *  The median is 2.0
	 *  
	 *  Example 2:
	 *  nums1 = [1, 2]
	 *  nums2 = [3, 4]
	 *  The median is (2 + 3)/2 = 2.5
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;
		int mid = totalLength / 2;
		int[] allNums = new int[totalLength];
		System.arraycopy(nums1, 0, allNums, 0, nums1.length);
		System.arraycopy(nums2, 0, allNums, nums1.length, nums2.length);

		int from = 0, to = totalLength - 1;
		int index = findMedianSortedArrays_getFlagValueIndex(allNums, from, to);
		while (index != mid) {
			if (index < mid) {
				index = findMedianSortedArrays_getFlagValueIndex(allNums, index + 1, to);
			} else {
				index = findMedianSortedArrays_getFlagValueIndex(allNums, from, index - 1);
			}
		}
		if (totalLength % 2 == 1) return allNums[mid];

		index = findMedianSortedArrays_getFlagValueIndex(allNums, from, to);
		while (index != mid - 1) {
			if (index < mid - 1) {
				index = findMedianSortedArrays_getFlagValueIndex(allNums, index + 1, to);
			} else {
				index = findMedianSortedArrays_getFlagValueIndex(allNums, from, index - 1);
			}
		}
		return (allNums[mid - 1] + allNums[mid]) / 2.0;
	}

	private int findMedianSortedArrays_getFlagValueIndex(int[] nums, int from, int to) {
		int flag = nums[from];
		int i = from, j = to;
		while (i < j) {
			while (i < j && nums[j] >= flag) --j;
			nums[i] = nums[j];
			while (i < j && nums[i] <= flag) ++i;
			nums[j] = nums[i];
		}
		nums[i] = flag;
		return i;
	}
	
	/**
	 *  [Medium]
	 *  #005. Longest Palindromic Substring
	 *  
	 *  Given a string S, find the longest palindromic substring in S. 
	 *  You may assume that the maximum length of S is 1000, 
	 *  and there exists one unique longest palindromic substring.
	 */
	public String longestPalindrome005(String s) {
		int maxLength = 1;
		int startIndex = 0;
		for (int k = 1; k < s.length(); ++k) {
			int i = k - 1;
			while (0 <= i && s.charAt(i) == s.charAt(k)) --i;
			int j = k + 1;
			while (j < s.length() && s.charAt(j) == s.charAt(k)) ++j;
			while (0 <= i && j < s.length()) {
				if (s.charAt(i) == s.charAt(j)) {
					--i;
					++j;
				}
				else {
					if (j - i - 1 > maxLength) {
						maxLength = j - i - 1;
						startIndex = i + 1;
					}
					break;
				}
			}
			if (i < 0 || j >= s.length()) {
				if (j - i - 1 > maxLength) {
					maxLength = j - i - 1;
					startIndex = i + 1;
				}
			}
		}
		return s.substring(startIndex, startIndex + maxLength);
	}
	
	/**
	 *  [Easy]
	 *  #006. ZigZag Conversion
	 *  
	 *  The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
	 *  (you may want to display this pattern in a fixed font for better legibility)
	 *  P   A   H   N
	 *  A P L S I I G
	 *  Y   I   R
	 *  
	 *  And then read line by line: "PAHNAPLSIIGYIR"
	 *  Write the code that will take a string and make this conversion given a number of rows:
	 *  string convert(string text, int nRows);
	 *  convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 */
	public String convert(String s, int numRows) {
		if (numRows == 1) return s;
		StringBuilder sb = new StringBuilder();
		int delta = numRows + numRows - 2;
		int r = 0;
		while (r < numRows) {
			int l = r;
			boolean flag = false;
			while (l < s.length()) {
				sb.append(s.charAt(l));
				if (r % (numRows - 1) == 0) {  // the first and last lines
					l += delta;
				} else if (flag) {
					l += r * 2;
					flag = false;
				} else {
					l += delta - r * 2;
					flag = true;
				}
			}
			++r;
		}
        return sb.toString();
    }
	
	/**
	 *  [Easy]
	 *  #007. Reverse Integer
	 *  
	 *  Reverse digits of an integer.
	 *  
	 *  Example1: x = 123, return 321
	 *  Example2: x = -123, return -321
	 *  
	 *  Have you thought about this?
	 *  Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
	 *  
	 *  If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
	 *  
	 *  Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
	 *  then the reverse of 1000000003 overflows. How should you handle such cases?
	 *  For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
	 *  
	 *  Update (2014-11-10):
	 *  Test cases had been added to test the overflow behavior.
	 */
	public int reverse(int x) {
		boolean isNegative = false;
		int num = x;
		if (num < 0) {
			num *= -1;
			isNegative = true;
		}
		long result = 0;  // define result as long type to check if overflow
		while (num > 0) {
			result *= 10;
			int left = num % 10;
			result += left;
			if (result > Integer.MAX_VALUE) return 0;  // if overflow
			num = num / 10;
		}
		if (isNegative) {
			result *= -1;
		}
		return (int) result;
    }
	
	/**
	 *  [Easy]
	 *  #008. String to Integer (atoi)
	 *  
	 *  Implement atoi to convert a string to an integer.
	 *  
	 *  Hint: Carefully consider all possible input cases. If you want a challenge, 
	 *  please do not see below and ask yourself what are the possible input cases.
	 *  
	 *  Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
	 *  You are responsible to gather all the input requirements up front.
	 *  
	 *  Update (2015-02-10):
	 *  The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, 
	 *  please click the reload button  to reset your code definition.
	 *  
	 *  spoilers alert... click to show requirements for atoi.
	 *  
	 *  Requirements for atoi:
	 *  The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
	 *  Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
	 *  and interprets them as a numerical value.
	 *  
	 *  The string can contain additional characters after those that form the integral number, 
	 *  which are ignored and have no effect on the behavior of this function.
	 *  
	 *  If the first sequence of non-whitespace characters in str is not a valid integral number, 
	 *  or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
	 *  
	 *  If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, 
	 *  INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
	 */
	public int myAtoi(String str) {
		if (str == null) return 0;
		str = str.trim();
		if (str.length() == 0) return 0;
		long result = 0;
		boolean isNegative = false;
		int i = 0;
		char c = str.charAt(i);
		if (c == '-' || c == '+') {
			isNegative = (c == '-');
			i++;
		}
		while (i < str.length()) {
			c = str.charAt(i++);
			if (c < '0' || c > '9') break;
			result = result * 10 + (c - '0');
			if (!isNegative && result >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
	        if (isNegative && (-1) * result <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
		}
		if (isNegative) {
			result *= -1;
		}
        return (int)result;
    }
	
	/**
	 *  [Easy]
	 *  #009. Palindrome Number
	 *  
	 *  Determine whether an integer is a palindrome. Do this without extra space.
	 *  
	 *  Some hints:
	 *  Could negative integers be palindromes? (ie, -1)
	 *  
	 *  If you are thinking of converting the integer to string, note the restriction of using extra space.
	 *  
	 *  You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
	 *  you know that the reversed integer might overflow. How would you handle such case?
	 *  
	 *  There is a more generic way of solving this problem.
	 */
	public boolean isPalindrome(int x) {
		if (x < 0) return false;
		if (x == 0) return true;
		int length = isPalindrome_getLengthOfInt(x);
		int factor = isPalindrome_getFactor(length);
		while (x > 9) {  // 12321 -> 232 -> 3
			             // 1221 -> 22 -> 0
			int a = x / factor;
			int b = x % 10;
			if (a != b) return false;
			x = (x - a * factor - b) / 10;
			factor = factor / 100;
		}
		if (x == 0) return true;
		if (factor != 1 && factor != 0) return false;
		return true;
    }
	private int isPalindrome_getLengthOfInt(int x) {
		if (x == 0) return 1;
		int length = 0;
		int num = x;
		while (num > 0) {
			length++;
			num = num / 10;
		}
		return length;
	}
	private int isPalindrome_getFactor(int length) {
		int factor = 1;
		for (int i = 0; i < length - 1; ++i) {
			factor *= 10;
		}
		return factor;
	}

	/**
	 *  [Medium]
	 *  #011 Container With Most Water
	 *  Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
	 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
	 *  which together with x-axis forms a container, such that the container contains the most water.
	 *  Note: You may not slant the container and n is at least 2.
	 */
	public int maxArea(int[] height) {
		int maxArea = Integer.MIN_VALUE;
		int i = 0;
		int j = height.length - 1;
		int area = 0;
		while (i < j) {
			area = Math.min(height[i], height[j]) * (j - i);
			if (area > maxArea) maxArea = area;
			if (height[i] < height[j]) ++i;
			else --j;
		}
		return maxArea;
	}
	
	/**
	 *  [Easy]
	 *  #012. Integer to Roman
	 *  
	 *  Given an integer, convert it to a roman numeral.
	 *  Input is guaranteed to be within the range from 1 to 3999.
	 *  Subscribe to see which companies asked this question
	 */
	public String intToRoman(int num) {
		Map<Integer, String> romanMap = new HashMap<Integer, String>();
		romanMap.put(1000, "M");
		romanMap.put(500, "D");
		romanMap.put(100, "C");
		romanMap.put(50, "L");
		romanMap.put(10, "X");
		romanMap.put(5, "V");
		romanMap.put(1, "I");
		StringBuilder sb = new StringBuilder();
		int n = num;
		int factor = 1000;
		while (n > 0) {
			int left = n / factor;
			if (left > 0) {
				sb.append(intToRoman_digitToRoman(left, factor, romanMap));
			}
			n = n - left * factor;
			factor = factor / 10;
		}
		return sb.toString();
    }
	private String intToRoman_digitToRoman(int digit, int factor, Map<Integer, String> map) {
		StringBuilder sb = new StringBuilder();
		if (digit == 9) {
			sb.append(map.get(factor));
			sb.append(map.get(factor * 10));
			return sb.toString();
		}
		else if (digit == 5) {
			return map.get(digit * factor);
		}
		else if (digit == 4) {
			sb.append(map.get(factor));
			sb.append(map.get(5 * factor));
			return sb.toString();
		}
		else if (digit < 4) {  // 1,2,3
			String sig = map.get(factor);
			for (int i = 0; i < digit; ++i) {
				
				sb.append(sig);
			}
			return sb.toString();
		}
		else { // digit 6,7,8
			sb.append(map.get(5 * factor));
			for (int i = 5; i < digit; ++i) {
				sb.append(map.get(factor));
			}
			return sb.toString();
		}
	}
	
	/**
	 *  [Easy]
	 *  #013. Roman to Integer
	 *  
	 *  Given a roman numeral, convert it to an integer.
	 *  
	 *  Input is guaranteed to be within the range from 1 to 3999.
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0) return 0;
		Map<Character, Integer> romanMap = new HashMap<Character, Integer>();
		romanMap.put('M', 1000);
		romanMap.put('D', 500);
		romanMap.put('C', 100);
		romanMap.put('L', 50);
		romanMap.put('X', 10);
		romanMap.put('V', 5);
		romanMap.put('I', 1);
		int result = 0;
		int prev = -1;
		for (int i = s.length() - 1; i >= 0; --i) {
			int val = romanMap.get(s.charAt(i));
			if (val < prev) {
				val *= -1;
			}
			result += val;
			prev = val;
		}
		return result;
    }
	
	/**
	 *  [Easy]
	 *  #014. Longest Common Prefix
	 *  
	 *  Write a function to find the longest common prefix string amongst an array of strings.
	 */
	public String longestCommonPrefix(String[] strs) {
		return longestCommonPrefix_solution1(strs);  // more efficient
		//return longestCommonPrefix_solution2(strs);
	}
	public String longestCommonPrefix_solution2(String[] strs) {
		if (strs.length == 0) return "";
		int minLength = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < strs.length; ++i) {
			if (strs[i].length() < minLength) {
				minLength = strs[i].length();
				index = i;
			}
		}
		String prefix = strs[index]; 
		int i = 0; // index of prefix
		while (i < prefix.length()) {
			char c1 = prefix.charAt(i);
			int j = 0; // index of String array
			while (j < strs.length) {
				char c2 = strs[j].charAt(i);
				if (c1 != c2) break;
				++j;
			}
			if (j >= strs.length) ++i;
			else break;
		}
		return prefix.substring(0, i);
	}
	
	public String longestCommonPrefix_solution1(String[] strs) {
		if (strs.length == 0) return "";
		int minLength = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < strs.length; ++i) {
			if (strs[i].length() < minLength) {
				minLength = strs[i].length();
				index = i;
			}
		}
		String prefix = strs[index];
		while (prefix.length() != 0) {
			int j = 0;
			for (;j < strs.length; ++j) {
				if (!strs[j].startsWith(prefix)) {
					break;
				}
			}
			if (j >= strs.length) return prefix;
			prefix = prefix.substring(0,  prefix.length() - 1);
		}
        return prefix;
    }
	
	/**
	 *  [Easy]
	 *  #019. Remove Nth Node From End of List
	 *  
	 *  Given a linked list, remove the nth node from the end of list and return its head.
	 *  
	 *  For example,
	 *  Given linked list: 1->2->3->4->5, and n = 2.
	 *  After removing the second node from the end, the linked list becomes 1->2->3->5.
	 *  
	 *  Note:
	 *  Given n will always be valid.
	 *  Try to do this in one pass.
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		Deque<ListNode> queue = new ArrayDeque<>(n + 1);
		ListNode node = head;
		while (node != null) {
			queue.addLast(node);
			if (queue.size() > n + 1) {
				queue.removeFirst();
			}
			node = node.next;
		}
		if (queue.size() < n + 1) {
			node = queue.removeFirst();
			return node.next;
		} else {
			node = queue.removeFirst();
			ListNode toRemove = queue.removeFirst();
			node.next = toRemove.next;
		}
        return head;
    }
	
	/**
	 *  [Easy]
	 *  #020. Valid Parentheses
	 * 
	 *  Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	 *  The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
	 */
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		try {
			for (int i = 0; i < s.length(); ++i) {
				char c = s.charAt(i);
				if (c == '(' || c == '{' || c == '[') {
					stack.push(c);
				} else if (c == ')' || c == '}' || c == ']') {
					char top = stack.peek();
					if ( (top == '(' && c == ')')
							|| (top == '{' && c == '}')
							|| (top == '[' && c == ']')) {
						stack.pop();
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		if (stack.size() == 0) return true;
        return false;
    }
	
	/**
	 *  [Easy]
	 *  #021. Merge Two Sorted Lists
	 *  
	 *  Merge two sorted linked lists and return it as a new list. 
	 *  The new list should be made by splicing together the nodes of the first two lists.
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode thead = new ListNode(-1);
		ListNode node = thead;
		ListNode p1 = l1;
		ListNode p2 = l2;
		while (p1 != null && p2 != null) {
			if (p1.val <= p2.val) {
				node.next = p1;
				p1 = p1.next;
			}
			else {
				node.next = p2;
				p2 = p2.next;
			}
			node = node.next;
		}
		if (p1 != null) {
			node.next = p1;
		}
		if (p2 != null) {
			node.next = p2;
		}
		return thead.next;
    }
	
	/**
	 *  [Medium]
	 *  #022. Generate Parentheses
	 *  
	 *  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
	 *  
	 *  For example, given n = 3, a solution set is:
	 *  [
	 *    "((()))",
	 *    "(()())",
	 *    "(())()",
	 *    "()(())",
	 *    "()()()"
	 *  ]
	 */
	public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String str = "(";
        generateParenthesis(result, str, n - 1, n);
        return result;
    }
	
	public void generateParenthesis(List<String> result, String str, int numStart, int numEnd) {
		if (numStart == 0) {
			int n = numEnd;
			while (n != 0) {
				str += ")";
				--n;
			}
			result.add(str);
		} else {
			if (numStart < numEnd) {
				generateParenthesis(result, str + "(", numStart - 1, numEnd);
				generateParenthesis(result, str + ")", numStart, numEnd - 1);
			} else if (numStart >= numEnd) {
				generateParenthesis(result, str + "(", numStart - 1, numEnd);
			}
		}
	}
	
	/**
	 *  [Hard]
	 *  #023. Merge k Sorted Lists
	 *  
	 *  Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) return null;
		if (lists.length == 1) return lists[0];
		int mid = (lists.length - 1) / 2;
		ListNode n1 = mergeKLists_mergeKLists(lists, 0, mid);
		ListNode n2 = mergeKLists_mergeKLists(lists, mid + 1, lists.length - 1);
		return mergeKLists_mergeKLists(n1, n2);
    }
	public ListNode mergeKLists_mergeKLists(ListNode[] lists, int start, int end) {
		if (start == end) {
			return lists[start];
		}
		int mid = (start + end) / 2;
		ListNode n1 = mergeKLists_mergeKLists(lists, start, mid);
		ListNode n2 = mergeKLists_mergeKLists(lists, mid + 1, end);
		return mergeKLists_mergeKLists(n1, n2);
	}
	public ListNode mergeKLists_mergeKLists(ListNode h1, ListNode h2) {
		ListNode nhead = new ListNode(-1);
		ListNode pnode = nhead;
		ListNode n1 = h1;
		ListNode n2 = h2;
		while (n1 != null && n2 != null) {
			if (n1.val < n2.val) {
				pnode.next = n1;
				n1 = n1.next;
			}
			else {
				pnode.next = n2;
				n2 = n2.next;
			}
			pnode = pnode.next;
		}
		if (n1 != null) {
			pnode.next = n1;
		}
		if (n2 != null) {
			pnode.next = n2;
		}
		return nhead.next;
	}
	
	/**
	 *  [Easy]
	 *  #024. Swap Nodes in Pairs
	 *  
	 *  Given a linked list, swap every two adjacent nodes and return its head.
	 *  
	 *  For example,
	 *  Given 1->2->3->4, you should return the list as 2->1->4->3.
	 *  
	 *  Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode thead = new ListNode(-1);
		ListNode prev = thead;
		ListNode node = head;
		while (node != null && node.next != null) {
			ListNode next = node.next;
			node.next = next.next;
			next.next = node;
			prev.next = next;
			
			prev = node;
			node = node.next;
		}
		if (node != null) {
			prev.next = node;
		}
		return thead.next;
    }
	
	/**
	 *  [Easy]
	 *  #026. Remove Duplicates from Sorted Array
	 *  
	 *  Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
	 *  
	 *  Do not allocate extra space for another array, you must do this in place with constant memory.
	 *  
	 *  For example,
	 *  Given input array nums = [1,1,2],
	 *  
	 *  Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
	 *  It doesn't matter what you leave beyond the new length.
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null) return -1;
		int length = nums.length;
		int dupi = -1;
		int prev = 0;
		int curr = 1;
		while (0 < curr && curr < length) {
			if (dupi == -1) {
				if (nums[prev] == nums[curr]) {
					dupi = curr;
				}
				prev = curr;
				curr++;
			}
			else {
				if (nums[prev] != nums[curr]) {
					removeDuplicates_moveForward(nums, curr, dupi);
					length -= (curr - dupi);
					curr = dupi;
					prev = curr - 1;
					dupi = -1;
				}
				else {
					prev = curr;
					curr++;
				}
			}
		}
		if (dupi != -1 && curr >= length) {
			length = dupi;
		}
		return length;
    }
	/*
	 *   |_______|i|_________|j|______|
	 *           to <------- from
	 *   to < from
	 */
	private void removeDuplicates_moveForward(int[] array, int from, int to) {
		int i = to;
		int j = from;
		while (i < j && j < array.length) {
			array[i++] = array[j++];
		}
	}
	
	/**
	 *  [Easy]
	 *  #027. Remove Element
	 *  
	 *  Given an array and a value, remove all instances of that value in place and return the new length.
	 *  
	 *  Do not allocate extra space for another array, you must do this in place with constant memory.
	 *  
	 *  The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 *  
	 *  Example:
	 *  Given input array nums = [3,2,2,3], val = 3
	 *  
	 *  Your function should return length = 2, with the first two elements of nums being 2.
	 *  
	 *  Hint:
	 *  
	 *  1. Try two pointers.
	 *  2. Did you use the property of "the order of elements can be changed"?
	 *  3. What happens when the elements to remove are rare?
	 */
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0) return 0;
		int length = nums.length;
		int i = 0;
		int j = length - 1;
		while (i <= j && i < nums.length && 0 <= j) {
			while (0 <= j && nums[j] == val) { // find the 1st num which does not equal to the val from the tail of nums
				--length;
				--j;
			}
			if (i < j && nums[i] == val) { // swap the nums[i] with nums[j] if nums[i] equals to val
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				--j;
				--length;
			}
			++i;
		}
		return length;
    }
	public int removeElement_solution1(int[] nums, int val) {
		if (nums == null || nums.length == 0) return 0;
		int length = nums.length;
		int i = 0;
		while (i < length) {
			if (nums[i] == val) {
				for (int j = i; j < length - 1; ++j) {
					nums[j] = nums[j + 1];
				}
				--length;
			}
			else {
				++i;
			}
		}
		return length;
	}
	
	/**
	 *  [Easy]
	 *  #028. Implement strStr()
	 *  
	 *  Implement strStr().
	 *  Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
	 */
	public int strStr(String haystack, String needle) {
		if (haystack.equals(needle)) return 0;
		if (haystack.length() < needle.length()) return -1;
		for (int i = 0; i < haystack.length() - needle.length() + 1; ++i) { // to avoid Time Limit Exceeded
			char c = haystack.charAt(i);
			int j = 0;
			int k = i;
			while (j < needle.length()) {
				if (c != needle.charAt(j)) {
					break;
				}
				++j;
				if (++k < haystack.length()) {
					c = haystack.charAt(k);
				}
			}
			if (j >= needle.length()) {
				return i;
			}
		}
        return -1;
    }
	
	/**
	 *  [Hard]
	 *  #033. Search in Rotated Sorted Array
	 *  
	 *  Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 *  
	 *  (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 *  
	 *  You are given a target value to search. If found in the array return its index, otherwise return -1.
	 *  
	 *  You may assume no duplicate exists in the array.
	 */
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		int pivot = nums[0];
		if (target < pivot) {
			for (int i = nums.length - 1; i >= 0; --i) {
				if (nums[i] > pivot) break;
				if (nums[i] == target) return i;
			}
		}
		else {
			for (int i = 0; i < nums.length; ++i) {
				if (nums[i] < pivot) break;
				if (nums[i] == target) return i;
			}
		}
		return -1;
    }
	
	/**
	 *  [Easy]
	 *  #035. Search Insert Position
	 *  
	 *  Given a sorted array and a target value, return the index if the target is found. 
	 *  If not, return the index where it would be if it were inserted in order.
	 *  
	 *  You may assume no duplicates in the array.
	 *  
	 *  Here are few examples.
	 *  [1,3,5,6], 5 -> 2
	 *  [1,3,5,6], 2 -> 1
	 *  [1,3,5,6], 7 -> 4
	 *  [1,3,5,6], 0 -> 0
	 */
	public int searchInsert(int[] nums, int target) {
		int i = 0;
		for (; i < nums.length; ++i) {
			if (target <= nums[i]) {
				return i;
			}
		}
		if (i >= nums.length) {
			return i;
		}
		return -1;
    }
	
	/**
	 *  [Easy]
	 *  #036. Valid Sudoku
	 *  
	 *  Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	 *  The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
	 *  A partially filled sudoku which is valid.
	 *  Note:
	 *  A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
	 */
	public boolean isValidSudoku(char[][] board) {
        // Each row must have the numbers 1-9 occuring just once.
		for (int i = 0; i < 9; ++i) {
			HashSet<Character> set = new HashSet<>();
			for (int j = 0; j < 9; ++j) {
				if (board[i][j] == '.') continue;
				if (set.contains(board[i][j])) return false;
				set.add(board[i][j]);
			}
		}
		// Each column must have the numbers 1-9 occuring just once.
		for (int i = 0; i < 9; ++i) {
			HashSet<Character> set = new HashSet<>();
			for (int j = 0; j < 9; ++j) {
				if (board[j][i] == '.') continue;
				if (set.contains(board[j][i])) return false;
				set.add(board[j][i]);
			}
		}
		// And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
		for (int k = 0; k < 9; ++k) {
			HashSet<Character> set = new HashSet<>();
			for (int i = (k / 3) * 3; i < (k / 3 + 1) * 3; ++i) {
				for (int j = (k % 3) * 3; j < (k % 3 + 1) * 3; ++j) {
					if (board[i][j] == '.') continue;
					if (set.contains(board[i][j])) return false;
					set.add(board[i][j]);
				}
			}
		}
		return true;
    }
	
	/**
	 *  [Easy]
	 *  #038. Count and Say
	 *  
	 *  The count-and-say sequence is the sequence of integers beginning as follows:
	 *  1, 11, 21, 1211, 111221, ...
	 *  
	 *  1 is read off as "one 1" or 11.
	 *  11 is read off as "two 1s" or 21.
	 *  21 is read off as "one 2, then one 1" or 1211.
	 *  Given an integer n, generate the nth sequence.
	 *  
	 *  Note: The sequence of integers will be represented as a string.
	 */
	public String countAndSay(int n) {
		String prev = "1";
		List<String> countAndSayList = new ArrayList<>();
		countAndSayList.add(prev);
		while (countAndSayList.size() < n) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			char c = prev.charAt(i++);
			int count = 1;
			for (; i < prev.length(); ++i) {
				if (prev.charAt(i) == c) ++count;
				else {
					sb.append(Integer.toString(count));
					sb.append(c);
					c = prev.charAt(i);
					count = 1;
				}
			}
			sb.append(Integer.toString(count));
			sb.append(c);
			prev = sb.toString();
			countAndSayList.add(prev);
		}
        return countAndSayList.get(n - 1);
    }
	
	/**
	 *  [Medium]
	 *  #046. Permutations
	 *  
	 *  Given a collection of distinct numbers, return all possible permutations.
	 *  
	 *  For example,
	 *  [1,2,3] have the following permutations:
	 *  [
	 *    [1,2,3],
	 *    [1,3,2],
	 *    [2,1,3],
	 *    [2,3,1],
	 *    [3,1,2],
	 *    [3,2,1]
	 *  ]
	 */
	private List<List<Integer>> permute_llist = null;
	public List<List<Integer>> permute(int[] nums) {
		permute_llist = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) return permute_llist;
		
		List<Integer> range = new LinkedList<Integer>();
		for (int i = 0; i < nums.length; ++i) {
			range.add(nums[i]);
		}
		List<Integer> list = new ArrayList<Integer>();
		permute_getPermutation(range, list);
		
		return permute_llist;
    }
	private void permute_getPermutation(List<Integer> range, List<Integer> list) {
		if (range.size() == 0) {
			permute_llist.add(new ArrayList<Integer>(list));
			return;
		}
		for (int i = 0; i < range.size(); ++i) {
			int val = range.remove(i);
			list.add(val);
			permute_getPermutation(range, list);
			range.add(i, val);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 *  [Easy]
	 *  #058. Length of Last Word
	 *  
	 *  Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
	 *  
	 *  If the last word does not exist, return 0.
	 *  
	 *  Note: A word is defined as a character sequence consists of non-space characters only.
	 *  
	 *  For example, 
	 *  Given s = "Hello World",
	 *  return 5.
	 */
	public int lengthOfLastWord(String s) {
		if (s == null) return 0;
		int length = 0;
		for (int i = s.length() - 1; i >= 0; --i) {
			if (s.charAt(i) == ' ') {
				if (length == 0) continue;
				else return length;
			}
			else length++;
		}
		return length;
    }
	
	/**
	 *  [Medium]
	 *  #059. Spiral Matrix II
	 *  
	 *  Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
	 *  
	 *  For example,
	 *  Given n = 3,
	 *  
	 *  You should return the following matrix:
	 *  [
	 *   [ 1, 2, 3 ],
	 *   [ 8, 9, 4 ],
	 *   [ 7, 6, 5 ]
	 *  ]
	 */
	public int[][] generateMatrix(int n) {
		if (n < 0) return null;
		int[][] metrix = new int[n][n];
		int value = 1;
		int times = 0;
		int endTimes = (n + 1) / 2;
		while (times < endTimes) {
			int i = times;
			int j = times;
			while (j < n - times) {
				metrix[i][j] = value++;
				++j;
			}
			--j;
			++i;
			while (i < n - times) {
				metrix[i][j] = value++;
				++i;
			}
			--i;
			--j;
			while (times <= j) {
				metrix[i][j] = value++;
				--j;
			}
			++j;
			--i;
			while (times < i) {
				metrix[i][j] = value++;
				--i;
			}
			++times;
		}
		return metrix;
    }
	
	/**
	 *  [Medium]
	 *  #062. Unique Paths
	 *  
	 *  A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
	 *  
	 *  The robot can only move either down or right at any point in time. 
	 *  The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
	 *  
	 *  How many possible unique paths are there?
	 *  
	 *  Above is a 3 x 7 grid. How many possible unique paths are there?
	 *  
	 *  Note: m and n will be at most 100.
	 */
	public int uniquePaths(int m, int n) {
		if (m <= 0 || n <= 0) return -1;
		int[][] metrix = new int[m][n];
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				metrix[i][j] = uniquePaths_getMetrixValue(metrix, i, j);
			}
		}
		return metrix[m - 1][n - 1];
    }
	private int uniquePaths_getMetrixValue(int[][] metrix, int i , int j) {
		if (i == 0 && j == 0) {
			return 1;
		}
		int up = 0;
		int left = 0;
		if (i - 1 >= 0) {
			up = metrix[i - 1][j];
		}
		if (j - 1 >= 0) {
			left = metrix[i][j - 1];
		}
		return up + left;
	}
	
	/**
	 *  [Medium]
	 *  #064. Minimum Path Sum
	 *  
	 *  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
	 *  
	 *  Note: You can only move either down or right at any point in time.
	 */
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
		for (int i = grid.length - 1; i >= 0; --i) {
			for (int j = grid[0].length - 1; j >= 0; --j) {
				grid[i][j] += minPathSum_getMinDist(grid, i, j);
			}
		}
		return grid[0][0];
    }
	private int minPathSum_getMinDist(int[][] grid, int i, int j) {
		if (i == grid.length - 1 && j == grid[0].length - 1) {
			return 0;
		}
		int right = Integer.MAX_VALUE;
		int down = Integer.MAX_VALUE;
		if (i + 1 < grid.length) {
			down = grid[i + 1][j];
		}
		if (j + 1 < grid[0].length) {
			right = grid[i][j + 1];
		}
		return Math.min(right, down);
	}
	
	/**
	 *  [Easy]
	 *  #066. Plus One
	 *  
	 *  Given a non-negative number represented as an array of digits, plus one to the number.
	 *  
	 *  The digits are stored such that the most significant digit is at the head of the list.
	 */
	public int[] plusOne(int[] digits) {
		if (digits == null) return null;
		int next = 1;
		for (int i = digits.length - 1; i >= 0; --i) {
			int number = digits[i];
			if (number + next > 9) {
				digits[i] = 0;
				next = 1;
			}
			else {
				digits[i] = number + next;
				next = 0;
			}
		}
		if (next == 1) {
			int[] newDigits = new int[digits.length + 1];
			newDigits[0] = 1;
			System.arraycopy(digits, 0, newDigits, 1, digits.length);
			return newDigits;
		}
        return digits;
    }
	
	/**
	 *  [Easy]
	 *  #067. Add Binary
	 *  
	 *  Given two binary strings, return their sum (also a binary string).
	 *  
	 *  For example,
	 *  a = "11"
	 *  b = "1"
	 *  Return "100".
	 */
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1;
		int j = b.length() - 1;
		int carry = 0;
		while (0 <= i && 0 <= j) {
			char c1 = a.charAt(i--);
			char c2 = b.charAt(j--);
			int sum = c1 - 48 + c2 - 48 + carry;
			carry = sum / 2;
			sb.append(sum % 2);
		}
		while (0 <= i) {
			char c = a.charAt(i--);
			int sum = c - 48 + carry;
			carry = sum / 2;
			sb.append(sum % 2);
		}
		while (0 <= j) {
			char c = b.charAt(j--);
			int sum = c - 48 + carry;
			carry = sum / 2;
			sb.append(sum % 2);
		}
		if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }
	
	/**
	 *  [Easy]
	 *  #070. Climbing Stairs
	 *  
	 *  You are climbing a stair case. It takes n steps to reach to the top.
	 *  
	 *  Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
	 */
	private Map<Integer, Integer> climbStairs_resMap = new HashMap<>();
	public int climbStairs(int n) {
		if (climbStairs_resMap.containsKey(n)) {
			return climbStairs_resMap.get(n);
		}
		if (n == 1 || n == 2) {
			climbStairs_resMap.put(n, n);
			return n;
		}
		int value = climbStairs(n - 1) + climbStairs(n - 2);
		climbStairs_resMap.put(n, value);
		return value;
	}
	
	/**
	 *  [Medium]
	 *  #073. Set Matrix Zeroes
	 *  
	 *  Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 *  
	 *  Follow up:
	 *  Did you use extra space?
	 *  A straight forward solution using O(mn) space is probably a bad idea.
	 *  A simple improvement uses O(m + n) space, but still not the best solution.
	 *  Could you devise a constant space solution?
	 */
	public void setZeroes(int[][] matrix) {
		boolean isFirstRow = false;
		boolean isFirstColumn = false;
		for (int j = 0; j < matrix[0].length; ++j) {
			if (matrix[0][j] == 0) {
				isFirstRow = true;
				break;
			}
		}
		for (int i = 0; i < matrix.length; ++i) {
			if (matrix[i][0] == 0) {
				isFirstColumn = true;
				break;
			}
		}
		for (int i = 1; i < matrix.length; ++i) {
			for (int j = 1; j < matrix[i].length; ++j) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// check row
		for (int i = 1; i < matrix.length; ++i) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < matrix[i].length; ++j) {
					matrix[i][j] = 0;
				}
			}
		}
		// check column
		for (int j = 1; j < matrix[0].length; ++j) {
			if (matrix[0][j] == 0) {
				for (int i = 1; i < matrix.length; ++i) {
					matrix[i][j] = 0;
				}
			}
		}
		if (isFirstRow) {
			for (int j = 0; j < matrix[0].length; ++j) {
				matrix[0][j] = 0;
			}
		}
		if (isFirstColumn) {
			for (int i = 0; i < matrix.length; ++i) {
				matrix[i][0] = 0;
			}
		}
    }
	
	/**
	 *  [Medium]
	 *  #074. Search a 2D Matrix
	 *  
	 *  Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
	 *  
	 *  Integers in each row are sorted from left to right.
	 *  The first integer of each row is greater than the last integer of the previous row.
	 *  For example,
	 *  
	 *  Consider the following matrix:
	 *  [
	 *    [1,   3,  5,  7],
	 *    [10, 11, 16, 20],
	 *    [23, 30, 34, 50]
	 *  ]
	 *  Given target = 3, return true.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) return false;
		int rows = matrix.length;
		int columns = matrix[0].length;
        int i = 0;
        while (i < rows) {
        	if (target <= matrix[i][columns - 1]) break;
        	++i;
        }
        if (i < rows) {
        	for (int j = 0; j < columns; ++j) {
        		if (matrix[i][j] == target) return true;
        		if (matrix[i][j] > target) break;
        	}
        }
        return false;
    }
	
	/**
	 *  [Medium]
	 *  #075. Sort Colors
	 *  
	 *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
	 *  with the colors in the order red, white and blue.
	 *  
	 *  Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
	 *  
	 *  Note:
	 *  You are not suppose to use the library's sort function for this problem.
	 *  
	 *  Follow up:
	 *  A rather straight forward solution is a two-pass algorithm using counting sort.
	 *  First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
	 *  
	 *  Could you come up with an one-pass algorithm using only constant space?
	 */
	public void sortColors(int[] nums) {
		if (nums == null || nums.length == 0) return;
		int i = 0;
		for (int k = 0; k < 2; ++k) {
			int j = nums.length - 1;
			while (i < j && i < nums.length) {
				while (i < nums.length && nums[i] == k) i++;
				while (0 <= j && nums[j] != k) j--;
				if (i < j) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
					i++;
				}
			}
		}
    }
	
	/**
	 *  [Medium]
	 *  #077. Combinations
	 *  
	 *  Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 *  
	 *  For example,
	 *  If n = 4 and k = 2, a solution is:
	 *  
	 *  [
	 *    [2,4],
	 *    [3,4],
	 *    [2,3],
	 *    [1,2],
	 *    [1,3],
	 *    [1,4],
	 *  ]
	 */
	private List<List<Integer>> combine_combineList = null;
	public List<List<Integer>> combine(int n, int k) {
		List<Integer> rangeList = new ArrayList<>();
		for (int i = 1; i <= n; ++i) {
			rangeList.add(i);
		}
		combine_combineList = new ArrayList<>();
		int level = 1;
		for (int i = 0; i < rangeList.size(); ++i) {
			List<Integer> list = new ArrayList<Integer>();
			int num = rangeList.get(i);
			list.add(num);
			combine_getCombine(rangeList, i + 1, level, k, list);
		}
		return combine_combineList;
    }
	private void combine_getCombine(List<Integer> range, int index, int level, int maxLevel, List<Integer> list) {
		if (level >= maxLevel) {
			combine_combineList.add(new ArrayList<Integer>(list));
			return;
		}
		level++;
		for (int i = index; i < range.size(); ++i) {
			int num = range.get(i);
			list.add(num);
			combine_getCombine(range, i + 1, level, maxLevel, list);
			list.remove(new Integer(num));
			//range.add(i, num);
		}
	}
	
	/**
	 *  [Medium]
	 *  #078. Subsets
	 *  
	 *  Given a set of distinct integers, nums, return all possible subsets.
	 *  
	 *  Note: The solution set must not contain duplicate subsets.
	 *  
	 *  For example,
	 *  If nums = [1,2,3], a solution is:
	 *  
	 *  [
	 *    [3],
	 *    [1],
	 *    [2],
	 *    [1,2,3],
	 *    [1,3],
	 *    [2,3],
	 *    [1,2],
	 *    []
	 *  ]
	 */
	private List<List<Integer>> subsets_illist = null;
	public List<List<Integer>> subsets(int[] nums) {
		subsets_illist = new LinkedList<>();
		for (int i = nums.length; i >= 0; --i) {
			subsets_getSubSets(nums, i);
		}
		for (int i = 0; i < subsets_illist.size(); ++i) {
			Collections.sort(subsets_illist.get(i));
		}
		Collections.reverse(subsets_illist);
		return subsets_illist;
    }
	private void subsets_getSubSets(int[] nums, int num) {
		List<Integer> list = new ArrayList<>();
		subsets_getSubSets(nums, 0, num, list);
	}
	private void subsets_getSubSets(int[] nums, int start, int num, List<Integer> list) {
		if (num == 0) {
			//Collections.sort(list);
			subsets_illist.add(new LinkedList<Integer>(list));
			return;
		}
		for (int i = start; i < nums.length; ++i) {
			list.add(nums[i]);
			subsets_getSubSets(nums, i + 1, num - 1, list);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 *  [Medium]
	 *  #080. Remove Duplicates from Sorted Array II
	 *  
	 *  Follow up for "Remove Duplicates":
	 *  What if duplicates are allowed at most twice?
	 *  
	 *  For example,
	 *  Given sorted array nums = [1,1,1,2,2,3],
	 *  
	 *  Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
	 *  It doesn't matter what you leave beyond the new length.
	 *  
	 *  Subscribe to see which companies asked this question
	 */
	public int removeDuplicatesII(int[] nums) {
		if (nums == null) return -1;
		int length = nums.length;
		int dupi = -1;
		int prev = 0;
		int curr = 1;
		while (0 < curr && curr < length) {
			if (dupi == -1) {
				if (nums[prev] == nums[curr]) {
					dupi = curr;
				}
				prev = curr;
				curr++;
			}
			else {
				if (nums[dupi] == nums[curr]) {
					removeDuplicatesII_moveForwardt(nums, curr, dupi);
					length -= (curr - dupi);
				}
				else {
					dupi = -1;
					prev = curr;
					curr++;
				}
			}
		}
		return length;
    }
	private void removeDuplicatesII_moveForwardt(int[] array, int from, int to) {
		int i = to;
		int j = from;
		while (i < j && j < array.length) {
			array[i++] = array[j++];
		}
	}
	
	/**
	 *  [Medium]
	 *  #081. Search in Rotated Sorted Array II
	 *  
	 *  Follow up for "Search in Rotated Sorted Array":
	 *  What if duplicates are allowed?
	 *  
	 *  Would this affect the run-time complexity? How and why?
	 *  
	 *  Write a function to determine if a given target is in the array.
	 */
	// It's the same as #033
	public boolean searchII(int[] nums, int target) {
		if (nums == null || nums.length == 0) return false;
		int pivot = nums[0];
		if (target < pivot) {
			for (int i = nums.length - 1; i >= 0; --i) {
				if (nums[i] > pivot) break;
				if (nums[i] == target) return true;
			}
		}
		else {
			for (int i = 0; i < nums.length; ++i) {
				if (nums[i] < pivot) break;
				if (nums[i] == target) return true;
			}
		}
		return false;
    }
	
	/**
	 *  [Medium]
	 *  #082. Remove Duplicates from Sorted List II
	 *  Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	 *  
	 *  For example,
	 *  Given 1->2->3->3->4->4->5, return 1->2->5.
	 *  Given 1->1->1->2->3, return 2->3.
	 */
	public ListNode deleteDuplicatesII(ListNode head) {
		ListNode thead = new ListNode(-1);
		ListNode tnode = thead;
		ListNode prev = head;
		ListNode node = prev;
		while (prev != null && node != null) {
			node = node.next;
			if (node == null || prev.val != node.val) {
				tnode.next = prev;
				tnode = tnode.next;
			}
			else {
				while (node != null && prev.val == node.val) {
					node = node.next;
				}
			}
			prev = node;
		}
		tnode.next = prev; // if node == null, append the left nodes to new list
		return thead.next;
    }
	
	/**
	 *  [Easy]
	 *  #083. Remove Duplicates from Sorted List
	 *  Given a sorted linked list, delete all duplicates such that each element appear only once.
	 *  
	 *  For example,
	 *  Given 1->1->2, return 1->2.
	 *  Given 1->1->2->3->3, return 1->2->3.
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode node = head;
		ListNode prev = head;
		while (node != null) {
			if (prev.val != node.val) {
				prev.next = node;
				prev = node;
			}
			node = node.next;
			prev.next = null;
		}
		
		return head;
    }
	
	/**
	 *  [Easy]
	 *  #088. Merge Sorted Array
	 *  
	 *  Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
	 *  
	 *  Note:
	 *  You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
	 *  The number of elements initialized in nums1 and nums2 are m and n respectively.
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int[] temp = new int[m + n];
		int i = 0;
		int j = 0;
		int index = 0;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j]) {
				temp[index++] = nums1[i++];
			}
			else {
				temp[index++] = nums2[j++];
			}
		}
		while (i < m) {
			temp[index++] = nums1[i++];
		}
		while (j < n) {
			temp[index++] = nums2[j++];
		}
		/* arraycopy(Object src, int srcPos, Object dest, int destPos, int length) 
		 * 
		 * src - the source array.
		 * srcPos - starting position in the source array.
		 * dest - the destination array.
		 * destPos - starting position in the destination data.
		 * length - the number of array elements to be copied. 
		 */
		System.arraycopy(temp, 0, nums1, 0, m + n);
    }
	
	/**
	 *  [Medium]
	 *  #089. Gray Code
	 *  
	 *  The gray code is a binary numeral system where two successive values differ in only one bit.
	 *  
	 *  Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
	 *  A gray code sequence must begin with 0.
	 *  
	 *  For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
	 *  
	 *  00 - 0
	 *  01 - 1
	 *  11 - 3
	 *  10 - 2
	 *  Note:
	 *  For a given n, a gray code sequence is not uniquely defined.
	 *  
	 *  For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
	 *  
	 *  For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
	 */
	public List<Integer> grayCode(int n) {
		List<Integer> gcList = new ArrayList<Integer>();
		gcList.add(0);
		int times = (int) (Math.pow(2, n) - 1);
		int number = 0;
		for (int i = 0; i < times; ++i) {
			int factor = 1;
			int temp = number ^ factor;
			while (gcList.contains(temp)) {
				factor <<= 1;
				temp = number ^ factor;
			}
			gcList.add(temp);
			number = temp;
		}
		return gcList;
    }
	
	/**
	 *  [Medium]
	 *  #090. Subsets II
	 *  Given a collection of integers that might contain duplicates, nums, return all possible subsets.
	 *  
	 *  Note: The solution set must not contain duplicate subsets.
	 *  
	 *  For example,
	 *  If nums = [1,2,2], a solution is:
	 *  
	 *  [
	 *    [2],
	 *    [1],
	 *    [1,2,2],
	 *    [2,2],
	 *    [1,2],
	 *    []
	 *  ]
	 */
	private List<List<Integer>> subsetsWithDup_illist = null;
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		subsetsWithDup_illist = new LinkedList<List<Integer>>();
		LinkedList<List<Integer>> myllist = new LinkedList<List<Integer>>();
		for (int i = nums.length; i >= 0; --i) {
			subsetsWithDup_getSubSets(nums, i);
		}
		for (int i = 0; i < subsetsWithDup_illist.size(); ++i) {
			List<Integer> list = subsetsWithDup_illist.get(i);
			Collections.sort(list);
			if (!myllist.contains(list)) {
				myllist.add(list);
			}
		}
		Collections.reverse(myllist);
		return myllist;
    }
	private void subsetsWithDup_getSubSets(int[] S, int num) {
		List<Integer> list = new ArrayList<Integer>();
		subsetsWithDup_getSubSets(S, 0, num, list);
	}
	private void subsetsWithDup_getSubSets(int[] S, int start, int num, List<Integer> list) {
		if (num == 0) {
			subsetsWithDup_illist.add(new LinkedList<Integer>(list));
			return;
		}
		for (int i = start; i < S.length; ++i) {
			list.add(S[i]);
			subsetsWithDup_getSubSets(S, i + 1, num - 1, list);
			list.remove(list.size() - 1);
		}
	}
	
	/**
	 *  [Medium]
	 *  #092. Reverse Linked List II
	 *  Reverse a linked list from position m to n. Do it in-place and in one-pass.
	 *  
	 *  For example:
	 *  Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 *  
	 *  return 1->4->3->2->5->NULL.
	 *  
	 *  Note:
	 *  Given m, n satisfy the following condition:
	 *  1 <= m <= n <= length of list.
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode thead = new ListNode(-1);
		thead.next = head;
		ListNode curr = head;
		ListNode prev = thead;
		int pos = 1;
		while (pos < m) {
			pos++;
			prev = curr;
			curr = curr.next;
		}
		ListNode h = curr;
		ListNode t = h;
		while (m <= pos && pos < n) {
			ListNode temp = h;
			h = t.next;
			t.next = h.next;
			h.next = temp;
			pos++;
		}
		prev.next = h;
		return thead.next;
    }
	
	/**
	 *  [Medium]
	 *  #094. Binary Tree Inorder Traversal
	 *  
	 *  Given a binary tree, return the inorder traversal of its nodes' values.
	 *  
	 *  For example:
	 *  Given binary tree [1,null,2,3],
	 *     1
	 *      \
	 *       2
	 *      /
	 *     3
	 *  return [1,3,2].
	 *  
	 *  Note: Recursive solution is trivial, could you do it iteratively?
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		//return inorderTraversal_recursive(root);
		return inorderTraversal_iterative(root);
    }
	private List<Integer> inorderTraversal_iterative(TreeNode root) {
		List<Integer> rList = new ArrayList<Integer>();
		if (root == null) return rList;
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		TreeNode node = root;
		while (node != null || stack.size() != 0) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.remove();
			rList.add(node.val);
			node = node.right;
		}
		return rList;
	}
	private List<Integer> inorderTraversal_recursive(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		this.inorderTraversal_recursive(list, root);
		return list;
	}
	private void inorderTraversal_recursive(List<Integer> list, TreeNode node) {
		if (node == null) {
			return;
		} else if (node.left == null && node.right == null) {  // if node is leaf
			list.add(node.val);
			return;
		} else {
			this.inorderTraversal_recursive(list, node.left);
			list.add(node.val);
			this.inorderTraversal_recursive(list, node.right);
		}
	}
	
	/**
	 *  [Easy]
	 *  #100. Same Tree
	 *  
	 *  Given two binary trees, write a function to check if they are equal or not.
	 *  
	 *  Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
	 */
	// check two of inorder/preorder/postorder
	public boolean isSameTree(TreeNode p, TreeNode q) {
        StringBuilder pathP1 = new StringBuilder();
        StringBuilder pathP2 = new StringBuilder();
        this.isSameTree_getPath_inorder(pathP1, p);
        this.isSameTree_getPath_preorder(pathP2, p);
        
        StringBuilder pathQ1 = new StringBuilder();
        StringBuilder pathQ2 = new StringBuilder();
        this.isSameTree_getPath_inorder(pathQ1, q);
        this.isSameTree_getPath_preorder(pathQ2, q);
        
        return pathP1.toString().equals(pathQ1.toString()) && pathP2.toString().equals(pathQ2.toString());
    }
	private void isSameTree_getPath_inorder(StringBuilder path, TreeNode node) {
		if (node == null) {
			path.append("N#"); // to avoid same value in all tree nodes
			return;
		} else if (node.left == null && node.right == null) {
			path.append(node.val);
			path.append('#');
			return;
		} else {
			this.isSameTree_getPath_inorder(path, node.left);
			path.append(node.val);
			path.append('#');
			this.isSameTree_getPath_inorder(path, node.right);
		}
	}
	private void isSameTree_getPath_preorder(StringBuilder path, TreeNode node) {
		if (node == null) {
			path.append("N#");  // to avoid same value in all tree nodes
			return;
		} else if (node.left == null && node.right == null) {
			path.append(node.val);
			path.append('#');
			return;
		} else {
			path.append(node.val);
			path.append('#');
			this.isSameTree_getPath_preorder(path, node.left);
			this.isSameTree_getPath_preorder(path, node.right);
		}
	}
	
	/**
	 *  [Easy]
	 *  #101. Symmetric Tree
	 *  
	 *  Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	 *  
	 *  For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	 *  
	 *      1
	 *     / \
	 *    2   2
	 *   / \ / \
	 *  3  4 4  3
	 *  But the following [1,2,2,null,3,null,3] is not:
	 *      1
 	 *     / \
	 *    2   2
	 *     \   \
	 *     3    3
	 *  Note:
	 *  Bonus points if you could solve it both recursively and iteratively.
	 */
	public boolean isSymmetric(TreeNode root) {
        //return this.isSymmetric_recurtive(root);
		return this.isSymmetric_iterative(root);
    }
	private boolean isSymmetric_iterative(TreeNode root) {
		if (root == null) return true;
		TreeNode flag = new TreeNode(Integer.MAX_VALUE);  // avoid duplicate -1
		TreeNode none = new TreeNode(Integer.MIN_VALUE);  // avoid duplicate -2
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.addLast(root);
		queue.addLast(flag); // use -1 as flag
		boolean hasNonEmptyNode = false; // identify if one row contains only "flag" and "none" nodes.
		while (queue.size() != 0) {
			List<TreeNode> row = new ArrayList<>();
			hasNonEmptyNode = false;
			TreeNode node = queue.poll();
			while (!node.equals(flag)) {
				if (!node.equals(none)) {
					if (node.left == null) queue.addLast(none);
					else {
						queue.addLast(node.left);
						hasNonEmptyNode = true;
					}
					if (node.right == null) queue.addLast(none);
					else {
						queue.addLast(node.right);
						hasNonEmptyNode = true;
					}
				}
				row.add(node);
				node = queue.poll();
			}
			queue.addLast(flag);
			int length = row.size();
			for (int i = 0; i < length / 2; ++i) {
				if (row.get(i).val != row.get(length - 1 - i).val) return false;
			}
			if (!hasNonEmptyNode) break;
		}
		return true;
	}
	private boolean isSymmetric_recurtive(TreeNode root) {
		StringBuilder path = new StringBuilder();
        this.isSymmetric_recurtive(path, root);
        String[] nodes = path.toString().split("#");
        int numOfNodes = nodes.length;
        for (int i = 0; i < numOfNodes / 2; ++i) {
        	if (!nodes[i].equals(nodes[numOfNodes - 1 - i])) return false;
        }
        return true;
	}
	private void isSymmetric_recurtive(StringBuilder path, TreeNode node) {
        if (node.left == null && node.right == null) {
        	path.append(node.val);
        	path.append('#');
        	return;
        } else {
        	if (node.left == null) {
        		path.append("L#");
        	} else {
        		this.isSymmetric_recurtive(path, node.left);
        	}
        	path.append(node.val);
        	path.append('#');
        	if (node.right == null) {
        		path.append("R#");
        	} else {
        		this.isSymmetric_recurtive(path, node.right);
        	}
        }
    }
	
	/**
	 *  [Easy]
	 *  #102. Binary Tree Level Order Traversal
	 *  Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 *  
	 *  For example:
	 *  Given binary tree [3,9,20,null,null,15,7],
	 *      3
	 *     / \
	 *    9  20
	 *      /  \
	 *     15   7
	 *  return its level order traversal as:
	 *  [
	 *    [3],
	 *    [9,20],
	 *    [15,7]
	 *  ]
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> rList = new ArrayList<>();
		if (root == null) return rList;
		TreeNode flag = new TreeNode(Integer.MAX_VALUE);
		Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.add(root);
		queue.add(flag);
		List<Integer> list = new ArrayList<Integer>();
		while (queue.size() != 0) {
			TreeNode node = queue.remove();
			if (!node.equals(flag)) {
				list.add(node.val);
				if (node.left != null) queue.add(node.left);
				if (node.right != null) queue.add(node.right);
			}
			else {
				if (queue.size() != 0) {
					queue.add(flag);
				}
				rList.add(list);
				list = new ArrayList<Integer>();
			}
		}
		return rList;
    }
	
	/**
	 *  [Medium]
	 *  #103. Binary Tree Zigzag Level Order Traversal
	 *  
	 *  Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
	 *  
	 *  For example:
	 *  Given binary tree [3,9,20,null,null,15,7],
	 *      3
	 *     / \
	 *    9  20
	 *      /  \
	 *     15   7
	 *  return its zigzag level order traversal as:
	 *  [
	 *    [3],
	 *    [20,9],
	 *    [15,7]
	 *  ]
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> rList = new ArrayList<List<Integer>>();
		if (root == null) return rList;
		Deque<TreeNode> stack1 = new ArrayDeque<TreeNode>();
		Deque<TreeNode> stack2 = new ArrayDeque<TreeNode>();
		stack1.push(root);
		int level = 1;
		List<Integer> list = new ArrayList<Integer>();
		while (stack1.size() != 0 || stack2.size() != 0) {
			if (level % 2 == 1) {
				TreeNode node = stack1.pop();
				list.add(node.val);
				if (node.left != null) stack2.push(node.left);
				if (node.right != null) stack2.push(node.right);
				if (stack1.size() == 0) {
					level++;
					rList.add(list);
					list = new ArrayList<Integer>();
				}
			}
			else {
				TreeNode node = stack2.pop();
				list.add(node.val);
				if (node.right != null) stack1.push(node.right);
				if (node.left != null) stack1.push(node.left);
				if (stack2.size() == 0) {
					level++;
					rList.add(list);
					list = new ArrayList<Integer>();
				}
			}
		}
		return rList;
    }
	
	/**
	 *  [Easy]
	 *  #104. Maximum Depth of Binary Tree
	 *  
	 *  Given a binary tree, find its maximum depth.
	 *  
	 *  The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
	 */
	private int maxDepth_maxDepth = Integer.MIN_VALUE;
	public int maxDepth(TreeNode root) {
		if (root == null) return 0;
		maxDepth_maxDepth = Integer.MIN_VALUE;
		int depth = 0;
		maxDepth_searchTreeMax(root, depth);
		return maxDepth_maxDepth;
    }
	private void maxDepth_searchTreeMax(TreeNode node, int depth) {
		if (node.left == null && node.right == null) {
			depth++;
			if (depth > maxDepth_maxDepth)
				maxDepth_maxDepth = depth;
			return;
		}
		depth++;
		if (node.left != null) maxDepth_searchTreeMax(node.left, depth);
		if (node.right != null) maxDepth_searchTreeMax(node.right, depth);
	}
	
	/**
	 *  [Medium]
	 *  #105. Construct Binary Tree from Preorder and Inorder Traversal
	 *  Given preorder and inorder traversal of a tree, construct the binary tree.
	 *  
	 *  Note:
	 *  You may assume that duplicates do not exist in the tree.
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) return null;
		if (inorder == null || inorder.length == 0) return null;
		if (preorder.length != inorder.length) return null;
		TreeNode root = new TreeNode(preorder[0]);
		int rootIndex = buildTree_findIndex(inorder, root.val, preorder.length);
		root.left = buildTree_build(preorder, 1, 1 + rootIndex, inorder, 0, rootIndex);
		root.right = buildTree_build(preorder, 1 + rootIndex, preorder.length, inorder, rootIndex + 1, inorder.length);
		return root;
    }
	private TreeNode buildTree_build(int[] preorder, int x1, int y1, int[] inorder, int x2, int y2) {
		if (preorder == null || preorder.length == 0 || x1 >= preorder.length) return null;
		if (inorder == null || inorder.length == 0 || x2 >= inorder.length) return null;
		TreeNode root = null;
		if (x1 != y1 && x2 != y2) {
			root = new TreeNode(preorder[x1]);
			int rootIndex = buildTree_findIndex(inorder, root.val, preorder.length);
			root.left = buildTree_build(preorder, x1 + 1, x1 + 1 + rootIndex - x2, inorder, x2, rootIndex);
			root.right = buildTree_build(preorder, x1 + 1 + rootIndex - x2, y1, inorder, rootIndex + 1, y2);
		}
		return root;
	}
	private int buildTree_findIndex(int[] array, int val, int end) {
		for (int i = 0; i < Math.min(array.length, end); ++i) {
			if (array[i] == val) return i;
		}
		return -1;
	}
	
	/**
	 *  [Medium]
	 *  #106. Construct Binary Tree from Inorder and Postorder Traversal
	 *  Given inorder and postorder traversal of a tree, construct the binary tree.
	 *  
	 *  Note:
	 *  You may assume that duplicates do not exist in the tree.
	 */
	public TreeNode buildTreeII(int[] inorder, int[] postorder) {
		if (postorder == null || postorder.length == 0) return null;
		if (inorder == null || inorder.length == 0) return null;
		if (postorder.length != inorder.length) return null;
		TreeNode root = new TreeNode(postorder[postorder.length - 1]);
		int rootIndex = buildTreeII_findIndex(inorder, root.val, postorder.length);
		int x1 = 0;
		int y1 = rootIndex;
		int x2 = 0;
		int y2 = rootIndex;
		root.left = buildTreeII_build(postorder, x1, y1, inorder, x2, y2);
		
		x1 = y1;
		y1 = postorder.length - 1;
		x2 = rootIndex + 1;
		y2 = inorder.length;
		root.right = buildTreeII_build(postorder, x1, y1, inorder, x2, y2);
		
		return root;
    }
	private TreeNode buildTreeII_build(int[] postorder, int x1, int y1, int[] inorder, int x2, int y2) {
		if (postorder == null || postorder.length == 0 || x1 >= postorder.length) return null;
		if (inorder == null || inorder.length == 0 || x2 >= inorder.length) return null;
		TreeNode root = null;
		if (x1 != y1 && x2 != y2) {
			root = new TreeNode(postorder[y1 - 1]);
			int rootIndex = buildTreeII_findIndex(inorder, root.val, postorder.length);
			root.left = buildTreeII_build(postorder, x1, x1 + rootIndex - x2, inorder, x2, rootIndex);
			root.right = buildTreeII_build(postorder, x1 + rootIndex - x2, y1 - 1, inorder, rootIndex + 1, y2);
		}
		return root;
	}
	
	private int buildTreeII_findIndex(int[] array, int val, int end) {
		for (int i = 0; i < Math.min(array.length, end); ++i) {
			if (array[i] == val) return i;
		}
		return -1;
	}
	
	/**
	 *  [Easy]
	 *  #107. Binary Tree Level Order Traversal II
	 *  Given a binary tree, return the bottom-up level order traversal of its nodes' values.
	 *  (ie, from left to right, level by level from leaf to root).
	 *  
	 *  For example:
	 *  Given binary tree [3,9,20,null,null,15,7],
	 *      3
	 *     / \
	 *    9  20
	 *      /  \
	 *     15   7
	 *  return its bottom-up level order traversal as:
	 *  [
	 *    [15,7],
	 *    [9,20],
	 *    [3]
	 *  ]
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) return result;
		Deque<TreeNode> nodes = new ArrayDeque<>();
		TreeNode flag = new TreeNode(-1);
		nodes.addLast(root);
		nodes.addLast(flag);
		List<Integer> list = new ArrayList<>();
		while (nodes.size() != 0) {
			TreeNode node = nodes.pollFirst();
			if (node == flag) {
				result.add(list);
				if (nodes.size() != 0) {
					nodes.addLast(flag);
					list = new ArrayList<>();
				}
			}
			else {
				if (node.left != null) nodes.addLast(node.left);
				if (node.right != null) nodes.addLast(node.right);
				list.add(node.val);
			}
		}
		List<List<Integer>> resultR = new ArrayList<>();
		for (int i = result.size() - 1; i >= 0; --i) {
			resultR.add(result.get(i));
		}
        return resultR;
    }
	
	/**
	 *  [Easy]
	 *  #110. Balanced Binary Tree
	 *  Given a binary tree, determine if it is height-balanced.
	 *  For this problem, a height-balanced binary tree is defined as a binary tree 
	 *  in which the depth of the two subtrees of every node never differ by more than 1.
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.addLast(root);
		while (queue.size() != 0) {
			TreeNode node = queue.pollFirst();
			if (node.left != null) queue.addLast(node.left);
			if (node.right != null) queue.addLast(node.right);
			int levelLeft = this.isBalanced_getTreeLevel(node.left, 0);
			int levelRight = this.isBalanced_getTreeLevel(node.right, 0);
			if (Math.abs(levelLeft - levelRight) > 1) return false;
		}
		return true;
    }
	
	public int isBalanced_getTreeLevel(TreeNode node, int level) {
		if (node == null) {
			return level;
		} else {
			++level;
			int levelLeft = isBalanced_getTreeLevel(node.left, level);
			int levelRight = isBalanced_getTreeLevel(node.right, level);
			return Math.max(levelLeft, levelRight);
		}
	}
	
	/**
	 *  [Easy]
	 *  #111. Minimum Depth of Binary Tree
	 *  
	 *  Given a binary tree, find its minimum depth.
	 *  
	 *  The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
	 */
	private int minDepth_minDepth = Integer.MAX_VALUE;
	public int minDepth(TreeNode root) {
		if (root == null) return 0;
		minDepth_minDepth = Integer.MAX_VALUE;
		int depth = 0;
		minDepth_searchTreeDFS(root, depth);
		return minDepth_minDepth;
    }
	private void minDepth_searchTreeDFS(TreeNode node, int depth) {
		if (node.left == null && node.right == null) {
			depth++;
			if (depth < minDepth_minDepth)
				minDepth_minDepth = depth;
			return;
		}
		depth++;
		if (node.left != null) minDepth_searchTreeDFS(node.left, depth);
		if (node.right != null) minDepth_searchTreeDFS(node.right, depth);
	}
	
	/**
	 *  [Easy]
	 *  #112. Path Sum
	 *  Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
	 *  
	 *  For example:
	 *  Given the below binary tree and sum = 22,
	 *                5
	 *               / \
	 *              4   8
	 *             /   / \
	 *            11  13  4
	 *           /  \      \
	 *          7    2      1
	 *  return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	 */
	private boolean hasPathSum_result = false;
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) return false;
		hasPathSum_result = false;
		int value = 0;
		hasPathSum_searchPath(root, value, sum);
		return hasPathSum_result;
    }
	public void hasPathSum_searchPath(TreeNode node, int value, int sum) {
		if (node.left == null && node.right == null) {
			value += node.val;
			if (value == sum) {
				hasPathSum_result = true;
			}
		}
		value += node.val;
		if (node.left != null) {
			hasPathSum_searchPath(node.left, value, sum);
		}
		if (node.right != null) {
			hasPathSum_searchPath(node.right, value, sum);
		}
	}
	
	/**
	 *  [Medium]
	 *  #113. Path Sum II
	 *  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	 *  
	 *  For example:
	 *  Given the below binary tree and sum = 22,
	 *                5
	 *               / \
	 *              4   8
	 *             /   / \
	 *            11  13  4
	 *           /  \    / \
	 *          7    2  5   1
	 *  return
	 *  [
	 *     [5,4,11,2],
	 *     [5,8,4,5]
	 *  ]
	 */
	private List<List<Integer>> pathSumII_rList = null;
	public List<List<Integer>> pathSumII(TreeNode root, int sum) {
		pathSumII_rList = new ArrayList<List<Integer>>();
		if (root == null) return pathSumII_rList;
		int value = 0;
		List<Integer> list = new ArrayList<Integer>();
		pathSumII_searchPath(root, value, sum, list);
		return pathSumII_rList;
    }
	private void pathSumII_searchPath(TreeNode node, int value, int sum, List<Integer> list) {
		if (node.left == null && node.right == null) {
			value += node.val;
			if (value == sum) {
				list.add(node.val);
				pathSumII_rList.add(new ArrayList<Integer>(list));
				list.remove(list.size() - 1);
			}
			return;
		}
		value += node.val;
		list.add(node.val);
		if (node.left != null) {
			pathSumII_searchPath(node.left, value, sum, list);
		}
		if (node.right != null) {
			pathSumII_searchPath(node.right, value, sum, list);
		}
		list.remove(list.size() - 1);
	}
	
	/**
	 *  [Medium]
	 *  #114. Flatten Binary Tree to Linked List
	 *  Given a binary tree, flatten it to a linked list in-place.
	 *  
	 *  For example,
	 *  Given
	 *  
 	 *           1
	 *          / \
	 *         2   5
	 *        / \   \
	 *       3   4   6
	 *  The flattened tree should look like:
	 *     1
	 *      \
	 *       2
	 *        \
	 *         3
	 *          \
	 *           4
	 *            \
	 *             5
	 *              \
	 *               6
	 *  
	 *  Hints:
	 *  If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
	 */
	private List<TreeNode> flatten_nodeList = null;
	public void flatten(TreeNode root) {
		flatten_nodeList = new ArrayList<TreeNode>();
		flatten_searchTreeNLR(root);
		TreeNode prev = new TreeNode(0);
		for (int i = 0; i < flatten_nodeList.size(); ++i) {
			TreeNode node = flatten_nodeList.get(i);
			node.left = null;
			prev.right = node;
			prev = node;
		}
    }
	private void flatten_searchTreeNLR(TreeNode node) {
		if (node == null) return;
		flatten_nodeList.add(node);
		flatten_searchTreeNLR(node.left);
		flatten_searchTreeNLR(node.right);
	}
	
	/**
	 *  [Hard]
	 *  #115. Distinct Subsequences
	 *  Given a string S and a string T, count the number of distinct subsequences of T in S.
	 *  
	 *  A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
	 *  without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	 *  
	 *  Here is an example:
	 *  S = "rabbbit", T = "rabbit"
	 *  
	 *  Return 3.
	 */
	public int numDistinct(String s, String t) {
		// length of S >= length of T
		int[][] dp = new int[s.length() + 1][t.length() + 1];
		for (int j = 0; j <= t.length(); ++j) {
			dp[0][j] = 0;
		}
		for (int i = 0; i <= s.length(); ++i) {
			dp[i][0] = 1;
		}
		for (int i = 0; i < s.length(); ++i) {
			for (int j = 0; j < t.length(); ++j) {
				if (s.charAt(i) == t.charAt(j)) {
					dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
				}
				else {
					dp[i + 1][j + 1] = dp[i][j + 1];
				}
			}
		}
		return dp[s.length()][t.length()];
    }
	
	/**
	 *  [Medium]
	 *  #116. Populating Next Right Pointers in Each Node
	 *  Given a binary tree
	 *  
	 *      struct TreeLinkNode {
	 *        TreeLinkNode *left;
	 *        TreeLinkNode *right;
	 *        TreeLinkNode *next;
	 *      }
	 *  Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
	 *  
	 *  Initially, all next pointers are set to NULL.
	 *  
	 *  Note:
	 *  
	 *  You may only use constant extra space.
	 *  You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	 *  For example,
	 *  Given the following perfect binary tree,
	 *  
	 *           1
	 *         /  \
	 *        2    3
	 *       / \  / \
	 *      4  5  6  7
	 *      
	 *  After calling your function, the tree should look like:
	 *  
	 *           1 -> NULL
	 *         /  \
	 *        2 -> 3 -> NULL
	 *       / \  / \
	 *      4->5->6->7 -> NULL
	 */
	public void connect(TreeLinkNode root) {
		if (root == null) return;
		ArrayDeque<TreeLinkNode> queue = new ArrayDeque<>();
		queue.add(root);
		TreeLinkNode prev = null;
		int index = 1;
		int factor = 1;
		while (!queue.isEmpty()) {
			TreeLinkNode node = queue.remove();
			if (index == factor) {
				node.next = null;
				factor *= 2;
				index = 0;
				if (prev != null) prev.next = node;
				prev = null;
			}
			else {
				if (prev == null) prev = node;
				else {
					prev.next = node;
					prev = node;
				}
			}
			index++;
			if (node.left != null) queue.add(node.left);
			if (node.right != null) queue.add(node.right);
		}
    }
	
	/**
	 *  [Hard]
	 *  #117. Populating Next Right Pointers in Each Node II
	 *  
	 *  Follow up for problem "Populating Next Right Pointers in Each Node".
	 *  
	 *  What if the given tree could be any binary tree? Would your previous solution still work?
	 *  
	 *  Note:
	 *  
	 *  You may only use constant extra space.
	 *  For example,
	 *  Given the following binary tree,
	 *  
	 *           1
	 *         /  \
	 *        2    3
	 *       / \    \
	 *      4   5    7
	 *      
	 *  After calling your function, the tree should look like:
	 *  
	 *           1 -> NULL
	 *         /  \
	 *        2 -> 3 -> NULL
	 *       / \    \
	 *      4-> 5 -> 7 -> NULL
	 */
	public void connectII(TreeLinkNode root) {
		if (root == null) return;
		Deque<TreeLinkNode> queue0 = new ArrayDeque<TreeLinkNode>();  // Queue 1
		Deque<TreeLinkNode> queue1 = new ArrayDeque<TreeLinkNode>();  // Queue 2
		int level = 0;
		queue0.add(root);
		TreeLinkNode prev = null;
		while (!queue0.isEmpty() || !queue1.isEmpty()) {
			if (level % 2 == 0) {
				while (!queue0.isEmpty()) {
					TreeLinkNode node = queue0.remove();
					if (prev == null) prev = node;
					else {
						prev.next = node;
						prev = node;
					}
					if (node.left != null) queue1.add(node.left);
					if (node.right != null) queue1.add(node.right);
				}
				prev.next = null;
				prev = null;
			}
			else {
				while (!queue1.isEmpty()) {
					TreeLinkNode node = queue1.remove();
					if (prev == null) prev = node;
					else {
						prev.next = node;
						prev = node;
					}
					if (node.left != null) queue0.add(node.left);
					if (node.right != null) queue0.add(node.right);
				}
				prev.next = null;
				prev = null;
			}
			level++;
		}
    }
	
	/**
	 *  [Easy]
	 *  #118. Pascal's Triangle
	 *  
	 *  Given numRows, generate the first numRows of Pascal's triangle.
	 *  
	 *  For example, given numRows = 5,
	 *  Return
	 *  
	 *  [
	 *       [1],
	 *      [1,1],
	 *     [1,2,1],
	 *    [1,3,3,1],
	 *   [1,4,6,4,1]
	 *  ]
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> llist = new ArrayList<List<Integer>>();
		int num = numRows;
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		while (num > 0) {
			llist.add(list);
			list = generate_generateList(list);
			num--;
		}
		
		return llist;
    }
	private List<Integer> generate_generateList(List<Integer> list) {
		List<Integer> resList = new ArrayList<Integer>();
		int prev = 0;
		for (int i = 0 ; i < list.size(); ++i) {
			int curr = list.get(i);
			resList.add(prev + curr);
			prev = curr;
		}
		resList.add(list.get(list.size() - 1));
		return resList;
	}
	
	/**
	 *  [Easy]
	 *  #119. Pascal's Triangle II
	 *  
	 *  Given an index k, return the kth row of the Pascal's triangle.
	 *  
	 *  For example, given k = 3,
	 *  Return [1,3,3,1].
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> list = new ArrayList<Integer>();
		int index = rowIndex + 1;
		while (index > 0) {
			int prev = 0;
			for (int i = 0; i < list.size(); ++i) {
				int curr = list.get(i);
				list.set(i,  prev + curr);
				prev = curr;
			}
			list.add(1);
			index--;
		}
		return list;
    }
	
	/**
	 *  [Medium]
	 *  #120. Triangle
	 *  
	 *  Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
	 *  
	 *  For example, given the following triangle
	 *  [
	 *       [2],
	 *      [3,4],
	 *     [6,5,7],
	 *    [4,1,8,3]
	 *  ]
	 *  The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
	 *  
	 *  Note:
	 *  Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0) {
			return 0;
		}
		int sizeX = triangle.size();
		for (int i = sizeX - 2; i >= 0; --i) {
			List<Integer> tlist1 = triangle.get(i);
			List<Integer> tlist2 = triangle.get(i + 1);
			for (int j = tlist2.size() - 2; j >= 0; --j) {
				int a = tlist2.get(j);
				int b = tlist2.get(j + 1);
				tlist1.set(j, tlist1.get(j) + (a < b ? a : b));
			}
		}
		return triangle.get(0).get(0);
    }
	
	/**
	 *  [Easy]
	 *  #121. Best Time to Buy and Sell Stock
	 *  
	 *  Say you have an array for which the ith element is the price of a given stock on day i.
	 *  
	 *  If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	 *  
	 *  Example 1:
	 *  Input: [7, 1, 5, 3, 6, 4]
	 *  Output: 5
	 *  
	 *  max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
	 *  
	 *  Example 2:
	 *  Input: [7, 6, 4, 3, 1]
	 *  Output: 0
	 *  
	 *  In this case, no transaction is done, i.e. max profit = 0.
	 */
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) return 0;
		int maxProfit = 0;
		int minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; ++i) {
			if (prices[i] < minPrice) {
				minPrice = prices[i];
			} else {
				if (prices[i] - minPrice > maxProfit) {
					maxProfit = prices[i] - minPrice;
				}
			}
		}
		return maxProfit;
    }
	
	/**
	 *  [Easy]
	 *  #125. Valid Palindrome
	 *  
	 *  Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
	 *  
	 *  For example,
	 *  "A man, a plan, a canal: Panama" is a palindrome.
	 *  "race a car" is not a palindrome.
	 *  
	 *  Note:
	 *  Have you consider that the string might be empty? This is a good question to ask during an interview.
	 *  For the purpose of this problem, we define empty string as valid palindrome.
	 */
	public boolean isPalindrome(String s) {
		if (s.length() == 0) return true;
		String str = s.toLowerCase();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); ++i) {
			char c = str.charAt(i);
			if (97 <= c && c <= 122 || 48 <= c && c <= 57) {
				sb.append(c);
			}
		}
		String checkStr = sb.toString();
		int length = checkStr.length();
		for (int i = 0; i < length / 2; ++i) {
			char c1 = checkStr.charAt(i);
			char c2 = checkStr.charAt(length - 1 - i);
			if (c1 != c2) return false;
		}
        return true;
    }
	
	/**
	 *  [Medium]
	 *  #129. Sum Root to Leaf Numbers
	 *  
	 *  Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
	 *  
	 *  An example is the root-to-leaf path 1->2->3 which represents the number 123.
	 *  
	 *  Find the total sum of all root-to-leaf numbers.
	 *  
	 *  For example,
	 *  
	 *      1
	 *     / \
	 *    2   3
	 *  The root-to-leaf path 1->2 represents the number 12.
	 *  The root-to-leaf path 1->3 represents the number 13.
	 *  
	 *  Return the sum = 12 + 13 = 25.
	 */
	public int sumNumbers(TreeNode root) {
		int sum = 0;
		if (root == null) return 0;
		List<String> list = new ArrayList<String>();
		String num = "";
		sumNumbers_searchDLS(root, num, list);
		for (int i = 0; i < list.size(); ++i) {
			int number = Integer.parseInt(list.get(i));
			sum += number;
		}
		return sum;
    }
	private void sumNumbers_searchDLS(TreeNode node, String num, List<String> resList) {
		if (node == null) {
			return;
		} else if (node.left == null && node.right == null) {
			num += String.format("%d", node.val);
			resList.add(num);
			return;
		}
		num += String.format("%d", node.val);
		sumNumbers_searchDLS(node.left, num, resList);
		sumNumbers_searchDLS(node.right, num, resList);
		
	}
	
	/**
	 *  [Medium]
	 *  #134. Gas Station
	 *  
	 *  There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
	 *  
	 *  You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
	 *  You begin the journey with an empty tank at one of the gas stations.
	 *  
	 *  Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
	 *  
	 *  Note:
	 *  The solution is guaranteed to be unique.
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int size = gas.length;
		int gas_left = 0;
		int gas_need = 0;
		int start = 0;
		for (int i = 0; i < size; ++i) {
			gas_left += (gas[i] - cost[i]);
			if (gas_left < 0) {
				start = i + 1;
				gas_need -= gas_left;
				gas_left = 0;
			}
		}
		return gas_left >= gas_need ? start : -1;
    }
	
	/**
	 *  [Easy]
	 *  #136. Single Number
	 *  
	 *  Given an array of integers, every element appears twice except for one. Find that single one.
	 *  
	 *  Note:
	 *  Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 */
	// Any number which is XOR twice will be no change.!!!
	public int singleNumber(int[] nums) {
		for (int i = 1; i < nums.length; ++i) {
			nums[0] = nums[0] ^ nums[i];
		}
		return nums[0];
    }
	
	/**
	 *  [Easy]
	 *  #141. Linked List Cycle
	 *  
	 *  Given a linked list, determine if it has a cycle in it.
	 *  
	 *  Follow up:
	 *  Can you solve it without using extra space?
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null) return false;
		if (head.next == null) return false;
		ListNode p1 = head;
		ListNode p2 = p1.next;
		while (p2 != null ) {
			if (p1 == p2) return true;
			p1 = p1.next;
			p2 = p2.next;  // two pointers, one moves by 1 step, another moves by 2 steps
			if (p2 == null) break;
			p2 = p2.next;
		}
		return false;
    }
	
	/**
	 *  [Medium]
	 *  #142. Linked List Cycle II
	 *  
	 *  Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	 *  
	 *  Note: Do not modify the linked list.
	 *  
	 *  Follow up:
	 *  Can you solve it without using extra space?
	 */
	public ListNode detectCycle(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return null;
		ListNode p1 = head.next;
		ListNode p2 = head.next.next;
		while (p2 != null ) {
			if (p1 == p2) return detectCycle_getJoint(head, p1);
			p1 = p1.next;
			p2 = p2.next;
			if (p2 == null) break;
			p2 = p2.next;
		}
		return null;
    }
	private ListNode detectCycle_getJoint(ListNode head, ListNode met) {
		ListNode p1 = head;
		ListNode p2 = met;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
	
	/**
	 *  [Medium]
	 *  #143. Reorder List
	 *  
	 *  Given a singly linked list L: L0->L1->...->Ln-1->Ln,
	 *  reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...
	 *  
	 *  You must do this in-place without altering the nodes' values.
	 *  
	 *  For example,
	 *  Given {1,2,3,4}, reorder it to {1,4,2,3}.
	 */
	public void reorderList(ListNode head) {
		ArrayList<ListNode> list = new ArrayList<>();
		ListNode p = head;
		while (p != null) {
			list.add(p);
			p = p.next;
		}
		ListNode nhead = new ListNode(0);
		ListNode tp = nhead;
		int len = list.size();
		int mid = len / 2;
		int i = 0;
		while (i < mid){
			tp.next = list.get(i);
			tp = tp.next;
			tp.next = list.get(len - 1 - i);
			tp = tp.next;
			i++;
		}
		if (len % 2 == 1) {
			tp.next = list.get(i);
			tp = tp.next;
		}
		tp.next = null;
		head = nhead.next;
    }
	
	/**
	 *  [Medium]
	 *  #144. Binary Tree Preorder Traversal
	 *  Given a binary tree, return the preorder traversal of its nodes' values.
	 *  
	 *  For example:
	 *  Given binary tree {1,#,2,3},
	 *     1
	 *      \
	 *       2
	 *      /
	 *     3
	 *  return [1,2,3].
	 *  
	 *  Note: Recursive solution is trivial, could you do it iteratively?
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
        //return this.preorderTraversal_recursive(root);
		return this.preorderTraversal_iterative(root);
    }
	private List<Integer> preorderTraversal_recursive(TreeNode root) {
		List<Integer> arrayList = new ArrayList<Integer>();
		preorderTraversal_recursive_preorder(root, arrayList);
		return arrayList;
	}
	private void preorderTraversal_recursive_preorder(TreeNode node, List<Integer> list) {
		if (node == null) return;
		list.add(node.val);
		if (node.left != null) {
			preorderTraversal_recursive_preorder(node.left, list);
		}
		if (node.right != null) {
			preorderTraversal_recursive_preorder(node.right, list);
		}
	}
	private List<Integer> preorderTraversal_iterative(TreeNode root) {
		List<Integer> arrayList = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		if (root != null) stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			arrayList.add(node.val);
			if (node.right != null) stack.push(node.right);
			if (node.left != null) stack.push(node.left);
		}
		return arrayList;
	}
	
	/**
	 *  [Hard]
	 *  #145. Binary Tree Postorder Traversal
	 *  
	 *  Given a binary tree, return the postorder traversal of its nodes' values.
	 *  
	 *  For example:
	 *  Given binary tree {1,#,2,3},
	 *     1
	 *      \
	 *       2
	 *      /
	 *     3
	 *  return [3,2,1].
	 *  
	 *  Note: Recursive solution is trivial, could you do it iteratively?
	 */
	public List<Integer> postorderTraversal(TreeNode root) {
        //return this.postorderTraversal_recursive(root);
		return this.postorderTraversal_iterative(root);
    }
	private List<Integer> postorderTraversal_recursive(TreeNode root) {
		List<Integer> arrayList = new ArrayList<Integer>();
		postorderTraversal_recursive_postorder(root, arrayList);
		return arrayList;
	}
	private void postorderTraversal_recursive_postorder(TreeNode node, List<Integer> list) {
		if (node == null) return;
		if (node.left != null) {
			postorderTraversal_recursive_postorder(node.left, list);
		}
		if (node.right != null) {
			postorderTraversal_recursive_postorder(node.right, list);
		}
		list.add(node.val);
	}
	private List<Integer> postorderTraversal_iterative(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		if (root != null) stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			list.add(node.val);
			if (node.left != null) stack.push(node.left);
			if (node.right != null) stack.push(node.right);
		}
		Collections.reverse(list);
		return list;
	}
	
	/**
	 *  [Medium]
	 *  #147. Insertion Sort List
	 *  
	 *  Sort a linked list using insertion sort.
	 */
	public ListNode insertionSortList(ListNode head) {
		return this.insertionSortList_solution2(head);
		//return this.insertionSortList_solution1(head);
    }
	private ListNode insertionSortList_solution2(ListNode head) {
		if (head == null) return null;
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = head;
        ListNode curr = head.next;
        head.next = null;
        while (curr != null) {
        	ListNode node = curr;
        	curr = curr.next;
        	node.next = null;
        	this.insertionSortList_solution2_insertNode(node, newHead);
        }
        return newHead.next;
	}
	private void insertionSortList_solution2_insertNode(ListNode node, ListNode from) {
		ListNode p = from;
		while (p != null && p != node) {
			if (p.val <= node.val && (p.next == null || node.val <= p.next.val)) {
				node.next = p.next;
				p.next = node;
			}
			p = p.next;
		}
	}
	private ListNode insertionSortList_solution1(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return head;
		ListNode nhead = new ListNode(Integer.MIN_VALUE);
		nhead.next = head;
		ListNode p = head.next;
		while (p != null) {
			p = insertionSortList_solution1_insertNode(p, nhead);
		}
		return nhead.next;
	}
	private ListNode insertionSortList_solution1_insertNode(ListNode node, ListNode from) {
		ListNode prev = insertionSortList_solution1_getPrevNode(from, node);
		ListNode next = node.next;
		ListNode p1 = from; //start
		ListNode p2 = node; //end
		while (p1 != null && p1 != p2) {
			if (p1.val <= node.val && node.val < p1.next.val) {
				node.next = p1.next;
				p1.next = node;
				prev.next = next;
				return next;
			}
			p1 = p1.next;
		}
		return next;
	}
	private ListNode insertionSortList_solution1_getPrevNode(ListNode head, ListNode node) {
		ListNode p1 = head;
		ListNode p2 = head.next;
		while (p2 != node) {
			p1 = p2;
			p2 = p2.next;
		}
		return p1;
	}
	/**
	 *  [Medium]
	 *  #148. Sort List
	 *  Sort a linked list in O(n log n) time using constant space complexity.
	 */
	public ListNode sortList(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return head;
		ListNode mid = sortList_getMiddleNode(head);
		ListNode nhead1 = head;
		ListNode nhead2 = mid.next;
		mid.next = null;
		ListNode h = null;
		if (nhead1 != null && nhead2 != null) {
			ListNode h1 = sortList(nhead1);
			ListNode h2 = sortList(nhead2);
			h = sortList_mergeListNode(h1, h2);
		}
		return h;
    }
	private ListNode sortList_getMiddleNode(ListNode head) {
		if (head == null) return null;
		if (head.next == null) return head;
		ListNode p1 = head;
		ListNode p2 = p1.next;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
		}
		return p1;
	}
	private ListNode sortList_mergeListNode(ListNode a, ListNode b) {
		ListNode h = new ListNode(0);
		ListNode p1 = a;
		ListNode p2 = b;
		ListNode head = h;
		while (p1!= null && p2 != null) {
			if (p1.val < p2.val) {
				h.next = p1;
				p1 = p1.next;
			}
			else {
				h.next = p2;
				p2 = p2.next;
			}
			h = h.next;
		}
		if (p1 != null) h.next = p1;
		if (p2 != null) h.next = p2;
		return head.next;
	}
	
	/**
	 *  [Medium]
	 *  #150. Evaluate Reverse Polish Notation
	 *  
	 *  Evaluate the value of an arithmetic expression in Reverse Polish Notation.
	 *  
	 *  Valid operators are +, -, *, /. Each operand may be an integer or another expression.
	 *  
	 *  Some examples:
	 *    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	 *    ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 */
	public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String element : tokens) {
        	switch (element) {
        		case "+":
        			Integer val1 = stack.pop();
        			Integer val2 = stack.pop();
        			stack.push(val1 + val2);
        			break;
        		case "-":
        			val1 = stack.pop();
        			val2 = stack.pop();
        			stack.push(val2 - val1);
        			break;
        		case "*":
        			val1 = stack.pop();
        			val2 = stack.pop();
        			stack.push(val2 * val1);
        			break;
        		case "/":
        			val1 = stack.pop();
        			val2 = stack.pop();
        			stack.push(val2 / val1);
        			break;
        		default:
        			stack.push(Integer.parseInt(element));
        			break;
        	}
        }
        return stack.pop();
    }
	
	/** [Medium]
	 *  #151. Reverse Words in a String
	 *  Given an input string, reverse the string word by word.
	 *  
	 *  For example,
	 *  Given s = "the sky is blue",
	 *  return "blue is sky the".
	 *  
	 *  Update (2015-02-12):
	 *  For C programmers: Try to solve it in-place in O(1) space.
	 *  
	 *  Clarification:
	 *  What constitutes a word?
	 *  A sequence of non-space characters constitutes a word.
	 *  
	 *  Could the input string contain leading or trailing spaces?
	 *  Yes. However, your reversed string should not contain leading or trailing spaces.
	 *  
	 *  How about multiple spaces between two words?
	 *  Reduce them to a single space in the reversed string.
	 */
	public String reverseWords(String s) {
		return this.reverseWords_solution3(s);
		//return this.reverseWords_solution1(s);
        //return this.reverseWords_solution2(s);
    }
	private String reverseWords_solution3(String s) {
		StringBuilder strBuilder = new StringBuilder();
		StringBuilder wordBuilder = new StringBuilder();
		int length = s.length();
		for (int i = 0; i < length; ++i) {
			char c = s.charAt(i);
			if (c != ' ') {
				wordBuilder.append(c);
			} else if (wordBuilder.length() != 0) {
				wordBuilder.append(' ');
				strBuilder.insert(0,  wordBuilder.toString());
				wordBuilder = new StringBuilder();
			}
		}
		if (wordBuilder.length() != 0) {
			wordBuilder.append(' ');
			strBuilder.insert(0,  wordBuilder.toString());
		}
		return strBuilder.toString().trim();
	}
	private String reverseWords_solution2(String s) {
		StringBuilder strBuilder = new StringBuilder();
		StringBuilder wordBuilder = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c != ' ') {
				wordBuilder.append(c);
			} else if(wordBuilder.length() != 0) {
				strBuilder.insert(0, wordBuilder.toString() + " ");
				wordBuilder = new StringBuilder();
			}
		}
		if (wordBuilder.length() != 0) {
			strBuilder.insert(0, wordBuilder.toString() + " ");
		}
		return strBuilder.toString().trim();
	}
	private String reverseWords_solution1(String s) {
		String[] words = s.split(" ");
		int length = words.length;
		int index = length / 2;
		for (int i = 0; i < index; ++i) {
			String temp = words[i];
			words[i] = words[length - 1 - i];
			words[length - 1 - i] = temp;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			if (!words[i].equals("")) builder.append(words[i] + " ");
		}
		return builder.toString().trim();
	}
	
	
	/**
	 *  [Easy]
	 *  #155. Min Stack
	 *  
	 *  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	 *  push(x) -- Push element x onto stack.
	 *  pop() -- Removes the element on top of the stack.
	 *  top() -- Get the top element.
	 *  getMin() -- Retrieve the minimum element in the stack.
	 *  
	 *  Example:
	 *  MinStack minStack = new MinStack();
	 *  minStack.push(-2);
	 *  minStack.push(0);
	 *  minStack.push(-3);
	 *  minStack.getMin();   --> Returns -3.
	 *  minStack.pop();
	 *  minStack.top();      --> Returns 0.
	 *  minStack.getMin();   --> Returns -2.
	 *  
	 *  Your MinStack object will be instantiated and called as such:
	 *  MinStack obj = new MinStack();
	 *  obj.push(x);
	 *  obj.pop();
	 *  int param_3 = obj.top();
	 *  int param_4 = obj.getMin();
	 */
	public class MinStack {
		public class Entry {
			int val;
			int minVal;
			public Entry(int v, int min) {
				this.val = v;
				this.minVal = min;
			}
		}
		int topIndex = -1;
		List<Entry> array = null;
	    /** initialize your data structure here. */
	    public MinStack() {
	    	topIndex = -1;
	        array = new ArrayList<>();
	    }
	    
	    public void push(int x) {
	        if (array.size() == 0) {
	        	array.add(new Entry(x, x));
	        } else {
	        	int min = array.get(0).minVal;
	        	for (int i = 1; i < array.size(); ++i) {
	        		Entry e = array.get(i);
	        		if (e.minVal < min) {
	        			min = e.minVal;
	        		}
	        	}
	        	array.add(new Entry(x, Math.min(x, min)));
	        }
	        ++topIndex;
	    }
	    
	    public void pop() {
	    	array.remove(topIndex);
	        --topIndex;
	    }
	    
	    public int top() {
	        return array.get(topIndex).val;
	    }
	    
	    public int getMin() {
	        return array.get(topIndex).minVal;
	    }
	}
	
	/**
	 *  [Easy]
	 *  #160. Intersection of Two Linked Lists
	 *  
	 *  Write a program to find the node at which the intersection of two singly linked lists begins.
	 *  
	 *  For example, the following two linked lists:
	 *  A:       a1 -> a2
	 *                  \
	 *                  c1 -> c2 -> c3
	 *                  /            
	 *  B: b1 -> b2 -> b3
	 *  begin to intersect at node c1.
	 *  
	 *  Notes:
	 *  If the two linked lists have no intersection at all, return null.
	 *  The linked lists must retain their original structure after the function returns.
	 *  You may assume there are no cycles anywhere in the entire linked structure.
	 *  Your code should preferably run in O(n) time and use only O(1) memory.
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (!this.getIntersectionNode_hasIntersection(headA, headB)) return null;
		int len1 = this.getIntersectionNode_getLength(headA);
		int len2 = this.getIntersectionNode_getLength(headB);
		
		ListNode list1 = len1 > len2 ? headA : headB;
		ListNode list2 = len1 > len2 ? headB : headA;
		int delta = Math.abs(len1 - len2);
		int i = 0;
		while (i < delta) {
			list1 = list1.next;
			++i;
		}
		while (list1 != list2) {
			list1 = list1.next;
			list2 = list2.next;
		}
		return list1;
    }
	
	public boolean getIntersectionNode_hasIntersection(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) return false;
		ListNode n1 = l1;
		while (n1.next != null) {
			n1 = n1.next;
		}
		ListNode n2 = l2;
		while (n2.next != null) {
			n2 = n2.next;
		}
		return n1 == n2;
	}
	
	public int getIntersectionNode_getLength(ListNode list) {
		int length = 0;
		while (list != null) {
			++length;
			list = list.next;
		}
		return length;
	}
	
	/**
	 *  [Easy]
	 *  #165. Compare Version Numbers
	 *  
	 *  Compare two version numbers version1 and version2.
	 *  If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
	 *  
	 *  You may assume that the version strings are non-empty and contain only digits and the . character.
	 *  The . character does not represent a decimal point and is used to separate number sequences.
	 *  For instance, 2.5 is not "two and a half" or "half way to version three", 
	 *  it is the fifth second-level revision of the second first-level revision.
	 *  Here is an example of version numbers ordering:
	 *  0.1 < 1.1 < 1.2 < 13.37
	 */
	public int compareVersion(String version1, String version2) {
		String[] ver1Strs = version1.split("[.]");
		String[] ver2Strs = version2.split("[.]");
		int i = 0;
		while (i < ver1Strs.length && i < ver2Strs.length) {
			int val1 = Integer.parseInt(ver1Strs[i]);
			int val2 = Integer.parseInt(ver2Strs[i]);
			if (val1 > val2) return 1;
			if (val1 < val2) return -1;
			++i;
		}
		while (i < ver1Strs.length) {
			if (Integer.parseInt(ver1Strs[i++]) != 0) {
				return 1;
			}
		}
		while (i < ver2Strs.length) {
			if (Integer.parseInt(ver2Strs[i++]) != 0) {
				return -1;
			}
		}
        return 0;
    }
	
	/**
	 *  [Easy]
	 *  #168. Excel Sheet Column Title
	 *  
	 *  Given a positive integer, return its corresponding column title as appear in an Excel sheet.
	 *  
	 *  For example:
	 *  1 -> A
	 *  2 -> B
	 *  3 -> C
	 *  ...
	 *  26 -> Z
	 *  27 -> AA
	 *  28 -> AB 
	 */
	public String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		char c = (char) ((n - 1) % 26 + 65);
		sb.append(c);
		int value = n - (c - 64);
		while (value / 26 != 0) {
			value = value / 26;
			c = (char) ((value - 1) % 26 + 65);
			sb.append(c);
			value = value - (c - 64);
		}
		return sb.reverse().toString();
    }
	
	/**
	 *  [Easy]
	 *  #169. Majority Element
	 *  
	 *  Given an array of size n, find the majority element. The majority element is the element that appears more than (n/2) times.
	 *  You may assume that the array is non-empty and the majority element always exist in the array.
	 */
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) {
			if (map.containsKey(nums[i])) {
				int val = map.get(nums[i]);
				map.put(nums[i], ++val);
			} else {
				map.put(nums[i], 1);
			}
		}
		int t = (int) Math.floor(nums.length / 2);
		Set<Integer> set = map.keySet();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			int key = it.next();
			int times = map.get(key);
			if (times > t) {
				return key;
			}
		}
        return 0;
    }
	
	/**
	 *  [Easy]
	 *  #171. Excel Sheet Column Number
	 *  
	 *  Related to question Excel Sheet Column Title
	 *  Given a column title as appear in an Excel sheet, return its corresponding column number.
	 *  
	 *  For example:
	 *  A -> 1
	 *  B -> 2
	 *  C -> 3
	 *  ...
	 *  Z -> 26
	 *  AA -> 27
	 *  AB -> 28 
	 */
	public int titleToNumber(String s) {
		int result = 0;
		int carry = 0;
		int factor = (int) Math.pow(26, carry);
		for (int i = s.length() - 1; i >= 0; --i) {
			result += factor * (int)(s.charAt(i) - 64);
			++carry;
			factor = (int) Math.pow(26, carry);
		}
        return result;
    }
	
	/**
	 *  [Easy]
	 *  #172. Factorial Trailing Zeroes
	 *  
	 *  Given an integer n, return the number of trailing zeroes in n!.
	 *  Note: Your solution should be in logarithmic time complexity.
	 */
	public int trailingZeroes(int n) {
		int result = 0;
		int value = n;
		while (value / 5 != 0) {
			result += value / 5;
			value = value / 5;
		}
        return result;
    }
	
	/**
	 *  [Easy]
	 *  #189. Rotate Array
	 *  
	 *  Rotate an array of n elements to the right by k steps.
	 *  
	 *  For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
	 *  
	 *  Note:
	 *  Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
	 *  
	 *  Hint:
	 *  Could you do it in-place with O(1) extra space?
	 *  Related problem: Reverse Words in a String II
	 */
	public void rotate(int[] nums, int k) {
		if (nums.length == 0 || k == 0) return;
		int length = nums.length;
		int count = 0;
		for (int j = 0; j < k; ++j) {
			int i = j;
			int next = nums[i];
			do {
				i = (i + k) % length;
				int temp = nums[i];
				nums[i] = next;
				next = temp;
				++count;
			} while (i != j);
			if (count == length) break; // important!
		}
    }
	
	/**
	 *  [Easy]
	 *  #190. Reverse Bits
	 *  
	 *  Reverse bits of a given 32 bits unsigned integer.
	 *  
	 *  For example, 
	 *  given input 43261596 (represented in binary as 00000010100101000001111010011100), 
	 *  return 964176192 (represented in binary as 00111001011110000010100101000000).
	 *  
	 *  Follow up:
	 *  If this function is called many times, how would you optimize it?
	 *  
	 *  Related problem: Reverse Integer
	 */
	// you need treat n as an unsigned value
    public int reverseBits(int n) {
    	long value = n & 0x7FFFFFFF;
    	long keyH = 2147483648L;
    	long keyL = 1L;
    	int result = 0;
    	while (value > 0) {
    		int carry = (int) (value / keyH);
    		if (carry > 0) {
    			result += carry * keyL;
    			value -= carry * keyH;
    		}
    		keyH = keyH >> 1;
    		keyL = keyL << 1;
    	}
    	if ((n & 0x80000000) == 0x80000000) ++result;
        return result;
    }
    
    /**
     *  [Easy]
     *  #191. Number of 1 Bits
     *  
     *  Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
     *  For example, the 32-bit integer '11' has binary representation 00000000000000000000000000001011, so the function should return 3.
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
    	int result = 0;
    	if ((n & 0x80000000) == 0x80000000) ++result;
    	long value = n & 0x7FFFFFFF;
    	long key = 2147483648L;
    	while (value > 0) {
    		int carry = (int) (value / key);
    		if (carry > 0) {
    			++result;
    			value -= carry * key;
    		}
    		key = key >> 1;
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #202. Happy Number
     *  
     *  Write an algorithm to determine if a number is "happy".
     *  A happy number is a number defined by the following process: Starting with any positive integer, 
     *  replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 
     *  (where it will stay), or it loops endlessly in a cycle which does not include 1. 
     *  Those numbers for which this process ends in 1 are happy numbers.
     *  
     *  Example: 19 is a happy number
     *  1^2 + 9^2 = 82
     *  8^2 + 2^2 = 68
     *  6^2 + 8^2 = 100
     *  1^2 + 0^2 + 0^2 = 1
     */
    public boolean isHappy(int n) {
    	String input = Integer.toString(n);
    	Set<Integer> set = new HashSet<>();
    	int total = 0;
    	while (total != 1) {
    		if (set.contains(total)) {
    			return false;
    		}
    		set.add(total);
    		total = 0;
        	for (int i = 0; i < input.length(); ++i) {
        		int value = input.charAt(i) - 48;
        		total += value * value;
        	}
        	input = Integer.toString(total);
    	}
        return true;
    }
    
    /**
     *  [Easy]
     *  #203. Remove Linked List Elements
     *  
     *  Remove all elements from a linked list of integers that have value val.
     *  
     *  Example
     *  Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
     *  Return: 1 --> 2 --> 3 --> 4 --> 5
     */
    public ListNode removeElements(ListNode head, int val) {
    	if (head == null) return null;
    	ListNode node = head;
    	while (node != null && node.val == val) {
    		// find the first element the value of which does not equals to val
    		node = node.next;
    	}
    	ListNode prev = node;
    	ListNode curr = null;
    	while (prev != null) {
    		curr = prev.next;
    		while (curr != null && curr.val == val) {
    			curr = curr.next;
    		}
    		prev.next = curr;
    		prev = curr;
    	}
        return node;
    }
    
    /**
     *  [Easy]
     *  #204. Count Primes
     *  
     *  Description:
     *  Count the number of prime numbers less than a non-negative number, n.
     *  
     *  Hint:
     *  1. Let's start with a isPrime function. To determine if a number is prime, 
     *     we need to check if it is not divisible by any number less than n. 
     *     The runtime complexity of isPrime function would be O(n) and hence counting the total prime numbers 
     *     up to n would be O(n2). Could we do better?
     *     
     *  2. As we know the number must not be divisible by any number > n / 2, 
     *     we can immediately cut the total iterations half by dividing only up to n / 2. Could we still do better?
     *     
     *  3. Let's write down all of 12's factors:
     *     2 * 6 = 12
     *     3 * 4 = 12
     *     4 * 3 = 12
     *     6 * 2 = 12
     *     As you can see, calculations of 4 * 3 and 6 * 2 are not necessary. 
     *     Therefore, we only need to consider factors up to sqrt(n) because, if n is divisible by some number p, 
     *     then n = p * q and since p <= q, we could derive that p <= sqrt(n).
     *     Our total runtime has now improved to O(n^1.5), which is slightly better. Is there a faster approach?
     *     
     *     public int countPrimes(int n) {
     *        int count = 0;
     *        for (int i = 1; i < n; i++) {
     *           if (isPrime(i)) count++;
     *        }
     *        return count;
     *     }
     *
     *     private boolean isPrime(int num) {
     *        if (num <= 1) return false;
     *        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
     *        // to avoid repeatedly calling an expensive function sqrt().
     *        for (int i = 2; i * i <= num; i++) {
     *           if (num % i == 0) return false;
     *        }
     *        return true;
     *     }
     *     
     *  4. The Sieve of Eratosthenes is one of the most efficient ways to find all prime numbers up to n. 
     *     But don't let that name scare you, I promise that the concept is surprisingly simple
     *     
     *     Sieve of Eratosthenes: algorithm steps for primes below 121. 
     *     "Sieve of Eratosthenes Animation" by SKopp is licensed under CC BY 2.0.
     *     
     *     We start off with a table of n numbers. Let's look at the first number, 2. We know all multiples of 2 must not be primes, 
     *     so we mark them off as non-primes. Then we look at the next number, 3. Similarly, all multiples of 3 such as 3 x 2 = 6, 
     *     3 x 3 = 9, ... must not be primes, so we mark them off as well. Now we look at the next number, 4, 
     *     which was already marked off. What does this tell you? Should you mark off all multiples of 4 as well?
     *     
     *  5. 4 is not a prime because it is divisible by 2, which means all multiples of 4 must also be divisible by 2 
     *     and were already marked off. So we can skip 4 immediately and go to the next number, 5. 
     *     Now, all multiples of 5 such as 5 x 2 = 10, 5 x 3 = 15, 5 x 4 = 20, 5 x 5 = 25, ... can be marked off. 
     *     There is a slight optimization here, we do not need to start from 5 x 2 = 10. Where should we start marking off?
     *     
     *  6. In fact, we can mark off multiples of 5 starting at 5 x 5 = 25, because 5 x 2 = 10 was already marked off by multiple of 2, 
     *     similarly 5 x 3 = 15 was already marked off by multiple of 3. Therefore, if the current number is p, 
     *     we can always mark off multiples of p starting at p2, then in increments of p: p2 + p, p2 + 2p, ... 
     *     Now what should be the terminating loop condition?
     *     
     *  7. It is easy to say that the terminating loop condition is p < n, which is certainly correct but not efficient. 
     *     Do you still remember Hint #3?
     *     
     *  8. Yes, the terminating loop condition can be p < sqrt(n), as all non-primes >= sqrt(n) must have already been marked off. 
     *     When the loop terminates, all the numbers in the table that are non-marked are prime.
     *     The Sieve of Eratosthenes uses an extra O(n) memory and its runtime complexity is O(n log log n). 
     *     For the more mathematically inclined readers, you can read more about its algorithm complexity on Wikipedia.
     *     
     *     public int countPrimes(int n) {
     *        boolean[] isPrime = new boolean[n];
     *        for (int i = 2; i < n; i++) {
     *           isPrime[i] = true;
     *        }
     *        // Loop's ending condition is i * i < n instead of i < sqrt(n)
     *        // to avoid repeatedly calling an expensive function sqrt().
     *        for (int i = 2; i * i < n; i++) {
     *           if (!isPrime[i]) continue;
     *           for (int j = i * i; j < n; j += i) {
     *              isPrime[j] = false;
     *           }
     *        }
     *        int count = 0;
     *        for (int i = 2; i < n; i++) {
     *           if (isPrime[i]) count++;
     *        }
     *        return count;
     *     }
     */
//    public List<Integer> countPrimes_list = new ArrayList<>();
//    public int countPrimes(int n) {
//    	if (countPrimes_list.size() == 0) {
//    		countPrimes_list.add(0);
//    	}
//    	if (n >= countPrimes_list.size()) {
//    		for (int i = countPrimes_list.size(); i < n + 1; ++i) {
//    			int count = countPrimes_list.get(i - 1);
//    			if (this.countPrimes_isPrime(i - 1)) {
//    				countPrimes_list.add(count + 1);
//    			} else {
//    				countPrimes_list.add(count);
//    			}
//    		}
//    	}
//    	if (n < countPrimes_list.size()) {
//    		return countPrimes_list.get(n);
//    	}
//        return 0;
//    }
//    public boolean countPrimes_isPrime(int n) {
//    	if (n == 0 || n == 1) return false;
//    	for (int i = 2; i * i <= n; ++i) {
//    		if (n % i == 0) return false;
//    	}
//    	return true;
//    }
    public int countPrimes(int n) {
    	boolean[] nums = new boolean[n];
    	for (int i = 2; i < n; ++i) {
    		nums[i] = true;
    	}
    	int p = 2;
    	while (p * p < n) {
    		if (nums[p]) {
    			int i = p * p;
            	while (i < n) {
            		nums[i] = false;
            		i += p;
            	}
    		}
        	++p;
    	}
    	int count = 0;
    	for (int j = 0; j < n; ++j) {
    		if (nums[j]) ++count;
    	}
    	return count;
    }
    
    /**
     *  [Easy]
     *  #205. Isomorphic Strings
     *  
     *  Given two strings s and t, determine if they are isomorphic.
     *  Two strings are isomorphic if the characters in s can be replaced to get t.
     *  All occurrences of a character must be replaced with another character while preserving the order of characters. 
     *  No two characters may map to the same character but a character may map to itself.
     *  
     *  For example,
     *  Given "egg", "add", return true.
     *  Given "foo", "bar", return false.
     *  Given "paper", "title", return true.
     *  
     *  Note:
     *  You may assume both s and t have the same length.
     */
    public boolean isIsomorphic(String s, String t) {
        return isIsomorphic_two(s, t) && isIsomorphic_two(t, s);
    }
    public boolean isIsomorphic_two(String str1, String str2) {
    	Map<Character, Character> map = new HashMap<>();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < str1.length(); ++i) {
    		char c1 = str1.charAt(i);
    		if (!map.containsKey(c1)) {
    			char c2 = str2.charAt(i);
    			map.put(c1, c2);
    			sb.append(c2);
    		} else {
    			sb.append(map.get(c1));
    		}
    	}
    	return sb.toString().equals(str2);
    }
    
    /**
     *  [Easy]
     *  #206. Reverse Linked List
     *  
     *  Reverse a singly linked list.
     *  
     *  Hint:
     *  A linked list can be reversed either iteratively or recursively. Could you implement both?
     */
    public ListNode reverseList(ListNode head) {
    	//return reverseList_recursive(head);
    	return reverseList_interative(head);
    }
    public ListNode reverseList_recursive(ListNode node) {
    	if (node == null || node.next == null) {
    		return node;
    	} else {
    		ListNode tail = node.next;
    		ListNode head = reverseList(node.next);
    		tail.next = node;
    		node.next = null;
    		return head;
    	}
    }
    public ListNode reverseList_interative(ListNode node) {
    	ListNode tail = node;
    	while (tail != null && tail.next != null) {
    		tail = tail.next;
    	}
    	ListNode head = tail;
    	
    	ListNode tailNew = head;
    	while (node != null && node.next != null) {
    		ListNode tailOld = node;
    		while (tailOld.next != tailNew) {
    			tailOld = tailOld.next;
    		}
    		tailNew.next = tailOld;
    		tailNew = tailOld;
    		tailNew.next = null;
    	}
    	return head;
    }
    
    /**
     *  [Easy]
     *  #217. Contains Duplicate
     *  
     *  Given an array of integers, find if the array contains any duplicates. 
     *  Your function should return true if any value appears at least twice in the array, 
     *  and it should return false if every element is distinct.
     */
    public boolean containsDuplicate(int[] nums) {
    	Set<Integer> set = new HashSet<>();
    	if (nums == null) return false;
    	for (int i = 0; i < nums.length; ++i) {
    		if (set.contains(nums[i])) {
    			return true;
    		}
    		else {
    			set.add(nums[i]);
    		}
    	}
        return false;
    }
    
    /**
     *  [Easy]
     *  #219. Contains Duplicate II
     *  
     *  Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
     *  such that nums[i] = nums[j] and the difference between i and j is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	Map<Integer, Integer> map = new HashMap<>();
    	if (nums == null) return false;
    	for (int i = 0; i < nums.length; ++i) {
    		if (map.containsKey(nums[i])) {
    			int j = map.get(nums[i]);
    			if (Math.abs(j - i) <= k) {
    				return true;
    			}
    			else {
    				map.put(nums[i], i);
    			}
    		} else {
    			map.put(nums[i], i);
    		}
    	}
        return false;
    }
    
    /**
     *  [Easy]
     *  #223. Rectangle Area
     *  
     *  Find the total area covered by two rectilinear rectangles in a 2D plane.
     *  Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
     *  
     *  Assume that the total area is never beyond the maximum possible value of int.
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	//if (E > C || G < A || F > D || H < B) return 0;
    	if (A == C || B == D) return computeArea_two(E, F, G, H);
    	if (E == G || F == H) return computeArea_two(A, B, C, D);
    	
    	int area1 = computeArea_two(A, B, C, D);
    	int area2 = computeArea_two(E, F, G, H);
    	if (E > C || G < A || F > D || H < B) {
    		return area1 + area2;
    	}
    	
		int x1 = A;
    	if (A <= E && E <= C) x1 = E;
    	int x2 = C;
    	if (A <= G && G <= C) x2 = G;
    	
    	int y1 = B;
    	if (B <= F && F <= D) y1 = F;
    	int y2 = D;
    	if (B <= H && H <= D) y2 = H;
    	
    	int comm = computeArea_two(x1, y1, x2, y2);
        return area1 + area2 - comm;
    }
    public int computeArea_two(int x1, int y1, int x2, int y2) {
    	return Math.abs(x1 - x2) * Math.abs(y1 - y2);
    }
    
    /**
     *  [Easy]
     *  #225. Implement Stack using Queues
     *  Implement the following operations of a stack using queues.
     *    push(x) -- Push element x onto stack.
     *    pop() -- Removes the element on top of the stack.
     *    top() -- Get the top element.
     *    empty() -- Return whether the stack is empty.
     *    
     *  Notes:
     *  You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
     *  Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
     *  You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
     *  
     *  Update (2015-06-11):
     *  The class name of the Java function had been updated to MyStack instead of Stack.
     */
    class MyStack {
    	Deque<Integer> deque = new ArrayDeque<>();
        // Push element x onto stack.
        public void push(int x) {
            deque.addLast(x);
        }

        // Removes the element on top of the stack.
        public void pop() {
            deque.pollLast();
        }

        // Get the top element.
        public int top() {
            return deque.peekLast();
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return deque.isEmpty();
        }
    }
    
    /**
     *  [Easy]
     *  #226. Invert Binary Tree
     *  Invert a binary tree.
     *  
     *       4
     *     /   \
     *    2     7
     *   / \   / \
     *  1   3 6   9
     *  
     *  to
     *       4
     *     /   \
     *    7     2
     *   / \   / \
     *  9   6 3   1
     */
    public TreeNode invertTree(TreeNode root) {
    	if (root == null) return root;
    	Deque<TreeNode> queue = new ArrayDeque<>();
    	queue.addLast(root);
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.pollFirst();
    		TreeNode temp = node.left;
    		node.left = node.right;
    		node.right = temp;
    		if (node.left != null) queue.addLast(node.left);
    		if (node.right != null) queue.addLast(node.right);
    	}
        return root;
    }
    
    /**
     *  [Easy]
     *  #231. Power of Two
     *  
     *  Given an integer, write a function to determine if it is a power of two.
     */
    public boolean isPowerOfTwo(int n) {
    	if (n == 0) return false;
        int value = n;
        while (value % 2 == 0) {
        	value = value / 2;
        }
        return value == 1;
    }
    
    /** 
     *  [Easy]
     *  #232. Implement Queue using Stacks
     *  
     *  Implement the following operations of a queue using stacks.
     *    push(x) -- Push element x to the back of queue.
     *    pop() -- Removes the element from in front of queue.
     *    peek() -- Get the front element.
     *    empty() -- Return whether the queue is empty.
     *  
     *  Notes:
     *  You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
     *  Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
     *  You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
     */
    class MyQueue {
        // Push element x to the back of queue.
    	Stack<Integer> stackIn = new Stack<>();
    	Stack<Integer> stackOut = new Stack<>();
        public void push(int x) {
            while (stackOut.size() != 0) {
            	stackIn.push(stackOut.pop());
            }
            stackIn.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            while (stackIn.size() != 0) {
            	stackOut.push(stackIn.pop());
            }
            stackOut.pop();
        }

        // Get the front element.
        public int peek() {
        	while (stackIn.size() != 0) {
        		stackOut.push(stackIn.pop());
        	}
            return stackOut.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }
    }
    
    /**
     *  [Easy]
     *  #234. Palindrome Linked List
     *  
     *  Given a singly linked list, determine if it is a palindrome.
     *  
     *  Follow up:
     *  Could you do it in O(n) time and O(1) space?
     */
    public boolean isPalindrome(ListNode head) {
    	//return isPalindrome_On_On(head);
    	return isPalindrome_On_O1(head);
    }
    // O(n) time and O(n) space
    public boolean isPalindrome_On_On(ListNode head) {
    	int length = 0;
    	ListNode node = head;
    	while (node != null) {
    		++length;
    		node = node.next;
    	}
    	int[] nums = new int[length];
    	node = head;
    	int j = 0;
    	while (node != null) {
    		nums[j++] = node.val;
    		node = node.next;
    	}
    	for (int i = 0; i < length / 2; ++i) {
    		if (nums[i] != nums[length - 1 - i]) {
    			return false;
    		}
    	}
        return true;
    }
    // O(n) time and O(1) space
    public boolean isPalindrome_On_O1(ListNode head) {
    	int length = 0;
    	ListNode node = head;
    	while (node != null) {
    		++length;
    		node = node.next;
    	}
    	int index2 = (int) Math.ceil(length / 2d);
    	ListNode head2 = head;
    	for (int i = 0; i < index2; ++i) {
    		head2 = head2.next;
    	}
    	if (head2 != null) {
    		head2 = isPalindrome_reverseList(head2);
        	for (int j = 0; j < length / 2; ++j) {
        		if (head.val != head2.val) return false;
        		head = head.next;
        		head2 = head2.next;
        	}
    	}
    	return true;
    }
    public ListNode isPalindrome_reverseList(ListNode head) {
    	if (head.next == null) {
    		return head;
    	} else {
    		ListNode tail = head.next;
    		ListNode newHead = isPalindrome_reverseList(head.next);
    		tail.next = head;
    		head.next = null;
    		return newHead;
    	}
    }
    
    /**
     *  [Easy]
     *  #235. Lowest Common Ancestor of a Binary Search Tree
     *  
     *  Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
     *  According to the definition of LCA on Wikipedia: "The lowest common ancestor is defined between two nodes v and w 
     *  as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself)."
     *  
     *          _______6______
     *         /              \
     *      ___2__          ___8__
     *     /      \        /      \
     *    0        4       7       9
     *           /  \
     *          3    5
     *           
     *  For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, 
     *  since a node can be a descendant of itself according to the LCA definition.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	List<TreeNode> path1 = new ArrayList<>();
    	List<TreeNode> path2 = new ArrayList<>();
    	lowestCommonAncestor(path1, root, p);
    	lowestCommonAncestor(path2, root, q);
    	//Utility.print(path1);
    	//Utility.print(path2);
    	for (int i = path1.size() - 1; i >= 0; --i) {
    		for (int j = path2.size() - 1; j >= 0; --j) {
    			if (path1.get(i) == path2.get(j)) {
    				return path1.get(i);
    			}
    		}
    	}
        return null;
    }
    // return true if found.
    public boolean lowestCommonAncestor(List<TreeNode> path, TreeNode node, TreeNode target) {
    	if (node == null) {
    		return false;
    	} else if (node == target) {
    		path.add(node);
    		return true;
    	}else {
    		path.add(node);
    		if (lowestCommonAncestor(path, node.left, target)) return true;
    		if (lowestCommonAncestor(path, node.right, target)) return true;
    		path.remove(node);
    		return false;
    	}
    }
    
    /**
     *  [Easy]
     *  #237. Delete Node in a Linked List
     *  
     *  Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
     *  Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
     *  the linked list should become 1 -> 2 -> 4 after calling your function.
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    
    /**
     *  [Easy]
     *  #242. Valid Anagram
     *  
     *  Given two strings s and t, write a function to determine if t is an anagram of s.
     *  
     *  For example,
     *  s = "anagram", t = "nagaram", return true.
     *  s = "rat", t = "car", return false.
     *  
     *  Note:
     *  You may assume the string contains only lowercase alphabets.
     */
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
        	char c = s.charAt(i);
        	if (!map.containsKey(c)) {
        		map.put(c, 1);
        	} else {
        		int v = map.get(c);
        		map.put(c, ++v);
        	}
        }
        for (int j = 0; j < t.length(); ++j) {
        	char c = t.charAt(j);
        	if (!map.containsKey(c)) {
        		return false;
        	} else {
        		int v = map.get(c);
        		map.put(c, --v);
        	}
        }
        Iterator<Character> it = map.keySet().iterator();
        while (it.hasNext()) {
        	if (map.get(it.next()) != 0) {
        		return false;
        	}
        }
        return true;
    }
    
    /**
     *  [Easy]
     *  #257. Binary Tree Paths
     *  
     *  Given a binary tree, return all root-to-leaf paths.
     *  For example, given the following binary tree:
     *  
     *     1
     *   /   \
     *  2     3
     *   \
     *    5
     *  All root-to-leaf paths are:
     *  ["1->2->5", "1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> result = new ArrayList<>();
    	if (root != null) {
    		this.binaryTreePaths(result, "" + root.val, root);
    	}
        return result;
    }
    
    public void binaryTreePaths(List<String> result, String path, TreeNode node) {
    	if (node.left == null && node.right == null) {
    		result.add(path);
    		return;
    	} else {
    		if (node.left != null) binaryTreePaths(result, path + "->" + node.left.val, node.left);
    		if (node.right != null) binaryTreePaths(result, path + "->" + node.right.val, node.right);
    	}
    }
    
    /**
     *  [Easy]
     *  #258. Add Digits
     *  
     *  Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
     *  
     *  For example:
     *  Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
     *  
     *  Follow up:
     *  Could you do it without any loop/recursion in O(1) runtime?
     */
    public int addDigits(int num) {
    	if (num == 0) return 0;
    	int result = num % 9;
        return result == 0 ? 9 : result;
    }

	/**
	 *  [Medium]
	 *  #260. Single Number III
	 *
	 *  Given an array of numbers nums, in which exactly two elements appear only once and
	 *  all the other elements appear exactly twice. Find the two elements that appear only once.
	 *  For example:
	 *  Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
	 *  Note:
	 *  1. The order of the result is not important. So in the above example, [5, 3] is also correct.
	 *  2. Your algorithm should run in linear runtime complexity.
	 *     Could you implement it using only constant space complexity?
	 */
	public int[] singleNumberIII(int[] nums) {
		int[] result = new int[2];
		int diff = 0;
		for (int num : nums) {
			diff ^= num;
		}
		diff &= -diff;  // 找出a和b最后一位是1还是0
		for (int num : nums) {
			if ((num & diff) == 0) {
				result[0] ^= num;
			} else {
				result[1] ^= num;
			}
		}
		return result;
	}

	/**
     *  [Easy]
     *  #263. Ugly Number
     *  
     *  Write a program to check whether a given number is an ugly number.
     *  Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
     *  For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
     *  Note that 1 is typically treated as an ugly number.
     */
    public boolean isUgly(int num) {
    	if (num == 0) return false;
    	int val = num;
    	int[] factors = new int[] {2, 3, 5};
    	while (val != 1) {
    		int i = 0;
    		for (; i < factors.length; ++i) {
    			if (val % factors[i] == 0) {
    				val = val / factors[i];
    				break;
    			}
    		}
    		if (i >= factors.length) return false;
    	}
        return true;
    }
    
    /**
     *  [Easy]
     *  #278. First Bad Version
     *  
     *  You are a product manager and currently leading a team to develop a new product. 
     *  Unfortunately, the latest version of your product fails the quality check. 
     *  Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     *  Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
     *  which causes all the following ones to be bad.
     *  You are given an API bool isBadVersion(version) which will return whether version is bad. 
     *  Implement a function to find the first bad version. You should minimize the number of calls to the API.
     */
    public int firstBadVer = 0;
    public int firstBadVersion(int n) {
    	long goodIndex = 1;
    	long badIndex  = n;
    	while (badIndex - goodIndex > 1 ) {
    		int checkIndex = (int) ((goodIndex + badIndex ) / 2);
    		if (isBadVersion(checkIndex)) badIndex = checkIndex;
    		else goodIndex = checkIndex;
    	}
        return (int) (isBadVersion((int)goodIndex) ? goodIndex : badIndex);
    }
    
    public boolean isBadVersion(int version) {
    	return version >= firstBadVer;
    }
    
    /**
     *  [Easy]
     *  #283. Move Zeroes
     *  
     *  Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     *  For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
     *  
     *  Note:
     *  1. You must do this in-place without making a copy of the array.
     *  2. Minimize the total number of operations.
     */
    public void moveZeroes(int[] nums) {
        int i = 0;
        while (i < nums.length) {
        	while (i < nums.length && nums[i] != 0) ++i;
 	        int j = i + 1;
 	        while (j < nums.length && nums[j] == 0) ++j;
 	        if (j >= nums.length) break;
        	int temp = nums[i];
 	        nums[i] = nums[j];
 	        nums[j] = temp;
        }
    }
    
    /**
     *  [Easy]
     *  #290. Word Pattern
     *  
     *  Given a pattern and a string str, find if str follows the same pattern.
     *  Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
     *  
     *  Examples:
     *  pattern = "abba", str = "dog cat cat dog" should return true.
     *  pattern = "abba", str = "dog cat cat fish" should return false.
     *  pattern = "aaaa", str = "dog cat cat dog" should return false.
     *  pattern = "abba", str = "dog dog dog dog" should return false.
     *  
     *  Notes:
     *  You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
     */
    public boolean wordPattern(String pattern, String str) {
    	String[] strs = str.split(" ");
    	if (pattern.length() != strs.length) return false;
    	Map<Character, String> map1 = new HashMap<>();
    	for (int i = 0; i < pattern.length(); ++i) {
    		if (!map1.containsKey(pattern.charAt(i))) {
    			map1.put(pattern.charAt(i), strs[i]);
    		} else {
    			String s = map1.get(pattern.charAt(i));
    			if (!s.equals(strs[i])) return false;
    		}
    	}
    	Map<String, Character> map2 = new HashMap<>();
    	for (int j = 0; j < pattern.length(); ++j) {
    		if (!map2.containsKey(strs[j])) {
    			map2.put(strs[j], pattern.charAt(j));
    		} else {
    			char c = map2.get(strs[j]);
    			if (c != pattern.charAt(j)) return false;
    		}
    	}
        return true;
    }
    
    /**
     *  [Easy]
     *  #292. Nim Game
     *  
     *  You are playing the following Nim Game with your friend: There is a heap of stones on the table, 
     *  each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. 
     *  You will take the first turn to remove the stones.
     *  Both of you are very clever and have optimal strategies for the game. 
     *  Write a function to determine whether you can win the game given the number of stones in the heap.
     *  
     *  For example, if there are 4 stones in the heap, then you will never win the game: 
     *  no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
    
    /**
     *  [Easy]
     *  #299. Bulls and Cows
     *  
     *  You are playing the following Bulls and Cows game with your friend: You write down a number 
     *  and ask your friend to guess what the number is. Each time your friend makes a guess, 
     *  you provide a hint that indicates how many digits in said guess match your secret number 
     *  exactly in both digit and position (called "bulls") and how many digits match the secret number 
     *  but locate in the wrong position (called "cows"). 
     *  Your friend will use successive guesses and hints to eventually derive the secret number.
     *  
     *  For example:
     *  
     *  Secret number:  "1807"
     *  Friend's guess: "7810"
     *  Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
     *  Write a function to return a hint according to the secret number and friend's guess, 
     *  use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
     *  Please note that both secret number and friend's guess may contain duplicate digits, for example:
     *  
     *  Secret number:  "1123"
     *  Friend's guess: "0111"
     *  In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
     *  You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
     */
    public String getHint(String secret, String guess) {
    	int bulls = 0;
    	int cows = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); ++i) {
        	if (secret.charAt(i) == guess.charAt(i)) {
        		++bulls;
        	}
        }
        for (int i = 0; i < secret.length(); ++i) {
        	char s = secret.charAt(i);
        	char g = guess.charAt(i);
        	if (s != g) {
        		if (!map.containsKey(s)) {
        			map.put(s, 1);
        		} else {
        			int v = map.get(s);
        			++v;
        			map.put(s, v);
        		}
        	}
        }
        for (int i = 0; i < guess.length(); ++i) {
        	char s = secret.charAt(i);
        	char g = guess.charAt(i);
        	if (s != g) {
        		if (map.containsKey(g)) {
        			++cows;
        			int v = map.get(g);
        			--v;
        			if (v == 0) {
        				map.remove(g);
        			} else {
        				map.put(g, v);
        			}
        		}
        	}
        }
    	return String.format("%dA%dB", bulls, cows);
    }
    
    /**
     *  [Easy]
     *  #303. Range Sum Query - Immutable
     *  Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
     *  
     *  Example:
     *  Given nums = [-2, 0, 3, -5, 2, -1]
     *  sumRange(0, 2) -> 1
     *  sumRange(2, 5) -> -1
     *  sumRange(0, 5) -> -3
     *  Note:
     *  You may assume that the array does not change.
     *  There are many calls to sumRange function.
     */
    public class NumArray {
    	int[] sum = null;
        public NumArray(int[] nums) {
            final int length = nums.length;
            sum = new int[length + 1];
            sum[0] = 0;
            for (int i = 1; i < length + 1; ++i) {
            	sum[i] = sum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }
    // Your NumArray object will be instantiated and called as such:
    // NumArray numArray = new NumArray(nums);
    // numArray.sumRange(0, 1);
    // numArray.sumRange(1, 2);
    
    /**
     *  [Easy]
     *  #326. Power of Three
     *  
     *  Given an integer, write a function to determine if it is a power of three.
     *  Follow up:
     *  Could you do it without using any loop / recursion?
     */
    public boolean isPowerOfThree(int n) {
        return this.isPowerOfThree_solution3(n);
    }
    public boolean isPowerOfThree_solution3(int n) {  // no loop or recursion
    	// the max value which is power of three is 1162261467
    	return n > 0 && 1162261467 % n == 0;
    }
    public boolean isPowerOfThree_solution2(int n) {  // Accepted
    	if (n == 0) return false;
    	int val = n;
    	while (val % 3 == 0) {
    		val = val / 3;
    	}
    	if (val == 1) return true;
    	return false;
    }
    public boolean isPowerOfThree_solution1(int n) { // Time Limit Exceeded
    	int factor = 1;
    	while (factor <= n) {
    		if (factor == n) return true;
    		factor *= 3;
    	}
    	return false;
    }
    
    /**
     *  [Medium]
     *  #338. Counting Bits
     *  Given a non negative integer number num. 
     *  For every numbers i in the range 0 <= i <= num calculate the number of 1's in their binary representation and return them as an array.
     *  
     *  Example:
     *  For num = 5 you should return [0,1,1,2,1,2].
     *  
     *  Follow up:
     *  
     *  It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
     *  But can you do it in linear time O(n) /possibly in a single pass?
     *  Space complexity should be O(n).
     *  Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
     *  
     *  Hint:
     *  
     *  You should make use of what you have produced already.
     *  Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous.
     *  Or does the odd/even status of the number help you in calculating the number of 1s?
     */
    public int[] countBits(int num) {
    	if (num == 0) return new int[] {0};
    	if (num == 1) return new int[] {0,1};
    	int[] result = new int[num + 1];
    	result[0] = 0;
    	result[1] = 1;
    	for (int i = 2; i <= num; ++i) {
    		int factor = 1;
    		while (factor <= i) {
    			factor = factor << 1;
    		}
    		factor = factor >> 1;
    		if (factor == i) {
    			result[i] = 1;
    			continue;
    		}
    		int left = i;
    		while (left > 0) {
    			if (left >= factor) {
    				result[i] += result[factor];
        			left -= factor;
    			}
    			factor = factor >> 1;
    		}
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #344. Reverse String
     *  
     *  Write a function that takes a string as input and returns the string reversed.
     *  
     *  Example:
     *  Given s = "hello", return "olleh".
     */
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
        	sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    
    /**
     *  [Easy]
     *  #345. Reverse Vowels of a String
     *  
     *  Write a function that takes a string as input and reverse only the vowels of a string.
     *  
     *  Example 1:
     *  Given s = "hello", return "holle".
     *  
     *  Example 2:
     *  Given s = "leetcode", return "leotcede".
     *  
     *  Note:
     *  The vowels does not include the letter "y".
     */
    public String reverseVowels(String s) {
    	Set<Character> vowels = new HashSet<>();
    	vowels.add('a');
    	vowels.add('e');
    	vowels.add('i');
    	vowels.add('o');
    	vowels.add('u');
    	vowels.add('A');
    	vowels.add('E');
    	vowels.add('I');
    	vowels.add('O');
    	vowels.add('U');
    	char[] cs = s.toCharArray();
    	int i = 0;
    	int j = s.length() - 1;
    	while (i < j) {
    		while (i < j && !vowels.contains(cs[i])) ++i;
    		while (i < j && !vowels.contains(cs[j])) --j;
    		char temp = cs[i];
    		cs[i] = cs[j];
    		cs[j] = temp;
    		++i;
    		--j;
    	}
    	return new String(cs);
    }
    
    /**
     *  [Easy]
     *  #349. Intersection of Two Arrays
     *  
     *  Given two arrays, write a function to compute their intersection.
     *  
     *  Example:
     *  Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
     *  
     *  Note:
     *  Each element in the result must be unique.
     *  The result can be in any order.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
    	Set<Integer> set1 = new HashSet<>();
    	for (int i = 0; i < nums1.length; ++i) {
    		set1.add(nums1[i]);
    	}
    	Set<Integer> resSet = new HashSet<>();
    	for (int j = 0; j < nums2.length; ++j) {
    		if (set1.contains(nums2[j])) {
    			resSet.add(nums2[j]);
    		}
    	}
    	int[] result = new int[resSet.size()];
    	int k = 0;
    	Iterator<Integer> it = resSet.iterator();
    	while (it.hasNext()) {
    		result[k++] = (int)it.next();
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #350. Intersection of Two Arrays II
     *  
     *  Given two arrays, write a function to compute their intersection.
     *  
     *  Example:
     *  Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
     *  
     *  Note:
     *  Each element in the result should appear as many times as it shows in both arrays.
     *  The result can be in any order.
     *  Follow up:
     *   - What if the given array is already sorted? How would you optimize your algorithm?
     *   - What if nums1's size is small compared to nums2's size? Which algorithm is better?
     *   - What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
     */
    public int[] intersect(int[] nums1, int[] nums2) {
    	Map<Integer, Integer> map1 = new HashMap<>();
    	for (int i = 0; i < nums1.length; ++i) {
    		int num = nums1[i];
    		if (!map1.containsKey(num)) {
    			map1.put(num, 1);
    		} else {
    			int val = map1.get(num);
    			map1.put(num, ++val);
    		}
    	}
    	List<Integer> resList = new ArrayList<>();
    	for (int j = 0; j < nums2.length; ++j) {
    		int num = nums2[j];
    		if (map1.containsKey(num)) {
    			resList.add(num);
    			int val = map1.get(num);
    			if (--val == 0) {
    				map1.remove(num);
    			} else {
    				map1.put(num, val);
    			}
    		}
    	}
    	int[] result = new int[resList.size()];
    	for (int k = 0; k < resList.size(); ++k) {
    		result[k] = resList.get(k);
     	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #371. Sum of Two Integers
     *  
     *  Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
     *  
     *  Example:
     *  Given a = 1 and b = 2, return 3.
     */
    public int getSum(int a, int b) {
    	int val1 = a;
    	int val2 = b;
    	int v1 = (val1 & val2) << 1;
    	int v2 = val1 ^ val2;
    	while ((v1 & v2) != 0) {
    		val1 = v1;
    		val2 = v2;
    		v1 = (val1 & val2) << 1;
    		v2 = val1 ^ val2;
    	}
        return v1 | v2;
    }
    
    /**
     *  [Easy]
     *  #374. Guess Number Higher or Lower
     *  We are playing the Guess Game. The game is as follows:
     *  I pick a number from 1 to n. You have to guess which number I picked.
     *  Every time you guess wrong, I'll tell you whether the number is higher or lower.
     *  You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
     *    -1 : My number is lower
     *     1 : My number is higher
     *     0 : Congrats! You got it!
     *  
     *  Example:
     *  n = 10, I pick 6.
     *  Return 6.
     */
    public int guessNumber(int n) {
    	long low = 1;
    	long high = n;
    	while (low < high && (high - low) > 1) {
    		long middle = (low + high) / 2;
    		int res = guess((int)middle);
    		if (res == 0) return (int)middle;
    		else if (res == -1) high = middle;
    		else if (res == 1) low = middle;
    	}
        return (int) (guess((int)low) == 0L ? low : high);
    }
    /* The guess API is defined in the parent class GuessGame.
     * @param num, your guess
     * @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
     * int guess(int num); */
    public int guessNumber_i_pick = 0;
    public int guess(int num) {
    	if (num < this.guessNumber_i_pick) return 1;
    	else if (num > this.guessNumber_i_pick) return -1;
    	else return 0;
    }
    
    /**
     *  [Easy]
     *  #383. Ransom Note
     *  
     *  Given an arbitrary ransom note string and another string containing letters from all the magazines, 
     *  write a function that will return true if the ransom note can be constructed from the magazines ; 
     *  otherwise, it will return false.Each letter in the magazine string can only be used once in your ransom note.
     *  
     *  Note:
     *  You may assume that both strings contain only lowercase letters.
     *  
     *  canConstruct("a", "b") -> false
     *  canConstruct("aa", "ab") -> false
     *  canConstruct("aa", "aab") -> true
     */
    public boolean canConstruct(String ransomNote, String magazine) {
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i < magazine.length(); ++i) {
    		char c = magazine.charAt(i);
    		if (!map.containsKey(c)) {
    			map.put(c, 1);
    		} else {
    			int val = map.get(c);
    			map.put(c, ++val);
    		}
    	}
    	for (int j = 0; j < ransomNote.length(); ++j) {
    		char r = ransomNote.charAt(j);
    		if (!map.containsKey(r)) return false;
    		else {
    			int val = map.get(r);
    			if (--val <= 0) {
    				map.remove(r);
    			} else {
    				map.put(r, val);
    			}
    		}
    	}
        return true;
    }
    
    /**
     *  [Easy]
     *  #387. First Unique Character in a String
     *  
     *  Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     *  
     *  Examples:
     *  s = "leetcode"
     *  return 0.
     *  
     *  s = "loveleetcode",
     *  return 2.
     *  Note: You may assume the string contain only lowercase letters.
     */
    public int firstUniqChar(String s) {
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i < s.length(); ++i) {
    		char c = s.charAt(i);
    		if (!map.containsKey(c)) {
    			map.put(c, 1);
    		} else {
    			int val = map.get(c);
    			map.put(c, ++val);
    		}
    	}
    	for (int j = 0; j < s.length(); ++j) {
    		char c = s.charAt(j);
    		int val = map.get(c);
    		if (val == 1) return j;
    	}
        return -1;
    }
    
    /**
     *  [Easy]
     *  #389. Find the Difference
     *  
     *  Given two strings s and t which consist of only lowercase letters.
     *  String t is generated by random shuffling string s and then add one more letter at a random position.
     *  Find the letter that was added in t.
     *  
     *  Example:
     *  Input:
     *  s = "abcd"
     *  t = "abcde"
     *  
     *  Output:
     *  e
     *  
     *  Explanation:
     *  'e' is the letter that was added.
     */
    public char findTheDifference(String s, String t) {
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i < s.length(); ++i) {
    		char c = s.charAt(i);
    		if (!map.containsKey(c)) {
    			map.put(c, 1);
    		} else {
    			int val = map.get(c);
    			map.put(c, ++val);
    		}
    	}
    	for (int j = 0; j < t.length(); ++j) {
    		char r = t.charAt(j);
    		if (!map.containsKey(r)) return r;
    		else {
    			int val = map.get(r);
    			if (--val <= 0) {
    				map.remove(r);
    			} else {
    				map.put(r, val);
    			}
    		}
    	}
        return ' ';
    }
    
    /**
     *  [Easy]
     *  #396. Rotate Function
     *  
     *  Given an array of integers A and let n to be its length.
     *  Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:
     *  F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
     *  Calculate the maximum value of F(0), F(1), ..., F(n-1).
     *  Note:
     *  n is guaranteed to be less than 10^5.
     *  
     *  Example:
     *  A = [4, 3, 2, 6]
     *  
     *  F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     *  F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     *  F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     *  F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     *  
     *  So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
     */
    public int maxRotateFunction(int[] A) {
    	/*
    	 * F(0) = 0 * A[0] + 1 * A[1] + 2 * A[2] + 3 * A[3]
    	 * F(1) = 1 * A[0] + 2 * A[1] + 3 * A[2] + 0 * A[3] = F(0) + ALL - 4 * A[3]
    	 * F(2) = 2 * A[0] + 3 * A[1] + 0 * A[2] + 1 * A[3] = F(0) + 2All - 4 * A[2] - 4 * A[3]
    	 *                                                  = F(1) + ALL - 4 * A[2]
    	 * F(3) = 3 * A[0] + 0 * A[1] + 1 * A[2] + 2 * A[3] = F(0) + 3ALL - 4 * A[1] - 4 * A[2] - 4 * A[3]
    	 *                                                  = F(2) + ALL - 4 * A[1]
    	 */
    	if (A == null || A.length == 0) return 0;
    	int N = A.length;
    	int f0 = 0;
    	for (int i = 0; i < N; ++i) {
    		f0 += i * A[i];
    	}
    	int sum = 0;
    	for (int i = 0; i < N; ++i) {
    		sum += A[i];
    	}
    	int result = f0;
    	int value = f0;
    	for (int j = 1; j < N; ++j) {
    		value = value + sum - N * A[N - j];
    		if (value > result) result = value;
    	}
    	return result;
    	//return maxRotateFunction_TimeLimitExceeded(A);
    }
    // Time Limit Exceeded
    public int maxRotateFunction_TimeLimitExceeded(int[] A) {
    	if (A == null || A.length == 0) return 0;
    	int N = A.length;
    	int result = Integer.MIN_VALUE;
    	for (int i = 0; i < N; ++i) {
    		int value = 0;
    		int factor = i;
    		for (int j = 0; j < N; ++j) {
    			value += A[j] * factor++;
    			if (factor >= N) factor = factor % N;
    		}
    		if (value > result) result = value;
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #400. Nth Digit
     *  
     *  Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
     *  
     *  Note:
     *  n is positive and will fit within the range of a 32-bit signed integer (n < 2^31).
     *  
     *  Example 1:
     *  
     *  Input:  3
     *  Output: 3
     *  
     *  Example 2:
     *  
     *  Input:  11
     *  Output: 0
     *  
     *  Explanation:
     *  The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
     */
    public int findNthDigit(int n) {
    	int pos = n;
    	int i = 1;
    	long power = 1;
    	long scope = 9 * power * i;
    	while (pos > scope) {
    		pos -= scope;
    		power *= 10;
    		++i;
    		scope = 9 * power * i;
    	}
    	if (i == 1) {  // 1 ~ 9
    		return pos;
    	} else if (pos % i == 1) {
    		return (int) (pos / (i * power) + 1);
    	} else if (pos % i == 0) {
    		return (pos / i - 1) % 10;
    	} else {
    		return (int) ((pos / (Math.pow(10, (i - pos % i)) * i) ) % 10);
    	}
    }
    
    /**
     *  [Easy]
     *  #401. Binary Watch
     *  
     *  A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
     *  Each LED represents a zero or one, with the least significant bit on the right.
     *  
     *  For example, the above binary watch reads "3:25".
     *  Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
     *  
     *  Example:
     *  
     *  Input: n = 1
     *  Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *  
     *  Note:
     *  The order of output does not matter.
     *  The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
     *  The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
     */
    public List<String> readBinaryWatch(int num) {
    	List<String> resultList = new ArrayList<>();
    	for (int n = 0; n <= num; ++n) {
    		List<Integer> hours = this.readBinaryWatch_hours(n);
    		List<Integer> minutes = this.readBinaryWatch_minutes(num - n);
    		for (int i = 0; i < hours.size(); ++i) {
    			for (int j = 0; j < minutes.size(); ++j) {
    				resultList.add(String.format("%d:%02d", hours.get(i), minutes.get(j)));
    			}
    		}
    	}
        return resultList;
    }
    public List<Integer> readBinaryWatch_hours(int num) {
    	List<Integer> hourList = new ArrayList<>();
    	this.readBinaryWatch_recurse(hourList, new int[] {1, 2, 4, 8}, 0, 0, 12, num);
    	return hourList;
    }
    public List<Integer> readBinaryWatch_minutes(int num) {
    	List<Integer> minuteList = new ArrayList<>();
    	this.readBinaryWatch_recurse(minuteList, new int[] {1, 2, 4, 8, 16, 32}, 0, 0, 60, num);
    	return minuteList;
    }
    public void readBinaryWatch_recurse(List<Integer> list, int[] binaries, int index, int val, int max, int left) {
    	if (left == 0) {
    		if (val < max) list.add(val);
    		return;
    	} else {
    		--left;
    		for (int i = index; i < binaries.length; ++i) {
    			this.readBinaryWatch_recurse(list, binaries, i + 1, val + binaries[i], max, left);
    		}
    	}
    }
    
    /**
     *  [Easy]
     *  #404. Sum of Left Leaves
     *  
     *  Find the sum of all left leaves in a given binary tree.
     *  
     *  Example:
     *  
     *      3
     *     / \
     *    9  20
     *      /  \
     *     15   7
     *  
     *  There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     */
    public int sumOfLeftLeaves(TreeNode root) {
    	List<Integer> leftLeafList = new ArrayList<>();
    	this.sumOfLeftLeaves_recurse(root, leftLeafList, false);
    	int result = 0;
    	for (int i = 0; i < leftLeafList.size(); ++i) {
    		result += leftLeafList.get(i);
    	}
    	return result;
    }
    public void sumOfLeftLeaves_recurse(TreeNode node, List<Integer> leftLeaves, boolean isLeft) {
    	if (node == null) {
    		return;
    	} else if (node.left == null && node.right == null && isLeft) {
    		leftLeaves.add(node.val);
    		return;
    	} else {
    		if (node.left != null)
    			this.sumOfLeftLeaves_recurse(node.left, leftLeaves, true);
    		if (node.right != null)
    			this.sumOfLeftLeaves_recurse(node.right, leftLeaves, false);
    	}
    }
    
    /**
     *  [Easy]
     *  #405. Convert a Number to Hexadecimal
     *  
     *  Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two's complement method is used.
     *  
     *  Note:
     *  All letters in hexadecimal (a-f) must be in lowercase.
     *  The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
     *  The given number is guaranteed to fit within the range of a 32-bit signed integer.
     *  You must not use any method provided by the library which converts/formats the number to hex directly.
     *  
     *  Example 1:
     *  
     *  Input:
     *  26
     *  Output:
     *  "1a"
     *  
     *  Example 2:
     *  
     *  Input:
     *  -1
     *  Output:
     *  "ffffffff"
     */
    public String toHex(int num) {
    	Map<Integer, Character> map = new HashMap<>();
    	map.put(0, '0');
    	map.put(1, '1');
    	map.put(2, '2');
    	map.put(3, '3');
    	map.put(4, '4');
    	map.put(5, '5');
    	map.put(6, '6');
    	map.put(7, '7');
    	map.put(8, '8');
    	map.put(9, '9');
    	map.put(10, 'a');
    	map.put(11, 'b');
    	map.put(12, 'c');
    	map.put(13, 'd');
    	map.put(14, 'e');
    	map.put(15, 'f');
    	StringBuilder sb = new StringBuilder();
    	long value = num;
    	if (value < 0) {
    		value += Long.MAX_VALUE + 1;
    	}
    	int factor = 16;
    	while (value > 0) {
    		int v = (int) (value % factor);
    		sb.insert(0, map.get(v));
    		value = (value - v) / factor;
    	}
    	String res = sb.toString();
    	if (res.length() == 0) return "0";
        return res.length() <= 8 ? res : res.substring(res.length() - 8);
    }
    
    /**
     *  [Medium]
     *  #406. Queue Reconstruction by Height
     *  
     *  Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), 
     *  where h is the height of the person and k is the number of people in front of this person who have a height 
     *  greater than or equal to h. Write an algorithm to reconstruct the queue.
     *  
     *  Note:
     *  The number of people is less than 1,100.
     *  
     *  Example
     *  
     *  Input:
     *  [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *  
     *  Output:
     *  [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     */
    public int[][] reconstructQueue(int[][] people) {
    	for (int i = 0; i < people.length; ++i) {
    		for (int j = i + 1; j < people.length; ++j) {
    			if (reconstructQueue_greater(people[i], people[j])) {
    				reconstructQueue_swap(people, i, j);
    			}
    		}
    	}
    	for (int i = 1; i < people.length; ++i) {
    		int count = 0;
    		for (int j = 0; j < i; ++j) {
    			if (people[j][0] >= people[i][0]) ++count;
    			if (count > people[i][1]) {
    				reconstructQueue_move(people, i, j);
    				break;
    			}
    		}
    	}
        return people;
    }
    private boolean reconstructQueue_greater(int[] p1, int[] p2) {
    	return (p1[1] > p2[1]) || (p1[1] == p2[1] && p1[0] > p2[0]);
    }
    private void reconstructQueue_swap(int[][] people, int x, int y) {
    	int[] temp = people[x];
    	people[x] = people[y];
    	people[y] = temp;
    }
    private void reconstructQueue_move(int[][] people, int from, int to) {
    	if (from == to) return;
    	int[] temp = people[from];
    	for (int i = from - 1; i >= to; --i) {
    		people[i + 1] = people[i];
    	}
    	people[to] = temp;
    }
    
    /**
     *  [Easy]
     *  #409. Longest Palindrome
     *  
     *  Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
     *  This is case sensitive, for example "Aa" is not considered a palindrome here.
     *  
     *  Note:
     *  Assume the length of given string will not exceed 1,010.
     *  
     *  Example:
     *  
     *  Input:
     *  "abccccdd"
     *  
     *  Output:
     *  7
     *  
     *  Explanation:
     *  One longest palindrome that can be built is "dccaccd", whose length is 7.
     */
    public int longestPalindrome(String s) {
    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i < s.length(); ++i) {
    		char c = s.charAt(i);
    		if (!map.containsKey(c)) {
    			map.put(c, 1);
    		} else {
    			int val = map.get(c);
    			map.put(c, ++val);
    		}
    	}
    	int result = 0;
    	boolean addOdd = false;
    	Iterator<Character> it = map.keySet().iterator();
    	while (it.hasNext()) {
    		int val = map.get(it.next());
    		if (val % 2 == 1) {
    			addOdd = true;
    			result += val - 1;
    		}
    		else {
    			result += val;
    		}
    	}
    	if (addOdd) ++result;
        return result;
    }
    
    /**
     *  [Easy]
     *  #412. Fizz Buzz
     *  
     *  Write a program that outputs the string representation of numbers from 1 to n.
     *  But for multiples of three it should output "Fizz" instead of the number and for the multiples of five output "Buzz". 
     *  For numbers which are multiples of both three and five output "FizzBuzz".
     *  
     *  Example:
     *  
     *  n = 15,
     *  Return:
     *  [
     *      "1",
     *      "2",
     *      "Fizz",
     *      "4",
     *      "Buzz",
     *      "Fizz",
     *      "7",
     *      "8",
     *      "Fizz",
     *      "Buzz",
     *      "11",
     *      "Fizz",
     *      "13",
     *      "14",
     *      "FizzBuzz"
     *  ]
     */
    public List<String> fizzBuzz(int n) {
        List<String> resList = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
        	int value = i;
        	boolean isNumber = true;
        	StringBuilder sb = new StringBuilder();
        	if (value % 3 == 0) {
        		isNumber = false;
        		sb.append("Fizz");
        	}
        	if (value % 5 == 0) {
        		isNumber = false;
        		sb.append("Buzz");
        	}
        	if (isNumber) sb.append(value);
        	resList.add(sb.toString());
        }
        return resList;
    }
    
    /**
     *  [Medium]
     *  #413. Arithmetic Slices
     *  
     *  A sequence of number is called arithmetic if it consists of at least three elements and 
     *  if the difference between any two consecutive elements is the same.
     *  
     *  For example, these are arithmetic sequence:
     *  
     *  1, 3, 5, 7, 9
     *  7, 7, 7, 7
     *  3, -1, -5, -9
     *  
     *  The following sequence is not arithmetic.
     *  
     *  1, 1, 2, 5, 7
     *  
     *  A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) 
     *  such that 0 <= P < Q < N.
     *  
     *  A slice (P, Q) of array A is called arithmetic if the sequence:
     *  A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
     *  
     *  The function should return the number of arithmetic slices in the array A.
     *  
     *  Example:
     *  
     *  A = [1, 2, 3, 4]
     *  
     *  return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     */
    public int numberOfArithmeticSlices(int[] A) {
        int result = 0;
        if (A == null || A.length < 3) return result;
        for (int i = 0; i < A.length - 2; ++i) {
        	int delta = A[i + 1] - A[i];
        	int curr = A[i + 1];
        	int j = i + 2;
        	while (j < A.length) {
        		int next = A[j++];
        		if (next - curr == delta) {
        			++result;
        			curr = next;
        		}
        		else break;
        	}
        }
        return result;
    }
    private int numberOfArithmeticSlices_wrong_but_correct(int[] A) {
    	// if P to Q is not continous
    	int result = 0;
        if (A == null || A.length < 3) return result;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; ++i) {
        	int num = A[i];
        	if (!map.containsKey(num)) {
        		map.put(num, 1);
        	} else {
        		int count = map.get(num);
        		map.put(num, ++count);
        	}
        }
        // check same values, e.g. 1,1,1...
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
        	int count = map.get(it.next());
        	if (count >= 3) {
        		result += (count - 3 + 1);
        	}
        }
        // check others, e.g. 1,2,3,4,5,6...
        // if 1,3,5 is the answer, the following solution is correct
//        for (int i = 0; i < A.length - 1; ++ i) {
//        	for (int j = i + 1; j < A.length; ++j) {
//        		int delta = A[j] - A[i];
//        		if (delta == 0) continue;
//        		int next = A[j] + delta;
//        		Map<Integer, Integer> copyMap = new HashMap<>(map);
//        		while (copyMap.containsKey(next)) {
//        			result++;
//        			int count = copyMap.get(next);
//        			--count;
//        			if (count == 0) copyMap.remove(next);
//        			else copyMap.put(next, count);
//        			next = next + delta;
//        		}
//        	}
//        }
        // if 1,3,5 is not, please use the following solution:
    	for (int i = 0; i < A.length - 1; ++i) {
    		int delta = A[i + 1] - A[i];
        	if (delta == 0) return result;
        	int next = A[i + 1] + delta;
        	Map<Integer, Integer> copyMap = new HashMap<>(map);
        	while (copyMap.containsKey(next)) {
        		result++;
        		int count = copyMap.get(next);
        		--count;
        		if (count == 0) copyMap.remove(next);
        		else copyMap.put(next, count);
        		next = next + delta;
        	}
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #414. Third Maximum Number
     *  
     *  Given a non-empty array of integers, return the third maximum number in this array. 
     *  If it does not exist, return the maximum number. 
     *  The time complexity must be in O(n).
     *  
     *  Example 1:
     *  Input: [3, 2, 1]
     *  Output: 1
     *  
     *  Explanation: The third maximum is 1.
     *  
     *  Example 2:
     *  Input: [1, 2]
     *  Output: 2
     *  
     *  Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
     *  
     *  Example 3:
     *  Input: [2, 2, 3, 1]
     *  Output: 1

     *  Explanation: Note that the third maximum here means the third maximum distinct number.
     *  Both numbers with value 2 are both considered as second maximum.
     */
    boolean hasMax = false;
    public int thirdMax(int[] nums) {
    	int max_1st = this.thirdMax_findNthMax(nums);
    	int max_2nd = this.thirdMax_findNthMax(nums, max_1st);
    	this.hasMax = false;
    	int max_3rd = this.thirdMax_findNthMax(nums, max_2nd);
        return hasMax ?  max_3rd : max_1st;
    }
    public int thirdMax_findNthMax(int[] nums) {
    	int max = Integer.MIN_VALUE;
    	for (int i = 0; i < nums.length; ++i) {
    		int num = nums[i];
    		if (num >= max) {
    			max = num;
    		}
    	}
    	return max;
    }
    public int thirdMax_findNthMax(int[] nums, int lessThan) {
    	int max = Integer.MIN_VALUE;
    	for (int i = 0; i < nums.length; ++i) {
    		int num = nums[i];
    		if (num >= max && num < lessThan) {
    			this.hasMax = true;
    			max = num;
    		}
    	}
    	return max;
    }
    
    /**
     *  [Easy]
     *  #415. Add Strings
     *  
     *  Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
     *  
     *  Note:
     *  1. The length of both num1 and num2 is < 5100.
     *  2. Both num1 and num2 contains only digits 0-9.
     *  3. Both num1 and num2 does not contain any leading zero.
     *  4. You must not use any built-in BigInteger library or convert the inputs to integer directly.
     */
    public String addStrings(String num1, String num2) {
    	StringBuilder sb = new StringBuilder();
    	int i = num1.length() - 1;
    	int j = num2.length() - 1;
    	int prev = 0;
    	while (i >= 0 && j >= 0) {
    		int v1 = num1.charAt(i--) - 48; // ASCII: '0' = 48
    		int v2 = num2.charAt(j--) - 48;
    		int v = v1 + v2 + prev;
    		if (v > 9) {
    			v -= 10;
    			prev = 1;
    		} else {
    			prev = 0;
    		}
    		sb.insert(0, (char)(v + 48));
    	}
    	while (i >= 0) {
    		int v1 = num1.charAt(i--) - 48;
    		int v = v1 + prev;
    		if (v > 9) {
    			v -= 10;
    			prev = 1;
    		} else {
    			prev = 0;
    		}
    		sb.insert(0, (char)(v + 48));
    	}
    	while (j >= 0) {
    		int v2 = num2.charAt(j--) - 48;
    		int v = v2 + prev;
    		if (v > 9) {
    			v -= 10;
    			prev = 1;
    		} else {
    			prev = 0;
    		}
    		sb.insert(0, (char)(v + 48));
    	}
    	if (prev != 0) {
    		sb.insert(0, (char)(prev + 48));
    	}
        return sb.toString();
    }
    
    /**
     *  [Medium]
     *  #419. Battleships in a Board
     *  
     *  Given an 2D board, count how many different battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. 
     *  You may assume the following rules:
     *  
     *  You receive a valid board, made of only battleships or empty slots.
     *  Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), 
     *  where N can be of any size.
     *  At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
     *  
     *  Example:
     *  X..X
     *  ...X
     *  ...X
     *  
     *  In the above board there are 2 battleships.
     *  
     *  Invalid Example:
     *  ...X
     *  XXXX
     *  ...X
     *  
     *  This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
     *  
     *  Follow up:
     *  Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
        	for (int j = 0; j < board[i].length; ++j) {
        		char el = board[i][j];
        		if (el == 'X') {
        			if (!this.countBattleships_isPartOfShip(board, i, j)) {
            			++count;
            		}
        		}
        	}
        }
        return count;
    }
    private boolean countBattleships_isPartOfShip(char[][] board, int i, int j) {
    	boolean isPartOfShip_vertical = false;
    	if (0 <= i - 1 && i - 1 < board.length) {
    		isPartOfShip_vertical = board[i - 1][j] == 'X';
    	}
    	boolean isPartOfShip_horizontal = false;
    	if (0 <= j - 1 && j - 1 < board[0].length) {
    		isPartOfShip_horizontal = board[i][j - 1] == 'X';
    	}
    	return isPartOfShip_vertical || isPartOfShip_horizontal;
    }
    
    /**
     *  [Easy]
     *  #437. Path Sum III
     *  
     *  You are given a binary tree in which each node contains an integer value.
     *  Find the number of paths that sum to a given value.
     *  The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
     *  The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     *  
     *  Example:
     *  root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *  
     *        10
     *       /  \
     *      5   -3
     *     / \    \
     *    3   2   11
     *   / \   \
     *  3  -2   1
     *  
     *  Return 3. The paths that sum to 8 are:
     *  
     *  1.  5 -> 3
     *  2.  5 -> 2 -> 1
     *  3. -3 -> 11
     */
    private int pathSum_count = 0;
    public int pathSum(TreeNode root, int sum) {
    	this.pathSum_count = 0;
    	Deque<TreeNode> queue = new ArrayDeque<>();
    	if (root != null) queue.addLast(root);
    	while (queue.size() != 0) {
    		TreeNode node = queue.pollFirst();
    		this.pathSum_findPath(node, 0, sum);
    		if (node.left != null) queue.addLast(node.left);
    		if (node.right != null) queue.addLast(node.right);
    	}
        return this.pathSum_count;
    }
    public void pathSum_findPath(TreeNode node, int total, int sum) {
    	if (node == null) {
    		return;
    	} else {
    		total += node.val;
    		if (total == sum) ++this.pathSum_count;
    		this.pathSum_findPath(node.left, total, sum);
    		this.pathSum_findPath(node.right, total, sum);
    	}
    }
    
    /**
     *  [Easy]
     *  #438. Find All Anagrams in a String
     *  
     *  Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     *  Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     *  The order of output does not matter.
     *  
     *  Example 1:
     *  
     *  Input:
     *  s: "cbaebabacd" p: "abc"
     *  
     *  Output:
     *  [0, 6]
     *  
     *  Explanation:
     *  The substring with start index = 0 is "cba", which is an anagram of "abc".
     *  The substring with start index = 6 is "bac", which is an anagram of "abc".
     *  
     *  Example 2:
     *  
     *  Input:
     *  s: "abab" p: "ab"
     *  
     *  Output:
     *  [0, 1, 2]
     *  
     *  Explanation:
     *  The substring with start index = 0 is "ab", which is an anagram of "ab".
     *  The substring with start index = 1 is "ba", which is an anagram of "ab".
     *  The substring with start index = 2 is "ab", which is an anagram of "ab".
     */
    public List<Integer> findAnagrams(String s, String p) {
    	return this.findAnagrams_solution2(s, p);
    }
    public List<Integer> findAnagrams_solution2(String s, String p) {
    	List<Integer> list = new ArrayList<>();
    	if (p.length() > s.length()) return list;
    	Map<Character, Integer> pMap = new HashMap<>();
    	for (int i = 0; i < p.length(); ++i) {
    		char c = p.charAt(i);
    		this.findAnagrams_mapAddOne(pMap, c);
    	}
    	int pl = p.length();
    	int sl = s.length();
    	int first = 0;
    	int last = 0;
    	while (first < sl && last < sl) {
    		char c = s.charAt(last);
    		if (pMap.containsKey(c)) {
    			this.findAnagrams_mapMinusOne(pMap, c);
    			++last;
    		}
    		else {
    			char cc = s.charAt(first);
    			++first;
    			this.findAnagrams_mapAddOne(pMap, cc);
    		}
    		if (first == last) {
    			char cc = s.charAt(last);
    			if (!pMap.containsKey(cc)) {
    				++first;
    				++last;
    			}
    		}
    		if (last - first == pl) {
    			list.add(first);
    			char cc = s.charAt(first);  // add the char back
    			this.findAnagrams_mapAddOne(pMap, cc);
    			++first;
    		}
    	}
    	
    	return list;
    }
    public void findAnagrams_mapAddOne(Map<Character, Integer> map, char c) {
    	if (map.containsKey(c)) {
    		int val = map.get(c);
    		map.put(c, ++val);
    	} else {
    		map.put(c, 1);
    	}
    }
    public void findAnagrams_mapMinusOne(Map<Character, Integer> map, char c) {
    	if (map.containsKey(c)) {
    		int val = map.get(c);
    		map.put(c, --val);
    		if (val == 0) map.remove(c);
    	}
    }
    public List<Integer> findAnagrams_solution1(String s, String p) {  // Time Limit Exceeded
    	List<Integer> list = new ArrayList<>();
    	if (p.length() > s.length()) return list;
    	Map<Character, Integer> pMap = new HashMap<>();
    	for (int i = 0; i < p.length(); ++i) {
    		char c = p.charAt(i);
    		if (!pMap.containsKey(c)) {
    			pMap.put(c, 1);
    		} else {
    			int val = pMap.get(c);
    			pMap.put(c, ++val);
    		}
    	}
    	int N = p.length();
    	for (int i = 0; i <= s.length() - N; ++i) {
    		Map<Character, Integer> map = new HashMap<>();
    		for (int j = i; j < i + N; ++j) {
    			char c = s.charAt(j);
    			if (!map.containsKey(c)) {
    				map.put(c, 1);
    			} else {
    				int val = map.get(c);
    				map.put(c, ++val);
    			}
    		}
    		if (map.equals(pMap)) {
    			list.add(i);
    		}
    	}
        return list;
    }
    
    /**
     *  [Easy]
     *  #441. Arranging Coins
     *  
     *  You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
     *  Given n, find the total number of full staircase rows that can be formed.
     *  n is a non-negative integer and fits within the range of a 32-bit signed integer.
     *  
     *  Example 1:
     *  n = 5
     *  
     *  The coins can form the following rows:
     *  o
     *  o o
     *  o o
     *  
     *  Because the 3rd row is incomplete, we return 2.
     *  
     *  Example 2:
     *  n = 8
     *  
     *  The coins can form the following rows:
     *  o
     *  o o
     *  o o o
     *  o o
     *  
     *  Because the 4th row is incomplete, we return 3.
     */
    public int arrangeCoins(int n) {
    	int result = 0;
    	int val = n;
    	int k = 1;
    	while (val >= k) {
    		val -= k;
    		++k;
    		++result;
    	}
        return result;
    }

	/**
	 *  [Medium]
	 *  #442. Find All Duplicates in an Array
	 *  Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
	 *
	 *  Find all the elements that appear twice in this array.
	 *
	 *  Could you do it without extra space and in O(n) runtime?
	 *
	 *  Example:
	 *  Input:  [4,3,2,7,8,2,3,1]
	 *  Output: [2,3]
	 */
	public List<Integer> findDuplicates(int[] nums) {
		for (int i = 0; i < nums.length; ++i) {
			while (i != nums[i] - 1) {
				if (nums[i] != nums[nums[i] - 1]) {
					findDuplicates_swap(nums, i, nums[i] - 1);
				} else {
					break;
				}
			}
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			if (i != nums[i] - 1) {
				result.add(nums[i]);
			}
		}
		//Collections.sort(result);  // order is not necessary
		return result;
	}

	private void findDuplicates_swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
     *  [Easy]
     *  #447. Number of Boomerangs
     *  
     *  Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) 
     *  such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
     *  Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all 
     *  in the range [-10000, 10000] (inclusive).
     *  
     *  Example:
     *  
     *  Input:
     *  [[0,0],[1,0],[2,0]]
     *  
     *  Output:
     *  2
     *  
     *  Explanation:
     *  The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
     */
    public int numberOfBoomerangs(int[][] points) {
    	return this.numberOfBoomerangs_solution2(points);
    }
    public int numberOfBoomerangs_solution2(int[][] points) {
    	int result = 0;
    	for (int i = 0; i < points.length; ++i) {
    		int[] p1 = points[i];
    		Map<Integer, Integer> map = new HashMap<>();
    		for (int j = 0; j < points.length; ++j) {
    			if (j == i) continue;
    			int[] p2 = points[j];
    			int dist = this.numberOfBoomerangs_distance(p1, p2);
    			if (!map.containsKey(dist)) {
    				map.put(dist, 1);
    			}
    			else {
    				int val = map.get(dist);
    				map.put(dist, ++val);
    			}
    		}
    		Iterator<Integer> it = map.keySet().iterator();
        	while (it.hasNext()) {
        		int count = map.get(it.next());
        		if (count >= 2) {
        			result += this.numberOfBoomerangs_Pn2(count);
        		}
        	}
    	}
        return result;
    }
    public int numberOfBoomerangs_Pn2(int value) {
    	int result = 1;
    	int val = value;
    	while (val > value - 2) {
    		result *= val--;
    	}
    	return result;
    }
    public int numberOfBoomerangs_distance(int[] p1, int[] p2) {
    	return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    public int numberOfBoomerangs_solution1(int[][] points) {
    	int result = 0;
    	for (int k = 0; k < points.length; ++k) {
    		int[] p = points[k];
    		for (int i = 0; i < points.length; ++i) {
    			if (i == k) continue;
    			int[] p1 = points[i];
    			for (int j = i + 1; j < points.length; ++j) {
    				if (j == k) continue;
    				int[] p2 = points[j];
    				if (this.numberOfBoomerangs_isOnline(p1, p2, p)) {
    					result += 2;
    				}
    			}
    		}
    	}
        return result;
    }
    public boolean numberOfBoomerangs_isOnline(int[] pj, int[] pk, int[] pi) {
    	// because all points are integer, so the value /2 is not necessary!
//    	if (pj[0] == pk[0] && pi[1] == (pj[1] + pk[1]) / 2) {
//    		return true;
//    	}
//    	if (pj[1] == pk[1] && pi[0] == (pj[0] + pk[0]) / 2) {
//    		return true;
//    	}
    	// 2x(x1-x2) + 2y(y1-y2) = (x1-x2)(x1+x2)+(y1-y2)(y1+y2)
    	int value = 2 * pi[0] * (pj[0] - pk[0]) + 2 * pi[1] * (pj[1] - pk[1]) 
    			    - ((pj[0] - pk[0]) * (pj[0] + pk[0]) + (pj[1] - pk[1]) * (pj[1] + pk[1]));
    	
    	return value == 0;
    }
    
    /**
     *  [Easy]
     *  #448. Find All Numbers Disappeared in an Array
     *  Given an array of integers where 1 <= a[i] <= n (n = size of array), some elements appear twice and others appear once.
     *  
     *  Find all the elements of [1, n] inclusive that do not appear in this array.
     *  
     *  Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
     *  
     *  Example:
     *  
     *  Input:
     *  [4,3,2,7,8,2,3,1]
     *  
     *  Output:
     *  [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
    	List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
        	int index = nums[i] < 0 ? -1 * nums[i] -1 : nums[i] - 1;
        	if (nums[index] > 0) nums[index] = -1 * nums[index];
        }
        for (int i = 0; i < nums.length; ++i) {
        	if (nums[i] > 0) list.add(i + 1);
        }
        return list;
    }
    
    /**
     *  [Medium]
     *  #451. Sort Characters By Frequency
     *  
     *  Given a string, sort it in decreasing order based on the frequency of characters.
     *  
     *  Example 1:
     *  
     *  Input:  "tree"
     *  Output: "eert"
     *  
     *  Explanation:
     *  'e' appears twice while 'r' and 't' both appear once.
     *  So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     *  
     *  Example 2:
     *  
     *  Input:  "cccaaa"
     *  Output: "cccaaa"
     *  
     *  Explanation:
     *  Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     *  Note that "cacaca" is incorrect, as the same characters must be together.
     *  
     *  Example 3:
     *  
     *  Input:  "Aabb"
     *  Output:  "bbAa"
     *  
     *  Explanation:
     *  "bbaA" is also a valid answer, but "Aabb" is incorrect.
     *  Note that 'A' and 'a' are treated as two different characters.
     */
    public String frequencySort(String s) {
    	Map<Character, StringBuilder> map1 = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
        	char c = s.charAt(i);
        	if (map1.containsKey(c)) {
        		map1.put(c, map1.get(c).append(c));
        	} else {
        		map1.put(c, new StringBuilder().append(c));
        	}
        }
        Iterator<Character> it1 = map1.keySet().iterator();
        Map<Integer, StringBuilder> map2 = new TreeMap<>((a, b) -> (a - b) * -1);
        while (it1.hasNext()) {
        	StringBuilder sb = map1.get(it1.next());
        	if (map2.containsKey(sb.length())) {
        		map2.put(sb.length(), map2.get(sb.length()).append(sb));
        	} else {
        		map2.put(sb.length(), new StringBuilder().append(sb));
        	}
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Integer> it2 = map2.keySet().iterator();
        while (it2.hasNext()) {
        	sb.append(map2.get(it2.next()));
        }
        return sb.toString();
    }
    
    /**
     *  [Easy]
     *  #453. Minimum Moves to Equal Array Elements
     *  
     *  Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, 
     *  where a move is incrementing n - 1 elements by 1.
     *  
     *  Example:
     *  
     *  Input:
     *  [1,2,3]
     *  
     *  Output:
     *  3
     *  
     *  Explanation:
     *  Only three moves are needed (remember each move increments two elements):
     *  [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
     */
    public int minMoves(int[] nums) {
    	return this.minMoves_solution3(nums);
    }
    public int minMoves_solution3(int[] nums) {
    	int min = Integer.MAX_VALUE;
    	for (int num : nums) {
    		if (num < min) min = num;
    	}
    	int steps = 0;
    	for (int num : nums) {
    		steps += num - min;
    	}
    	return steps;
    }
    public int minMoves_solution2(int[] nums) {  // Time Limit Exceeded
    	int N = nums.length;
    	int max = Integer.MIN_VALUE;
    	int min = Integer.MAX_VALUE;
    	for (int i = 0; i < N; ++i) {
    		int num = nums[i];
    		if (num > max) max = num;
    		if (num < min) min = num;
    	}
    	if (max == min) return 0;
    	int top = max;
    	int total = 0;
    	int max2 = Integer.MIN_VALUE;
    	do {
    		total = 0;
    		max2 = Integer.MIN_VALUE;
    		for (int i = 0; i < N; ++i) {
    			int val = top - nums[i];
    			if (val > max2) max2 = val;
    			total += val;
    		}
    		++top;
    	} while (total % (max2 * (N - 1)) != 0);
    	return top - 1 - min;
    }
    public int minMoves_solution1(int[] nums) { // Time Limit Exceeded
    	int steps = 0;
    	while (!this.minMoves_equalsAll(nums)) {
    		int maxIndex = this.minMoves_getMaxIndex(nums);
    		this.minMoves_addExcept(nums, maxIndex);
    		++steps;
    	}
    	return steps;
    }
    public boolean minMoves_equalsAll(int[] nums) {
    	int val = nums[0];
    	for (int i = 1; i < nums.length; ++i) {
    		if (val != nums[i]) return false;
    	}
    	return true;
    }
    public int minMoves_getMaxIndex(int[] nums) {
    	int max = Integer.MIN_VALUE;
    	int maxIndex = -1;
    	for (int i = 0; i < nums.length; ++i) {
    		int num = nums[i];
    		if (num > max) {
    			max = num;
    			maxIndex = i;
    		}
    	}
    	return maxIndex;
    }
    public void minMoves_addExcept(int[] nums, int except) {
    	for (int i = 0; i < nums.length; ++i) {
    		if (i == except) continue;
    		nums[i]++;
    	}
    }
    
    /**
     *  [Easy]
     *  #455. Assign Cookies
     *  
     *  Assume you are an awesome parent and want to give your children some cookies. 
     *  But, you should give each child at most one cookie. Each child i has a greed factor gi, 
     *  which is the minimum size of a cookie that the child will be content with; 
     *  and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, 
     *  and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
     *  
     *  Note:
     *  You may assume the greed factor is always positive. 
     *  You cannot assign more than one cookie to one child.
     *  
     *  Example 1:
     *  Input: [1,2,3], [1,1]
     *  Output: 1
     *  
     *  Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
     *  And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
     *  You need to output 1.
     *  
     *  Example 2:
     *  Input: [1,2], [1,2,3]
     *  Output: 2
     *  
     *  Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
     *  You have 3 cookies and their sizes are big enough to gratify all of the children, 
     *  You need to output 2.
     */
    public int findContentChildren(int[] g, int[] s) {
    	int gl = g.length;
    	int sl = s.length;
    	Arrays.sort(g);
    	Arrays.sort(s);
    	int minLength = Math.min(gl, sl);
    	int count = Math.min(gl, sl);
		int i = 0, j = 0;
		while (i < minLength && j < sl) {
			if (g[i] <= s[j]) {
				++i;
			}
			++j;
		}
		if (i >= minLength) {
			return count;
		}
		if (j >= minLength) {
			count -= (minLength - i);
		}
        return count;
    }
    
    /**
     *  [Easy]
     *  #459. Repeated Substring Pattern
     *  
     *  Given a non-empty string check if it can be constructed by taking a substring of it 
     *  and appending multiple copies of the substring together. You may assume the given string consists of lowercase English letters only 
     *  and its length will not exceed 10000.
     *  
     *  Example 1:
     *  Input: "abab"
     *  Output: True
     *  
     *  Explanation: It's the substring "ab" twice.
     *  
     *  Example 2:
     *  Input: "aba"
     *  Output: False
     *  
     *  Example 3:
     *  Input: "abcabcabcabc"
     *  Output: True
     *  
     *  Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
     */
    public boolean repeatedSubstringPattern(String str) {
    	int length = str.length();
    	int factor = 2;
    	while (factor <= length) {
    		if (length % factor == 0) {
    			int delta = length / factor;
    			int i = 0;
    			while (i < delta) {
    				char c0 = str.charAt(i);
    				int j = i + delta;
    				while (j < length) {
    					char c = str.charAt(j);
    					if (c != c0) break;
    					j += delta;
    				}
    				if (j < length) break;
    				++i;
    			}
    			if (i >= delta) {
    				return true;
    			}
    		}
    		++factor;
    	}
        return false;
    }
    
    /**
     *  [Easy]
     *  #461. Hamming Distance
     *  The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
     *  
     *  Given two integers x and y, calculate the Hamming distance.
     *  
     *  Note:
     *  0 <= x, y < 2^31.
     *  
     *  Example:

     *  Input: x = 1, y = 4
     *  
     *  Output: 2
     *  
     *  Explanation:
     *  1   (0 0 0 1)
     *  4   (0 1 0 0)
     *         ^   ^
     *  
     *  The above arrows point to positions where the corresponding bits are different.
     */
    public int hammingDistance(int x, int y) {
        int bits = 0;
        int result = x ^ y;
        while (result != 0) {
        	if ((result & 1) == 1) ++bits;
        	result = result >> 1;
        }
        return bits;
    }
    
    /**
     *  [Medium]
     *  #462. Minimum Moves to Equal Array Elements II
     *  
     *  Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
     *  
     *  You may assume the array's length is at most 10,000.
     *  
     *  Example:
     *  
     *  Input:  [1,2,3]
     *  
     *  Output: 2
     *  
     *  Explanation:
     *  Only two moves are needed (remember each move increments or decrements one element):
     *  
     *  [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
     */
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int mid = length / 2;
        minMoves2_quickSort(nums, 0, length - 1);
        int midValue = nums[mid];
        int steps = 0;
        for (int i = 0; i < length; ++i) {
        	steps += Math.abs(nums[i] - midValue);
        }
        return steps;
    }
    private int minMoves2_getFlagIndex(int[] nums, int low, int high) {
    	int i = low;
    	int j = high;
    	int flag = nums[i];
    	while (i < j) {
    		while (i < j && flag <= nums[j]) --j;
    		nums[i] = nums[j];
    		while (i < j && nums[i] <= flag) ++i;
    		nums[j] = nums[i];
    	}
    	nums[i] = flag;
    	return i;
    }
    private void minMoves2_quickSort(int[] nums, int low, int high) {
    	if (low > high) return;
    	int index = minMoves2_getFlagIndex(nums, low, high);
        minMoves2_quickSort(nums, low, index - 1);
        minMoves2_quickSort(nums, index + 1, high);
    }
    
    /**
     *  [Easy]
     *  #463. Island Perimeter
     *  
     *  You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
     *  Grid cells are connected horizontally/vertically (not diagonally). 
     *  The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). 
     *  The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
     *  One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. 
     *  Determine the perimeter of the island.
     *  
     *  Example:
     *  
     *  [[0,1,0,0],
     *   [1,1,1,0],
     *   [0,1,0,0],
     *   [1,1,0,0]]
     *  
     *  Answer: 16
     *  Explanation: The perimeter is the 16 yellow stripes in the image below:
     */
    public int islandPerimeter(int[][] grid) {
    	int perimeter = 0;
    	for (int i = 0; i < grid.length; ++i) {
    		int[] row = grid[i];
    		for (int j = 0; j < row.length; ++j) {
    			if (row[j] == 1) {
    				perimeter += this.islandPerimeter_getSides(grid, i, j);
    			}
    		}
    	}
        return perimeter;
    }
    public int islandPerimeter_getSides(int[][] grid, int i, int j) {
    	int sides = 0;
    	// top side
    	if (i - 1 < 0 || grid[i - 1][j] == 0) ++sides;
    	// bottom side
    	if (i + 1 >= grid.length || grid[i + 1][j] == 0) ++sides;
    	// left side
    	if (j - 1 < 0 || grid[i][j - 1] == 0) ++sides;
    	// right side
    	if (j + 1 >= grid[i].length || grid[i][j + 1] == 0) ++sides;
    	
    	return sides;
    }
    
    /**
     *  [Easy]
     *  #476. Number Complement
     *  
     *  Given a positive integer, output its complement number. 
     *  The complement strategy is to flip the bits of its binary representation.
     *  
     *  Note:
     *  The given integer is guaranteed to fit within the range of a 32-bit signed integer.
     *  You could assume no leading zero bit in the integer’s binary representation.
     *  
     *  Example 1:
     *  Input: 5
     *  Output: 2
     *  Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. 
     *  So you need to output 2.
     *  
     *  Example 2:
     *  Input: 1
     *  Output: 0
     *  Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. 
     *  So you need to output 0.
     */
    public int findComplement(int num) {
    	if (num < 0) {
    		return -1 * (num + 1);
    	}
    	int factor = 1;
    	int numOfDigits = 1;
        while (num >= factor && numOfDigits < 32) {  //Integer.highestOneBit(i)
        	++numOfDigits;
        	factor = factor << 1;
        }
        int mask = factor - 1;
        return num ^ mask;
    }
    
    /**
     *  [Easy]
     *  #485. Max Consecutive Ones
     *  
     *  Given a binary array, find the maximum number of consecutive 1s in this array.
     *  
     *  Example 1:
     *  Input: [1,1,0,1,1,1]
     *  Output: 3
     *  Explanation: The first two digits or the last three digits are consecutive 1s.
     *      The maximum number of consecutive 1s is 3.
     *  
     *  Note:
     *  The input array will only contain 0 and 1.
     *  The length of input array is a positive integer and will not exceed 10,000
     */
    public int findMaxConsecutiveOnes(int[] nums) {
    	int max = 0;
    	int num = 0;
    	for (int i = 0; i < nums.length; ++i) {
    		if (nums[i] == 1) {
    			++num;
    		} else {  // == 0
    			if (num > max) max = num;
    			num = 0;
    		}
    	}
    	if (num > max) max = num;
        return max;
    }
    
    /**
     *  [Easy]
     *  #492. Construct the Rectangle
     *  
     *  For a web developer, it is very important to know how to design a web page's size. 
     *  So, given a specific rectangular web page’s area, your job by now is to design a rectangular web page, 
     *  whose length L and width W satisfy the following requirements:
     *  
     *  1. The area of the rectangular web page you designed must equal to the given target area.
     *  2. The width W should not be larger than the length L, which means L >= W.
     *  3. The difference between length L and width W should be as small as possible.
     *  
     *  You need to output the length L and the width W of the web page you designed in sequence.
     *  
     *  Example:
     *  Input: 4
     *  Output: [2, 2]
     *  Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
     *  But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. So the length L is 2, and the width W is 2.
     *  
     *  Note:
     *  The given area won't exceed 10,000,000 and is a positive integer
     *  The web page's width and length you designed must be positive integers.
     */
    public int[] constructRectangle(int area) {
        int[] result = new int[2];
        double d = area;
        int len = (int) Math.ceil(Math.sqrt(d));
        while (area % len != 0) {
        	--len;
        }
        int a = len;
        int b = area / len;
        result[0] = Math.max(a, b);
        result[1] = Math.min(a, b);
        return result;
    }
    
    /**
     *  [Medium]
     *  #495. Teemo Attacking
     *  
     *  In LLP world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition. 
     *  Now, given the Teemo's attacking ascending time series towards Ashe 
     *  and the poisoning time duration per Teemo's attacking, 
     *  you need to output the total time that Ashe is in poisoned condition.
     *  
     *  You may assume that Teemo attacks at the very beginning of a specific time point, 
     *  and makes Ashe be in poisoned condition immediately.
     *  
     *  Example 1:
     *  Input: [1,4], 2
     *  Output: 4
     *  Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately. 
     *  This poisoned status will last 2 seconds until the end of time point 2. 
     *  And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds. 
     *  So you finally need to output 4.
     *  
     *  Example 2:
     *  Input: [1,2], 2
     *  Output: 3
     *  Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned. 
     *  This poisoned status will last 2 seconds until the end of time point 2. 
     *  However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status. 
     *  Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3. 
     *  So you finally need to output 3.
     *  
     *  Note:
     *  You may assume the length of given time series array won't exceed 10000.
     *  You may assume the numbers in the Teemo's attacking time series and his poisoning time duration per attacking are non-negative integers, which won't exceed 10,000,000.
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
    	if (timeSeries == null || timeSeries.length == 0) return 0;
    	int count = duration;
    	int prev = timeSeries[0] + duration - 1;
    	for (int i = 1; i < timeSeries.length; ++i) {
    		if (timeSeries[i] == prev) {
    			count += duration - 1;
    		} else if (timeSeries[i] < prev) {
    			count += duration - (prev - timeSeries[i] + 1);
    		} else {  // timeSeries[i] > prev
    			count += duration;
    		}
    		prev = timeSeries[i] + duration - 1;
    	}
        return count;
    }
    private int findPoisonedDuration_TimeLimitExceeded(int[] timeSeries, int duration) {
    	if (timeSeries == null || timeSeries.length == 0) return 0;
    	if (duration == 0) return 0;
    	int size = timeSeries[timeSeries.length - 1] + duration;
    	boolean[] maskTable = new boolean[size];
    	for (int i = 0; i < size; ++i) {
    		maskTable[i] = false;
    	}
    	for (int i = 0; i < timeSeries.length; ++i) {
    		int offset = timeSeries[i];
    		for (int j = 0; j < duration; ++j) {
    			maskTable[j + offset] = true;
    		}
    	}
    	int count = 0;
    	for (int i = 0; i < size; ++i) {
    		if (maskTable[i]) ++count;
    	}
        return count;
    }

    /**
     *  [Easy]
     *  #496. Next Greater Element I
     *  
     *  You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
     *  Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
     *  The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. 
     *  If it does not exist, output -1 for this number.
     *  
     *  Example 1:
     *  Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     *  Output: [-1,3,-1]
     *  Explanation:
     *      For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
     *      For number 1 in the first array, the next greater number for it in the second array is 3.
     *      For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
     * 
     *  Example 2:
     *  Input: nums1 = [2,4], nums2 = [1,2,3,4].
     *  Output: [3,-1]
     *  Explanation:
     *      For number 2 in the first array, the next greater number for it in the second array is 3.
     *          For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
     *  
     *  Note:
     *  All elements in nums1 and nums2 are unique.
     *  The length of both nums1 and nums2 would not exceed 1000.
     */
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] res = new int[findNums.length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
        	map.put(nums[i], i);
        }
        for (int i = 0; i < findNums.length; ++i) {
        	int findNum = findNums[i];
        	int j = map.get(findNum) + 1;
        	for (; j < nums.length; ++j) {
        		if (nums[j] > findNum) {
        			res[i] = nums[j];
        			break;
        		}
        	}
        	if (j >= nums.length) {
        		res[i] = -1;
        	}
        }
        return res;
    }
    
    /**
     *  [Easy]
     *  #500. Keyboard Row
     *  Given a List of words, return the words that can be typed using letters of alphabet 
     *  on only one row's of American keyboard like the image below.
     *  
     *  Example 1:
     *  Input: ["Hello", "Alaska", "Dad", "Peace"]
     *  Output: ["Alaska", "Dad"]
     *  
     *  Note:
     *  You may use one character in the keyboard more than once.
     *  You may assume the input string will only contain letters of alphabet.
     */
    public String[] findWords(String[] words) {
    	Map<Character, Integer> map = new HashMap<>();
    	map.put('Q', 1);
    	map.put('W', 1);
    	map.put('E', 1);
    	map.put('R', 1);
    	map.put('T', 1);
    	map.put('Y', 1);
    	map.put('U', 1);
    	map.put('I', 1);
    	map.put('O', 1);
    	map.put('P', 1);
    	map.put('A', 2);
    	map.put('S', 2);
    	map.put('D', 2);
    	map.put('F', 2);
    	map.put('G', 2);
    	map.put('H', 2);
    	map.put('J', 2);
    	map.put('K', 2);
    	map.put('L', 2);
    	map.put('Z', 3);
    	map.put('X', 3);
    	map.put('C', 3);
    	map.put('V', 3);
    	map.put('B', 3);
    	map.put('N', 3);
    	map.put('M', 3);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
        	String word = words[i].toUpperCase();
        	int row = map.get(word.charAt(0));
        	int j = 1;
        	for (; j < word.length(); ++j) {
        		if (row != map.get(word.charAt(j))) {
        			break;
        		}
        	}
        	if (j >= word.length()) {
        		result.add(words[i]);
        	}
        }
        return result.toArray(new String[]{});
    }
    
    /**
     *  [Medium]
     *  #508. Most Frequent Subtree Sum
     *  
     *  Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
     *  
     *  Examples 1
     *  Input:
     *  
     *    5
     *   /  \
     *  2   -3
     *  return [2, -3, 4], since all the values happen only once, return all of them in any order.
     *  
     *  Examples 2
     *  Input:
     *  
     *    5
     *   /  \
     *  2   -5
     *  return [2], since 2 happens twice, however -5 only occur once.
     *  Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
     */
    private Map<Integer, Integer> findFrequentTreeSum_iiMap = null;
    public int[] findFrequentTreeSum(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	if (root != null) {
    		findFrequentTreeSum_iiMap = new HashMap<>();
    		findFrequentTreeSum_getSum(root);
    		Iterator<Integer> it = findFrequentTreeSum_iiMap.keySet().iterator();
    		int maxCount = 0;
    		while (it.hasNext()) {
    			int sum = it.next();
    			int count = findFrequentTreeSum_iiMap.get(sum);
    			if (count > maxCount) {
    				result.clear();
    				maxCount = count;
    				result.add(sum);
    			} else if (count == maxCount) {
    				result.add(sum);
    			}
    		}
    	}
    	return result.stream().mapToInt((Integer i) -> i.intValue()).toArray();
    }
    private int findFrequentTreeSum_getSum(TreeNode node) {
    	if (node.left == null && node.right == null) {
    		findFrequentTreeSum_putToMap(node.val);
    		return node.val;
    	}
    	int sum = node.val;
    	if (node.left != null) {
    		sum += findFrequentTreeSum_getSum(node.left);
    	}
    	if (node.right != null) {
    		sum += findFrequentTreeSum_getSum(node.right);
    	}
    	findFrequentTreeSum_putToMap(sum);
    	return sum;
    }
    private void findFrequentTreeSum_putToMap(int val) {
    	if (findFrequentTreeSum_iiMap.containsKey(val)) {
    		int count = findFrequentTreeSum_iiMap.get(val);
    		findFrequentTreeSum_iiMap.put(val, ++count);
    	} else {
    		findFrequentTreeSum_iiMap.put(val, 1);
    	}
    }
    
    /**
     *  [Easy]
     *  #513. Find Bottom Left Tree Value
     *  
     *  Given a binary tree, find the leftmost value in the last row of the tree.
     *  
     *  Example 1:
     *  Input:
     *  
     *      2
     *     / \
     *    1   3
     *  
     *  Output:
     *  1
     *  
     *  Example 2: 
     *  Input:
     *  
     *          1
     *         / \
     *        2   3
     *       /   / \
     *      4   5   6
     *         /
     *        7
     *  
     *  Output:
     *  7
     *  
     *  Note: You may assume the tree (i.e., the given root node) is not NULL.
     */
    public int findBottomLeftValue(TreeNode root) {
    	Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
    	queue.offerLast(root);
    	if (root.right != null) queue.offerLast(root.right);
    	if (root.left != null) queue.offerLast(root.left);
    	while (queue.size() > 1) {
    		TreeNode node = queue.pollFirst();
    		if (node.right != null) queue.offerLast(node.right);
    		if (node.left != null) queue.offerLast(node.left);
    	}
        return queue.pollFirst().val;
    }
    
    /**
     *  {Medium]
     *  #515. Find Largest Value in Each Tree Row
     *  
     *  You need to find the largest value in each row of a binary tree.
     *  
     *  Example:
     *  Input: 
     *  
     *            1
     *           / \
     *          3   2
     *         / \   \  
     *        5   3   9 
     *  
     *  Output: [1, 3, 9]
     *  Subscribe to see which companies asked this question.
     */
    public List<Integer> largestValues(TreeNode root) {
    	List<Integer> result = new ArrayList<>();
    	if (root == null) return result;
    	Deque<TreeNode> queue = new ArrayDeque<>();
    	TreeNode flag = new TreeNode(Integer.MIN_VALUE);
    	queue.offerLast(root);
    	queue.offerLast(flag);
    	int maxInRow = Integer.MIN_VALUE;
    	while (queue.size() > 0) {
    		TreeNode node = queue.pollFirst();
    		if (node == flag) {
    			result.add(maxInRow);
    			maxInRow = Integer.MIN_VALUE;
    			if (queue.size() != 0) queue.offerLast(flag);
    		} else {
    			if (node.val > maxInRow) maxInRow = node.val;
    			if (node.left != null) queue.offerLast(node.left);
    			if (node.right != null) queue.offerLast(node.right);
    		}
    	}
        return result;
    }
    
    /**
     *  [Easy]
     *  #520. Detect Capital
     *  
     *  Given a word, you need to judge whether the usage of capitals in it is right or not.
     *  
     *  We define the usage of capitals in a word to be right when one of the following cases holds:
     *  
     *  1. All letters in this word are capitals, like "USA".
     *  2. All letters in this word are not capitals, like "leetcode".
     *  3. Only the first letter in this word is capital if it has more than one letter, like "Google".
     *  Otherwise, we define that this word doesn't use capitals in a right way.
     *  
     *  Example 1:
     *  Input: "USA"
     *  Output: True
     *  
     *  Example 2:
     *  Input: "FlaG"
     *  Output: False
     *  
     *  Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
     */
    public boolean detectCapitalUse(String word) {
    	if (word == null || word.length() == 0) return false;
    	if (word.length() == 1) return true;
    	char fchar = word.charAt(0);
    	if (detectCapitalUse_isUppercase(fchar)) {
    		fchar = word.charAt(1);
    	}
    	boolean flag = detectCapitalUse_isUppercase(fchar);
    	for (int i = 1; i < word.length(); ++i) {
    		if (flag != detectCapitalUse_isUppercase(word.charAt(i))) {
    			return false;
    		}
    	}
        return true;
    }
    private boolean detectCapitalUse_isUppercase(char c) {
    	return  ('A' <= c && c <= 'Z');
    }

	/**
	 *  [Easy]
	 *  #521. Longest Uncommon Subsequence I
	 *
	 *  Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings.
	 *  The longest uncommon subsequence is defined as the longest subsequence of one of these strings and
	 *  this subsequence should not be any subsequence of the other strings.
	 *  A subsequence is a sequence that can be derived from one sequence by deleting some characters
	 *  without changing the order of the remaining elements. Trivially, any string is a subsequence of itself
	 *  and an empty string is a subsequence of any string.
	 *  The input will be two strings, and the output needs to be the length of the longest uncommon subsequence.
	 *  If the longest uncommon subsequence doesn't exist, return -1.
	 *
	 *  Example 1:
	 *  Input: "aba", "cdc"
	 *  Output: 3
	 *  Explanation: The longest uncommon subsequence is "aba" (or "cdc"),
	 *  because "aba" is a subsequence of "aba",
	 *  but not a subsequence of any other strings in the group of two strings.
	 *
	 *  Note:
	 *  Both strings' lengths will not exceed 100.
	 *  Only letters from a ~ z will appear in input strings.
	 */
	public int findLUSlength(String a, String b) {
		return a.equals(b) ? -1 : Math.max(a.length(), b.length());
	}

	/**
	 *  [Medium]
	 *  #526 Beautiful Arragement
	 *
	 *  Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
	 *  these N numbers successfully if one of the following is true for the i-th position (1 <= i <= N) in this array:
	 *
	 *   - The number at the i-th position is divisible by i.
	 *   - i is divisible by the number at the i-th position.
	 *  Now given N, how many beautiful arrangements can you construct?
	 *
	 *  Example 1:
	 *
	 *  Input: 2
	 *  Output: 2
	 *
	 *  Explanation:
	 *
	 *  The first beautiful arrangement is [1, 2]:
	 *  Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
	 *  Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
	 *
	 *  The second beautiful arrangement is [2, 1]:
	 *  Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
	 *  Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
	 *
	 *  Note:
	 *  N is a positive integer and will not exceed 15.
	 */
	private int countArrayngement_count = 0;
	public int countArrangement(int N) {
		countArrayngement_count = 0;
		boolean[] used = new boolean[N];
		Arrays.fill(used, false);
		countArrangement_recurse(0, N, used);
		return countArrayngement_count;
	}
	private void countArrangement_recurse(int index, int N, boolean[] used) {
	    if (index >= N) {
	        ++countArrayngement_count;
	        return;
        }
		for (int i = 0; i < N; ++i) {
		    if (!used[i] && countArrangement_isBeautiful(i + 1, index + 1)) {
		        used[i] = true;
		        countArrangement_recurse(index + 1, N, used);
		        used[i] = false;
            }
        }

	}
	private boolean countArrangement_isBeautiful(int num, int index) {
        return num % index == 0 || index % num == 0;
    }
	/**
     *  [Medium]
     *  #529. Minesweeper
     *  
     *  Let's play the minesweeper game (Wikipedia, online game)!
     *  
     *  You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 
     *  'E' represents an unrevealed empty square, 
     *  'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, 
     *  digit ('1' to '8') represents how many mines are adjacent to this revealed square, 
     *  and finally 'X' represents a revealed mine.
     *  
     *  Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
     *  return the board after revealing this position according to the following rules:
     *  
     *  If a mine ('M') is revealed, then the game is over - change it to 'X'.
     *  If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') 
     *  and all of its adjacent unrevealed squares should be revealed recursively.
     *  If an empty square ('E') with at least one adjacent mine is revealed, 
     *  then change it to a digit ('1' to '8') representing the number of adjacent mines.
     *  Return the board when no more squares will be revealed.
     *  
     *  Example 1:
     *  Input: 
     *  
     *  [['E', 'E', 'E', 'E', 'E'],
     *   ['E', 'E', 'M', 'E', 'E'],
     *   ['E', 'E', 'E', 'E', 'E'],
     *   ['E', 'E', 'E', 'E', 'E']]
     *  
     *  Click : [3,0]
     *  
     *  Output: 
     *  
     *  [['B', '1', 'E', '1', 'B'],
     *   ['B', '1', 'M', '1', 'B'],
     *   ['B', '1', '1', '1', 'B'],
     *   ['B', 'B', 'B', 'B', 'B']]
     *   
     *  Example 2:
     *  Input: 
     *  
     *  [['B', '1', 'E', '1', 'B'],
     *   ['B', '1', 'M', '1', 'B'],
     *   ['B', '1', '1', '1', 'B'],
     *   ['B', 'B', 'B', 'B', 'B']]
     *  
     *  Click : [1,2]
     *  
     *  Output: 
     *  
     *  [['B', '1', 'E', '1', 'B'],
     *   ['B', '1', 'X', '1', 'B'],
     *   ['B', '1', '1', '1', 'B'],
     *   ['B', 'B', 'B', 'B', 'B']]
     *   
     *  Note:
     *  - The range of the input matrix's height and width is [1,50].
     *  - The click position will only be an unrevealed square ('M' or 'E'), 
     *    which also means the input board contains at least one clickable square.
     *  - The input board won't be a stage when game is over (some mines have been revealed).
     *  - For simplicity, not mentioned rules should be ignored in this problem. For example, 
     *    you don't need to reveal all the unrevealed mines when the game is over, 
     *    consider any cases that you will win the game or flag any squares.
     */
    public char[][] updateBoard(char[][] board, int[] click) {
    	int x = click[0];
    	int y = click[1];
    	if (board[x][y] == 'M') {
    		board[x][y] = 'X';
    	} else if (board[x][y] == 'E') {
    		board = updateBoard_setNumOfMines(board, click);
    		Deque<List<Integer>> queue = new ArrayDeque<>();
    		List<Integer> plist = new ArrayList<>();
    		plist.add(x);
    		plist.add(y);
    		queue.offerLast(plist);
    		while (queue.size() > 0) {
    			plist = queue.pollFirst();
    			int[] point = plist.stream().mapToInt(i -> i).toArray();
    			if (board[point[0]][point[1]] == 'B') {
    				int[][] range = updateBoard_getPointRange(board, point);
    				for (int i = range[0][0]; i <= range[0][1]; ++i) {
    		    		for (int j = range[1][0]; j <= range[1][1]; ++j) {
    		    			if (board[i][j] == 'E') {
    		    				board = updateBoard_setNumOfMines(board, new int[]{i,j});
    		    				plist = new ArrayList<>();
    		    				plist.add(i);
    		    				plist.add(j);
    		    				queue.offerLast(plist);
    		    			}
    		    		}
    		    	}
    			}
    		}
    	}
        return board;
    }
    private char[][] updateBoard_setNumOfMines(char[][] board, int[] point) {
    	int x1 = point[0];
    	int y1 = point[1];
    	int[][] range = updateBoard_getPointRange(board, point);
    	int numOfMines = 0;
    	for (int i = range[0][0]; i <= range[0][1]; ++i) {
    		for (int j = range[1][0]; j <= range[1][1]; ++j) {
    			if (board[i][j] == 'M' && (i != x1 || j != y1)) {
    				++numOfMines;
    			}
    		}
    	}
    	if (numOfMines == 0) board[x1][y1] = 'B';
    	else board[x1][y1] = (char) ('0' + numOfMines);
    	return board;
    }
    private int[][] updateBoard_getPointRange(char[][] board, int[] point) {
    	int height = board.length;
    	int width  = board[0].length;
    	int x1 = point[0];
    	int x0 = x1 - 1 >= 0 ? x1 - 1 : 0;
    	int x2 = x1 + 1 < height ? x1 + 1 : height - 1;
    	int y1 = point[1];
    	int y0 = y1 - 1 >= 0 ? y1 - 1 : 0;
    	int y2 = y1 + 1 < width ? y1 + 1 : width - 1;
    	return new int[][] {{x0, x2}, {y0, y2}};
    }

	/**
	 *  [Medium]
	 *  #537 Complex Number Multiplication
	 *
	 *  Given two strings representing two complex numbers.
	 *  You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
	 *
	 *  Example 1:
	 *  Input: "1+1i", "1+1i"
	 *  Output: "0+2i"
	 *  Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
	 *
	 *  Example 2:
	 *  Input: "1+-1i", "1+-1i"
	 *  Output: "0+-2i"
	 *  Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
	 *
	 *  Note:
	 *  The input strings will not have extra blank.
	 *  The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of
	 *  [-100, 100]. And the output should be also in this form.
	 */
	public class ComplexNumber {
		private int a;
		private int b;
		public ComplexNumber(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public ComplexNumber(String str) {
			int i = str.indexOf('+');
			this.a = Integer.parseInt(str.substring(0, i));
			this.b = Integer.parseInt(str.substring(i + 1, str.length() - 1));
		}
		public String toString() {
			return String.format("%d+%di", a, b);
		}
		public ComplexNumber multiply(ComplexNumber cn) {
			int a = this.a * cn.a + this.b * cn.b * -1;
			int b = this.a * cn.b + this.b * cn.a;
			this.a = a;
			this.b = b;
			return this;
		}
	}
	public String complexNumberMultiply(String a, String b) {
		ComplexNumber cn1 = new ComplexNumber(a);
		ComplexNumber cn2 = new ComplexNumber(b);
		return cn1.multiply(cn2).toString();
	}

	/**
	 *  [Easy]
	 *  #538. Convert BST to Greater Tree
	 *
	 *  Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST
	 *  is changed to the original key plus sum of all keys greater than the original key in BST.
	 *  Example:
	 *  Input: The root of a Binary Search Tree like this:
	 *     5
	 *   /   \
	 *  2     13
	 *
	 *  Output: The root of a Greater Tree like this:
	 *      18
	 *    /   \
	 *  20     13
	 */
	public TreeNode convertBST(TreeNode root) {
		// 实质就是中序遍历
		if (root == null) return root;
		List<TreeNode> list = new ArrayList<>();
		converBST_inorder(root, list);
//		for (int i = 0 ; i < list.size(); ++i) {
//			System.out.print(list.get(i).val + " ");
//		}
//		System.out.println("");
		for (int i = 0; i < list.size() - 1; ++i) {
			list.get(i + 1).val += list.get(i).val;
		}
		return root;
	}
	private void converBST_inorder(TreeNode node, List<TreeNode> list) {
		if (node.left == null && node.right == null) {
			list.add(node);
			return;
		}
		if (node.right != null) converBST_inorder(node.right, list);
		list.add(node);
		if (node.left != null) converBST_inorder(node.left, list);
	}

	/**
	 *  [Medium]
	 *  #540. Single Element in a Sorted Array
	 *
	 *  Given a sorted array consisting of only integers where every element appears twice
	 *  except for one element which appears once. Find this single element that appears only once.
	 *  Example 1:
	 *  Input: [1,1,2,3,3,4,4,8,8]
	 *  Output: 2
	 *
	 *  Example 2:
	 *  Input: [3,3,7,7,10,11,11]
	 *  Output: 10
	 *
	 *  Note: Your solution should run in O(log n) time and O(1) space.
	 */
	public int singleNonDuplicate(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int result = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			if (i % 2 == 0) {
				result += nums[i];
			} else {
				result -= nums[i];
			}
		}
		return Math.abs(result);
	}

	/**
	 *  [Medium]
	 *  #547. Friend Circles
	 *
	 *  There are N students in a class. Some of them are friends, while some are not.
	 *  Their friendship is transitive in nature. For example, if A is a direct friend of B,
	 *  and B is a direct friend of C, then A is an indirect friend of C.
	 *  And we defined a friend circle is a group of students who are direct or indirect friends.
	 *  Given a N*N matrix M representing the friend relationship between students in the class.
	 *  If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not.
	 *  And you have to output the total number of friend circles among all the students.
	 *
	 *  Example 1:
	 *  Input:
	 *  [[1,1,0],
	 *  [1,1,0],
	 *  [0,0,1]]
	 *  Output: 2
	 *  Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
	 *  The 2nd student himself is in a friend circle. So return 2.
	 *
	 *  Example 2:
	 *  Input:
	 *  [[1,1,0],
	 *  [1,1,1],
	 *  [0,1,1]]
	 *  Output: 1
	 *  Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
	 *  so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
	 *
	 *  Note:
	 *  N is in range [1,200].
	 *  M[i][i] = 1 for all students.
	 *  If M[i][j] = 1, then M[j][i] = 1.
	 */
	public int findCircleNum(int[][] M) {
		List<Set<Integer>> lSet = new ArrayList<>();
		for (int i = 0; i < M.length; ++i) {
			if (findCircleNum_hasAdded(lSet, i)) continue;
			Deque<Integer> queue = new ArrayDeque<>();
			queue.addLast(i);
			Set<Integer> group = new HashSet<>();
			while (queue.size() != 0) {
				int f = queue.pollFirst();
				group.add(f);
				for (int j = 0; j < M.length; ++j) {
					if (j != f && !group.contains(j) && M[f][j] == 1) {
						queue.addLast(j);
					}
				}
			}
			lSet.add(group);
		}
		return lSet.size();
	}
	private boolean findCircleNum_hasAdded(List<Set<Integer>> lSet, int friend) {
		for (Set<Integer> set : lSet) {
			if (set.contains(friend)) return true;
		}
		return false;
	}

	/**
     *  [Medium]
     *  #553. Optimal Division
     *
     *  Given a list of positive integers, the adjacent integers will perform the float division.
     *  For example, [2,3,4] -> 2 / 3 / 4.
     *  However, you can add any number of parenthesis at any position to change the priority of operations.
     *  You should find out how to add parenthesis to get the maximum result, and return the corresponding expression
     *  in string format. Your expression should NOT contain redundant parenthesis.
     *
     *  Example:
     *  Input: [1000,100,10,2]
     *  Output: "1000/(100/10/2)"
     *
     *  Explanation:
     *  1000/(100/10/2) = 1000/((100/10)/2) = 200
     *  However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
     *  since they don't influence the operation priority. So you should return "1000/(100/10/2)".
     *
     *  Other cases:
     *  1000/(100/10)/2 = 50
     *  1000/(100/(10/2)) = 50
     *  1000/100/10/2 = 0.5
     *  1000/100/(10/2) = 2
     *
     *  Note:
     *  The length of the input array is [1, 10].
     *  Elements in the given array will be in range [2, 1000].
     *  There is only one optimal division for each test case.
     */
    public String optimalDivision(int[] nums) {
		String result = String.valueOf(nums[nums.length - 1]);
		double value = nums[nums.length - 1];
		double prevValue = value;
		int i = nums.length - 2;
		while (i > 0) {
    		value = optimalDivision_getResult(nums, i);
    		if (value <= (nums[i] / prevValue)) {
    			result = String.format("%d/%s", nums[i], result);
				prevValue = value;
			} else {
    			result = String.format("%d/(%s)", nums[i], result);
    			prevValue = nums[i] / prevValue;
			}
			--i;
		}
		if (i == 0) {
			value = optimalDivision_getResult(nums, i);
			if (value >= (nums[i]) / prevValue) {
				result = String.format("%d/%s", nums[i], result);
			} else {
				result = String.format("%d/(%s)", nums[i], result);
			}
		}
        return result;
    }
    private double optimalDivision_getResult(int[] nums, int from) {
    	double result = nums[from];
    	for (int i = from + 1; i < nums.length; ++i) {
    		result /= nums[i];
		}
		return result;
	}

	/**
	 *  [Easy]
	 *  #557 Reverse Words in a String III
	 *
	 *  Given a string, you need to reverse the order of characters in each word within a sentence
	 *  while still preserving whitespace and initial word order.
	 *
	 *  Example 1:
	 *  Input: "Let's take LeetCode contest"
	 *  Output: "s'teL ekat edoCteeL tsetnoc"
	 *
	 *  Note: In the string, each word is separated by single space and there will not be any extra space in the string.
	 */
	public String reverseWordsIII(String s) {
		StringBuilder result = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; --i) {
			char c = s.charAt(i);
			if (c == ' ') {
				result.insert(0, c + sb.toString());
				sb = new StringBuilder();
			} else {
				sb.append(c);
			}
		}
		if (sb.length() != 0) {
			result.insert(0, sb.toString());
		}
		return result.toString();
	}

	/**
	 *  [easy]
	 *  #561. Array Partition I
	 *
	 *  Given an array of 2n integers, your task is to group these integers into n pairs of integer,
	 *  say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
	 *
	 *  Example 1:
	 *  Input: [1,4,3,2]
	 *  Output: 4
	 *  Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	 *
	 *  Note:
	 *  n is a positive integer, which is in the range of [1, 10000].
	 *  All the integers in the array will be in the range of [-10000, 10000].
	 */
	public int arrayPairSum(int[] nums) {
		arrayPairSum_quickSort(nums, 0, nums.length - 1);
		int sum = 0;
		for (int i = 0; i < nums.length; i += 2) {
			sum += nums[i];
		}
		return sum;
	}

	private void arrayPairSum_quickSort(int[] nums, int start, int end) {
		if (start >= end) return;
		int index = arrayPairSum_getIndex(nums, start, end);
		arrayPairSum_quickSort(nums, start, index - 1);
		arrayPairSum_quickSort(nums, index + 1, end);
	}

	private int arrayPairSum_getIndex(int[] nums, int start, int end) {
		int i = start;
		int j = end;
		int f = nums[i];
		while (i < j) {
			while (i < j && nums[j] >= f) --j;
			nums[i] = nums[j];
			while (i < j && nums[i] <= f) ++i;
			nums[j] = nums[i];
		}
		nums[i] = f;
		return i;
	}

    /**
     *  [Medium]
     *  #565. Array Nesting
     *  A zero-indexed array A consisting of N different integers is given. The array contains all integers
     *  in the range [0, N - 1].
     *  Sets S[K] for 0 <= K < N are defined as follows:
     *  S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.
     *  Sets S[K] are finite for each K and should NOT contain duplicates.
     *  Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for
     *  this array.
     *
     *  Example 1:
     *  Input: A = [5,4,0,3,1,6,2]
     *  Output: 4
     *  Explanation:
     *  A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
     *
     *  One of the longest S[K]:
     *  S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
     *
     *  Note:
     *  N is an integer within the range [1, 20,000].
     *  The elements of A are all distinct.
     *  Each element of array A is an integer within the range [0, N-1].
     */
    public int arrayNesting(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	boolean[] flags = new boolean[nums.length];
    	Arrays.fill(flags, true);
    	int maxSize = 0;
    	int size = 0;
    	int f = 0;
    	int i = f;
    	while (f < nums.length && i < nums.length) {
    		if (flags[i]) {
    			flags[i] = false;
    			i = nums[i];
    			++size;
			} else {
    			if (size > maxSize) {
					maxSize = size;
				}
				size = 0;
				while (f < nums.length && !flags[f]) {
					++f;
				}
				i = f;
			}
		}
		if (size > maxSize) {
			maxSize = size;
		}

        return maxSize;
    }

	/**
	 *  [easy]
	 *  #566. Reshape the Matrix
	 *  In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with
	 *  different size but keep its original data.
	 *  You're given a matrix represented by a two-dimensional array, and two positive integers r and c
	 *  representing the row number and column number of the wanted reshaped matrix, respectively.
	 *  The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing
	 *  order as they were.
	 *  If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix;
	 *  Otherwise, output the original matrix.
	 *
	 *  Example 1:
	 *  Input:
	 *  nums =
	 *  [[1,2],
	 *  [3,4]]
	 *  r = 1, c = 4
	 *  Output:
	 *  [[1,2,3,4]]
	 *  Explanation:
	 *  The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix,
	 *  fill it row by row by using the previous list.
	 *
	 *  Example 2:
	 *  Input:
	 *  nums =
	 *  [[1,2],
	 *  [3,4]]
	 *  r = 2, c = 4
	 *  Output:
	 *  [[1,2],
	 *  [3,4]]
	 *  Explanation:
	 *  There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
	 *
	 * Note:
	 * The height and width of the given matrix is in range [1, 100].
	 * The given r and c are all positive.
	 */
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int r0 = nums.length;
		int c0 = nums[0].length;
		int size = r0 * c0;
		int count = r * c;
		if (size < count) return nums;
		int[][] result = new int[r][c];
		int i = 0;
		while (i < count) {
			result[i/c][i%c] = nums[i/c0][i%c0];
			++i;
		}
		return result;
	}

	/**
	 *  [easy]
	 *  #575. Distribute Candies
	 *  Given an integer array with even length, where different numbers in this array represent different kinds of candies.
	 *  Each number means one candy of the corresponding kind. You need to distribute these candies equally in number
	 *  to brother and sister. Return the maximum number of kinds of candies the sister could gain.
	 *  Example 1:
	 *  Input: candies = [1,1,2,2,3,3]
	 *  Output: 3
	 *  Explanation:
	 *  There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
	 *  Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
	 *  The sister has three different kinds of candies.
	 *
	 *  Example 2:
	 *  Input: candies = [1,1,2,3]
	 *  Output: 2
	 *  Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
	 *  The sister has two different kinds of candies, the brother has only one kind of candies.
	 *
	 *  Note:
	 *  The length of the given array is in range [2, 10,000], and will be even.
	 *  The number in given array is in range [-100,000, 100,000].
	 */
	public int distributeCandies(int[] candies) {
		int num = candies.length / 2;
		Set<Integer> kindsSet = new HashSet<>();
		for (int c : candies) {
			kindsSet.add(c);
		}
		return Math.min(num, kindsSet.size());
	}

	/**
	 *  [Easy]
	 *  #606. Construct String from Binary Tree
	 *
	 *  You need to construct a string consists of parenthesis and integers from a binary tree with the preorder
	 *  traversing way.
	 *  The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty
	 *  parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original
	 *  binary tree.
	 *
	 *  Example 1:
	 *  Input: Binary tree: [1,2,3,4]
	 *        1
	 *      /   \
	 *    2     3
	 *   /
	 *  4
	 *
	 *  Output: "1(2(4))(3)"
	 *
	 *  Explanation: Originally it needs to be "1(2(4)())(3()())",
	 *  but you need to omit all the unnecessary empty parenthesis pairs.
	 *  And it will be "1(2(4))(3)".
	 *
	 *  Example 2:
	 *  Input: Binary tree: [1,2,3,null,4]
	 *     1
	 *   /   \
	 *  2     3
	 *   \
	 *    4
	 *
	 *  Output: "1(2()(4))(3)"
	 *
	 *  Explanation: Almost the same as the first example,
	 *  except we can't omit the first parenthesis pair to break the one-to-one mapping relationship
	 *  between the input and the output.
	 */
	public String tree2str(TreeNode t) {
		StringBuilder sb = new StringBuilder();
		if (t != null) {
			tree2str(t, sb);
		}
		return sb.toString();
	}
	public void tree2str(TreeNode node, StringBuilder sb) {
		if (node.left == null && node.right == null) {
			sb.append(node.val);
			return;
		}
		sb.append(node.val);
		sb.append("(");
		if (node.left != null) tree2str(node.left, sb);
		sb.append(")");
		if (node.right != null) {
			sb.append("(");
			tree2str(node.right, sb);
			sb.append(")");
		}
	}

	/**
	 *  [Medium]
	 *  #609. Find Duplicate File in System
	 *
	 *  Given a list of directory info including directory path, and all the files with contents in this directory,
	 *  you need to find out all the groups of duplicate files in the file system in terms of their paths.
	 *  A group of duplicate files consists of at least two files that have exactly the same content.
	 *  A single directory info string in the input list has the following format:
	 *
	 *  "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
	 *
	 *  It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content,
	 *  respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0.
	 *  If m = 0, it means the directory is just the root directory.
	 *  The output is a list of group of duplicate file paths.
	 *  For each group, it contains all the file paths of the files that have the same content.
	 *  A file path is a string that has the following format:
	 *  "directory_path/file_name.txt"
	 *
	 *  Example 1:
	 *  Input:
	 *  ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
	 *  Output:
	 *  [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

	 *  Note:
	 *  No order is required for the final output.
	 *  You may assume the directory name, file name and file content only has letters and digits,
	 *  and the length of file content is in the range of [1,50].
	 *  The number of files given is in the range of [1,20000].
	 *  You may assume no files or directories share the same name in the same directory.
	 *  You may assume each given directory info represents a unique directory.
	 *  Directory path and file info are separated by a single blank space.
	 *
	 *  Follow-up beyond contest:
	 *  Imagine you are given a real file system, how will you search files? DFS or BFS?
	 *  If the file content is very large (GB level), how will you modify your solution?
	 *  If you can only read the file by 1kb each time, how will you modify your solution?
	 *  What is the time complexity of your modified solution?
	 *  What is the most time-consuming part and memory consuming part of it? How to optimize?
	 *  How to make sure the duplicated files you find are not false positive?
	 */
	public List<List<String>> findDuplicate(String[] paths) {
		List<List<String>> llist = new ArrayList<>();
		if (paths == null || paths.length == 0) return llist;
		Map<String, List<String>> map = new HashMap<>();
		for (int i = 0; i < paths.length; ++i) {
			String[] strs = paths[i].split("[ ]");
			String path = strs[0];
			for (int j = 1; j < strs.length; ++j) {
				String fc = strs[j];
				int index = fc.indexOf("(");
				String filename = fc.substring(0, index);
				String content = fc.substring(index + 1, fc.length() - 1);
				String fullPath = String.format("%s/%s", path, filename);
				if (map.containsKey(content)) {
					List<String> l = map.get(content);
					l.add(fullPath);
					map.put(content, l);
				} else {
					List<String> l = new ArrayList<>();
					l.add(fullPath);
					map.put(content, l);
				}
			}
		}
		for (String c : map.keySet()) {
			List<String> t = map.get(c);
			if (t.size() > 1) {
				llist.add(t);
			}
		}
		return llist;
	}

	/**
	 *  [easy]
	 *  #617. Merge Two Binary Trees
	 *
	 *  Given two binary trees and imagine that when you put one of them to cover the other,
	 *  some nodes of the two trees are overlapped while the others are not.
	 *  You need to merge them into a new binary tree. The merge rule is that if two nodes overlap,
	 *  then sum node values up as the new value of the merged node. Otherwise,
	 *  the NOT null node will be used as the node of new tree.
	 *
	 *  Example 1:
	 *  Input:
	 *  Tree 1                     Tree 2
	 *      1                         2
	 *     / \                       / \
	 *    3   2                     1   3
	 *   /                           \   \
	 *  5                             4   7
	 *
	 *  Output:
	 *  Merged tree:
	 *       3
	 *      / \
	 *     4   5
	 *    / \   \
	 *  5   4   7
	 *  Note: The merging process must start from the root nodes of both trees.
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		TreeNode root = null;
		if (t1 == null && t2 == null) return root;
		else if (t1 == null) {
			root = new TreeNode(t2.val);
			root.left = t2.left;
			root.right = t2.right;
		}
		else if (t2 == null) {
			root = new TreeNode(t1.val);
			root.left = t1.left;
			root.right = t1.right;
		}
		else {
			root = mergeTrees_merge(t1, t2);
			root.left = mergeTrees(t1.left, t2.left);
			root.right = mergeTrees(t1.right, t2.right);
		}
		return root;
	}

	private TreeNode mergeTrees_merge(TreeNode node1, TreeNode node2) {
		int sum = 0;
		if (node1 != null) sum += node1.val;
		if (node2 != null) sum += node2.val;
		return new TreeNode(sum);
	}

	/**
	 *  [easy]
	 *  #637. Average of Levels in Binary Tree
	 *  Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
	 *  Example 1:
	 *  Input:
	 *    3
	 *   / \
	 *  9  20
	 *     /  \
	 *    15   7
	 *  Output: [3, 14.5, 11]
	 *  Explanation:
	 *  The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
	 *
	 *  Note:
	 *  The range of node's value is in the range of 32-bit signed integer.
	 */
	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> result = new ArrayList<>();
		Deque<TreeNode> queue1 = new ArrayDeque<>();
		Deque<TreeNode> queue2 = new ArrayDeque<>();
		queue1.addLast(root);
		while (queue1.size() != 0 || queue2.size() != 0) {
			double sum = 0;
			double count = 0;
			while (queue1.size() != 0) {
				TreeNode node = queue1.pollFirst();
				sum += node.val;
				++count;
				if (node.left != null) queue2.addLast(node.left);
				if (node.right != null) queue2.addLast(node.right);
			}
			if (count != 0) result.add(sum / count);
			sum = 0;
			count = 0;
			while (queue2.size() != 0) {
				TreeNode node = queue2.pollFirst();
				sum += node.val;
				++count;
				if (node.left != null) queue1.addLast(node.left);
				if (node.right != null) queue1.addLast(node.right);
			}
			if (count != 0) result.add(sum / count);
		}
		return result;
	}

	/**
	 *  [Medium]
	 *  #647. Palindromic Substrings
	 *
	 *  Given a string, your task is to count how many palindromic substrings in this string.
	 *  The substrings with different start indexes or end indexes are counted as different substrings
	 *  even they consist of same characters.
	 *
	 *  Example 1:
	 *  Input: "abc"
	 *  Output: 3
	 *  Explanation: Three palindromic strings: "a", "b", "c".
	 *
	 *  Example 2:
	 *  Input: "aaa"
	 *  Output: 6
	 *  Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
	 *
	 *  Note:
	 *  The input string length won't exceed 1000.
	 */
	public int countSubstrings(String s) {
		int result = 0;
		for (int m = 0; m < s.length(); ++m) {
			int i = m;
			int j = m;
			while (i <= j && 0 <= i && j <= s.length() - 1) {
				if (s.charAt(i) == s.charAt(j)) {
					++result;
				} else {
					break;
				}
				--i;
				++j;
			}
		}
		for (int m = 0; m < s.length() - 1; ++m) {
			int i = m;
			int j = m + 1;
			while (i < j && 0 <= i && j < s.length()) {
				if (s.charAt(i) == s.charAt(j)) {
					++result;
				} else {
					break;
				}
				--i;
				++j;
			}
		}
		return result;
	}

	/**
	 *  [Easy]
	 *  #653. Two Sum IV - Input is a BST
	 *
	 *  Given a Binary Search Tree and a target number, return true if there exist two elements in the BST
	 *  such that their sum is equal to the given target.
	 *
	 *  Example 1:
	 *  Input:
	 *      5
	 *     / \
	 *    3   6
	 *   / \   \
	 *  2   4   7
	 *
	 *  Target = 9
	 *
	 *  Output: True
	 *
	 *  Example 2:
	 *  Input:
	 *      5
	 *     / \
	 *    3   6
	 *   / \   \
	 *  2   4   7
	 *
	 *  Target = 28
	 *
	 *  Output: False
	 */
	public boolean findTarget(TreeNode root, int k) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (queue.size() != 0) {
            TreeNode node = queue.pollFirst();
            list.add(node);
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < list.size(); ++i) {
            int val = list.get(i).val;
            if (set.contains(val)) return true;
            set.add(val);
            set.add(k - val);
        }
		return false;
	}

	/**
	 *  [Medium]
	 *  #654. Maximum Binary Tree
	 *
	 *  Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
	 *  - The root is the maximum number in the array.
	 *  - The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
	 *  - The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
	 *
	 *  Construct the maximum tree by the given array and output the root node of this tree.
	 *  Example 1:
	 *  Input: [3,2,1,6,0,5]
	 *  Output: return the tree root node representing the following tree:
	 *
	 *      6
	 *   /    \
	 *  3      5
	 *  \     /
	 *   2   0
	 *    \
	 *    1
	 *
	 *  Note:
	 *  The size of the given array will be in the range [1,1000].
	 */
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null || nums.length == 0) return null;
		int maxIndex = constructMaximumBinaryTree_findMax(nums, 0, nums.length);
		TreeNode root = new TreeNode(nums[maxIndex]);
		root.left = constructMaximumBinaryTree(nums, 0, maxIndex);
		root.right = constructMaximumBinaryTree(nums, maxIndex + 1, nums.length);
		return root;
	}
	private int constructMaximumBinaryTree_findMax(int[] nums, int from, int to) {
		int i = from;
		int index = from;
		int max = nums[i];
		for (; i < to; ++i) {
			if (nums[i] > max) {
				max = nums[i];
				index = i;
			}
		}
		return index;
	}
	private TreeNode constructMaximumBinaryTree(int[] nums, int from, int to) {
		if (from >= to) return null;
		int maxIndex = constructMaximumBinaryTree_findMax(nums, from, to);
		TreeNode node = new TreeNode(nums[maxIndex]);
		node.left = constructMaximumBinaryTree(nums, from, maxIndex);
		node.right = constructMaximumBinaryTree(nums, maxIndex + 1, to);
		return node;
	}

	/**
	 *  [Medium]
	 *  #655. Print Binary Tree
	 *
	 *  Print a binary tree in an m*n 2D string array following these rules:
	 *  The row number m should be equal to the height of the given binary tree.
	 *  The column number n should always be an odd number.
	 *  The root node's value (in string format) should be put in the exactly middle of the first row it can be put.
	 *  The column and the row where the root node belongs will separate the rest space into two parts
	 *  (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part
	 *  and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part
	 *  should have the same size. Even if one subtree is none while the other is not, you don't need to print
	 *  anything for the none subtree but still need to leave the space as large as that for the other subtree.
	 *  However, if two subtrees are none, then you don't need to leave space for both of them.
	 *  Each unused space should contain an empty string "".
	 *  Print the subtrees following the same rules.
	 *  Example 1:
	 *  Input:
	 *    1
	 *   /
	 *  2
	 *  Output:
	 *  [["", "1", ""],
	 *  ["2", "", ""]]
	 *
	 *  Example 2:
	 *  Input:
	 *    1
	 *   / \
	 *  2   3
	 *   \
	 *   4
	 *  Output:
	 *  [["", "", "", "1", "", "", ""],
	 *  ["", "2", "", "", "", "3", ""],
	 *  ["", "", "4", "", "", "", ""]]
	 *
	 *  Example 3:
	 *  Input:
	 *        1
	 *       / \
	 *      2   5
	 *     /
	 *    3
	 *   /
	 *  4
	 *  Output:
	 *
	 *  [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
	 *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
	 *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
	 *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
	 *
	 *  Note: The height of binary tree is in the range of [1, 10].
	 */
	public List<List<String>> printTree(TreeNode root) {
		List<List<String>> llist = new ArrayList<>();
		if (root != null) {
			int height = printTree_getHeight(root);
			int width = (int)(Math.pow(2, height) - 1);
			for (int i = 0; i < height; ++i) {
				String[] array = new String[width];
				Arrays.fill(array, "");
				llist.add(Arrays.asList(array));
			}
			int index = width / 2;
			llist.get(0).set(index, String.valueOf(root.val));
			printTree_searchTree(llist, root, index, 0, width - 1,1);
		}
		return llist;
	}
	public int printTree_getHeight(TreeNode node) {
		if (node.left == null && node.right == null) {
			return 1;
		}
		int height = 1;
		int heightL = 0;
		int heightR = 0;
		if (node.left != null) {
			heightL = printTree_getHeight(node.left);
		}
		if (node.right != null) {
			heightR = printTree_getHeight(node.right);
		}
		return height + Math.max(heightL, heightR);
	}
	private void printTree_searchTree(List<List<String>> llist, TreeNode node, int nodeIndex, int from, int to, int height) {
		if (node.left == null && node.right == null) {
			return;
		}
		if (node.left != null) {
			int index = (from + nodeIndex - 1) / 2;
			llist.get(height).set(index, String.valueOf(node.left.val));
			printTree_searchTree(llist, node.left, index, from, nodeIndex - 1, height + 1);
		}
		if (node.right != null) {
			int index = (nodeIndex + 1 + to) / 2;
			llist.get(height).set(index, String.valueOf(node.right.val));
			printTree_searchTree(llist, node.right, index, nodeIndex + 1, to, height + 1);
		}
	}

	/**
	 *  #778
	 */
	public enum VertexStatus {
		UNDISCOVERED,
		DISCOVERED,
		PROCESSED;
	}
	public static class EdgeNode1 {
		int y;
		EdgeNode1 next;
		public EdgeNode1(int y, EdgeNode1 next) {
			this.y = y;
			this.next = next;
		}
	}
	public static class Graph1 {
		EdgeNode1[] edges;
		VertexStatus[] status;
		boolean isDirected = false;
		int nVertices;
		int nEdges;
		public Graph1(int size) {
			this.nVertices = size;
			this.nEdges = 0;
			this.edges = new EdgeNode1[size];
			this.status = new VertexStatus[size];
			Arrays.fill(edges, null);
		}
		public void insert(int x, int y, boolean isDirected) {
			EdgeNode1 e = new EdgeNode1(y, this.edges[x]);
			this.edges[x] = e;
			this.nEdges++;
			if (!isDirected) {
				insert(y, x, true);
			}
		}
		public void bfs(int start) {
			if (start < 0 || start >= nVertices) return;
			Arrays.fill(status, VertexStatus.UNDISCOVERED);
			Deque<Integer> stack = new ArrayDeque<>();
			stack.offerFirst(start);
			while (!stack.isEmpty()) {
				int x = stack.pollFirst();
				EdgeNode1 edge = edges[x];
				while (edge != null) {
					int y = edge.y;
					if (status[y] == VertexStatus.UNDISCOVERED) {
						status[y] = VertexStatus.DISCOVERED;
						stack.offerFirst(y);
					} else if (status[y] != VertexStatus.PROCESSED || !isDirected) {
						// TODO;
					}

					edge = edge.next;
				}
				status[x] = VertexStatus.PROCESSED;
			}
		}
		public boolean isConnected(int start, int end) {
			this.bfs(start);
			return VertexStatus.PROCESSED.equals(status[end]);
		}

	}
	public static class Point {
		public int x;
		public int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public int swimInWater(int[][] grid) {
		int n = grid.length;
		int len = n * n;
		Map<Integer, Point> map = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				map.put(grid[i][j], new Point(i, j));
			}
		}
		Graph1 g1 = new Graph1(len);
		int time = 1;
		while (time < len) {
			Point p = map.get(time);
            int y = p.x * n + p.y;
			int v = getValue(grid, p.x -1, p.y);
			if (v != -1 && v <= time) {
			    int x = (p.x - 1) * n + p.y;
				g1.insert(x, y, false);
			}
			v = getValue(grid, p.x + 1, p.y);
			if (v != -1 && v <= time) {
			    int x = (p.x + 1) * n + p.y;
				g1.insert(x, y, false);
			}
			v = getValue(grid, p.x, p.y - 1);
			if (v != -1 && v <= time) {
			    int x = p.x * n + p.y - 1;
				g1.insert(x, y, false);
			}
			v = getValue(grid, p.x, p.y + 1);
			if (v != -1 && v <= time) {
			    int x = p.x * n + p.y + 1;
				g1.insert(x, y, false);
			}
			if (g1.isConnected(0, len - 1)) {
				return time;
			}

			time++;
		}
		return time;
	}
	public int getValue(int[][] grid, int x, int y) {
		if (x < 0 || x >= grid.length) return -1;
		if (y < 0 || y >= grid[0].length) return -1;
		return grid[x][y];
	}

    /**
     *  #807. Max Increase to Keep City Skyline
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[][] skyline = new int[2][n];
        skyline[0] = new int[n];  // horizon
        skyline[1] = new int[n];  //vertical
        for (int i = 0; i < n; ++i) {
            int hMax = 0;
            int vMax = 0;
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] > hMax) hMax = grid[i][j];
                if (grid[j][i] > vMax) vMax = grid[j][i];
            }
            skyline[0][i] = hMax;
            skyline[1][i] = vMax;
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sum += Math.min(skyline[0][i], skyline[1][j]) - grid[i][j];
            }
        }
        return sum;
    }
}
