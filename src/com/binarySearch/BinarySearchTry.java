package com.binarySearch;
/*
 * 二分搜索
 * 搜索前需要先排序
 * Arrays中提供了二分搜索的方法
 * 搜索前需要先对数组排序
 * 如果数组包含多个带有指定值的元素，则无法保证找到的是哪一个。
 * static int binarySearch(int[] a, int key)
 * static int binarySearch(int[] a, int fromIndex, int toIndex, int key)
 * 其它基本数据类型和Object类型形式相同
 * static <T> int binarySearch(T[] a, T key, Comparator<? super T> c) 
 * static <T> int binarySearch(T[] a, int fromIndex, int toIndex, T key, Comparator<? super T> c)
 * Collections中也提供了binarySearch方法（针对List的，搜索前要先排序）
 * static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
 * static <T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)
 * Collections中的sort方法也是针对List的
 */
import java.util.Arrays;

public class BinarySearchTry {
	
	public static void bubbleSort(int[] arr) {
		for(int i=arr.length-1; i>0; i--) {
			for(int j=0; j<i; j++) {
				if(arr[j] > arr[j+1]) {
					swap(arr, j, j+1);
				}
			}
		}
	}
	
	private static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	public static int binarySearch(int[] arr, int val) {
		int left = 0;
		int right = arr.length - 1;
		int middle = 0;
		while(true) {
			middle = (left + right)/2;
			if(arr[middle] == val) {
				return middle;
			} else {
				if(arr[middle] > val) {
					right = middle-1;
				} else {
					left = middle+1;
				}
				if(left > right) {
					return -1;
				}
			}
		}
	}
	
	public static int SortAndBinarySearch(int[] arr, int val) {
		Arrays.sort(arr);
		return Arrays.binarySearch(arr, val);
	}
	
	private static int[] generateArr(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			// Math.random()返回的是double型结果
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArr(10, 10);
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println(binarySearch(arr, 2));
		
		int[] arr2 = generateArr(10, 10);
		int index = SortAndBinarySearch(arr2, 2);
		System.out.println(Arrays.toString(arr2));
		System.out.println(index);
	}
}
