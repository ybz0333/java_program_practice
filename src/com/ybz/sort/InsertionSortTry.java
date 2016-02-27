package com.ybz.sort;
/*
 * 插入排序
 * 时间复杂度O(n^2)
 * 数组左侧维护一个有序段，每次向右扩展一个数
 * 将该数插入到有序段中的适当位置
 * （插入时采取从右向左逐个交换，直到位置正确的方法）
 */
/*
 * 时间复杂度为O(n^2)的算法：
 * 冒泡排序
 * 选择排序
 * 插入排序
 */
public class InsertionSortTry {
	static void insertionSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		//k:有序的边界下标
		for(int k = 0; k< arr.length-1; k++) {
			for(int i = k+1; i > 0; i--) {
				if(arr[i] < arr[i-1]) {
					swap(arr, i , i-1);
				}
				else {
					break;
				}
			}
		}
	}
	
	static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = temp;
	}
	
	static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i = 0; i < length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		insertionSort(arr);
		printArray(arr);
	}
}
