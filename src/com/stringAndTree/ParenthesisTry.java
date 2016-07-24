package com.stringAndTree;
/*
 * 对于一个字符串，请设计一个算法，判断其是否为一个合法的括号串。
 * 给定一个字符串A和它的长度n，请返回一个bool值代表它是否为一个合法的括号串。
 * 测试样例：
 * "(()())",6
 * 返回：true
 * 测试样例：
 * "()a()()",7
 * 返回：false
 * 测试样例：
 * "()(()()"
 * 返回：false
 */
public class ParenthesisTry {
	public static boolean chkParenthesis(String A, int n) {
		char[] charArr = A.toCharArray();
		int length = A.length();
		int num = 0;
		for(int i=0; i<length; i++) {
			/*
			if(charArr[i] == '(') {
				num++;
			} else if(charArr[i] == ')'){
				num--;
			} else {
				return false;
			}
			*/
			switch(charArr[i]) {
			case '(':
				num++;
				break;
			case ')':
				num--;
				break;
			default:
				return false;
			}
			if(num < 0) {
				return false;
			}
		}
		if(num == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(chkParenthesis("(()())",6));
		System.out.println(chkParenthesis("()a()()",7));
		System.out.println(chkParenthesis("()(()()",7));
	}
}
