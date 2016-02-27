package com.ybz.sort;

/*
 * 快速排序
 * 时间复杂度为O(n*log(n))
 随机选一个数（该数称为划分值），小于等于该数的数统一放在该数的左边，大于该数的数统一放在该数的右边（该过程称为划分过程partition）。递归执行。
 划分过程（partition）：
 将随机选中的划分值交换到数组的最右边；
 在数组左侧维护一个“小于等于区间”（初始长度为0）
 向右遍历数组，若该数大于划分值，则继续遍历下一个数，若该数小于等于划分值，则将该数与小于等于区间的下一个数交换，再将小于等于区间向右扩展一个位置。
 遍历完所有元素后，将划分值与小于等于区间的下一个数交换，就完成一次划分过程。
 划分过程的时间复杂度为O(n)。
 */
/*
 * 注意强制转换：(int)1.9的结果为1
 */
public class QuickSortTry {
	public static void quickSort(int[] arr) {
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int indexR = (int) (left + Math.random() * (right - left + 1));// 划分值下标
		swap(arr, indexR, right);
		int middle = partition(arr, left, right);
		process(arr, left, middle - 1);
		process(arr, middle + 1, right);
	}
	public static int partition(int[] arr, int left, int right) {
		int indexB = left-1;//小于等于区间边界下标值
		for(int i=left; i<right; i++) {
			if(arr[i] <= arr[right]) {
				indexB++;
				swap(arr, i, indexB);
			}
		}
		indexB++;
		swap(arr, right, indexB);
		return indexB;
	}

	public static void swap(int[] arr, int index1, int index2) {
		int temp = arr[index2];
		arr[index2] = arr[index1];
		arr[index1] = temp;
	}
	
	public static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		quickSort(arr);
		printArray(arr);
	}
}
