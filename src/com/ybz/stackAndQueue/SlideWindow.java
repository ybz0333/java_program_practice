package com.ybz.stackAndQueue;
/*
 * 牛客网4.8 滑动窗口练习题参考答案
 */
import java.util.*;

public class SlideWindow {
    public int[] slide(int[] arr, int n, int w) {
        if (arr == null || w < 1 || n < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[n - w + 1];
        int index = 0;
        for (int i = 0; i < n; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}