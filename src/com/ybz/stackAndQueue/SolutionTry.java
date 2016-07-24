package com.ybz.stackAndQueue;
/*
 * 牛客网4.2 可查询最值的栈练习题
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * 
 * 思路：
 * 使用两个栈（stackData和stackMin）
 * 入stackData栈时取当前数与栈顶元素的较小值入stackMin栈，出stackData栈时同步出stackMin栈
 * stackMin栈始终维护着stackData中的数对应的最小值
 * 
 * 注意Deque接口中的方法（实现类有LinkedList、ArrayDeque、LinkedBlockingDeque）：
	与队列相关：
	boolean offer(E e)  不会抛出异常
	E poll()  不会抛出异常
	E peek()  不会抛出异常
	（注意Deque的poll()和peek()操作的队头是双端队列的第一个元素）
	与堆栈相关：
	void push(E e)  会抛出异常（超出容量限制）
	E pop()  会抛出异常
	E peek()  不会抛出异常
	
 * Stack类中的方法：
	E peek()  会抛出异常
	E pop()  会抛出异常
	E push(E item)  不会抛出异常
 */
import java.util.Stack;

public class SolutionTry {

    Stack<Integer> stackData = new Stack<Integer>();
    Stack<Integer> stackMin = new Stack<Integer>();
	
    public void push(int node) {
        stackData.push(node);
        if(stackMin.size() > 0) {
	        int stackMinTop = stackMin.peek();
	        stackMin.push(node < stackMinTop ? node : stackMinTop);
	    } else {
	    	stackMin.push(node);
	    }
    }
    
    public void pop() {
        stackData.pop();
        stackMin.pop();
    }
    
    public int top() {
        return stackData.peek();
    }
    
    public int min() {
        return stackMin.peek();
    }
    
    public static void main(String[] args) {
		SolutionTry myStack = new SolutionTry();
		myStack.push(3);
		myStack.push(4);
		myStack.push(5);
		myStack.push(1);
		myStack.push(2);
		myStack.push(1);
		System.out.println(myStack.min());
		myStack.pop();
		myStack.pop();
		myStack.pop();
		System.out.println(myStack.min());
	}
}