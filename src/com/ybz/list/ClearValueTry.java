package com.ybz.list;
/*
 * 牛客网5.8 链表指定值清除练习题
 * 现在有一个单链表。链表中每个节点保存一个整数，再给定一个值val，把所有等于val的节点删掉。
 * 给定一个单链表的头结点head，同时给定一个值val，请返回清除后的链表的头结点，保证链表中有不等于该值的其它值。请保证其他元素的相对顺序。
 * 测试样例：
 * {1,2,3,4,3,2,1},2
 * {1,3,4,3,1}
 * 
 * 思路1：构造新链表，将不等于val的节点接入新链表（注意清空尾节点的next域）
 * 思路2：普通的删除节点的思路
 */
public class ClearValueTry {
	public static ListNode clear1(ListNode head, int val) {
		ListNode headNew = null;
		ListNode tearNew = null;
		ListNode curr = head;
		while(curr != null) {
			if(curr.val != val) {
				if(headNew == null) {
					headNew = curr;
					tearNew = curr;
				} else {
					tearNew.next = curr;
					tearNew = curr;
				}
			}
			curr = curr.next;
		}
		// 注意清空尾节点的next域
		if(tearNew != null) {
			tearNew.next = null;
		}
		return headNew;
	}
	
	public static ListNode clear2(ListNode head, int val) {
		ListNode pre = null;
		ListNode curr = head;
		while(curr != null) {
			if(curr.val == val) {
				if(pre == null) {
					head = curr.next;
				} else {
					pre.next = curr.next;
				}
			}
			pre = curr;
			curr = curr.next;
		}
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode node2 = new ListNode(2);
		head.next = node2;
		ListNode node3 = new ListNode(3);
		node2.next = node3;
		ListNode node4 = new ListNode(4);
		node3.next = node4;
		ListNode node5 = new ListNode(3);
		node4.next = node5;
		ListNode node6 = new ListNode(2);
		node5.next = node6;
		ListNode node7 = new ListNode(1);
		node6.next = node7;
		ListNode.printList(head);
		//ListNode headNew = clear1(head,2);
		ListNode headNew = clear2(head,2);
		ListNode.printList(headNew);
	}
}
