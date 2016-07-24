package com.ybz.list;
/*
 * 牛客网5.5 链表的分化练习题
 * 对于一个链表，我们需要用一个特定阈值完成对它的分化，使得小于等于这个值的结点移到前面，大于该值的结点在后面，同时保证两类结点内部的位置关系不变。
 * 给定一个链表的头结点head，同时给定阈值val，请返回一个链表，使小于等于它的结点在前，大于等于它的在后，保证结点值不重复。
 * 测试样例：
 * {1,4,2,5},3
 * {1,2,4,5}
 * 
 * 思路：
 * 方法1：将链表复制到数组中，使用类似快速排序中的方法调整次序，再将结果复制回链表。这种方法需要N的额外空间。
 * 方法2：遍历链表的过程中，将节点划分到小于、等于、大于三个链表中，再连接三个链表
 */
public class DivideTry {
	
	/*
	 * 这种答案显示没有通过
	 * {360,220,2},2 对应输出应该为为{2,360,220} 本输出为{2,220,360}
	 * 调换了大于val值的数之间的顺序
	 * 也是满足要求的
	 * 若需要不调换顺序，则使用方法2
	 */
	public static ListNode listDivide1(ListNode head, int val) {
		// 先遍历求出长度
		int length = 0;
		ListNode curr = head;
		while(curr != null) {
			length++;
			curr = curr.next;
		}
		// 将链表复制到数组中
		int[] arr = new int[length];
		curr = head;
		for(int i=0; i<length; i++) {
			arr[i] = curr.val;
			curr = curr.next;
		}
		// 使用类似快速排序中的调整方法，设定边界下标
		// （这里indexB表示小于等于区间的最右侧下标）
		int indexB = -1;
		for(int i=0; i<length; i++) {
			if(arr[i] <= val) {
				indexB++;
				swap(arr, i, indexB);
			}
		}
		// 将数组复制回链表中
		curr = head;
		for(int i=0; i<length; i++) {
			curr.val = arr[i];
			curr = curr.next;
		}
		return head;
	}
	
	/*
	 * 答案要求等于在前，小于次之，大于在最后
	 * 这种答案通过了
	 */
	public static ListNode listDivide2(ListNode head, int val) {
		ListNode headSmall=null;
		ListNode tearSmall=null;
		ListNode headEqual=null;
		ListNode tearEqual=null;
		ListNode headLarge=null;
		ListNode tearLarge=null;
		ListNode curr=head;
		while(curr != null) {
			if(curr.val < val) {
				if(headSmall == null) {
					headSmall = curr;
					tearSmall = curr;
				} else {
					tearSmall.next = curr;
					tearSmall = curr;
				}
			} else if(curr.val == val) {
				if(headEqual == null) {
					headEqual = curr;
					tearEqual = curr;
				} else {
					tearEqual.next = curr;
					tearEqual = curr;
				}				
			} else {
				if(headLarge == null) {
					headLarge = curr;
					tearLarge = curr;
				} else {
					tearLarge.next = curr;
					tearLarge = curr;
				}
			}
			// 这样操作以断开节点和后面节点的连接
			ListNode nodeTemp = curr.next;
			curr.next = null;
			curr = nodeTemp;
		}
		//ListNode.printList(headSmall);
		//ListNode.printList(headEqual);
		//ListNode.printList(headLarge);
		// 连接三个链表
		// 答案要求等于在前，小于次之，大于在最后
		if(headEqual != null) {
			if(tearSmall != null) {
				tearEqual.next = headSmall;
				tearSmall.next = headLarge;
			} else {
				tearEqual.next = headLarge;
			}
		} else {
			if(headSmall != null) {
				tearSmall.next = headLarge;
			}
		}
		if(headEqual != null) {
			return headEqual;
		} else if(headSmall != null) {
			return headSmall;
		} else {
			return headLarge;
		}
	}
	
	private static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode node2 = new ListNode(4);
		head.next = node2;
		ListNode node3 = new ListNode(2);
		node2.next = node3;
		ListNode node4 = new ListNode(5);
		node3.next = node4;
		ListNode head2 = listDivide1(head,3);
		ListNode.printList(head2);
		ListNode head3 = listDivide2(head,3);
		ListNode.printList(head3);
	}
}