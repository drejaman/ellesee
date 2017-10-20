package filepattern;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TestFilePattern {
public TestFilePattern()
{

}
public void test() throws Exception
{
	//streamOfNumbers("infiniteStream.txt");
	System.out.println(retrieveFromFile());
}

public void streamOfNumbers(String fileName) throws Exception
{
	BufferedReader brd=new BufferedReader(new InputStreamReader(new FileInputStream("/home/az99/Preload/Users/Zaman/workspace/JavaCup/src/fileStore/infiniteStream.txt")));
	//BufferedWriter bwd=new BufferedWriter(new FileWriter(fileName));
	//BufferedReader brr=new BufferedReader(new FileReader(fileName));
	
	int oddMax=0,evenMax=0;
	String redLine;
	//while(current!=0 || current!=-1)
	while((redLine=brd.readLine())!=null)
	{
		int start=0,end=0;
		int current=-1;
		while(end<redLine.length())
		{
			end=redLine.indexOf(" ", start);
			//System.out.println(end);
			if(end==-1)
			{
				end=redLine.length();
				//System.out.println(end);
			}
			current=Integer.parseInt(redLine.substring(start, end));
			start=end+1;
			System.out.println(current);
			if(current==0)
				break;
			if(current%2==0 && current>evenMax)
				evenMax=current;
			else if(current%2==1 && current>oddMax)
				oddMax=current;					
		}
		if(current==0)
			break;
	}
	System.out.println("Odd region max: "+oddMax);
	System.out.println("Even region max: "+evenMax);
	brd.close();
}


public StringBuffer retrieveFromFile()
{
	StringBuffer sb = new StringBuffer();
	try
	{
		Pattern pattern = Pattern.compile("b[ [a-z][A-Z][0-9] ]*");
		Scanner s = null;
		try
		{
			s = new Scanner(
			new BufferedReader(
			new FileReader("/home/az99/Preload/Users/Zaman/workspace/JavaCup/src/fileStore/uniqueIDs.txt")));
			
			while (s.hasNext())
			{
				if (s.hasNext(pattern))
				{
					sb.append(s.next() + "\n");
				}
				else
				{
					s.next();
				}
			}
		}
		finally
		{
			if (s != null)
			{
				s.close();
			}
		}
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	return sb;
}

}
