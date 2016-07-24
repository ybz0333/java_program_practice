package com.ybz.list;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 牛客网5.9 链表的回文结构练习题
 * 请编写一个函数，检查链表是否为回文。
 * 给定一个链表ListNode* pHead，请返回一个bool，代表链表是否为回文。
 * 测试样例：
 * {1,2,3,2,1}
 * 返回：true
 * {1,2,3,2,3}
 * 返回：false
 * 
 * 思路1：
 * 所有节点依次入栈，再逐个出栈和原链表比较
 * 需要N的额外空间
 * 思路2：
 * 找中间位置（使用两个指针遍历链表，一个一次走两步，一个一次走一步；走两步的指针走到头时，走一步的指针在中间位置）
 * 在寻找中间位置的过程中将前一半节点依次入栈；找到中间位置后，将节点逐一出栈与后半段链表比较
 * 需要N/2的额外空间
 * 思路3：
 * 找中间位置，再将后半段链表逆序，再从两头到中间比较
 * 若要求不改动原链表，还需要将链表还原
 * 占用的额外空间最少
 */

public class PalindromeTry {
	/*
	 * 开始时curr和assist赋pHead指针
	 * 先判断assist是否为空，向下遍历两个节点，之后curr入栈，向下遍历一个节点
	 * assist遍历时记录节点数量
	 */
	public static boolean isPalindrome(ListNode pHead) {
		if(pHead == null) {
			return false;
		}
		ListNode curr = pHead;
		ListNode assist = pHead;
		Deque<ListNode> deque = new LinkedList<ListNode>();
		int numNode = 0;
		while(true) {
			if(assist != null) {
				numNode++;
				assist = assist.next;
			} else {
				break;
			}
			if(assist != null) {
				numNode++;
				assist = assist.next;
			} else {
				break;
			}
			deque.push(curr);
			curr = curr.next;
		}
		
		if(numNode == 1) {
			return true;
		} else {
			if(numNode%2 == 1) {
				// numNode为奇数时，curr指针指向中间的节点
				// numNode为偶数时，curr指针指向后半段链表的第一个节点
				curr = curr.next;
			}
			while(curr != null) {
				ListNode node = deque.pop();
				//System.out.println(node.val);
				if(curr.val != node.val) {
					return false;
				}
				curr = curr.next;
			}
			return true;
		}
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode node2 = new ListNode(2);
		head.next = node2;
		ListNode node3 = new ListNode(3);
		node2.next = node3;
		ListNode node4 = new ListNode(2);
		node3.next = node4;
		ListNode node5 = new ListNode(1);
		node4.next = node5;
		ListNode.printList(head);
		System.out.println(isPalindrome(head));
		node5.val = 3;
		ListNode.printList(head);
		System.out.println(isPalindrome(head));
	}
}
