package com.stringAndTree;
/*
 * 牛客网3.7字符串移位
 * 对于一个字符串，请设计一个算法，将字符串的长度为len的前缀平移到字符串的最后。
 * 给定一个字符串A和它的长度，同时给定len，请返回平移后的字符串。
 * 测试样例：
 * "ABCDE",5,3
 * 返回："DEABC"
 * 
 * 使用手摇算法：
 * 先将前一部份逆序，再将后一部分，最后整体逆序
 */
public class TranslationTry {
	public static String stringTranslation(String A, int n, int len) {
		char[] charArr = A.toCharArray();
		reverse(charArr, 0, len-1);
		reverse(charArr, len, A.length()-1);
		reverse(charArr, 0, A.length()-1);
		return new String(charArr);
	}
	
	public static void reverse(char[] charArr, int left, int right) {
		while(left < right) {
			char cTemp = charArr[left];
			charArr[left] = charArr[right];
			charArr[right] = cTemp;
			left++;
			right--;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(stringTranslation("ABCDE", 5, 3));
	}
}
