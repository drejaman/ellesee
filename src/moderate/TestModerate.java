package moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestModerate {
public TestModerate()
{
}

public void test()
{
	//swapInPlace(3,2);
	//int max=getMaxWithoutIf(10,15);
	//System.out.println(max);
	
	//prints money amount in words
	/*
	//int amount=154231;
	int amount=119231;
	AmountInWord amtWord=new AmountInWord(amount);
	System.out.print(amount+" : ");
	amtWord.printInWord();
	*/
	
	//hashedSumFinder(10);
	testHashCounter();
	
}

public void swapInPlace(int a,int b)
{
	a=a+b;
	b=a-b;
	a=a-b;
	
	System.out.println(a);
	System.out.println(b);	
}

public int getMaxWithoutIf(int a,int b)
{
	int c=a-b;
	int k= (c>>31) & 0x01;
	int max=a-k*c;
	return max;
}

public void hashedSumFinder(int sum)
{
	HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
	int marker=sum+1;
	map.put(1, marker);
	map.put(2, marker);
	map.put(3, marker);
	map.put(9, marker);
	map.put(8, marker);
	map.put(7, marker);
	map.put(11, marker);
	
	int i=0;
	while(i++<=sum/2)
	{
		if(map.containsKey(i))
		if(map.get(i)==marker)
			if(map.get(sum-i)==marker)
			{
				map.put(i, sum-i);
				map.remove(sum-i);
			}
			
	}
	
	Iterator iter=map.entrySet().iterator();
	
	while(iter.hasNext())
	{
		Map.Entry pairs=(Map.Entry) iter.next();
		if(((Integer) pairs.getValue())!=marker)
			System.out.println(pairs.getKey()+" + "+pairs.getValue()+" = "+sum);
	}	
}

public void testHashCounter()
{
	ArrayList<Integer> array=new ArrayList<Integer>();
	for(int i=0;i<7;i++)
		array.add(i+1);
	for(int i=0;i<15;i++)
		array.add(11);
	for(int i=0;i<7;i++)
		array.add(100-i);
	
	HashCounter hashCounter=new HashCounter();
	hashCounter.initializeHashTable(array);
	hashCounter.printArray(hashCounter.removeNby2(20));
	
}


}
