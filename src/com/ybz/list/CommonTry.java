package com.ybz.list;

import java.util.Arrays;

/*
 * 牛客网5.6 打印两个链表的公共值练习题
 * 现有两个升序链表，且链表中均无重复元素。请设计一个高效的算法，打印两个链表的公共值部分。
 * 给定两个链表的头指针headA和headB，请返回一个vector，元素为两个链表的公共部分。请保证返回数组的升序。两个链表的元素个数均小于等于500。保证一定有公共值
 * 测试样例：
 * {1,2,3,4,5,6,7},{2,4,6,8,10}
 * 返回：[2.4.6]
 * 
 * 思路：同时遍历两个链表：
 * 若1的当前节点值大于2的当前节点值，则继续遍历1的下一个节点；
 * 若2的当前节点值大于1的当前节点值，则继续遍历2的下一个节点；
 * 若两节点值相等，则找到一个公共值，1和2都继续遍历下一个节点；
 * 若任意一个链表遍历到null，则结束
 */
public class CommonTry {
	public static int[] findCommonParts(ListNode headA, ListNode headB) {
		ListNode curr1=headA,curr2=headB;
		ListNode headCommon = null;
		ListNode tearCommon = null;
		int sizeCommon = 0;
		while(curr1 != null && curr2 != null) {
			if(curr1.val > curr2.val) {
				curr2 = curr2.next;
			} else if(curr1.val < curr2.val) {
				curr1 = curr1.next;
			} else {
				ListNode node = new ListNode(curr1.val);
				if(headCommon == null) {
					headCommon = node;
					tearCommon = node;
				} else {
					tearCommon.next = node;
					tearCommon = node;
				}
				sizeCommon++;
				curr1 = curr1.next;
				curr2 = curr2.next;
			}
		}
		ListNode curr = headCommon;
		int[] arrRet = new int[sizeCommon];
		for(int i=0; i<sizeCommon; i++) {
			arrRet[i] = curr.val;
			curr = curr.next;
		}
		return arrRet;
	}
	
	public static void main(String[] args) {
		ListNode headA = new ListNode(1);
		ListNode node12 = new ListNode(2);
		headA.next = node12;
		ListNode node13 = new ListNode(3);
		node12.next = node13;
		ListNode node14 = new ListNode(4);
		node13.next = node14;
		ListNode node15 = new ListNode(5);
		node14.next = node15;
		ListNode node16 = new ListNode(6);
		node15.next = node16;
		ListNode node17 = new ListNode(7);
		node16.next = node17;
		ListNode headB = new ListNode(2);
		ListNode node22 = new ListNode(4);
		headB.next = node22;
		ListNode node23 = new ListNode(6);
		node22.next = node23;
		ListNode node24 = new ListNode(8);
		node23.next = node24;
		ListNode node25 = new ListNode(10);
		node24.next = node25;
		int[] arrRet = findCommonParts(headA, headB);
		System.out.println(Arrays.toString(arrRet));
	}
}
