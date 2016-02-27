package com.ybz.sort;
/*
 * 冒泡排序
 * 时间复杂度O(n^2)
 * 依次交换0~n-1，将最大的数交换至n-1处
 * 再依次交换0~n-2，将最大的数交换至n-2处
 * 以此类推
 */
/*
 * Math.random()返回返回带正号的 double 值，该值大于等于 0.0 且小于 1.0。
 */
public class BubbleSortTry {
	public static void bubbleSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		for(int i = arr.length - 1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
	public static int[] generateArray(int len, int range) {
		if(len < 0) {
			return null;
		}
		int[] arr = new int[len];
		for(int i = 0; i < len; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		bubbleSort(arr);
		printArray(arr);
	}
}