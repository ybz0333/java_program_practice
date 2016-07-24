package com.stringAndTree;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 牛客网3.8拼接最小字典序练习题
 * 对于一个给定的字符串数组，请找到一种拼接顺序，使所有小字符串拼接成的大字符串是所有可能的拼接中字典序最小的。
 * 给定一个字符串数组strs，同时给定它的大小，请返回拼接成的串。
 * 测试样例：
 * ["abc","de"],2
 * "abcde"
 * 
 * 思路：看成排序问题。两个字符串的法则为比较str1+str2和str2+str1的字典序
 * （不能直接比较str1和str2的字典序，因为牵涉到"abc"和"abca"、"abc"和"abcd"这样的例子）
 * 
 * 知识点：Comparable、字符串的compareTo方法、字符串与字符串数组的拷贝方法、Arrays的方法
 * Comparable<T>接口和Comparator<T>接口的使用场景：1.用在Collections.sort()方法和Arrays.sort()方法（Comparator<T>或Comparable） 2.用在有序Map中的键或有序集合中的元素（Comparable）
 * 即便实现了Comparable，也不能直接使用>、<比较大小，会提示操作符未定义）
 * 接口中的待实现方法都是： int compareTo(T o);（变量名o可换成other），返回值取1、0、-1。
 * 实现时推荐满足“(e1.compareTo((Object)e2) == 0) 与e1.equals((Object)e2) 具有相同的布尔值”的条件
 * 实现时注意泛型：
 * class MyClass implements Comparable<MyClass>
 * class MyComparator implements Comparator<MyClass>
 * 注意Arrays与Collections的sort方法：
 * Arrays：
 * static void sort(int[] a) 
 * static void sort(int[] a, int fromIndex, int toIndex)（其它基本数据类型和Object类似） 
 * static <T> void sort(T[] a, Comparator<? super T> c) 
 * static <T> void sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)
 * Collections：
 * static <T extends Comparable<? super T>> void sort(List<T> list)（前面的返回值可以当成void？）
 * static <T> void sort(List<T> list, Comparator<? super T> c) （前面的返回值可以当成void？）
 * 注意Arrays和Collection的排序是就地处理的，不生成新的数组/集合，返回值可以看成void？
 * 
 * 注意java的泛型不支持基本数据类型
 * 
 * 数组拷贝：
 * 1.使用Arrays.copyOf()
 * static int[] copyOf(int[] original, int newLength) （其它基本数据类型和Object类似）
 * static <T> T[] copyOf(T[] original, int newLength)
 * 注意要给出newlength参数，不满足的截取或用0填充
 * 2.使用System.arrayCopy()
 * static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
 * src、dest为数组对象。从src的srcPos到srcPos+length-1向dest到destPos+length-1复制。（dest数组要有足够长度）
 * 注意System.arrayCopy()是native方法，而Arrays.copyOf()调用了System.arrayCopy()
 * 3.使用clone()
 * 对于自定义数据类型，要调用clone方法，需要实现Cloneable接口，重写clone方法（Object类的clone方法是protected类型的，重写时要定义成public类型）
 * 例如：
 * class MyClass implements Cloneable {
 * 	int num;
 * 	@override
 * 	public Object clone() {
 * 		MyClass o = null;
 * 		try {
 * 			o = (MyClass)super.clone();
 * 		} catch(CloneNotSupportException e) {
 * 			e.printStackTrace();
 * 		}
 * 		return o;
 * 	}
 * }
 * 如果某类没有实现Cloneable接口，并调用了Object的clone()方法，Object的clone()方法就会抛出CloneNotSupportedException异常。
 * 自定义类中有引用数据类型成员的情况，还要另行处理
 * 注意影子克隆和深克隆的概念
 * 对String和基本数据类型数组（和String[]）可以直接使用clone()？
 * 对集合类的实现类（ArrayList、LinkedList这种），已实现Cloneable接口、重写了clone()方法，其API说明为：
 * public Object clone() 返回此ArrayList（LinkedList）实例的浅表副本。（不复制这些元素本身）那么这里实现的是影子克隆吗？影子克隆处理基本数据类型没有问题？那处理String和数组呢？
 * 
 * 注意java的基本数据类型只有8种（4类）：
 * 整型 byte short int long
 * 浮点型 float double
 * 逻辑型 boolean
 * 字符型 char
 * String和数组是引用数据类型，是两种特殊的类型
 * 
 * String的构造函数有：
 * String(byte[] bytes)
 * String(char[] value)
 * String(String original)
 * String(StringBuffer buffer)
 * String(StringBuilder builder)
 * 等
 * 会返回String的副本（是新的String对象）
 * 
 * 关于数组和集合的相互转换：
 * 数组转集合：
 * static <T> List<T> asList(T... a)
 * 该方法的参数是可变参数，直接填多个元素，而不支持数组为输入参数。
 * 数组转集合还是要遍历。
 * 集合转数组：
 * List和Set都提供了两个toArray方法：
 *  Object[] toArray() 
 *  <T> T[] toArray(T[] a) 
 *  
 */
public class PriorTry {
	public static String findSmallest(String[] strs, int n) {
		class MyComparator implements Comparator<String> {

			@Override
			public int compare(String str1, String str2) {
				String str12 = str1 + str2;
				String str21 = str2 + str1;
				return str12.compareTo(str21);
			}
		}
		String[] strsResult = Arrays.copyOf(strs, strs.length);
		Arrays.sort(strsResult, new MyComparator());
		StringBuilder strBuilder = new StringBuilder();
		for(String str : strsResult) {
			strBuilder.append(str);
		}
		return strBuilder.toString();
	}
	
	public static void main(String[] args) {
		String strResult = PriorTry.findSmallest(new String[]{"abc","de"}, 2);
		System.out.println(strResult);
	}
}
