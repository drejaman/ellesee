package stacQueue;

import java.util.Stack;

//https://leetcode.com/problems/min-stack/
//Cracking3.2
class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int x) {
        stack.push(x);
        
        if(minStack.isEmpty() 
        		|| x <= minStack.peek())
        {
        		minStack.push(x);
        }
    }
    
    public void pop() {
    	
	    	if(!stack.isEmpty())
	    	{
	        int x = stack.pop();
	        
	        if(x == minStack.peek())
	        {
	        		minStack.pop();
	        }    		
	    	}
    }
    
    public int top() {

		if(!stack.isEmpty())
	    {
	    		return stack.peek();
	    }

		return -1;
    }
    
    public int getMin() {
        if(!minStack.isEmpty())
        {
        		return minStack.peek();
        }
        
        return -1;
    }
}