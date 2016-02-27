package com.ybz.sort;
/*
 * 插入排序答案
 */
public class InsertionSort {
	 
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            index = i;
            while (index > 0) {
                if (arr[index - 1] > arr[index]) {
                    swap(arr, index - 1, index);
                    index--;
                } else {
                    break;
                }
            }
        }
    }
 
    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
 
    public static int[] generateArray(int len, int range) {
        if (len < 1) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        return arr;
    }
 
    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
 
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
 
    public static void main(String[] args) {
        int len = 10;
        int range = 10;
        int testTimes = 50000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(len, range);
            insertionSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
 
        int len2 = 13;
        int range2 = 10;
        int testTimes2 = 50000;
        for (int i = 0; i < testTimes2; i++) {
            int[] arr = generateArray(len2, range2);
            insertionSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
    }
}