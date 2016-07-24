package com.ybz.list;
/*
 * 5.3 访问单个节点的删除练习题
 * 实现一个算法，删除单向链表中间的某个结点，假定你只能访问该结点。
 * 给定带删除的节点，请执行删除操作，若该节点为尾节点，返回false，否则返回true
 * 
 * 思路：
 * 不知道前驱节点，所以只能把待删除节点的值改成后继节点的值，再将next指向后继节点的后继节点。
 * 若待删除节点为链表的最后一个节点，则无法删除（将待删除节点的引用赋值为null是无效的，前驱节点的next依然指向待删除节点的实例）
 */
public class RemoveTry {

	public static boolean removeNode(ListNode pNode) {
		if(pNode.next == null) {
			return false;
		} else {
			pNode.val = pNode.next.val;
			pNode.next = pNode.next.next;
			return true;
		}
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(0);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		ListNode.printList(head);
		RemoveTry.removeNode(node2);
		ListNode.printList(head);
	}
}
