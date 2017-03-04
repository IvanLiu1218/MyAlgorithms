package com.ivanliu.learn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AboutTree {
	
	public static final String SPLITER = ",";

	public static class TreeNode<T> {
		public T value;
		public TreeNode<T> left;
		public TreeNode<T> right;
		public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
			this.value = val;
			this.left = left;
			this.right = right;
		}
		public TreeNode(T val) {
			this(val, null, null);
		}
	}
	
	// DFS: Depth First Search
	public static <T> String toStringByDFS(TreeNode<T> root) {
		if (root == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(root.value);
		Deque<TreeNode<T>> stack = new ArrayDeque<>();
		if (root.right != null) stack.push(root.right);
		if (root.left != null) stack.push(root.left);
		while (stack.size() != 0) {
			TreeNode<T> node = stack.pop();
			sb.append(SPLITER + node.value);
			if (node.right != null) stack.push(node.right);
			if (node.left != null) stack.push(node.left);
		}
		return sb.toString();
	}
	
	// BFS: Breadth First Search
	public static <T> String toStringByBFS(TreeNode<T> root) {
		if (root == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(root.value);
		Deque<TreeNode<T>> queue = new ArrayDeque<>();
		if (root.left != null) queue.offerLast(root.left);
		if (root.right != null) queue.offerLast(root.right);
		while (queue.size() != 0) {
			TreeNode<T> node = queue.pollFirst();
			sb.append(SPLITER + node.value);
			if (node.left != null) queue.offerLast(node.left);
			if (node.right != null) queue.offerLast(node.right);
		}
		return sb.toString();
	}
	
	public static <T> List<String> getAllPaths(TreeNode<T> root) {
		List<String> paths = new ArrayList<>();
		String path = new String("");
		getAllPaths(root, path, paths);
		return paths;
	}
	
	private static <T> void getAllPaths(TreeNode<T> node, String path, List<String> paths) {
		if (node == null) return;
		else if (node.left == null && node.right == null) {
			path += node.value;
			paths.add(path);
		} else {
			path += node.value;
			getAllPaths(node.left, path, paths);
			getAllPaths(node.right, path, paths);
		}
	}
	
	public static int getTotal(TreeNode<String> root) {
		int total = 0;
		if (root != null) {
			Deque<TreeNode<String>> queue = new ArrayDeque<>();
			queue.offerLast(root);
			while (queue.size() != 0) {
				TreeNode<String> node = queue.pollFirst();
				if (node.left == null && node.right == null) {
					total += Integer.parseInt(node.value);
				}
				if (node.left != null) {
					node.left.value = node.value + node.left.value;
					queue.offerLast(node.left);
				}
				if (node.right != null) {
					node.right.value = node.value + node.right.value;
					queue.offerLast(node.right);
				}
			}
		}
		return total;
	}
}
