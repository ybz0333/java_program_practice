package com.ybz.list;

/*
 * 牛客网5.2 环形链表插值练习题
 * 有一个整数val，如何在节点值有序的环形链表中插入一个节点值为val的节点，并且保证这个环形单链表依然有序。
 * 给定链表的信息，及元素的值A及对应的nxt指向的元素编号同时给定val，请构造出这个环形链表，并插入该值。
 * 测试样例：
 * [1,3,4,5,7],[1,2,3,4,0],2
 * 返回：{1,2,3,4,5,7}
 * 
 * 思路：
 * 关键点在于对边界的讨论、对特殊值的讨论（null等情况）
 * 遍历判断一遍，没有找到合适的插入位置，说明需要在队头或队尾处插入
 * 有改动头节点的情况，有返回头节点的需求
 * 
 * 注意链表结点的定义方法（类名叫LinkNode更合适？）
 * 
 * 注意也可以使用数组的形式定义链表（两个数组，一个存放值，另一个存放下一个节点的下标）
 * 
 * 该程序没有通过在线测试（未能在规定时间内运行结束）
 */

/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class InsertValueTry {
	class ListNode {
		int val;
		ListNode next = null;
		ListNode() {
		}
		ListNode(int val) {
			this.val = val;
		}
	}
	// 注意这里要求返回头结点
    public ListNode insert(int[] A, int[] nxt, int val) {
    	// 构造环形链表
    	if(A == null || A.length == 0) {
    		ListNode head = new ListNode(val);
    		head.next = head;
    		return head;
    	}
    	int length = nxt.length;
    	ListNode head = new ListNode(A[0]);
    	ListNode curr = head;
    	int idxCurr = 0;
    	while(true) {
    		if(nxt[idxCurr] == 0) {
    			curr.next = head;
    			break;
    		} else {
    			ListNode node = new ListNode(A[nxt[idxCurr]]);
    			curr.next = node;
    			curr = node;
    			idxCurr = nxt[idxCurr];
    		}
    	}
    	// 打印链表
    	curr = head;
    	for(int i=0; i<length; i++) {
    		System.out.print(curr.val+" ");
    		curr = curr.next;
    	}
    	System.out.println();
    	// 插入节点
    	ListNode nodeVal = new ListNode(val);
    	curr = head;
    	while(curr.next != head) {
    		if(curr.val <= val && curr.next.val >= val) {
    			nodeVal.next = curr.next;
    			curr.next = nodeVal;
    			break;
    		}
    		curr = curr.next;
    	}
    	if(curr.next == head) {
    		ListNode rear = curr;
    		if(val < head.val) {
    			nodeVal.next = head;
    			head.next = nodeVal;
    			head = nodeVal;
    		}
    		if(val >= rear.val) {
    			rear.next = nodeVal;
    			nodeVal.next = head;
    		}
    	}
    	// 打印链表
    	curr = head;
    	for(int i=0; i<length+1; i++) {
    		System.out.print(curr.val+" ");
    		curr = curr.next;
    	}
    	System.out.println();
    	return head;
    }
    
    public static void main(String[] args) {
    	InsertValueTry insertValue = new InsertValueTry();
/*    	int[] arr1 = {1,3,4,5,7};
    	int[] arr2 = {1,2,3,4,0};
    	insertValue.insert(arr1, arr2, 2);*/
    	int[] arr1 = {1,3,4,5,6};
    	int[] arr2 = {1,0,4,3,2};
    	insertValue.insert(arr1, arr2, 0);
	}
}