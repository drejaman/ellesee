package stacQueue;

import java.util.Stack;

//Cracking3.4
public class MyWay<T> {

	Stack<T> oldest, newest;
	
	MyWay()
	{
		oldest = new Stack<T>();
		newest = new Stack<T>();
	}
	
	public int size() 
	{
		return oldest.size() + newest.size();
	}
	
	//logic:
	// addition always goes to newest that keeps the new elements
	// top of the newest is actually end of the queue 
	// whenever we get a new element we just push into newest without any thought
	// removal always from oldest. top of oldest is the first of queue
	// for removal/peek we need to make sure we look at the first of queue (which is top of oldest)
	// shiftstack makes sure if the oldest ever gets empty then we move all the elements from newest 
	// to oldest in reverse order making sure oldest keeps track of the first elements in its top

	public void add(T value)
	{
		newest.push(value);
	}
	
	public void shiftStacks()
	{
		if(oldest.isEmpty())
		{
			while(!newest.isEmpty())
			{
				oldest.push(newest.pop());
			}
		}
	}
	
	public T remove()
	{
		// we make sure oldest gets the first queue elements on top 
		shiftStacks();
		
		return oldest.pop();
	}

	public T peek()
	{
		// we make sure oldest gets the first queue elements on top 
		shiftStacks();
		
		return oldest.peek();
	}
}
