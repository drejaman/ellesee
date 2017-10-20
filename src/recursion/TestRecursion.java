package recursion;

import java.util.Arrays;

public class TestRecursion {
public TestRecursion()
{
}

public void test()
{
	//System.out.println(Fibonacci(11));
	//stringPermutation("abcd","");
	//stringPermutationLex("cadb");
	//insertCharsInString("123"," +-","");
	char[] chars= {'a','b','c'};
	printAllWordsRepeat(chars,"",chars.length);
}

public int Fibonacci(int n)
{
	if(n==2) return 1;
	else if(n==1) return 1;
	else return (Fibonacci(n-1)+Fibonacci(n-2));	
}

public void stringPermutation(String str,String printStr)
{
	if(str.length()==1)
	{
		System.out.println(printStr+str);
		return;
	}
	for(int i = 0; i < str.length(); i++)
	{
		String single = str.substring(i, i+1);
		
		StringBuilder stb = new StringBuilder();
		stb.append(str.substring(0, i));
		stb.append(str.substring(i+1, str.length()));
		stringPermutation(stb.toString(),printStr+single);
	}	
}

public void stringPermutationLex(String str)
{
	char[] chars=str.toCharArray();
	Arrays.sort(chars);
	String sorted=new String(chars);
	stringPermutation(sorted,"");
}

public void printAllWordsRepeat(char[] chars,String str,int len)
{
	if(str.length()==len)
	{
		System.out.println(str);
		return;
	}
	for(int i=0;i<len;i++)
	{
		printAllWordsRepeat(chars,str+chars[i],len);
	}
}

//this doesn't work now
public void findMinimalSquare(int number)
{
	int nearest=(int) Math.sqrt((1.0)*number);
	int remainder = number-nearest*nearest;
	if(remainder==0)
	{
		System.out.println(nearest);
		return;
	}
	findMinimalSquare(remainder);
}

//recursive solutions are beautiful but a waste of time and space
//time complexity for this is O(str2.length()^(str1.length()-1))
//O(k^(n-1))
public void insertCharsInString(String str1,String str2,String printable)
{
	if(str1.length()<=1)
	{
		System.out.println(printable+str1);
		return;
	}

	for(int j=0;j<str2.length();j++)
	{
		insertCharsInString(str1.substring(1,str1.length()),str2,printable+str1.substring(0,1)+str2.charAt(j));
	}
		
}


}
