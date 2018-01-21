package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TestStrings {
public TestStrings()
{
}


public void test()
{
	//System.out.println(checkAnagram("last","stal"));
	//System.out.println(checkAnagram("last","fast"));	
	
//	findWords(new String[] {"Hello", "Alaska", "Dad", "Peace"});
	firstUniqChar("leetcode");	
}


public boolean checkAnagram(String str1,String str2)
{
	if(str1.length()!=str2.length())
		return false;
	int index=0,sum1=0,sum2=0;
	while(index<str1.length())
	{
		sum1+=str1.charAt(index);
		sum2+=str2.charAt(index);
		index++;		
	}
	if(sum1==sum2)
		return true;
	else return false;
}


public boolean checkPalinDrome(String str)
{
	char[] chars=str.toCharArray();
	int len=str.length();
	int start=0;
	int end=len-1;
	while(start<end)
	{
		if(chars[start]!=chars[end])
			return false;		
		start++;
		end--;
	}
	return true;	
}


// https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
public String reverseWords(String s) {
	if(s == null || s.isEmpty()) return s;
	
	StringBuilder builder = new StringBuilder();
	
	// slice the string into words based on space and calls subroutine reverseWhole to reverse the whole word
	for(String word : s.split(" "))
	{
		builder.append(reverseWhole(word));
		builder.append(" ");
	}	
	
	return builder.toString().trim();
}

// this method reverse the whole given string
private String reverseWhole(String s) {
	if(s == null || s.isEmpty()) return s;
	
	char[] stringChars = s.toCharArray();
	
	for(int i = 0, j = s.length() - 1; i < j ; i++, j--)
	{
		char temp = stringChars[i];
		stringChars[i] = stringChars[j];
		stringChars[j] = temp;
		
	}
	
	return new String(stringChars);
}

// https://leetcode.com/problems/keyboard-row/description/
// This one solved the problem by calling Subroutine isValidWordUsingSameKBLine that checks each word's validity
public String[] findWords(String[] words) {
	
	if(words == null || words.length == 0) return words;
	
	// A good demonstration for 2D ArrayList and how to initialize an ArrayList
	ArrayList<ArrayList<Character>> lines = new ArrayList<ArrayList<Character>>();

	lines.add(new ArrayList<Character>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')));
	lines.add(new ArrayList<Character>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')));
	lines.add(new ArrayList<Character>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm')));
	
	ArrayList<String> validWords = new ArrayList<String>();
	
	for(String wordAnyCase : words)
	{
		String word = wordAnyCase.toLowerCase();
		char firstChar = word.charAt(0);
		int lineIndex = lines.get(0).contains(firstChar) == true ? 0 : -1;
		
		if(lineIndex != 0)
		{
			lineIndex = lines.get(1).contains(firstChar) == true ? 1 : 2;
		}
		
		if(isValidWordUsingSameKBLine(word, lines.get(lineIndex)))
		{
			validWords.add(wordAnyCase);
		}
	}
	
	return validWords.toArray(new String[validWords.size()]);
}

// Validate Individual Word is created using the same line of the Keyboard
private boolean isValidWordUsingSameKBLine(String word, ArrayList<Character> line){
	if(word == null || word.isEmpty()) return true;
	
	// each character should be in the the given line of the keyboard. 
	// if that's not the case then it is not a valid word according to constructing a word using the same kb line
	for(char ch: word.toCharArray())
	{
		if(!line.contains(ch))
		{
			return false;
		}
	}
	
	return true;
}

/// https://leetcode.com/problems/first-unique-character-in-a-string/description/
public int firstUniqChar(String s) {
	char[] allChars = s.toCharArray();
	
	// this keeps track of the characters the way it is inserted
	LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();
	
	for(int i = 0; i < allChars.length; i++)
	{
		char key = allChars[i];
		
		if(map.containsKey(key))
		{
			map.remove(key);
			map.put(key, -1);
		}
		else
		{
			map.put(key, i);
		}
	}
	
	for(Entry<Character, Integer> entry : map.entrySet())
	{
		if(entry.getValue() != -1)
		{
			return entry.getValue();
		}
	}
	
	return -1;
}



}
