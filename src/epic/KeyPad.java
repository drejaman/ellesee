package epic;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyPad {

	public KeyPad()
	{
	}

	public void keyPadSequence()
	{
		ArrayList<String> two=new ArrayList<String>();
		ArrayList<String> three=new ArrayList<String>();
		ArrayList<String> four=new ArrayList<String>();
		ArrayList<String> five=new ArrayList<String>();
		ArrayList<String> six=new ArrayList<String>();
		ArrayList<String> seven=new ArrayList<String>();
		ArrayList<String> eight=new ArrayList<String>();
		ArrayList<String> nine=new ArrayList<String>();
		two.add("a");two.add("b");two.add("c");
		three.add("d");three.add("e");three.add("f");
		four.add("g");four.add("h");four.add("i");
		five.add("j");five.add("k");five.add("i");
		six.add("m");six.add("n");six.add("o");
		seven.add("p");seven.add("q");seven.add("r");seven.add("s");
		eight.add("t");eight.add("u");eight.add("v");
		nine.add("w");nine.add("x");nine.add("y");nine.add("z");
		
		HashMap<Integer, ArrayList<String>> map=new HashMap<Integer, ArrayList<String>>();
		map.put(0, null);
		map.put(1, null);
		map.put(2, two);
		map.put(3, three);
		map.put(4, four);
		map.put(5, five);
		map.put(6, six);
		map.put(7, seven);
		map.put(8, eight);
		map.put(9, nine);
		
		String entry="12390";
		int index=0;
		ArrayList<ArrayList<String>> finalRound=new ArrayList<ArrayList<String>>();
		while(index<entry.length())
		{
			int current=Integer.parseInt(entry.substring(index, index+1));
			System.out.println(current);
			if((map.get(current))!=null)
			{
				ArrayList<String> keyList=(ArrayList<String>) map.get(current);
				finalRound.add(keyList);
			}
			index++;
		}
				
		printWords(finalRound,0,finalRound.size(),"");
	}
	
	public void printWords(ArrayList<ArrayList<String>> arrays,int index,int size,String str)
	{
		if(index > size-1) return;
		ArrayList<String> temp=arrays.get(index);
		index++;
		
		for(int i=0;i<temp.size();i++)
		{
			if(index==size)
				System.out.println(str + temp.get(i));
			printWords(arrays,index,size,str+temp.get(i));
		}
	}
}