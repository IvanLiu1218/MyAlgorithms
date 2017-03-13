package com.ivanliu.microsoft;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ivanliu.microsoft.Utility.BSTreeNode;

public class SolutionTest {
	Solution solution = new Solution();

	@Test
	public void test001() {
		BSTreeNode root = new BSTreeNode(10);
		root.left = new BSTreeNode(6);
		root.left.left = new BSTreeNode(4);
		root.left.right = new BSTreeNode(8);
		root.right = new BSTreeNode(14);
		root.right.left = new BSTreeNode(12);
		root.right.right = new BSTreeNode(16);
		BSTreeNode head = solution.treeToLinkedList_solution1(root);
		BSTreeNode tail = solution.treeToLinkedList_solution1_getTail(root);
		assertEquals("4->6->8->10->12->14->16", Utility.toStringByRight(head));
		assertEquals("4<-6<-8<-10<-12<-14<-16", Utility.toStringByLeft(tail));
		
		root = new BSTreeNode(10);
		root.left = new BSTreeNode(6);
		root.left.left = new BSTreeNode(4);
		root.left.right = new BSTreeNode(8);
		root.right = new BSTreeNode(14);
		root.right.left = new BSTreeNode(12);
		root.right.right = new BSTreeNode(16);
		BSTreeNode node = solution.treeToLinkedList_solution2(root);
		assertEquals("4->6->8->10->12->14->16", Utility.toStringByRight(node));
		assertEquals("4<-6<-8<-10<-12<-14<-16", Utility.toStringByLeft(tail));
	}

}
