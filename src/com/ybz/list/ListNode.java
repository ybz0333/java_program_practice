package com.ybz.list;

public class ListNode {
	int val;
	ListNode next = null;
	ListNode() {
	}
	ListNode(int val) {
		this.val = val;
	}
	
	static void printList(ListNode head) {
		ListNode curr = head;
		while(curr != null) {
			System.out.print(curr.val + " ");
			curr = curr.next;
		}
		System.out.println();
	}
}