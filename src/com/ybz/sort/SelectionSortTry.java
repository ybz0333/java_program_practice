package com.ybz.sort;

/*
 * 选择排序，时间复杂度为O(n^2)
 * 从0~n-1选出最小值（用下标记录），与0号位置交换
 * 从1~n-1选出最小值（用下标记录），与1号位置交换
 * 以此类推
 */
public class SelectionSortTry {
	static void selectSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		int minTag = 0;
		for(int i=0; i < arr.length-1; i++) {
			minTag = i;
			for(int j=i+1; j<arr.length; j++) {
				if(arr[j] < arr[minTag]) {
					minTag = j;
				}
			}
			if(minTag != i) {
				swap(arr, minTag, i);				
			}
		}
	}
	
	static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = temp;
	}
	
	static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i < length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		selectSort(arr);
		printArray(arr);
	}
}
