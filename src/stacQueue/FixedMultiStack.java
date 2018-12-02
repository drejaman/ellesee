package stacQueue;

//Cracking3.1
public class FixedMultiStack {
	private int numberOfStacks = 3;
	private int stackCapacity;
	
	private int[] values;
	private int[] sizes;
	
	FixedMultiStack(int capacity)
	{
		stackCapacity = capacity;
		values = new int[numberOfStacks * stackCapacity];
		sizes = new int[numberOfStacks];
	}
	
	public void push(int stackNum, int value) //throws StackFullException
	{
		//if(isFull(stackNum)) throw new StackFullException();
		if(isFull(stackNum)) return;
		// increase stack pointer to the next free index when the new item can be stored
		sizes[stackNum]++;
		// gets the free index
		values[indexOfTop(stackNum)] = value;
	}
	
	public int pop(int stackNum)
	{
		if(isEmpty(stackNum)) return -1;
		
		int value = values[indexOfTop(stackNum)];
		// this line is optional to clear and assumed it will automatically clear
		values[indexOfTop(stackNum)] = 0;
		sizes[stackNum]--;
		return value;
	}
	
	public int peek(int stackNum)
	{
		if(isEmpty(stackNum)) return -1;
		
		return values[indexOfTop(stackNum)];		
	}

	public boolean isEmpty(int stackNum)
	{
		return sizes[stackNum] == 0;
	}
	
	public boolean isFull(int stackNum)
	{
		return (sizes[stackNum] == stackCapacity);
	}
	
	public int indexOfTop(int stackNum)
	{
		return (stackNum - 1) * stackCapacity + sizes[stackNum];
	}
}
