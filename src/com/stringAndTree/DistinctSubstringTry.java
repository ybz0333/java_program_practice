package com.stringAndTree;

import java.util.HashMap;
import java.util.Map;
/*
 * 牛客网3.12 最长无重复字符子串练习题
 * 对于一个字符串,请设计一个高效算法，找到字符串的最长无重复字符的子串长度。
 * 给定一个字符串A及它的长度n，请返回它的最长无重复字符子串长度。保证A中字符全部为小写英文字符，且长度小于等于500。
 * 测试样例：
 * "aabcb",5
 * 返回：3
 * 
 * 思路：
 * 最优解为时间复杂度O(N)，额外空间复杂度O(N)
 * 求出以str中每个字符结尾的情况下，最长无重复字符串的长度，并在其中找出最大值返回。
 * 需要两个辅助变量：
 * 哈希表map	->	其中统计了每种字符之前出现的位置
 * 整型变量pre -> 代表以s[i-1]结尾的情况下，最长无重复子串的长度。
 * 遍历数组，每遍历一个位置，都从哈希表中取值，看当前位置字符在之前出现的位置，同时通过pre计算
 * 以s[i-1]结尾的最长无重复子串的起始位置。通过比较这两个位置可确定以s[i]结尾的最长无
 * 重复子串的长度。每次对pre和map做更新。
 */
public class DistinctSubstringTry {
	public static int longestSubstring(String A, int n) {
		char[] charArr = A.toCharArray();
		Map<Character,Integer> indexMap = new HashMap<Character,Integer>();
		int preLongest = 0;
		int longest = 0;
		for(int i=0; i<charArr.length; i++) {
			char c = charArr[i];
			Integer lastIdx = indexMap.get(c);
			if(lastIdx == null || i-preLongest > lastIdx) {
				preLongest++;
			} else {
				preLongest = i-lastIdx;
			}
			indexMap.put(c, i);
			if(preLongest > longest) {
				longest = preLongest;
			}
		}
		return longest;
	}
	
	public static void main(String[] args) {
		System.out.println(longestSubstring("abcd", 4));
		System.out.println(longestSubstring("abcb", 4));
		System.out.println(longestSubstring("aabcb", 5));
	}
}
