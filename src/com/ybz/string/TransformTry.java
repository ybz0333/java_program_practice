package com.ybz.string;
/*
 * 2.3 词语变形练习题
对于两个字符串A和B，如果A和B中出现的字符种类相同且每种字符出现的次数相同，则A和B互为变形词，请设计一个高效算法，检查两给定串是否互为变形词。
给定两个字符串A和B及他们的长度，请返回一个bool值，代表他们是否互为变形词。
测试样例：
"abc",3,"bca",3
返回：true
 */
public class TransformTry {
	//注意String取长度用length()方法，数组取长度用length成员
	public boolean chkTransform(String A, int lena, String B, int lenb) {
		if(A == null || B == null || A.length() != B.length()) {
			return false;
		}
		char[] gcA = A.toCharArray();
		char[] gcB = B.toCharArray();
		//数组这样初始化，元素默认初始化为0？
		int[] arrayA = new int[256];
		for(int i=0; i<gcA.length; i++) {
			arrayA[gcA[i]]++;
		}
		
		int[] arrayB = new int[256];
		for(int i=0; i<gcB.length; i++) {
			arrayB[gcB[i]]++;
		}
		
		for(int i=0; i<arrayA.length; i++) {
			if(arrayA[i] != arrayB[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		String A = "abc";
		String B = "bca";
		TransformTry transformTry = new TransformTry();
		System.out.println(transformTry.chkTransform(A, A.length(), B, B.length()));
		
		String C = "acbd";
		System.out.println(transformTry.chkTransform(A, A.length(), C, C.length()));
		
		String D = "aabbcc";
		String E = "abcabc";
		System.out.println(transformTry.chkTransform(D, D.length(), E, E.length()));
		
		boolean result = transformTry.chkTransform("UGRPJTDLDYILAZVKSFALAVUYWYTMQFKMN",33,"MFGJPSVALYNdDTKRAFUQWYZIADMLTYLKV",33);
		System.out.println(result);
	}
}
