package com.ybz.string;
/*
 * 2.2 拓扑结构相同子树练习题答案
 */
/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
    public TreeNode(int val) {
        this.val = val;
    }
}*/
/*
 * 这里的serialByPre函数：
 * 输入为空：输出#!
 * 输入为以下树：
 *     1
 *    / \
 *   2   3
 *  / \ / \
 *  4 5 6 7
 * 输出：
 * 1!2!4!#!#!5!#!#!3!6!#!#!7!#!#!
 */
public class IdenticalTree {
    public boolean chkIdentical(TreeNode t1, TreeNode t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }
 
    public String serialByPre(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.val + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }
 
    // KMP
    public int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index = 0;
        int mi = 0;
        while (index < ss.length && mi < ms.length) {
            if (ss[index] == ms[mi]) {
                index++;
                mi++;
            } else if (nextArr[mi] == -1) {
                index++;
            } else {
                mi = nextArr[mi];
            }
        }
        return mi == ms.length ? index - mi : -1;
    }
 
    public int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[] { -1 };
        }
        int[] nextArr = new int[ms.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < nextArr.length) {
            if (ms[pos - 1] == ms[cn]) {
                nextArr[pos++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[pos++] = 0;
            }
        }
        return nextArr;
    }
    
    public static void main(String[] args) {
		TreeNode treeA = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		treeA.left = node2;
		treeA.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		//node3就是TreeB
		
		TreeNode treeC = new TreeNode(3);
		treeC.left = new TreeNode(4);
		treeC.right = new TreeNode(5);
		
		TreeNode treeD = new TreeNode(4);
		
		IdenticalTree identicalTree = new IdenticalTree();
		System.out.println(identicalTree.chkIdentical(treeA, node3));
		System.out.println(identicalTree.chkIdentical(treeA, treeC));
		System.out.println(identicalTree.chkIdentical(treeA, treeD));
	}
}