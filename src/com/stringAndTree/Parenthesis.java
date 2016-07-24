package com.stringAndTree;
/*
 * 牛客网3.11 合法括号序列判断练习题答案
 */

public class Parenthesis {
    public boolean chkParenthesis(String A, int n) {
        if (A == null || n == 0) {
            return false;
        }
        char[] chas = A.toCharArray();
        int status = 0;
        for (int i = 0; i < n; i++) {
            if (chas[i] != ')' && chas[i] != '(') {
                return false;
            }
            if (chas[i] == ')' && --status < 0) {
                return false;
            }
            if (chas[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }
}