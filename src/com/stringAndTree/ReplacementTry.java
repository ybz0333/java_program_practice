package com.stringAndTree;
/*
 * 请编写一个方法，将字符串中的空格全部替换为“%20”。假定该字符串有足够的空间存放新增的字符，并且知道字符串的真实长度(小于等于1000)，同时保证字符串由大小写的英文字母组成。
 * 给定一个string iniString 为原始的串，以及串的长度 int len, 返回替换后的string。
 * 测试样例：
 * "Mr John Smith”,13'
 * 返回："Mr%20John%20Smith"
 * ”Hello  World”,12
 * 返回：”Hello%20%20World”
 * 
 * 不使用replace时的思路：
 * 先统计空格的数量，计算出新字符串的长度；再从右向左一边拷贝一边生成新字符串。
 * 
 * 注意char[]不能直接用"test"初始化，要用{'t','e','s','t'}初始化
 * 注意System.arrayCopy()，第一个和第三个参数是Object类型，但需要填数组类型，直接填"%20"是String类型，是错误的。
 */
public class ReplacementTry {
    public static String replaceSpace(String iniString, int length) {
    	return iniString.replace(" ", "%20");
    }
    
    public static String replaceSpace2(String iniString, int length) {
    	char[] charArr = iniString.toCharArray();
    	int oldLength = charArr.length;
    	int numSpace = 0;
    	for(int i=0; i<oldLength; i++) {
    		if(charArr[i] == ' ') {
    			numSpace++;
    		}
    	}
    	int newLength = oldLength + 2*numSpace;
    	char[] newCharArr = new char[newLength];
    	int index = newLength-1;
    	char[] spaceCharArr = "%20".toCharArray();
    	for(int i=oldLength-1; i>=0; i--) {
    		if(charArr[i] != ' ') {
    			newCharArr[index] = charArr[i];
    			index--;
    		} else {
    			System.arraycopy(spaceCharArr, 0, newCharArr, index-2, 3);
    			index-=3;
    		}
    	}
    	return new String(newCharArr);
    }
    
    public static void main(String[] args) {
    	String str = "Hello  world";
		System.out.println(replaceSpace(str, str.length()));
		System.out.println(replaceSpace2(str, str.length()));
	}
}
