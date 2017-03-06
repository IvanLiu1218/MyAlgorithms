package com.ivanliu.utilities;

public class ReverseLinkedList {
	
	public static class ListNode<T> {
		
		private T value;
		private ListNode<T> next;
		
		public ListNode(T val, ListNode<T> next) {
			this.value = val;
			this.next = next;
		}
		
		public ListNode(T val) {
			this(val, null);
		}
		
		public static String getAsString(ListNode<?> list) {
			if (list == null) return "";
			StringBuilder sb = new StringBuilder();
			ListNode<?> node = list;
			sb.append(node.value);
			while (node.next != null) {
				node = node.next;
				sb.append("->" + node.value);
			}
			return sb.toString();
		}
	}
	
	public static <T> ListNode<T> recursive(ListNode<T> list) {
		if (list.next == null) {
			return list;
		} else {
			ListNode<T> tail = list.next;
			ListNode<T> newHead = recursive(list.next);
			tail.next = list;
			list.next = null;
			return newHead;
		}
	}
	
	public static <T> ListNode<T> nonRecursive(ListNode<T> list) {
		ListNode<T> prev = null;
		ListNode<T> next = null;
		ListNode<T> curr = list;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}
}
