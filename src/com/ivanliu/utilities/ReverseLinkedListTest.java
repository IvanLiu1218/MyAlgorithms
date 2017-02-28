package com.ivanliu.utilities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ivanliu.utilities.ReverseLinkedList.ListNode;

public class ReverseLinkedListTest {
	
	private ListNode<Integer> list = null;

	@Before
	public void before() {
		list = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, new ListNode<>(5, new ListNode<>(6, new ListNode<>(7)))))));
	}
	
	@Test
	public void testRecursive() {
		ListNode<Integer> newList = ReverseLinkedList.recursive(list);
		assertEquals("7->6->5->4->3->2->1", ListNode.getAsString(newList));
	}
	
	@Test
	public void testNonRecursive() {
		ListNode<Integer> newList = ReverseLinkedList.nonRecursive(list);
		assertEquals("7->6->5->4->3->2->1", ListNode.getAsString(newList));
	}

}
