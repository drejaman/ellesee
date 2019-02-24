package recursion;

import java.util.Arrays;

/**
 * @author afzaman
 *
 */
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

// worst case implementation. should be at least iterative or memoized
/**
 * @param n calculate n-th fibo number
 * @return calculated fibonacci number
 */
public int Fibonacci(int n)
{
	if (n == 2) return 1;
	else if(n == 1) return 1;
	else return (Fibonacci(n - 1) + Fibonacci(n - 2));	
}

// goes to common backtracking
public void stringPermutation(String strLeftToPerm, String permutedString)
{
	//stopping condition when the permutation is done
	if(strLeftToPerm.length() == 1)
	{
		System.out.println(permutedString + strLeftToPerm);
		return;
	}
	
	//we take out one character from 
	//different positions of strLeftToPerm
	//add that taken character to already permutedString
	//recursively call the permute method with the rest of
	//the characters from permutedString
	for(int i = 0; i < strLeftToPerm.length(); i++)
	{
		String single = strLeftToPerm.substring(i, i+1);
		
		StringBuilder stb = new StringBuilder();
		stb.append(strLeftToPerm.substring(0, i));
		stb.append(strLeftToPerm.substring(i+1, strLeftToPerm.length()));
		stringPermutation(stb.toString(),permutedString+single);
	}	
}

// Same as above(stringPermutation). we just call stringPermutation
// after sorting the input string
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
	
	for(int i = 0; i < len; i++)
	{
		printAllWordsRepeat(chars, str + chars[i], len);
	}
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