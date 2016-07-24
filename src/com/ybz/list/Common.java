package com.ybz.list;
/*
 * 牛客网5.6 打印两个链表的公共值练习题答案
 * 这里用到了LinkedList类
 */
import java.util.*;

/*
public class ListNode {
    int val;
    ListNode next = null;
 
    ListNode(int val) {
        this.val = val;
    }
}*/
public class Common {
    public int[] findCommonParts(ListNode head1, ListNode head2) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head1 = head1.next;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            } else {
                list.add(head1.val);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        int[] res = new int[list.size()];
        int index = 0;
        while (!list.isEmpty()) {
            res[index++] = list.pollFirst();
        }
        return res;
    }
}