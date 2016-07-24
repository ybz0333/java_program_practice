package com.ybz.list;
/*
 * 牛客网5.3 访问单个节点的删除练习题答案
 */

/*
public class ListNode {
    int val;
    ListNode next = null;
 
    ListNode(int val) {
        this.val = val;
    }
}*/
public class Remove {
    public boolean removeNode(ListNode pNode) {
        if (pNode == null) {
            return false;
        }
        ListNode next = pNode.next;
        if (next == null) {
            return false;
        }
        pNode.val = next.val;
        pNode.next = next.next;
        return true;
    }
}