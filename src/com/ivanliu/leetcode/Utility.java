package com.ivanliu.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Utility {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode (int x) {
			this(x, null);
		}
		ListNode (int x, ListNode n) {
			val = x;
			next = n;
		}
	}
	
	public static ListNode buildListNode(int[] nums) {
		if (nums.length == 0) return null;
		ListNode head = new ListNode(nums[0]);
		ListNode node = head;
		for (int i = 1; i < nums.length; ++i) {
			node.next = new ListNode(nums[i]);
			node = node.next;
		}
		return head;
	}
	
	public static String toString(ListNode list) {
		StringBuilder result = new StringBuilder("[");
		ListNode node = list;
		while (node != null) {
			result.append(node.val);
			if (node.next != null) {
				result.append(",");
			}
			node = node.next;
		}
		result.append("]");
		return result.toString();
	}
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public static void print(List<TreeNode> list) {
		if (list.size() <= 0) return;
		System.out.print(list.get(0).val);
		for (int i = 1; i < list.size(); ++i) {
			System.out.print(String.format(",%d", list.get(i).val));
		}
		System.out.println(' ');
	}
	
	public static String toString(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (root == null) sb.append("null");
		else {
			TreeNode none = new TreeNode(Integer.MAX_VALUE);
			sb.append(root.val);
			Deque<TreeNode> queue = new ArrayDeque<>();
			if (root.left == null) queue.addLast(none);
			else queue.addLast(root.left);
			if (root.right == null) queue.addLast(none);
			else queue.addLast(root.right);
			while (queue.size() != 0) {
				TreeNode node = queue.poll();
				if (node.equals(none)) sb.append(",null");
				else {
					sb.append(String.format(",%d", node.val));
					if (node.left == null) queue.addLast(none);
					else queue.addLast(node.left);
					if (node.right == null) queue.addLast(none);
					else queue.addLast(node.right);
				} 
			}
		}
		sb.append(']');
		return sb.toString();
	}
	
	public static class TreeLinkNode {
		int val;
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		TreeLinkNode(int x) { val = x; }
	}
}
