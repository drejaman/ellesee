package stacQueue;

//Cracking3.1
public class FixedMultiStack {
	private int numberOfStacks = 3;
	private int stackCapacity;
	
	//keeps all the elements
	private int[] values;
	
	//points to the index in each stack
	private int[] indexInStack;
	
	FixedMultiStack(int capacity)
	{
		stackCapacity = capacity;
		values = new int[numberOfStacks * stackCapacity];
		indexInStack = new int[numberOfStacks];
	}
	
	public void push(int stackNum, int value) //throws StackFullException
	{
		//if(isFull(stackNum)) throw new StackFullException();
		if(isFull(stackNum)) return;
		// increase stack pointer to the next free index when the new item can be stored
		indexInStack[stackNum]++;
		// gets the free index
		values[indexOfTop(stackNum)] = value;
	}
	
	public int pop(int stackNum)
	{
		if(isEmpty(stackNum)) return -1;
		
		int value = values[indexOfTop(stackNum)];
		// this line is optional to clear and assumed it will automatically clear
		values[indexOfTop(stackNum)] = 0;
		indexInStack[stackNum]--;
		return value;
	}
	
	public int peek(int stackNum)
	{
		if(isEmpty(stackNum)) return -1;
		
		return values[indexOfTop(stackNum)];		
	}

	public boolean isEmpty(int stackNum)
	{
		return indexInStack[stackNum] == 0;
	}
	
	public boolean isFull(int stackNum)
	{
		return (indexInStack[stackNum] == stackCapacity);
	}
	
	public int indexOfTop(int stackNum)
	{
		return (stackNum - 1) * stackCapacity + indexInStack[stackNum];
	}
}
