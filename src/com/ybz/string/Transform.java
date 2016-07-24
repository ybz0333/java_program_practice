package com.ybz.string;
/*
 * 牛客网2.3 词语变形练习题答案
 */

public class Transform {
    public boolean chkTransform(String str1, int a, String str2, int b) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < chas1.length; i++) {
            map[chas1[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (map[chas2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }
}
