package com.stringAndTree;
/*
 * 牛客网3.8 拼接最小字典序练习题答案
 */

import java.util.*;

public class Prior {
    public class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }
 
    public String findSmallest(String[] strs, int n) {
        if (strs == null || n == 0) {
            return "";
        }
        // 根据新的比较方式排序
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < n; i++) {
            res += strs[i];
        }
        return res;
    }
}