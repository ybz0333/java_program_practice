package com.ybz.list;
/*
 * 牛客网5.5 链表的分化练习题答案
 */

/*
public class ListNode {
    int val;
    ListNode next = null;
 
    ListNode(int val) {
        this.val = val;
    }
}*/
public class Divide {
    public ListNode listDivide(ListNode head, int pivot) {
        ListNode sH = null; // small head
        ListNode sT = null; // small tail
        ListNode bH = null; // big head
        ListNode bT = null; // big tail
        ListNode next = null; // save next node
        // every node distributed to three lists
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val <= pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            }else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        if (sT != null) {
            sT.next = bH;
        }
        return sH != null ? sH : bH;
    }
}