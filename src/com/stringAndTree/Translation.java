package com.stringAndTree;
/*
 * 牛客网3.7字符串移位参考答案
 */
public class Translation {
    public String stringTranslation(String A, int n, int len) {
        char[] s = A.toCharArray();
        rotate1(s, len);
        return String.valueOf(s);
    }
 
    public void rotate1(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }
 
    public void reverse(char[] chas, int start, int end) {
        char tmp = 0;
        while (start < end) {
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }
}
