package com.ybz.sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 时间复杂度为O(n)的排序算法
不是基于比较的排序算法
思想来自于桶排序
比较常规的有计数排序、基数排序

计数排序：
身高
张三175、李四185、王五168
设身高范围为100~300，建立从100到300的桶，将三个员工归入桶中，再从100号桶开始依次倒出员工，倒出的顺序就是按身高排序的顺序
王五168、张三175、李四185

基数排序：
假设被排序的数是十进制的
023 014 101 072 084 011
准备0~9共10个桶
根据个位数的大小将所有数入桶
	011			084
	101	072	023	014
0	1	2	3	4	5	6	7	8	9
再从0号到9号依次倒出
101 011 072 023 014 084
再根据十位数的大小从左到右将所有数入桶，再从0号到9号依次倒出
再根据百位数的大小从左到右将所有数入桶，再从0号到9号依次倒出，即可完成排序
基数排序的入桶出桶用队列来实现？（每个桶先进先出）
 */
/*
 * LinkedList
LinkedList是基于双向链表实现的List,虽然可以根据索引来访问集合中的元素,但性能不
高(平均时间复杂度为O(N)),但其插入/删除操作非常迅速(尤其是在头尾,平均时间复杂度
为O(1));除此之外,LinkedList还实现了Deque接口,因此还可以当成[双端]队列/栈来使用. 
LinkedList实现了List和Deque接口（双端队列），Deque接口又继承自Queue接口（队列）。
因此，LinkedList可以作为链表（List）、双端队列（Deque）、队列（Queue）、堆栈（利
用双端队列接口中的堆栈方法）
 */
/*
 * foreach一般用于遍历，需要修改的用for循环？是不能修改吗？
 * 实验表明，foreach是只读的
 */
/*
 * 取余运算符%比四级运算符中的 "+"和"-"优先级高
 * 取余运算符%与四级运算符中的"*"和"/"优先级相同
 */
/*
 * 实验表明，java的数组长度可以为0
 */
public class RadixSortTry {
	public static void radixSort(int[] arr) {
		//将数组中的数分成正数与负数两部分
		int numPos = 0,numNeg = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] >= 0) {
				numPos++;
			}
			else {
				numNeg++;
			}
		}
		int[] arrPos = new int[numPos];
		int[] arrNeg = new int[numNeg];
		int indexPos = 0;
		int indexNeg = 0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i] >= 0) {
				arrPos[indexPos++] = arr[i];
			}
			else {
				arrNeg[indexNeg++] = -arr[i];
			}			
		}
		radioSortPos(arrPos);
		radioSortPos(arrNeg);
		int index = 0;
		for(int i=arrNeg.length-1; i>=0; i--) {
			arr[index++] = -arrNeg[i];
		}
		for(int i=0; i<arrPos.length; i++) {
			arr[index++] = arrPos[i];
		}
	}
	
	//这里将数组倒入桶，再将桶倒回数组
	//答案中采用两个桶来回倒（由桶1倒入桶2，再交换桶1和桶2）
	public static void radioSortPos(int[] arr) {
		List<LinkedList<Integer>> helpList = new ArrayList<LinkedList<Integer>>();
		for(int i=0; i<10; i++) {
			helpList.add(new LinkedList<Integer>());
		}
		
		//这里的base注意定义成long类型
		for(long base=1; base<Integer.MAX_VALUE; base*=10) {
			boolean isAllZero = true;//全0代表最高的位数已被处理，可以退出
			for(int i=0; i<arr.length; i++) {
				int num = (int)(arr[i]/base%10);
				if(num != 0) {
					isAllZero = false;
				}
				helpList.get(num).offer(arr[i]);//这里将LinkedList用作队列
			}
			int index = 0;
			for(int i=0; i<10; i++) {
				Queue<Integer> queueTemp = helpList.get(i);
				while(!queueTemp.isEmpty()) {
					arr[index++] = queueTemp.poll();
				}
			}
			if(isAllZero == true) {
				break;
			}
		}
	}
	
	public static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static int[] generateArray(int length, int lowerBound, int upperBound) {
		int range = upperBound - lowerBound;
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = (int)(lowerBound + Math.random() * range);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, -50, 50);
		printArray(arr);
		radixSort(arr);
		printArray(arr);
	}
}
