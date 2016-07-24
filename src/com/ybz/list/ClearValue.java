package com.ybz.list;
/*
 * 牛客网5.8 链表指定值清除练习题答案
 */

/*
public class ListNode {
    int val;
    ListNode next = null;
 
    ListNode(int val) {
        this.val = val;
    }
}*/
public class ClearValue {
    public ListNode clear(ListNode head, int num) {
        while (head != null) {
            if (head.val != num) {
                break;
            }
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
