package com.ivanliu.microsoft;

import java.util.ArrayList;
import java.util.List;

import com.ivanliu.microsoft.Utility.BSTreeNode;

public class Solution {

	/**
	 *  #001 把二元查找树转变成排序的双向链表
	 *  输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
	 *  要求不能创建任何新的结点，只调整指针的指向。
	 *  
	 *      10
	 *     /   \
	 *    6    14
	 *   / \   / \
	 *  4   8 12 16
	 *  
	 *  转换成双向链表
	 *  4=6=8=10=12=14=16。
	 *  
	 *   left        right
	 *  <-----|value|----->
	 */
	private List<BSTreeNode> treeToLinkedList_list = null;
	private BSTreeNode treeToLinkedList_tail = null;
	public BSTreeNode treeToLinkedList(BSTreeNode root) {
		//return treeToLinkedList_solution1(root);
		return treeToLinkedList_solution2(root);
	}
	public BSTreeNode treeToLinkedList_getTail(BSTreeNode root) {
		//return treeToLinkedList_solution1_getTail(null);
		return treeToLinkedList_tail;
	}
	// Solution #2
	public BSTreeNode treeToLinkedList_solution2(BSTreeNode root) {
		treeToLinkedList_tail = null;
		BSTreeNode head = treeToLinkedList_solution2_getRightHead(root);
		BSTreeNode tail = head;
		while (tail.right != null) {
			tail = tail.right;
		}
		treeToLinkedList_tail = tail;
		return head;
	}
	private BSTreeNode treeToLinkedList_solution2_getLeftTail(BSTreeNode node) {
		if (node == null) return null;
		if (node.left == null && node.right == null) {
			return node;
		}
		BSTreeNode leftTail = treeToLinkedList_solution2_getLeftTail(node.left);
		BSTreeNode rightHead = treeToLinkedList_solution2_getRightHead(node.right);
		if (leftTail != null) {
			leftTail.right = node;
			node.left = leftTail;
		}
		if (rightHead != null) {
			rightHead.left = node;
			node.right = rightHead;
		}
		BSTreeNode rightTail = node;
		while (rightTail.right != null) {
			rightTail = rightTail.right;
		}
		return rightTail;
		
	}
	public BSTreeNode treeToLinkedList_solution2_getRightHead(BSTreeNode node) {
		if (node == null) return null;
		if (node.left == null && node.right == null) {
			return node;
		}
		BSTreeNode leftTail = treeToLinkedList_solution2_getLeftTail(node.left);
		BSTreeNode rightHead = treeToLinkedList_solution2_getRightHead(node.right);
		if (leftTail != null) {
			leftTail.right = node;
			node.left = leftTail;
		}
		if (rightHead != null) {
			rightHead.left = node;
			node.right = rightHead;
		}
		BSTreeNode leftHead = node;
		while (leftHead.left != null) {
			leftHead = leftHead.left;
		}
		return leftHead;
	}
	
	// Solution #1
	public BSTreeNode treeToLinkedList_solution1(BSTreeNode root) {
		if (root == null) return null;
		treeToLinkedList_list = new ArrayList<BSTreeNode>();
		treeToLinkedList_searchTree(root);
		BSTreeNode prev = null;
		for (int i = 0; i < treeToLinkedList_list.size(); ++i) {
			BSTreeNode node = treeToLinkedList_list.get(i);
			node.left = prev;
			node.right = null;
			if (i + 1 < treeToLinkedList_list.size()) {
				node.right = treeToLinkedList_list.get(i + 1);
			}
			prev = node;
		}
		return treeToLinkedList_list.get(0);
	}
	public BSTreeNode treeToLinkedList_solution1_getTail(BSTreeNode root) {
		if (treeToLinkedList_list == null || treeToLinkedList_list.isEmpty()) return null;
		return treeToLinkedList_list.get(treeToLinkedList_list.size() - 1);
	}
	private void treeToLinkedList_searchTree(BSTreeNode node) {
		if (node == null) {
			return ;
		} else {
			treeToLinkedList_searchTree(node.left);
			treeToLinkedList_list.add(node);
			treeToLinkedList_searchTree(node.right);
		}
	}
}
