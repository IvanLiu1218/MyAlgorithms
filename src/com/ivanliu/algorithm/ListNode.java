package com.ivanliu.algorithm;

public class ListNode {
    public int value;
    public ListNode next;
    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
    public ListNode(int value) {
        this(value, null);
    }
}
