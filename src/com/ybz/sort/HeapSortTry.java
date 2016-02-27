package com.ybz.sort;
/*
 * 堆排序
 * 时间复杂度为O(n*log(n))

将数组中的n个数建成一个大小为n的大根堆（堆顶为最大值）。
将堆顶元素与堆的最后一个位置的元素交换，将最大值放在数组的最后位置并脱离堆，使数组最后的部分维护一个有序段。
将剩余的n-1大小的堆从堆顶位置开始进行大根堆的调整。再将堆顶元素与堆的最后一个位置的元素交换，将最大值放在数组的最后位置并脱离堆，使数组最后的部分维护一个有序段。
以此类推，直到堆的元素个数减为1。
4 5 3 0 1 7 2 6
最大堆：
       7
     /   \
    6     5
   / \   / \
  4   1 3   2
 /
0
对应数组：
7 6 5 4 1 3 2 0

堆的特点
是一棵完全二叉树
（完全二叉树的性质：
节点i的左孩子节点为2i+1，右孩子节点为2i+2，父节点为(i-1)/2。
最后一个节点的父节点的后面一个节点一直到最后一个节点都是叶子节点（叶子节点可看成满足最大堆性质的子树）
）
分为最大堆与最小堆，最大堆的任意子树根节点不小于任意子节点，最小堆的任意子树根节点不大于任意子节点。


堆排序算法中堆的构建、大根堆的调整都要采用maxHeap函数：
该函数假设一个元素的两个子节点都满足最大堆的性质（左右子树都是最大堆），只有根节点可能违反最大堆性质，那么把该元素以及左右子节点的最大元素找出来，如果该元素已经最大，那么整棵树都是最大堆，函数返回，否则交换根元素与最大元素的位置，继续调用maxHeap处理原最大元素所在的子树。该算法是分治发的典型应用。
由数组构建最大堆：由于最后一个节点的父节点的后面一个节点一直到最后一个节点都是叶子节点（叶子节点可看成满足最大堆性质的子树），则构建时从最后一个节点的父节点开始一直到第一个节点（设数组长度为n，则从n/2-1到0），依次向上将子树最大堆化

所谓堆排序就是利用堆这种数据结构来对数组排序（这里使用最大堆）。处理的思想和冒泡排序、选择排序非常类似，一层层封顶，只是最大元素的选取使用了最大堆。

堆的插入：总是插入到最后一个位置，再恢复堆次序。
堆的删除：总是删除第一个元素。最后一个元素被用来填补空缺位置，再恢复堆次序。
 */

public class HeapSortTry {

	public static void heapSort(int[] arr) {
		buildMaxHeap(arr);
		for(int i=arr.length-1;i>=1;i--) {
			swap(arr,0,i);
			maxHeap(arr,i,0);
		}
	}
	
	public static void buildMaxHeap(int[] arr) {
		//依次向上将子树最大堆化
		for(int i=arr.length/2-1; i>=0; i--) {
			maxHeap(arr, arr.length-1, i);
		}
	}
	
	//indexR为进行maxHeap的子树的根节点的下标
	//传入heapSize是为了判断左右孩子节点下标是否有效
	public static void maxHeap(int[] arr, int heapSize, int indexRoot) {
		if(heapSize <= 1) {
			return;
		}
		int indexLeft = 2*indexRoot+1;
		int indexRight = 2*indexRoot+2;
		int indexLarge = indexRoot;
		if(indexLeft<heapSize && arr[indexLeft]>arr[indexRoot]) {//要判断是否存在左孩子节点
			indexLarge = indexLeft;
		}
		if(indexRight<heapSize && arr[indexRight]>arr[indexLarge]) {//要判断是否存在右孩子节点
			indexLarge = indexRight;
		}
		//若根节点已是最大，则认为该子树满足最大堆的条件，返回
		if(indexLarge == indexRoot ) {
			return;
		}
		else {
			swap(arr, indexLarge, indexRoot);
			maxHeap(arr, heapSize, indexLarge);
		}
		
	}
	
	public static void printMaxHeap(int[] arr) {
		for(int i=1; i<= arr.length; i*=2) {
			for(int j=i-1; j<=2*i-2 && j<arr.length; j++) {
				System.out.print(arr[j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void swap(int[] arr, int index1, int index2) {
		arr[index1] = arr[index1] + arr[index2];
		arr[index2] = arr[index1] - arr[index2];
		arr[index1] = arr[index1] - arr[index2];
	}
	
	public static int[] generateArray(int length, int range) {
		int[] arr = new int[length];
		for(int i=0; i<length; i++) {
			arr[i] = (int)(Math.random() * range);
		}
		return arr;
	}
	public static void printArray(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] arr = generateArray(10, 10);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
		System.out.println();
		
		int[] arr2 = generateArray(10, 10);
		printArray(arr2);
		buildMaxHeap(arr2);
		printMaxHeap(arr2);
	}
}
