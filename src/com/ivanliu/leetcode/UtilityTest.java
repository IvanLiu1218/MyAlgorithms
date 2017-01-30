package com.ivanliu.leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ivanliu.leetcode.Utility.ListNode;
import com.ivanliu.leetcode.Utility.TreeNode;

public class UtilityTest {

	@Test
	public void testBuildListNode() {
		ListNode list = Utility.buildListNode(new int[] {1,2,3,4,5});
		assertEquals("[1,2,3,4,5]", Utility.toString(list));
	}
	
	@Test
	public void testToStringTreeNode() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		assertEquals("[1,2,3,4,5,6,7,null,null,null,null,null,null,null,null]", Utility.toString(root));
	}

}
