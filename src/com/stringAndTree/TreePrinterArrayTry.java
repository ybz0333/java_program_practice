package com.stringAndTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
 * 1.2 二叉树打印练习题
 * 有一棵二叉树，请设计一个算法，按照层次打印这棵二叉树。
 * 给定二叉树的根结点root，请返回打印结果，结果按照每一层一个数组进行储存，所有数组
 * 的顺序按照层数从上往下，且每一层的数组内元素按照从左往右排列。保证结点数小于等于500。
 * 
 * 方法与TreePrinter类似。
 */
public class TreePrinterArrayTry {
    // 使用层序遍历的方式遍历二叉树
    // 使用非递归方式，借助队列
    public static int[][] printTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int n = 1; // n记录当前行的元素个数
        //int nScanned = 0;
        List<int[]> list = new ArrayList<int[]>(); // 使用list存储每层数组的引用
        while(true) {
            int[] arr = new int[n];
            for(int i=0; i<n; i++) { // i记录当前行已遍历的元素个数
	            TreeNode node = queue.poll();
                arr[i] = node.val;
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(arr);
            n = queue.size();
            if(n == 0) {
                break;
            }
        }
        // 注意二维数组的这种创建方法是可以的：int[][] arrs = new int[n][];
		int[][] arrs = new int[list.size()][];
        for(int i=0; i<list.size(); i++) {
            arrs[i] = list.get(i);
        }
        return arrs;
    }
    
    public static void printArrays(int[][] arrs) {
    	for(int i=0; i<arrs.length; i++) {
    		System.out.println(Arrays.toString(arrs[i]));
    	}
    }
    
	/*
	 * 输入为以下树：
	 *     1
	 *    / \
	 *   2   3
	 *  / \ / \
	 *  4 5 6 7
	 */
	public static void main(String[] args) {
		TreeNode node2 = new TreeNode(2);
		node2.left = new TreeNode(4);
		node2.right = new TreeNode(5);
		TreeNode node3 = new TreeNode(3);
		node3.left = new TreeNode(6);
		node3.right = new TreeNode(7);
		TreeNode root = new TreeNode(1);
		root.left = node2;
		root.right = node3;
		
		int[][] arrs = printTree(root);
		printArrays(arrs);
	}
}