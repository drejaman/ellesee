package stacQueue;

import java.util.Stack;

public class TestStacQueue {

	//Cracking3.5
	public void sort(Stack<Integer> s)
	{
		Stack<Integer> helper = new Stack<Integer>();
		
		while(!s.isEmpty())
		{
			int temp = s.pop();
			
			// most important logic of this code
			while(!helper.isEmpty() && helper.peek() > temp)
			{
				s.push(helper.pop());
			}
			
			helper.push(temp);
		}
		
		while(!helper.isEmpty())
		{
			s.push(helper.pop());
		}
	}
}
