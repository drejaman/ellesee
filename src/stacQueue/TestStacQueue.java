package stacQueue;

import java.util.Stack;

public class TestStacQueue {

	//Cracking3.5
	public void sort(Stack<Integer> s)
	{
		//this stack is used to sort
		Stack<Integer> helper = new Stack<Integer>();
		
		while(!s.isEmpty())
		{
			//pop one element from original stack
			int temp = s.pop();
			
			//most important logic of this code
			//as long as the helper's element is greater than temp
			//transfer it to original stack
			while(!helper.isEmpty() && helper.peek() > temp)
			{
				s.push(helper.pop());
			}
			
			//push temp to right position in helper stack
			helper.push(temp);
		}
		
		//now s is Empty and 
		//the helper stack contains the sorted numbers 
		while(!helper.isEmpty())
		{
			s.push(helper.pop());
		}
	}
}
