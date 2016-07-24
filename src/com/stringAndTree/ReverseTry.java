package com.stringAndTree;
/*
 * 3.6 句子的逆序练习题
 * 对于一个字符串，请设计一个算法，只在字符串的单词间做逆序调整，也就是说，字符串由一些由空格分隔的部分组成，你需要将这些部分逆序。
 * 给定一个原字符串A和他的长度，请返回逆序后的字符串。
 * 测试样例：
 * "dog loves pig",13
 * 返回："pig loves dog"
 * 
 * 思路：
 * 1.提供任意段字符串逆序的函数
 * 2.字符串整体逆序
 * 3.每一段字符串逆序
 * 
 * 注意这里定位每个单词段的方法。使用left和right游标。
 * 这里使用char[]更便捷。使用String的toCharArray方法可以将String转换为char[]
 * 
 * 注意牛客网提供的答案中的逆序函数：不需要计算中间位置的下标
 */
public class ReverseTry {
	public static String reverseSentence(String A, int n) {
		char[] charArr = A.toCharArray();
		int length = A.length();
		// 先翻转整个字符数组
		reverseCharArray(charArr, 0, length-1);
		// left指示需要翻转的字符串片段的的最左侧字符的下标
		// right指示需要翻转的字符串片段的的最右侧字符的下标
		int left = 0;
		int right = 0;
		while(left<length) {
			// 寻找未考察字符串片段的最左端的非空白字符
			while(left < length && charArr[left] == ' ') {
				left++;
			}
			if(left == length) {
				break;
			}
			// 寻找未考察字符串片段的最右端的非空白字符
			right = left;
			// 注意这里&&的短路特性，前面的条件先判断，要将下标不越界写在前面
			while(right < length && charArr[right] != ' ') {
				right++;
			}
			right--;
			// 翻转该字符串片段
			reverseCharArray(charArr, left, right);
			left = right+1;
		}
		return new String(charArr);
	}
	
	// 翻转下标从left到right（包括两端）的字符数组
	public static void reverseCharArray(char[] charArr, int left, int right) {
		// left+right-i即为i对称位置的下标
		int nAdd = left + right;
		int middle = (nAdd+1)/2;
		for(int i=left; i<middle; i++) {
			int iRight = nAdd - i;
			char cTemp = charArr[i];
			charArr[i] = charArr[iRight];
			charArr[iRight] = cTemp;
		}
	}
	
	public static void main(String[] args) {
		String result = reverseSentence("dog loves pig", 13);
		System.out.println(result);
	}
}
