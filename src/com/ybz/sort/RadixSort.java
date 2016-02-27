package com.ybz.sort;

/*
 * 计数排序答案
 */
import java.util.ArrayList;
import java.util.LinkedList;
 
public class RadixSort {
    //  ˝◊Èø…∞¸∫¨’˝ ˝°¢∏∫ ˝°¢0µƒª˘ ˝≈≈–Ú
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int negNum = 0; // arr÷–∏∫ ˝µƒ∏ˆ ˝
        for (int i = 0; i < arr.length; i++) {
            negNum += arr[i] < 0 ? 1 : 0;
        }
        int[] negArr = new int[negNum];
        int[] posArr = new int[arr.length - negNum];
        int negi = 0;
        int posi = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                negArr[negi++] = -arr[i];
            } else {
                posArr[posi++] = arr[i];
            }
        }
        radixSortForPositive(negArr);
        radixSortForPositive(posArr);
        int index = 0;
        for (int i = negArr.length - 1; i >= 0; i--) {
            arr[index++] = -negArr[i];
        }
        for (int i = 0; i < posArr.length; i++) {
            arr[index++] = posArr[i];
        }
    }
 
    // ÷ªƒ‹Ω‚æˆ’˝’˚ ˝ ˝◊Èµƒª˘ ˝≈≈–Ú
    public static void radixSortForPositive(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        ArrayList<LinkedList<Integer>> qArr1 = new ArrayList<LinkedList<Integer>>();
        ArrayList<LinkedList<Integer>> qArr2 = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < 10; i++) {
            qArr1.add(new LinkedList<Integer>());
            qArr2.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < arr.length; i++) {
            qArr1.get(arr[i] % 10).offer(arr[i]);
        }
        long base = 10;
        while (base <= Integer.MAX_VALUE) {
            for (int i = 0; i < 10; i++) {
                LinkedList<Integer> queue = qArr1.get(i);
                while (!queue.isEmpty()) {
                    int value = queue.poll();
                    qArr2.get((int) (value / base) % 10).offer(value);
                }
            }
            ArrayList<LinkedList<Integer>> tmp = qArr1;
            qArr1 = qArr2;
            qArr2 = tmp;
            base *= 10;
        }
        int index = 0;
        for (int i = 0; i < 10; i++) {
            LinkedList<Integer> queue = qArr1.get(i);
            while (!queue.isEmpty()) {
                arr[index++] = queue.poll();
            }
        }
    }
 
    public static int[] generateArray(int len, int range) {
        if (len < 1) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - 500000000;
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
        int range = Integer.MAX_VALUE;
        int testTimes = 50000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateArray(len, range);
            radixSort(arr);
            if (!isSorted(arr)) {
                System.out.println("Wrong Case:");
                printArray(arr);
                break;
            }
        }
    }
}