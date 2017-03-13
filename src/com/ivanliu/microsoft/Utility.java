package com.ivanliu.microsoft;

public class Utility {
	
	public static final String ARROW_LEFT = "<-";
	public static final String ARROW_RIGHT = "->";
	
	public static class BSTreeNode {
		public int value;
		public BSTreeNode left;
		public BSTreeNode right;
		public BSTreeNode(int val, BSTreeNode left, BSTreeNode right) {
			this.value = val;
			this.left = left;
			this.right = right;
		}
		public BSTreeNode(int val) {
			this(val, null, null);
		}
	}
	
	public static String toStringByRight(BSTreeNode node) {
		if (node == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(node.value);
		while (node.right != null) {
			sb.append(ARROW_RIGHT + node.right.value);
			node = node.right;
		}
		return sb.toString();
	}
	public static String toStringByLeft(BSTreeNode node) {
		if (node == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(node.value);
		while (node.left != null) {
			sb.insert(0, node.left.value + ARROW_LEFT);
			node = node.left;
		}
		return sb.toString();
	}
}
