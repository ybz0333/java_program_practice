package com.ybz.sort;
/*
 * 希尔排序
 * 时间复杂度为O(n*log(n))
是插入排序的改进
插入排序向左插入时要进行数的交换，采用的步长为1（与左侧相邻的数直接交换）
希尔排序的步长从某个数逐一减到1
6 5 3 1 8 7 2 4
如初始步长为3，则左侧3个数先不考虑，从第4个数开始向右逐一遍历。每个数向左隔3个数进行比较，如果比左边的数小，就交换（如1比6小，则交换），直到左侧越界。
之后将步长减为2，进行相同操作。
最后减为1，进行相同操作。
步长（即初始步长）的选取对希尔排序的性能有很大影响

/////////////

希尔排序是把记录按下标的一定增量分组（相隔某个增量的元素组成一组），对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减少为1时，数组中的所有元素被分到同一组中，此时再对全体元素进行一次直接插入排序，就完成了排序。

希尔排序的实质就是分组插入排序，该方法又称缩小增量排序。

希尔排序值基于插入排序的以下两点性质而提出改进方法的：
1.插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
2.但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。

希尔排序是非稳定排序算法。
 */
public class ShellSortTry {
	
	public static void shellSort(int[] arr) {
		int length = arr.length;
		int step = length / 2;// 这里取初始步长为数组总长度的一半
		for (int s = step; s >= 1; s/=2) {
			for (int i = s; i < length; i++) {
				// 这里l为左边考察到的数相隔一个步长的数的下标
				for (int j = i, l = j - s; l >= 0; j -= s, l -= s) {
					if (arr[j] < arr[l]) {
						swap(arr, j, l);
					}
				}
			}
		}
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		arr[index1] = arr[index1] + arr[index2];
		arr[index2] = arr[index1] - arr[index2];
		arr[index1] = arr[index1] - arr[index2];
	}
	
	public static void printArray(int[] arr) {
		for(int x : arr) {
			System.out.print(x + " ");
		}
		System.out.println();
	}
	
	public static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		shellSort(arr);
		printArray(arr);
	}
}
