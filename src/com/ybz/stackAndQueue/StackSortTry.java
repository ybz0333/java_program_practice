package com.ybz.stackAndQueue;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 牛客网4.6 双栈排序练习题
 * 请编写一个程序，按升序对栈进行排序（即最大元素位于栈顶），要求最多只能使用一个额外的栈存放临时数据，但不得将元素复制到别的数据结构中。
 * 给定一个int[] numbers(C++中为vector<int>)，其中第一个元素为栈顶，请返回排序后的栈。请注意这是一个栈，意味着排序过程中你只能访问到第一个元素。
 * 测试样例：
 * [1,2,3,4,5]
 * 返回：[5,4,3,2,1]
 * 
 * 思路：
 * 使用两个栈stack和help，元素逐个从stack出栈放入help，顺序不对时将help中的元素倒回stack直至找到合适的位置，元素入栈后再从stack倒回stack。整个过程完成后从help倒回stack。
 * 
 * Deque接口中，有关队列的接口，队头都为此双端队列的第一个元素；有关栈的接口，栈顶都为此双端队列的第一个元素。
 * 
 * isEmpty方法
 * 注意Collection接口中有isEmpty方法，所以List、Set、Vector、Stack这种都有isEmpty方法。
 * Map接口中也有isEmpty方法，所以HashMap等类有isEmpty方法。
 */
public class StackSortTry {
	private Stack<Integer> help = new Stack<Integer>();
	public ArrayList<Integer> twoStacksSort(int[] numbers) {
		int length = numbers.length;
		int idxTop = 0;
		while(idxTop < length) {
			int top = numbers[idxTop];
			idxTop++;
			if(help.size() == 0 || top <= help.peek()) {
				help.push(top);
			} else {
				int numMove = 0;
				while(help.size() > 0 && help.peek() < top) {
					idxTop--;
					numbers[idxTop] = help.pop();
					numMove++;
				}
				help.push(top);
				while(numMove > 0) {
					help.push(numbers[idxTop]);
					idxTop++;
					numMove--;
				}
			}
		}
		while(help.size() > 0) {
			idxTop--;
			numbers[idxTop] = help.pop();
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(idxTop < length) {
			list.add(numbers[idxTop]);
			idxTop++;
		}
		return list;
	}
	
	public static void main(String[] args) {
		StackSortTry stackSortTry = new StackSortTry();
		int[] arr = {1, 2, 3, 4, 5};
		ArrayList<Integer> list = stackSortTry.twoStacksSort(arr);
		System.out.println(list);
	}
}
