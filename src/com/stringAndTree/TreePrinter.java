package com.stringAndTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 1.1.1 二叉树打印
 * 要求打印换行符。
 * 关键点：
 * 1.二叉树的广度优先遍历（非递归，使用队列）
 * 2.需要知道何时换行：
 * 一种思路：
 * 使用一个变量维护当前行的元素个数。使用另一个变量记录当前行已遍历的元素个数，以判断何时遍历完一行。
 * 当遍历完一行时，队列中剩余元素个数即为下一行元素个数。下一行元素个数为0时结束。
 * 
 * 这里还给出了另外两种方法：
 * 递归的方法进行层序遍历，先计算高度
 * 非递归的方法进行层序遍历，不计算高度
 */
class TreeNode {
	int val = 0;
	TreeNode left = null;
	TreeNode right = null;
	public TreeNode() {
	}
	public TreeNode(int val) {
		this.val = val;
	}
}
public class TreePrinter {
	// 1.这里用到层序遍历的非递归方法。借助队列
	// 对空节点的处理？（遇到非完全二叉树时，遍历的中途可能出现空节点。这里的处理方法直接是不加入队列（没有入队就没有出队，就不打印））
	public static void printTree(TreeNode tree) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(tree);
		int n = 1; //n记录当前行的元素个数
		int nScanned = 0; //nScanned记录当前行已遍历的元素个数
		while(true) {
			while(nScanned < n) {
				TreeNode nodeTemp = queue.poll();
				System.out.print(nodeTemp.val + " ");//出队时访问
				if(nodeTemp.left != null) {
					queue.offer(nodeTemp.left);
				}
				if(nodeTemp.right != null) {
					queue.offer(nodeTemp.right);
				}
				nScanned++;
			}
			//输出换行符
			System.out.println();
			
			n = queue.size();
			if(n == 0) {
				break;
			}
			nScanned = 0;
		}
	}
	
	
	// 2.使用非递归的方式进行层序遍历。感觉这种方式要重复递归，冗余操作较多。
	// 这里可以先求树的高度（也可以不求？整行为空时结束？）
	// 有关树的几个概念：
	// 树的高度的定义："height of the root"
	// 节点高度的定义："number of edges in longest path from the node to a leaf node". 如：叶子节点的高度是0.
	// 节点深度的定义："number of edges in path from root to that node"
	// 树的高度的计算：
	// 计算高度的时候，利用递归,从父节点到子节点，直至叶子节点，设置叶子节点的高度是0。
	// 再从叶子回到父节点，直至跟根节点，height(parentnode) = max(height(left),height(right))+1
	//
	// Math类的常用方法：
	// max、min 都是比较两个值
	// random、abs、pow、sqrt
	// round、floor、ceil
	// 三角函数
	public static int height(TreeNode root) {
		if(root == null) {
			return -1;
		}
		return Math.max(height(root.left), height(root.right))+1;
	}
	
	public static void printTreeRecur(TreeNode root) {
		int height = height(root);
		for(int i=0; i<=height; i++) {
			printGivenLevel(root, i);
			System.out.println();
		}
	}
	
	// 这里同样是：节点为空则不打印
	public static void printGivenLevel(TreeNode root, int level) {
		if(root == null) {
			return;
		}
		if(level == 0) {
			System.out.print(root.val + " ");
		} else {
			printGivenLevel(root.left, level-1);
			printGivenLevel(root.right, level-1);	
		}
	}
	
	// 3.使用递归的方式，不计算高度，进行层序遍历
	public static void printTreeRecur2(TreeNode root) {
		int i=0;
		// java中不能写while(1)，要用while(true)
		while(true) {
			int ret = printGivenLevel2(root, i);
			if(ret == -1) {
				break;
			}
			System.out.println();
			i++;
		}
	}
	
	// 这里同样是：节点为空则不打印
	public static int printGivenLevel2(TreeNode root, int level) {
		if(root == null) {
			return -1;
		}
		if(level == 0) {
			System.out.print(root.val + " ");
		} else {
			int ret1 = printGivenLevel2(root.left, level-1);
			int ret2 = printGivenLevel2(root.right, level-1);
			if(ret1 == -1 && ret2 == -1) {
				return -1;
			}	
		}
		return 0;
	}	
	
	// 通过返回值返回孩子节点都为空的信息
	
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
		//第一种方法（非递归层序遍历）
		printTree(root);
		System.out.println();
		//第二种方法（递归层序遍历，先计算树的高度）
		System.out.println("height: "+height(root));
		printTreeRecur(root);
		System.out.println();
		//第三种方法（递归层序遍历，不计算树的高度）
		printTreeRecur2(root);
	}
}
