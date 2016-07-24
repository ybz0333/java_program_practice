package com.stringAndTree;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 二叉树序列化与反序列化
 * 基本思路：
 * 用!表示一个二叉树节点值的结束；用#表示空节点（不标识空节点将无法反序列化）
 * 可使用前序、中序、后序、层序的方式进行序列化与反序列化
 */
// 这里的TreeNode结构体来自TreePrinter中的定义
public class TreeSerialize {

	static class PreOrder {
		// 使用前序遍历（递归的方式）实现序列化与反序列化
		
		// 这里可以使用返回值返回，也可以使用形参作为传出参数的方式返回
		// 但实际使用中发现，使用形参作为传出参数需要满足：
		// 1.需要有成员变量的对象，对其成员变量的改变才能传出；
		// 2.该对象需要在传入前被实例化，传入null是无效的；
		// 3.String类型或是Integer这种包装类型无法作为传出参数（虽然底层
		// 包含成员变量，但本身是只读类型？），而StringBuilder可以作为传出参数。
		// 所以对于该问题，还是使用返回值返回的方式更方便
		//
		// StringBuilder：
		// StringBuilder为可变长字符串，
		// 最常用方法：append(X) X可为各种参数（包括cahr[]）
		// 有toString方法，但没有toCharArray()方法（所以要先转换成String再使用toCharArray？）
		// 与String有相同的接口CharSequence（CharSequence是String、StringBuilder、StringBuffer的接口），实现接口的方法chatAt、length、toString()
		// （注意StringBuilder的toString返回的结果是String类型的）
		// 其它与String相同或相似的方法：indexOf、lastIndexOf、subString、replace（参数不同）
		// （注意String、StringBuffer都有的indexOf的两种形式：indexOf(String str)、indexOf(String str, int fromIndex)，StringBuilder类的输入参数也是String类型（String类还支持char输入（以int为参数类型）））
		// 独有的方法：setCharAt(int index, char ch)、deleteCharAt(int index)、insert(int offset, X)（X为各种参数，包括基本数据类型、char[]）、delete(int start, int end)、reverse
		// StringBuffer的方法与StringBuilder基本一致
		public static void serialize(TreeNode root, StringBuilder str) {
			if(str == null) {
				str = new StringBuilder();
			}
			if(root != null) {
				str.append(root.val).append("!");
				serialize(root.left,str);
				serialize(root.right,str);
			} else {
				str.append("#!");
				return;
			}
		}
		// String类配合下标，可以解决处理子串的问题
		// 这里需要使用返回值来传递startIdx的变化
		public static int deserialize(String str, int startIdx, TreeNode root) {
			if(str == null || startIdx >= str.length()) {
				return -1;
			}
			int idx = str.indexOf("!", startIdx);
			String strVal = str.substring(startIdx, idx);
			if(!strVal.equals("#")) {
				// String类的valueOf、Integer类的parseInt：从对方往自己的类型转
				int val = Integer.parseInt(strVal);
				if(root == null) {
					root = new TreeNode(val);
				} else {
					root.val = val;
				}
				// 这里由于使用root作为传出参数，要调用函数前要先实例化。而实例化之后，
				// 若该节点为空节点又要置为null。但函数中又无法将作为输出参数的形参置
				// 为null，所以只能先设置值为-1，返回后由上一层置为null。
				if(root.left == null) {
					root.left = new TreeNode();
				}
				if(root.right == null) {
					root.right = new TreeNode();
				}
				startIdx = idx + 1;
				startIdx = deserialize(str, startIdx, root.left);
				if(root.left.val == -1) {
					root.left = null;
				}
				startIdx = deserialize(str, startIdx, root.right);
				if(root.right.val == -1) {
					root.right = null;
				}
			} else {
				root.val = -1;
				startIdx = idx + 1;
			}
			return startIdx;
		}
		
		// 这里给出使用返回值返回的方式实现的版本，相比于上面一个版本更加方便。
		public static StringBuilder serializeUseRet(TreeNode root) {
			StringBuilder str = new StringBuilder();
			if(root != null) {
				str.append(root.val).append("!");
				str.append(serializeUseRet(root.left));
				str.append(serializeUseRet(root.right));
			} else {
				str.append("#!");
			}
			return str;
		}
		
		// 在写反序列化代码时能够感觉到：序列化时加入#的重要作用就是标识叶子节点，结束递归调用。
		public static TreeNode deserializeUseRet(StringBuilder str) {
			if(str == null || str.length() == 0) {
				return null;
			}
			TreeNode node = new TreeNode();
			int idx = str.indexOf("!");
			String strVal = str.substring(0, idx);
			// 删除已分析的部分
			str.delete(0, idx+1);
			if(!strVal.equals("#")) {
				int val = Integer.parseInt(strVal);
				node.val = val;
				node.left = deserializeUseRet(str);
				node.right = deserializeUseRet(str);
			} else {
				node = null;
			}
			return node;
		}
		
		// 使用前序遍历（非递归的方式）实现序列化（借助栈）
		// 非递归方式实现前序遍历的思路：
		// 先让根进栈。只要栈不为空，就可以弹栈。每次弹出一个节点，要把它的左右节点进栈（右节点先进栈）。
		// 注意这里对空节点的处理：这里空节点也要序列化成#!，所以空节点也要进栈、出栈。但空节点没有孩子节点，其出栈后孩子节点不用进栈。
		//
		// java中栈可借助Stack类（继承了Vector类），其中有push()、pop()、peek()、empty()、search()方法，其中栈为空时，pop()和peek()会抛出异常。
		// 更好的方法是使用Deque接口（双端队列）中提供的栈的方法：
		// push() 等同于addFirst()（在超出容量限制时抛出异常而不是返回特殊值）
		// pop() 等同于removeLast()（在双端队列为空时抛出异常而不是返回特殊值）
		// peek() 等同于peekFirst()（在双端队列为空时返回特殊值而不是抛出异常） <- 注意这里另外两者与不同
		//
		// Deque接口的实现有：
		// LinkedList 线程不安全。无容量限制？支持null元素插入。
		// ArrayDeque 线程不安全。不支持null元素插入。
		// LinkedBlockDeque 位于concurrent包中。线程安全。有容量限制。不支持null元素插入。
		public static StringBuilder serializeNoRecur(TreeNode root) {
			StringBuilder str = new StringBuilder();
			if(root == null) {
				str.append("#!");
			} else {
				Deque<TreeNode> deque = new LinkedList<TreeNode>();
				deque.push(root);
				while(true) {
					TreeNode node = deque.pop();
					if(node != null) {
						str.append(node.val).append("!");
						deque.push(node.right);
						deque.push(node.left);
					} else {
						str.append("#!");
					}
					if(deque.size() == 0) {
						break;
					}
				}
			}
			return str;
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
		TreeNode root = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		root.left = node2;
		root.right = node3;
		
		// 测试使用前序遍历（递归，传出参数的方式）实现序列化与反序列化
		StringBuilder str = new StringBuilder();
		PreOrder.serialize(root, str);
		System.out.println(str);
		TreeNode root2 = new TreeNode();
		PreOrder.deserialize(str.toString(), 0, root2);
		TreePrinter.printTree(root2);
		System.out.println();
		
		// 测试使用前序遍历（递归，返回值的方式）实现序列化与反序列化
		StringBuilder str2 = PreOrder.serializeUseRet(root);
		System.out.println(str2);
		TreeNode root3 = PreOrder.deserializeUseRet(str2);
		TreePrinter.printTree(root3);
		System.out.println();
		
		// 测试使用前序遍历（非递归的方式）实现序列化
		StringBuilder str3 = PreOrder.serializeNoRecur(root);
		System.out.println(str3);
	}
}
