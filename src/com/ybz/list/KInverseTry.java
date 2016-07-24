package com.ybz.list;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 牛客网5.7 链表的k逆序练习题
 * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。例如链表1->2->3->4->5->6->7->8->null，K=3这个例子。调整后为，3->2->1->6->5->4->7->8->null。因为K==3，所以每三个节点之间逆序，但其中的7，8不调整，因为只有两个节点不够一组。
 * 给定一个单链表的头指针head,同时给定K值，返回逆序后的链表的头指针。
 * 
 * 思路1：使用栈，需要k个额外空间
 * 思路2：直接逆序，使用辅助指针
 */
public class KInverseTry {
	public static ListNode inverse1(ListNode head, int k) {
		Deque<ListNode> deque = new LinkedList<ListNode>();
		ListNode curr = head;
		ListNode headNew = null;
		ListNode tearNew = null;
		while(curr != null) {
			for(int i=0; i<k; i++) {
				deque.offerFirst(curr);
				curr = curr.next;
				if(curr == null) {
					break;
				}
			}
			ListNode headTemp = null;
			ListNode tearTemp = null;
			if(deque.size() == k) {
				while(deque.size() > 0) {
					ListNode node = deque.pollFirst();
					if(headTemp == null) {
						headTemp = node;
						tearTemp = node;
					} else {
						tearTemp.next = node;
						tearTemp = node;
					}
				}
			} else {
				while(deque.size() > 0) {
					ListNode node = deque.pollLast();
					if(headTemp == null) {
						headTemp = node;
						tearTemp = node;
					} else {
						tearTemp.next = node;
						tearTemp = node;
					}
				}				
			}
			if(headNew == null) {
				headNew = headTemp;
				tearNew = tearTemp;
			} else {
				tearNew.next = headTemp;
				tearNew = tearTemp;
			}
		}
		// 注意尾节点的后继节点要置为null
		tearNew.next = null;
		return headNew;
	}
	
	/*
	 * pre、curr、next引用用于每段链表的逆置
	 * headNew、tearNew用于记录链表已处理部分的头节点和尾节点
	 * headTemp、tearTemp用于记录当前段（逆置后）的头节点和尾节点，用于辅助段之间的连接
	 */
	public static ListNode inverse2(ListNode head, int k) {
		ListNode pre = null;
		ListNode curr = head;
		ListNode next = head.next;
		// 用于标记是否是第一次调换（会产生新的头节点）
		boolean isFirst = true;
		ListNode headNew = head;
		ListNode tearNew = null;
		while(true) {
			ListNode headTemp = curr;
			int i=0;
			for(i=0; i<k; i++) {
				if(curr == null) {
					break;
				}
				curr = curr.next;
			}
			curr = headTemp;
			// 若i==k，则剩余节点数量大于等于k，执行链表段的逆置操作
			if(i == k) {
				ListNode tearTemp = null;
				for(int j=0; j<k; j++) {
					if(j == 0) {
						tearTemp = curr;
					}
					if(j == k-1) {
						headTemp = curr;
					}
					curr.next = pre;
					pre = curr;
					curr = next;
					if(curr != null) {
						next = curr.next;
					}
				}
				// 第一段逆置时会改变整个链表的头节点
				if(isFirst == true) {
					headNew = headTemp;
					isFirst = false;
				}
				// 连接已逆置的段段
				if(tearNew == null) {
					tearNew = tearTemp;
				} else {
					tearNew.next = headTemp;
					tearNew = tearTemp;
				}
				// 这里要考虑pre、curr、next的维护
				pre = null;
			} else {
				tearNew.next = headTemp;
				break;
			}	
		}
		return headNew;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode node2 = new ListNode(2);
		head.next = node2;
		ListNode node3 = new ListNode(3);
		node2.next = node3;
		ListNode node4 = new ListNode(4);
		node3.next = node4;
		ListNode node5 = new ListNode(5);
		node4.next = node5;
		ListNode node6 = new ListNode(6);
		node5.next = node6;
		ListNode node7 = new ListNode(7);
		node6.next = node7;
		ListNode node8 = new ListNode(8);
		node7.next = node8;
		ListNode.printList(head);
		ListNode headNew = KInverseTry.inverse1(head, 3);
		ListNode.printList(headNew);
		
		ListNode curr = headNew;
		for(int i=1; i<9; i++) {
			curr.val = i;
			curr = curr.next;
		}
		ListNode.printList(headNew);
		ListNode headNew2 = KInverseTry.inverse2(headNew, 3);
		ListNode.printList(headNew2);
	}
}