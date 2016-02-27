package com.ybz.sort;
/*
 * 快速排序答案
 */
public class QuickSort {
	 
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }
 
    public static void process(int[] arr, int left, int right) {
        if (left < right) {
            int random = left + (int) (Math.random() * (right - left + 1));
            swap(arr, random, right);
            int mid = partition(arr, left, right);
            process(arr, left, mid - 1);
            process(arr, mid + 1, right);
        }
    }
 
    public static int partition(int[] arr, int left, int right) {
        int pivot = left - 1;
        int index = left;
        while (index <= right) {
            if (arr[index] <= arr[right]) {
                swap(arr, ++pivot, index);
            }
            index++;
        }
        return pivot;
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
            quickSort(arr);
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
            quickSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
    }
 
}