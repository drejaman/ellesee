package moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashCounter {
	
Map<Integer,Integer> map=new HashMap<Integer,Integer>();

public HashCounter()
{
	
}

public void initializeHashTable(ArrayList<Integer> array)
{
	int i=0;
	while(i<array.size())
	{
		if(map.containsKey(array.get(i)))
		{
			map.put(array.get(i), map.get(array.get(i))+1);
		}
		else
			map.put(array.get(i), 1);
		i++;
	}
}

public ArrayList<Integer> removeNby2(int n)
{
	ArrayList<Integer> array=new ArrayList<Integer>();
	Iterator<?> it=map.entrySet().iterator();
	while(it.hasNext())
	{
		Map.Entry pairs=(Map.Entry) it.next();
		//System.out.println((Integer)pairs.getValue());
		if((Integer)pairs.getValue()>(n/2))
			;
			//map.remove(pairs.getKey());
		else
			{
				for(int i=0;i<(Integer)pairs.getValue();i++)
					array.add((Integer)pairs.getKey());
			}			
	}
	
	return array;	
}

public void printArray(ArrayList<Integer> array)
{
	for(int i=0;i<array.size();i++)
		System.out.println(array.get(i));
}
}
