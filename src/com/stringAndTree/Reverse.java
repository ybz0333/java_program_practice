package com.stringAndTree;

/*
 * 牛客网3.6句子逆序参考答案
 */
public class Reverse {
    public String reverseSentence(String A, int n) {
        if (A == null || n == 0) {
            return A;
        }
        char[] s = A.toCharArray();
        rotateWord(s);
        return String.valueOf(s);
    }
 
    public void rotateWord(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        reverse(chas, 0, chas.length - 1);
        int l = -1;
        int r = -1;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ' ') {
                l = i == 0 || chas[i - 1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
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