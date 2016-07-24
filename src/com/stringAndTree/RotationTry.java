package com.stringAndTree;
/*
 * 1.4 两串旋转练习题
 * 如果对于一个字符串A，将A的前面任意一部分挪到后边去形成的字符串称为A的旋转词。比如A="12345",A的旋转词有"12345","23451","34512","45123"和"51234"。对于两个字符串A和B，请判断A和B是否互为旋转词。
 * 给定两个字符串A和B及他们的长度lena，lenb，请返回一个bool值，代表他们是否互为旋转词。
 * 测试样例：
 * "cdab",4,"abcd",4
 * 返回： true
 * 
 * 方法：
 * 1.先比较长度，不相等则排除
 * 2.在str1+str1中判断是否包含str2（KMP算法，时间复杂度O(n)）
 */
public class RotationTry {
	public static boolean chkRotation(String A, int lena, String B, int lenb) {
		if(lena != lenb) {
			return false;
		}
		String strAll = A + A;
		if(strAll.indexOf(B) != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	// 用蛮力法实现
	public static boolean chkRotation2(String A, int lena, String B, int lenb) {
		if(lena != lenb) {
			return false;
		}
		String strAll = A + A;
		// 注意for(int i=0;i<n;i++)中，i是for循环中的局部变量，在循环以外不能访问
		for(int i=0; i<lena; i++) {
			int j=0;
			while(j<lenb) {
				if(strAll.charAt(i+j) !=  B.charAt(j)) {
					break;
				}
				j++;
			}
			if(j == lenb) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(chkRotation("cdab", 4, "abcd", 4));
		System.out.println(chkRotation("cdab", 4, "abdc", 4));
		System.out.println(chkRotation2("cdab", 4, "abcd", 4));
		System.out.println(chkRotation2("cdab", 4, "abdc", 4));
	}
}
