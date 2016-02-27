package com.ybz.sort;

/*
 * 归并排序
 * 时间复杂度为O(n*log(n))
 每一个数看成长度为1的有序区间
 相邻两两合并成为长度为2的有序区间
 再合并成为长度为4的有序区间
 以此类推
 合并过程：
 1278
 3456
 -->
 12345678
 使用一个辅助数组存放合并的结果
 同时考察两个数组，从左向右取出较小的数存入辅助数组中。两个数组分别使用一个下标记录考察到的位置
 编程时刻采用类似二分法的思路，从总到分
 处理过程分为：递归处理左边，递归处理右边，合并
 */
public class MergeSortTry {
	static void MergeSort(int[] arr) {
		process(arr, 0, arr.length - 1);
	}

	static void process(int[] arr, int left, int right) {
		// 结束递归的条件
		if (left == right) {
			return;
		}
		int middle = (left + right) / 2;
		process(arr, left, middle);
		process(arr, middle + 1, right);
		merge(arr, left, middle, right);
	}

	static void merge(int[] arr, int left, int middle, int right) {
		int[] help = new int[right - left + 1];
		int l = left;
		int r = middle + 1;
		int index = 0;
		while (l <= middle && r <= right) {
			if (arr[l] < arr[r]) {
				help[index++] = arr[l++];
			} else {
				help[index++] = arr[r++];
			}
		}
		while (l <= middle) {
			help[index++] = arr[l++];
		}
		while (r <= right) {
			help[index++] = arr[r++];
		}
		for (int i = 0; i < help.length; i++) {
			arr[left + i] = help[i];
		}
	}
	
	static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		MergeSort(arr);
		printArray(arr);
	}
}
