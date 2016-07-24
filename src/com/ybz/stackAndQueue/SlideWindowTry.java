package com.ybz.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
 * 牛客网4.8 滑动窗口练习题
 * 有一个整型数组 arr 和一个大小为 w 的窗口从数组的最左边滑到最右边,窗口每次向右边滑一个位置。 返回一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。 以数组为[4,3,5,4,3,3,6,7]，w=3为例。因为第一个窗口[4,3,5]的最大值为5，第二个窗口[3,5,4]的最大值为5，第三个窗口[5,4,3]的最大值为5。第四个窗口[4,3,3]的最大值为4。第五个窗口[3,3,6]的最大值为6。第六个窗口[3,6,7]的最大值为7。所以最终返回[5,5,5,4,6,7]。
 * 给定整形数组arr及它的大小n，同时给定w，请返回res数组。保证w小于等于n，同时保证数组大小小于等于500。
 * 测试样例：
 * [4,3,5,4,3,3,6,7],8,3
 * 返回：[5,5,5,4,6,7]
 * 
 * 思路：
 * 普通解法的时间复杂度为O(N*w)，也就是每次对每一个窗口遍历其中的w个数，选出最大值。
 * 最优解可以做到时间复杂度为O(N)
 * 利用双端队列实现窗口最大值的更新
 * 双端队列存储数组中的下标值
 * 窗口滑动的过程中维护双端队列，使队头保持为当前窗口的最大值
 * （队头也可能存放过期的下标，即下标范围已不属于当前窗口的下标，在pollFirst时判断去除即可）
 * 每遍历一个数（窗口滑动一次），都考查队尾，对应值小于等于当前值的下标都弹出，再向队尾添加当前下标
 * 每遍历一个数（窗口滑动一次），判断队头是否过期，过期则去除，不过期则对应值为当前窗口最大值。
 * 
 * 注意Deque有peekFirst和peekLast方法
 */
public class SlideWindowTry {
	public static int[] slide(int[] arr, int n, int w) {
		Deque<Integer> idxDeque = new ArrayDeque<Integer>();
		int length = arr.length;
		int[] arrRet = new int[length-w+1];
		for(int i=0; i<length; i++) {
			while(!idxDeque.isEmpty() && arr[idxDeque.peekLast()] <= arr[i]) {
				idxDeque.pollLast();
			}
			idxDeque.offerLast(i);
			if(i >= w-1) {
				int idxWinStart = i-w+1;
				while(!idxDeque.isEmpty()) {
					int idxFront = idxDeque.peekFirst();
					if(idxFront < idxWinStart) {
						idxDeque.pollFirst(); 
					} else {
						arrRet[idxWinStart] = arr[idxDeque.peekFirst()];
						break;
					}
				}
			}
		}
		return arrRet;
	}
	
	public static void main(String[] args) {
		int[] arr = {4,3,5,4,3,3,6,7};
		int[] arrRet = slide(arr, 8, 3);
		System.out.println(Arrays.toString(arrRet));
	}
}
