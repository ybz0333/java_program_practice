package com.ybz.stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/*
 * 牛客网4.5 栈的反转练习题答案
 * 实现一个栈的逆序，但是只能用递归函数和这个栈本身的pop操作来实现，而不能自己申请另外的数据结构。
 * 给定一个整数数组A即为给定的栈，同时给定它的大小n，请返回逆序后的栈。
 * 测试样例：
 * [4,3,2,1],4
 * 返回：[1,2,3,4]
 * 
 * 思路：
 * 1.实现函数get，功能为：移除栈底元素并返回（使用递归实现）
 * 2.实现把栈中元素逆序的主方法（使用递归实现）
 * 
 * 实现递归时注意：
 * 1.在实现的过程中调用自身，假设自身已实现。关键是找出递推过程（或递推表达式）
 * （假设自身已经实现，看看怎么利用已经实现的自己来实现自己）
 * 2.注意递归调用的结束条件
 * 
 * 注意Set、List、Vector、Stack等的toArray方法：
 * 有两种形式：
 * Object[] toArray() 
 * <T> T[] toArray(T[] a) 
 * 第一种形式不需要参数，但返回的是Object[]类型（Object[]类型不能强制转换成Integer[]这样的类型）
 * 第二种形式需要参数，会把结果直接赋到参数的数组中（注意参数的数组要初始化，分配好大小）。
 * 第二种形式有数组类型的返回值，返回的数组对象和函数参数是同一个对象，可以不使用。
 */
public class StackReverse {
	public int[] reverseStack(int[] A, int n) {
		int length = A.length;
		int[] arr = new int[length];
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<length; i++) {
			stack.push(A[i]);
		}
		reverse(stack);
		Integer[] arrInteger = new Integer[stack.size()];
		Integer[] arr2 = stack.toArray(arrInteger);
		System.out.println(arrInteger);
		System.out.println(arr2);
		for(int i=0; i<length; i++) {
			arr[i] = arrInteger[i];
		}
		return arr;
	}
	
	// 该函数功能为，移除栈底元素并返回
	private int get(Stack<Integer> stack) {
		int result = stack.pop();
		if(stack.isEmpty()) {
			return result;
		} else {
			int last = get(stack);
			stack.push(result);
			return last;
		}
	}
	
	//该函数为把栈中元素逆序的主方法
	private void reverse(Stack<Integer> stack) {
		if(stack.size() == 0) {
			return;
		}
		int i = get(stack);
		reverse(stack);
		stack.push(i);
	}
	
	public static void main(String[] args) {
		StackReverse stackReverse = new StackReverse();
		int[] arr = {4, 3, 2, 1};
		int[] arrRet = stackReverse.reverseStack(arr, 4);
		System.out.println(Arrays.toString(arrRet));
	}
}
