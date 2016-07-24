package com.ybz.stackAndQueue;
/*
 * 编写一个类,只能用两个栈结构实现队列,支持队列的基本操作(push，pop)。
 * 给定一个操作序列ope及它的长度n，其中元素为正数代表push操作，为0代表pop操作，保证操作序列合法且一定含pop操作，请返回pop的结果序列。
 * 测试样例：
 * [1,2,3,0,4,0],6
 * 返回：[1,2]
 * 
 * 思路：
 * 栈：先进后出 队列：先进先出
 * 用两个栈（stackPush、stackPop）可以把顺序反过来，从而实现类似队列的操作
 * 注意点：
 * 1.如果stackPush要往stackPop中倒入数据，那么必须要把stackPush中的所有数据一次性倒完。
 * 2.如果stackPop中有数据，则不能发生倒数据的行为。
 * “可以选择在很多换方法中包含倒数据的逻辑，但要保证倒入数据的两个注意点不被违反。”
 * 这里选择在pop和peek时倒数据（stackPop为空时倒数据）。
 * 若只在push中倒数据，当倒过数据后再push，再连续pop时会出错。
 * 
 * 注意Arrays.toString能将数组转换为可供显示的字符串
 * 
 * 注意这种写法：for(int i=0,numPop=0;i<length;i++)
 * 换成for(int i=0,int numPop=0;i<length;i++)则不正确
 * 
 * int[] arr = new int[0];这种写法也是允许的
 */
import java.util.*;

public class TwoStackTry {
	
	Stack<Integer> stackPush = new Stack<Integer>();
	Stack<Integer> stackPop = new Stack<Integer>();
	
    public int[] twoStack(int[] ope, int n) {
    	int length = ope.length;
    	int[] arr = new int[length];
    	int numPop = 0;
    	for(int i=0; i<length; i++) {
    		if(ope[i] > 0) {
    			push(ope[i]);
    		} else if(ope[i] == 0) {
    			arr[numPop] = pop();
    			numPop++;
    		}
    	}
		int[] arrRet = new int[numPop];
		System.arraycopy(arr, 0, arrRet, 0, numPop);
		return arrRet;
    }
    
    private void push(int n) {
    	stackPush.push(n);
    }
    
    private int pop() {
    	if(stackPop.size() == 0) {
    		int size = stackPush.size();
    		for(int i=0; i<size; i++) {
    			stackPop.push(stackPush.pop());
    		}
    	}
    	return stackPop.pop();
    }
    
    private int peek() {
    	if(stackPop.size() == 0) {
    		int size = stackPush.size();
    		for(int i=0; i<size; i++) {
    			stackPop.push(stackPush.pop());
    		}
    	}
    	return stackPop.peek();
    }
    
    private int size() {
    	return stackPush.size() + stackPop.size();
    }
    
    public static void main(String[] args) {
    	TwoStackTry twoStack = new TwoStackTry();
    	int[] arr = {1,2,3,0,4,0};
    	int[] arrRet = twoStack.twoStack(arr, 6);
    	System.out.println(Arrays.toString(arrRet));
	}
}