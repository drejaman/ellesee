package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class TestStrings {
public TestStrings()
{

	complexNumberMultiply("1+1i","1+1i");
}


public void test()
{
	//System.out.println(checkAnagram("last","stal"));
	//System.out.println(checkAnagram("last","fast"));	
	
//	findWords(new String[] {"Hello", "Alaska", "Dad", "Peace"});
//	firstUniqChar("leetcode");
//	shortestToChar("loveleetcode", 'e');
//	shortestToChar("loveleetcode", 'l');
//	subdomainVisits(new String[] {"9001 discuss.leetcode.com"});
//	String tinyUrl = encode("https://leetcode.com/problems/design-tinyurl");
//	decodeAtIndex("leet2code3", 10);
	
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

// https://leetcode.com/problems/jewels-and-stones/
public int numJewelsInStones(String J, String S) { 
	ArrayList<Character> jewels = new ArrayList<Character>();
	
	// adds the jewels
	for(char ch : J.toCharArray())
	{
		jewels.add(ch);
	}
	
	int jewelCount = 0;
	
	// checks each stone if they are jewel
	for(char ch : S.toCharArray())
	{
		if(jewels.contains(ch))
		{
			jewelCount++;
		}
	}
	
	return jewelCount;
}

// https://leetcode.com/problems/shortest-distance-to-a-character/description/
public int[] shortestToChar(String S, char C) {
	ArrayList<Integer> positionsOfC = new ArrayList<Integer>();
	
	char[] sChars = S.toCharArray();
	
	// determining the indices of C in the String
	for(int i = 0; i < sChars.length; i++ )
	{
		if (sChars[i] == C)
		{
			positionsOfC.add(i);
		}
	}
	
	int cIndex = 0;
	
	int[] shortestDistance = new int[S.length()];
	
	for(int i = 0; i < sChars.length; i++)
	{
		// if the currentIndex of the Character C is not the last one then
		if(cIndex < positionsOfC.size() - 1)
		{
			shortestDistance[i] = Math.min(Math.abs(i - positionsOfC.get(cIndex)), Math.abs(i - positionsOfC.get(cIndex + 1)));

			if(i >= positionsOfC.get(cIndex + 1)) cIndex++;				
		}
		else
		{
			shortestDistance[i] = Math.abs(i - positionsOfC.get(cIndex));
		}
		
	}
		
	return shortestDistance;
}

// https://leetcode.com/problems/subdomain-visit-count/description/
public List<String> subdomainVisits(String[] cpdomains) {
	HashMap<String, Integer> subDomainCount = new HashMap<String, Integer>();
	
	for(String domain : cpdomains)
	{
		String[] domainWithCount = domain.split(" ");
		
		int count = Integer.parseInt(domainWithCount[0]);
		
		// the dot(.) in the split need to be handled as special character
		//https://stackoverflow.com/questions/14833008/java-string-split-with-dot
		String[] subDomains = domainWithCount[1].split("\\.");
		
		//Each address will have either 1 or 2 "." characters.
		// mail.google.com -> mail, google, com [0, 1, 2] -> mail.google.com [0, 1, 2], google.com [1, 2], com [2] 
		// discuss.leetcode.com
		// hotmail.com -> hotmail, com [0, 1] -> hotmail.com [0, 1], com[1]

		if(subDomains.length == 3)
		{
			String subDomain = domainWithCount[1];
			
			if(subDomainCount.containsKey(subDomain))
			{
				int currentCount = subDomainCount.remove(subDomain);
				subDomainCount.put(subDomain, currentCount + count);
			}
			else
			{
				subDomainCount.put(subDomain, count);				
			}
		}
		
		// when the length is 2/3
		String subDomain = subDomains[subDomains.length - 2] + "." + subDomains[subDomains.length - 1]; 

		if(subDomainCount.containsKey(subDomain))
		{
			int currentCount = subDomainCount.remove(subDomain);
			subDomainCount.put(subDomain, currentCount + count);
		}
		else
		{
			subDomainCount.put(subDomain, count);				
		}

		subDomain = subDomains[subDomains.length - 1];
		
		if(subDomainCount.containsKey(subDomain))
		{
			int currentCount = subDomainCount.remove(subDomain);
			subDomainCount.put(subDomain, currentCount + count);
		}
		else
		{
			subDomainCount.put(subDomain, count);				
		}
	}
	
	ArrayList <String> output = new ArrayList<String>();
	
	Iterator it = subDomainCount.entrySet().iterator();
	
	while(it.hasNext())
	{
		Map.Entry entry = (Map.Entry) it.next();
		output.add(entry.getValue() + " " + entry.getKey());
		it.remove();
	}
	
	return output;
}

// https://leetcode.com/problems/encode-and-decode-tinyurl/description/
// Straight forward implementation using a HashMap
private static HashMap<String, String> urlMapper = new HashMap<String, String>();

public String encode(String longUrl) {
	
	// when the url is already mapped
	if(urlMapper.containsValue(longUrl))
	{
		Iterator it = urlMapper.entrySet().iterator();
		
		while(it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			if(entry.getValue().equals(longUrl))
				return entry.getKey().toString();
		}
	}
	
	// otherwise gets a new entry
    String newKey = "http:////tinyurl.com//" + GenerateRandomString(6);
    
    // ensuring that we are using a new key
    while(urlMapper.containsKey(newKey))
    {
    		newKey = "http:////tinyurl.com//" + GenerateRandomString(6);
    }
    
    urlMapper.put(newKey, longUrl);
    
    return newKey;
}

// Decodes a shortened URL to its original URL.
public String decode(String shortUrl) {
	if(urlMapper.containsKey(shortUrl))
		return urlMapper.get(shortUrl);
	
	return "";
}

private String GenerateRandomString(int len){
	String AlphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	StringBuilder randomString = new StringBuilder();
	
	Random rand = new Random();
	
	for(int i = 0; i < len; i++)
	{
		int randomIndex = rand.nextInt(35);
		randomString.append(AlphaNumeric.charAt(randomIndex));
	}
	
	return randomString.toString();
}

//notworking
// https://leetcode.com/problems/decoded-string-at-index/description/
// Works for regular cases but for complicated case memory limit exceeds
// Sample non working example - "y959q969u3hb22odq595", 222280369
public String decodeAtIndex(String S, int K) {
    StringBuilder builder = new StringBuilder();
    String searchCharacter = "";
        
    char[] chars = S.toCharArray();
    
    for(char ch : chars)
    {
    		boolean isNumeric = (ch - '0' >= 0 && ch -'0' <= 9);
    			
    		if(!isNumeric)
    		{
    			builder.append(ch);
    		}
    		else
    		{
    			String currentString = builder.toString();
        		int repeat = ch - '0';    			
    			
        		for(int i = 1; i < repeat ; i++)
    			{
        			builder.append(currentString);
    			}
    		}

    		if(builder.length() >= K)
    		{
    			searchCharacter = builder.charAt(K - 1) + "";
    			break;
    		}
    }
    
    return searchCharacter;
}

//notworking
// https://leetcode.com/problems/complex-number-multiplication/description/
public String complexNumberMultiply(String a, String b) {
    int[] complexNumberA = extractComplexNumbers(a);
    int[] complexNumberB = extractComplexNumbers(b);
    
    int resultNumber = complexNumberA[0] * complexNumberB[0] + (-1) * complexNumberA[1] * complexNumberB[1];
    int resultComplex  = complexNumberA[0] * complexNumberB[1] + complexNumberA[1] * complexNumberB[0];
    
    return resultNumber + "+" +  resultComplex + "i"; 
}

// the format of complex number here is a + bi. so we can split the String representation in 2 parts using '+' split
private int[] extractComplexNumbers(String complexNumber)
{
	if(complexNumber == null || complexNumber.isEmpty()) return null;
	
	int[] complexPortions = new int[2];
	
	// need to handle dangling meta character
	// https://stackoverflow.com/questions/40246231/java-util-regex-patternsyntaxexception-dangling-meta-character-near-index-0
	String[] stringRepresentations = complexNumber.split("\\+");
	
	complexPortions[0] = Integer.parseInt(stringRepresentations[0]);
	//complexPortions[1] = Integer.parseInt(stringRepresentations[1]);
	complexPortions[1] = Integer.parseInt(stringRepresentations[1].substring(0, stringRepresentations[1].indexOf('i')).trim());
	
	return complexPortions;
}

//notworking
// this will not give the right result for cases like 23:51, 23:58, 00:01
// with this implementation the answer will be 7 where it should be 3
public int findMinDifference(List<String> timePoints)
{
	int[] timeArray = new int[timePoints.size()];
	
	for(int i = 0; i < timePoints.size(); i++)
	{
		timeArray[i] = timeStringToInt(timePoints.get(i));
	}
	
	Arrays.sort(timeArray);
	
	int minTime = Integer.MAX_VALUE;

	for(int i = 0; i < timeArray.length; i++)
	{
		if(Math.abs(timeArray[i] - timeArray[i + 1]) < minTime)
		{
			minTime = Math.abs(timeArray[i] - timeArray[i + 1]);
		}
	}
	
	return minTime;
}

private int timeStringToInt(String timeString)
{
	
	String[] timeParts = timeString.split(":");
	
	return Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
}

public List<String> printParenthesis(int n)
{
	List<String> parenthesisList = new ArrayList<String>();
	printParenthesis(parenthesisList, new StringBuilder(), n, n);
	
	return parenthesisList;
}

private void printParenthesis(List<String> parenthesisList, StringBuilder parenBuilder, int left, int right)
{
	if(right == 0)
	{
		parenthesisList.add(parenBuilder.toString());
		return;
	}
	
	if(left >= right)
	{
		printParenthesis(parenthesisList, parenBuilder.append('('), left - 1, right);
	}
	else
	{
		printParenthesis(parenthesisList, parenBuilder.append(')'), left, right - 1);		
	}
}

//notworking
// https://leetcode.com/problems/ambiguous-coordinates/
// "(123)" -> ["(1, 2.3)","(1, 23)","(1.2, 3)","(12, 3)"]
public List<String> ambiguousCoordinates(String S) {
	List<String> coordinates = new ArrayList<String>();

	int coordLen = S.length();

	coordinates.add("0." + S );

	for(int i = 0; i < coordLen - 1; i++)
	{
		coordinates.add(S.substring(0, i) + "." + S.substring(i+1));
	}

	return coordinates;
}

//notworking (index out of range)
//https://leetcode.com/problems/multiply-strings/
//123 * 26 = 123 * 6 + 123 * 20
public String multiply(String num1, String num2) {
	double result = 0;
	int multiplyLen = num2.length();
	
	for(int i = multiplyLen; i >= 0; i++)
	{
		result += multiplyByDigit(num1, (num2.charAt(i) - '0') * Math.pow(10, multiplyLen - i));
	}
	
	return Double.toString(result);
}

private double multiplyByDigit(String number, double digit)
{
	return Integer.parseInt(number) * digit;
}

//https://leetcode.com/problems/integer-to-roman/
public String intToRoman(int num) {
	String []romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	int []values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
	
	StringBuilder builder = new StringBuilder();
	
	int i = 0;
	
	while(num > 0)
	{
		int k = num / values[i];
		num /= values[i];
		
		for(int j = 0; j < k ; j++)
		{
			builder.append(romanChars[i]);
		}
		i++;
	}
	
	return builder.toString();
}

//https://leetcode.com/problems/roman-to-integer/
public int romanToInt(String s) {
	HashMap<Character, Integer> romanMap= new HashMap<Character, Integer>()
	{{
		put('M', 1000); 
		put('D', 500);
		put('C', 100);
		put('L', 50);
		put('X', 10);
		put('V', 5);
		put('I', 1);
	}};
	
	int value = 0;
	int prev = 0, current = 0;
	
	for(char ch : s.toCharArray())
	{
		current = romanMap.get(ch);
		value += current > prev ? current - 2 * prev : current;
		prev = current;
	}
	
	return value;	
}
}
