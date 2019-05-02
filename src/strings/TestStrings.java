package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Stack;

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

// for anagram the characters can be in different places
// but they have to be the same characters with same no of repeats
//TODO validate if this logic is right. find some contradiction
public boolean checkAnagram(String str1, String str2)
{
	if(str1.length() != str2.length())
		return false;
	
	int index = 0, sum1 = 0, sum2 = 0, len = str1.length();
	
	while(index < len)
	{
		sum1 += str1.charAt(index);
		sum2 += str2.charAt(index);
		index++;		
	}
	
	if(sum1 == sum2)
		return true;
	
	return false;
}

//TODO Anagram difference (super important in HackerRank

public boolean checkPalinDrome(String str)
{
	char[] chars = str.toCharArray();
	int len = str.length();
	int start = 0;
	int end = len-1;
	
	while(start < end)
	{
		if(chars[start] != chars[end])
			return false;		
		start++;
		end--;
	}
	
	return true;	
}

// https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
//Input: "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"
public String reverseWords(String s) {
	if(s == null || s.isEmpty()) return s;
	
	StringBuilder builder = new StringBuilder();
	
	// slice the string into words based on space and 
	// call subroutine reverseWhole to reverse the whole word
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
	
	// done in O(N/2) passes
	// set i = 0 and j = N - 1
	//trick: i < j need to be careful not putting <=
	for(int i = 0, j = s.length() - 1; i < j ; i++, j--)
	{
		char temp = stringChars[i];
		stringChars[i] = stringChars[j];
		stringChars[j] = temp;		
	}
	
	return new String(stringChars);
}

//https://leetcode.com/problems/reverse-words-in-a-string/
//Input: "the sky is blue",
//Output: "blue is sky the"
//lastnight
public String reverseWords1(String s) {
    if(s == null || s.isEmpty()) return s;
    
    //we start from the end as we are reversing the line
    int j = s.length();
    
    StringBuilder builder = new StringBuilder();
    
    for(int i = s.length() - 1; i >= 0; i--)
    {
		//that means we have finished a word/
		//reached end of the word
		//the word is in between index 
        if(s.charAt(i) == ' '){
            j = i;
        }
        
        //we have reached end of a word
        //or beginning of the sentence
        //say for 'is blue' when i at b then s.charAt(i - 1) == ' '
        else if(i == 0 || s.charAt(i - 1) == ' ')
        {
        	//don't add the space at the beginning of the line
        	//builder is still empty so we don't anything
            if(builder.length() != 0)
            {
                builder.append(' ');
            }
            
            //add the word as is 
            //j is pointing at the end of the line or the space after the word
            //i is pointing at the beginning of the word
            //we add 'blue' to builder
            builder.append(s.substring(i, j));
        }
    }
    
    return builder.toString();
}

// https://leetcode.com/problems/keyboard-row/description/
// This one solved the problem by calling Subroutine isValidWordUsingSameKBLine that checks each word's validity
// Input: ["Hello", "Alaska", "Dad", "Peace"]
// Output: ["Alaska", "Dad"]
public String[] findWords(String[] words) {
	
	if(words == null || words.length == 0) return words;
	
	// A good demonstration for 2D ArrayList and how to initialize an ArrayList
	ArrayList<ArrayList<Character>> lines = new ArrayList<ArrayList<Character>>();

	//trick: how to initialize the array is important
	lines.add(new ArrayList<Character>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')));
	lines.add(new ArrayList<Character>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')));
	lines.add(new ArrayList<Character>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm')));
	
	ArrayList<String> validWords = new ArrayList<String>();
	
	for(String wordAnyCase : words)
	{
		String word = wordAnyCase.toLowerCase();
		
		//at first we need to figure out what kb lines is probably used
		char firstChar = word.charAt(0);
		int lineIndex = lines.get(0).contains(firstChar) == true ? 0 : -1;
		
		if(lineIndex != 0)
		{
			lineIndex = lines.get(1).contains(firstChar) == true ? 1 : 2;
		}
		
		//pass the right kb line and validate 
		//whole word is constructed using same kb letters
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
// trick: to use LinkedHashMap
// Hash table and linked list implementation of the Map interface, with predictable iteration order.
public int firstUniqChar(String s) {
	char[] allChars = s.toCharArray();
	
	//trick: this keeps track of the characters the way it is inserted
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
		// we return the first character in the map that is not duplicate
		// in the map, value != -1 (index) not duplicate
		// value == -1 duplicate
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

// https://leetcode.com/problems/shortest-distance-to-a-character/
public int[] shortestToChar(String S, char C) {
	ArrayList<Integer> positionsOfC = new ArrayList<Integer>();
	
	char[] sChars = S.toCharArray();
	
	// determine the indices of C in the String
	for(int i = 0; i < sChars.length; i++ )
	{
		if (sChars[i] == C)
		{
			positionsOfC.add(i);
		}
	}
	
	int cIndex = 0;
	
	int[] shortestDistance = new int[S.length()];
	
	//trick: to find the right cIndex that are closest to that character
	for(int i = 0; i < sChars.length; i++)
	{
		// if the currentIndex of the Character C is not the last one
		// then we compare with 2 closest cIndex and update with min
		if(cIndex < positionsOfC.size() - 1)
		{
			// running index i will be either closer to cIndex or cIndex + 1
			shortestDistance[i] = Math.min(Math.abs(i - positionsOfC.get(cIndex)), 
											Math.abs(i - positionsOfC.get(cIndex + 1)));

			// but if running index i is greater than cIndex then we 
			if(i >= positionsOfC.get(cIndex + 1)) cIndex++;				
		}
		// case when cIndex at its last and 
		// we are handling i that are greater than last cIndex
		// so checking with the last cIndex is good enough
		else
		{
			shortestDistance[i] = Math.abs(i - positionsOfC.get(cIndex));
		}
		
	}
		
	return shortestDistance;
}

// https://leetcode.com/problems/subdomain-visit-count/description/
//Input: 
//["9001 discuss.leetcode.com"]
//Output: 
//["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
public List<String> subdomainVisits(String[] cpdomains) {
	HashMap<String, Integer> subDomainCount = new HashMap<String, Integer>();
	
	for(String domain : cpdomains)
	{		
		// first split based on space to get count and website
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
//				int currentCount = subDomainCount.remove(subDomain);
//				subDomainCount.put(subDomain, currentCount + count);
				subDomainCount.put(subDomain, subDomainCount.getOrDefault(subDomain, 0) + count);
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
	
	Iterator<Entry<String, Integer>> it = subDomainCount.entrySet().iterator();
	
	while(it.hasNext())
	{
		Entry<String, Integer> entry = it.next();
		output.add(entry.getValue() + " " + entry.getKey());
		it.remove();
	}
	
	return output;
}

// https://leetcode.com/problems/encode-and-decode-tinyurl/
// Straight forward implementation using a HashMap
private static HashMap<String, String> urlMapper = new HashMap<String, String>();

public String encode(String longUrl) {
	
	// when the url is already mapped
	if(urlMapper.containsValue(longUrl))
	{
		for(Entry<String, String> entry : urlMapper.entrySet())
		{
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

// https://leetcode.com/problems/find-and-replace-pattern/
public List<String> findAndReplacePattern(String[] words, String pattern) {
	List<String> matchedWords = new ArrayList<String>();
	HashMap<Character, Character> patternMapper = new HashMap<Character, Character>();
	char[] patternChars = pattern.toCharArray();
 
	//for each word we map to pattern word's chars and 
	//check if the letters match subsequent time
	 for(String word: words)
	 {
	 	char[] wordChars = word.toCharArray();
	 	
	 	if(wordChars.length != patternChars.length) continue;
	 	
	 	// resets mapper for new word
		patternMapper.clear();
		boolean isMatched = true;
		
		for(int i = 0; i < wordChars.length; i++)
		{
			// pattern key is already in mapper
			if(patternMapper.containsKey(patternChars[i]))
			{
				// they matched
				if(patternMapper.get(patternChars[i]) == wordChars[i])
				{
					continue;
				}
				// they don't match
				else
				{
					isMatched = false;
					break;    				
				}
			}
			//
			//trick:this logic is important to make sure "abb" and "ccc" are not matched
			// as c is already keyed with a. without this logic c will be also keyed with b
			else if(patternMapper.containsValue(wordChars[i]))
			{
				isMatched = false;
				break;
			}
			// otherwise add the key
			else
			{
				patternMapper.put(patternChars[i], wordChars[i]);    			
			}
 	}
 	
	//for each matched word we put them in the matchedWords list
 	if(isMatched)
 	{
 		matchedWords.add(word);
 	}
 }
 
 return matchedWords;
}

//https://leetcode.com/problems/word-pattern/
//Input: pattern = "abba", str = "dog cat cat dog"
//Output: true
//Input:pattern = "abba", str = "dog cat cat fish"
//Output: false
public boolean WordPattern(String pattern, String str) {
    if (pattern.isEmpty() || str.isEmpty()) return false;
    
    HashMap<Character, String> tracker = new HashMap<Character, String>();

    char[] patternChars = pattern.toCharArray();
    String[] stringList = str.split(" ");
    
    if(patternChars.length != stringList.length) return false;

    for (int i = 0; i < patternChars.length; i++)
    {
        if (!tracker.containsKey(patternChars[i]))
        {
            // handle cases like "abba" "dog dog dog dog"
            if (tracker.containsValue(stringList[i]))
            {
                return false;
            }

            tracker.put(patternChars[i], stringList[i]);
        }
        else
        { 
            if (tracker.containsKey(patternChars[i]))
            {
                String value = tracker.get(patternChars[i]);
                if (!value.equals(stringList[i]))
                    return false;
            }
        }
    }

    return true;
}

// https://leetcode.com/problems/implement-strstr/
// Return the index of the first occurrence of needle in haystack, 
// or -1 if needle is not part of haystack.
public int strStr(String haystack, String needle) {
	if(needle == null || needle.isEmpty()) return 0;
	
	if(haystack == null || haystack.isEmpty()) return -1;
	
	for(int i = 0; i < haystack.length(); i++)
	{
		int matchingIndex = i;
		
		// for any index i start matching with index 0 of needle
		// inside this loop increase i and j while they keep on matching
		// while doing this check if we reach the end of needle then we 
		// return the matchingIndex
		for(int j = 0; j < needle.length() && (j + needle.length()) < haystack.length() ; j++)
		{
			while(i < haystack.length() && j < needle.length() &&
					Character.toLowerCase(haystack.charAt(i)) == Character.toLowerCase(needle.charAt(j)))
			{
				i++;
				j++;
			}
			
			if(j == needle.length()) return matchingIndex;
		}
	}
	
	return -1;
}

public int isSubstring(String haystack, String needle)
{
	int hLen = haystack.length();
	int nLen = needle.length();
	
	for(int i = 0 ; i < hLen - nLen; i++)
	{
		int j = 0;
		
		for(; j < nLen; j++)
		{
			if(haystack.charAt(i) != needle.charAt(j))
			{
				break;
			}
		}
		
		if(j == nLen) return i;
	}
	
	return -1;
}

	//https://leetcode.com/problems/length-of-last-word/
	public int LengthOfLastWord(String s) {
	    char[] sentenceArray = s.trim().toCharArray();
	
	    StringBuilder builder = new StringBuilder();
	    String currentString = "";
	
		for(int i = 0; i <= sentenceArray.length; i++)
		{
		    // condition check order is important 
		    // otherwise it will throw null pointer exception based on this implementation
		    if (i == sentenceArray.length || sentenceArray[i] == ' ' )
	        {
	            currentString = builder.toString();
	            builder.setLength(0);
	        }
	        else
	        {
	            builder.append(sentenceArray[i]);
	        }
	    }
	
	    return currentString.length();
	}
	
	//https://leetcode.com/problems/longest-common-prefix/
    public String LongestCommonPrefix(String[] strs)
    {
    	String prefix = "";

        if (strs == null || strs.length == 0) return prefix;

        if (strs.length == 1) return strs[0];

        prefix = this.FindFirstPrefix(strs[0], strs[1]);

        if (strs.length == 2) return prefix;

        for (int i = 2; i < strs.length; i++)
        {
            char[] prefixChars = prefix.toCharArray();
            char[] currentString = strs[i].toCharArray();
            
            int j = 0;
            StringBuilder prefixBuilder = new StringBuilder();

            while (j < prefixChars.length && j < strs[i].length() && prefixChars[j] == currentString[j])
            {
                prefixBuilder.append(prefixChars[j++]);
            }

            prefix = prefixBuilder.toString();
        }

        return prefix;
    }

    private String FindFirstPrefix(String s1, String s2)
    {
        char[] s1chars = s1.toCharArray();
        char[] s2chars = s2.toCharArray();

        int i = 0;
        StringBuilder prefix = new StringBuilder();

        while (i < s1.length() && i < s2.length() && s1chars[i] == s2chars[i])
        {
            prefix.append(s1chars[i++]);
        }

        return prefix.toString();
    }

	//https://leetcode.com/problems/roman-to-integer/
	//Input: "MCMXCIV"
	//Output: 1994
	//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
    //lastnight
	public int romanToInt(String s) {
		//trick: look how the hashMap is initialized
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
			//MCMXCIV
			//M(1000), C (100), M(1000), X (10), C(100), I(1), V(5)
			current = romanMap.get(ch);
			//trick
			//logic: this is the main logic
			//when the current is greater than prev that means either 9 (IX), 90 (XC), etc
			//corrective measure is current(say X is 10) - 2 * prev (I is 1)
			//as we added the prev already to value in previous step
			//so the right addition is current - 2 * prev to also remove addition of prev
			//1000, 100, 1000 - 2 * 100 = 800, 10,   100 - 2 * 10 = 80, 1, 5 - 2 * 1 			
			value += current > prev ? current - 2 * prev : current;
			//1000, 100, 1000, 10, 100, 1, 5
			prev = current;
		}
		
		return value;	
	}
	
  	//https://leetcode.com/problems/integer-to-roman/
	// trick: initialize array, start with the highest value also considering 9's like 900 90 etc.
	// start dividing up the number from the highest value values[0]
	//lastnight
  	public String intToRoman(int num) {
  		String[] romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
  		int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  		
  		StringBuilder builder = new StringBuilder();
  		
  		int i = 0;
  		
  		//992 -> CMXCII
  		while(num > 0)
  		{
  			int k = num / values[i];//992/1000 = 0, 992/900 = 1, ...., 92/90 = 1, ..., 2/1 = 2  
  			num /= values[i];//992%1000 = 992, 992 % 900 = 92, ...., 92%90 = 2, 2%1 = 0
  			
  			for(int j = 0; j < k ; j++)
  			{
  				builder.append(romanChars[i]);// , CM, XC, I I
  			}
  			
  			i++;
  		}
  		
  		return builder.toString();
  	}   
	
    //https://leetcode.com/problems/string-to-integer-atoi/
  	//trick:
  	// only formula is int digit = Character.getNumericValue(str.charAt(i));   
  	//	      number = number * 10 + digit;
  	// the rest is handling the corner cases
    public int myAtoi(String str) { 
	    	// need to return 0 as required by Leetcode
	    	if(str == null || str.isEmpty()) return 0;
	    	
	    	// required to handle overflow
	    	int maxDiv10 = Integer.MAX_VALUE / 10;
	    	
	    	int i = 0;
	    	int len = str.length();
	    	
	    	while(i < len && Character.isWhitespace(str.charAt(i))) i++;
	    	
	    	int number = 0;
	    	int sign = 1;
	
	    	if(i < len && str.charAt(i) == '-')
		{
			sign = -1;
			i++;
		}
	    	else if(i < len && str.charAt(i) == '+')
		{
			i++;
		}
	
		    	// as required by Leetcode any non alphanumeric character will result into 0
	    	while(i < len && Character.isDigit(str.charAt(i)))
	    	{
	      int digit = Character.getNumericValue(str.charAt(i));       
	      
	      // required to handle overflow		      
	      if (number > maxDiv10 || number == maxDiv10 && digit >= 8) 
	      {          
	    	  	return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;       
	      }
		
	      number = number * 10 + digit;
		  i++;
	    	}
	
	    	/* alternate implementation
	    	 * case like "why this is 987" will return 987
	    	while(i < len)
	    	{	
	    		if(Character.isDigit(str.charAt(i)))
	    		{	
		      int digit = Character.getNumericValue(str.charAt(i));       
		      
		      // required to handle overflow		      
		      if (number > maxDiv10 || number == maxDiv10 && digit >= 8) 
		      {          
		    	  return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;       
		      }
				number = number * 10 + digit;
			}
	    		i++;
	    	}
	    	*/
	
	    	return sign * number;
    }
    
    //cracking1.5
    //lastnight
    public boolean oneEditDistanceAway(String s1, String s2)
    {
	    	if(Math.abs(s1.length() - s2.length()) > 1) return false;
	    	
	    	String shorter = s1.length() < s2.length() ? s1 : s2;
	    	String longer = s1.length() < s2.length() ? s2 : s1;
	    	
	    	boolean foundMismatch = false;
	    	
	    	int index1 = 0, index2 = 0;
	    	
	    	while(index1 < shorter.length() && index2 < longer.length())
	    	{
	    		// the characters are not matched. so it has to be insert or replace
	    		if(shorter.charAt(index1) != longer.charAt(index2))
	    		{
	    			// already one mismatch found before. so another mismatch means not one edit distance away
	    			if(foundMismatch) return false;
	    			//otherwise mark the first mismatch
	    			foundMismatch = true;
	    			
	    			//if the lengths are same that means it is a replace. so move shorter pointer
	    	   		if(shorter.length() == longer.length())
	        		{
	        			index1++;
	        		}
	    	   		//otherwise is an insert. so no short pointer is changed
	        	}
	    		//they are matching so move both pointers are increased
	    		else
	    		{
	    			index1++;
	    		}
	    		//longer pointer is always increased
	    		index2++;
	    	}
	    	
	    	return true;
    }
    
    //https://leetcode.com/problems/split-array-into-fibonacci-sequence/
    //Input: "123456579"
    //Output: [123,456,579]
    public List<Integer> splitIntoFibonacci(String S) {
    	
		List<Integer> result = new ArrayList<Integer>();
    
		if(S == null || S.isEmpty() || S.length() < 3)
			return result;
    			
        int strLen = S.length();
        
        //start with len 1 and keep on going to strLen/3
        for(int len = 1; len < strLen / 3; len++)
        {
    		//we need to clear the result everytime we change len for numbers
        	result.clear();
        	int beginIndex = 0, first = 0, second = 0, third = 0;
        	
        	if(beginIndex + len < strLen)
        	first = Integer.parseInt(S.substring(beginIndex, beginIndex + len));
        	
        	beginIndex = beginIndex + len;
        	if(beginIndex + len < strLen)
        	second = Integer.parseInt(S.substring(beginIndex, beginIndex + len));
        	
        	beginIndex = beginIndex + len;
        	if(beginIndex + len < strLen)
        	third = Integer.parseInt(S.substring(beginIndex, beginIndex + len));        
        	
        	if(first + second == third && third != 0)
        	{
        		result.add(first);
        		result.add(second);

        		while(first + second == third)
	        	{
	        		result.add(third);
	        		
	        		first = second;
	        		second = third;
	        		//now beginIndex points to the end of previous third number
		        	beginIndex = beginIndex + len;
		        	
		        	if(beginIndex + len < strLen)
		        	{
		        		third = Integer.parseInt(S.substring(beginIndex, beginIndex  + len));			        		
			        	
		        		// to handle cases like below
		        		// at the end second = 8, third = 1, len = 1
		        		// as third < second, len = 2, third = 13
		        		// Input: "11235813"
		        		// Output: [1,1,2,3,5,8,13]
		        		if(third < second)
		        		{
		        			len++;
			        		third = Integer.parseInt(S.substring(beginIndex, beginIndex  + len));			        		
		        		}
		        	}				        	
		        }
	        }	
	        	
        	if(beginIndex +  len == S.length()) return result;
        }
        
        return result;
    }

    //https://leetcode.com/problems/group-anagrams/
    //sort the strings based on their anagram key
    //Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    //Output:
    //	[
    //	  ["ate","eat","tea"],
    //	  ["nat","tan"],
    //	  ["bat"]
    //	]
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> wordList = new ArrayList<List<String>>();
	        
	    	if(strs == null || strs.length == 0) return wordList;
	    	
	    	HashMap<String, List<String>> anaList = new HashMap<String, List<String>>();
	    	
	    	for(String str : strs)
	    	{
				char[] strChars = str.toCharArray();
				Arrays.sort(strChars);
				String sorted = new String(strChars);
		    		
	    		//gives you the list based on the key 'sorted' or a new List of Strings
		        List<String> list = anaList.getOrDefault(sorted, new ArrayList<String>());
		        list.add(str);
		        anaList.put(sorted, list);
	    	}
	    	
	    	//in the resule we only need the list not the key
	    for (String key: anaList.keySet()) {
    			wordList.add(anaList.get(key));
	    }
        
        return wordList;
    }
    
    //https://leetcode.com/problems/valid-parentheses/
    public boolean IsValid(String s) {
        
        //best part of Length check is we don't need to go to rest of it
        if (s.isEmpty() || s.length() % 2 != 0) return false;

        char[] parens = s.toCharArray();

        Stack<Character> tracker = new Stack<Character>();

        for (char ch : parens)
        {
        		//trick: don't use switch/case as that will not give you the right result
            if (ch == '(' || ch == '{' || ch == '[')
            {
                tracker.push(ch);
            }
            else 
            {
                if (tracker.size() <= 0) return false;

                char poppedChar = tracker.pop();

                if (!(poppedChar == '(' && ch == ')'
                    || poppedChar == '{' && ch == '}'
                    || poppedChar == '[' && ch == ']'))
                {
                    return false;
                }
            }
        }

        return tracker.size() == 0;
        //return tracker.isEmpty();
    }    

    //https://leetcode.com/problems/word-subsets/
    //logic: Reduce B to a single word bmax as described above, 
    //then compare the counts of letters between words a in A, and bmax.
    //Time Complexity: O(A+B), where A and B is the total amount of information in A and B respectively.
    //Space Complexity: O(A.length+B.length). 
    //Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
    //Output: ["facebook","google","leetcode"]
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bmax = count("");//gets a fresh array of bmax[26]
        
        //for the given input bmax[e] = 1, bmax[o] = 1
        for (String b: B) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; ++i)
                bmax[i] = Math.max(bmax[i], bCount[i]);
        }

        List<String> ans = new ArrayList<String>();
        
        //for the given sample 
        //for amazon, aCount[e] = 0 < bmax[e] so continue
        //for facebook, aCount[e] = 1 == bmax[e], aCount[o] = 2 > bmax[o] 
        //so add facebook to result
        search: for (String a: A) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; ++i)
                if (aCount[i] < bmax[i])
                    continue search;//for amazon, aCount[e] = 0 < bmax[e] so continue
            ans.add(a);
        }

        return ans;
    }

    private int[] count(String S) {
        int[] ans = new int[26];
        for (char c: S.toCharArray())
            ans[c - 'a']++;
        return ans;
    }
    
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    //Input: "abcdcefg"
    //Output: 5 
    //lastnight
    public int lengthOfLongestSubstring(String s) {
		boolean exists[] = new boolean[256];
		
		int maxLen = 0, i = 0;
		
		for(int j = 0; j < s.length(); j++)
		{
			while(i < s.length() && exists[s.charAt(j)])//for j = 4 this will become true
			{
				// keep on incrementing i until we past the first occurrence of the repeated character
				// in the current substring
				// for example in abcdcefg, j = 4 when we get the current max substring abcd
				// the next possible non repeated substring will start from index, i = 3 (d) as then
				// so after i is increased to 2 then exists[c] is reset resulting into i = 3 and this while 
				// loop breaks
					//then it will reset exists[s.charAt(i)] from i = 0 to 2
					//like exists[a], exists[b], exists[c] all will reset to false
				exists[s.charAt(i)] = false;
				i++;
			}
			
			// case when the character doesn't already exist in 
			// current ongoing longest substring consideration
			exists[s.charAt(j)] = true;//exists[c] will be set to true second time here
			maxLen = Math.max(j - i + 1, maxLen);
		}
		
		return maxLen;
    }    
    
    //https://leetcode.com/problems/excel-sheet-column-number/
    public int TitleToNumber(String s) {
        int columnValue = 0;

        if (s == null || s.length() == 0)
        {
            return columnValue;
        }

        int len = s.length() - 1;

        char[] excelColumn = s.toCharArray();

        //logic: the left most letter need to be exponentialized to with decreasing order
        //ACE -> ('A' - 'A' + 1 ) * 26 ^ (3-0) + ('C' - 'A' + 1 ) * 26 ^ (3-1) + ('E' - 'A' + 1 ) * 26 ^ (3-2)
        for (int i = 0; i <= len; i++)
        {
            columnValue += (excelColumn[i] - 'A' + 1) * (int) Math.pow(26, len - i);
        }

        return columnValue;        
    }

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
	    	
	    	String[] stringRepresentations = complexNumber.split("\\+");
    	
  		complexPortions[0] = Integer.parseInt(stringRepresentations[0]);
  		//complexPortions[1] = Integer.parseInt(stringRepresentations[1]);
  		complexPortions[1] = Integer.parseInt(stringRepresentations[1].substring(0, stringRepresentations[1].indexOf('i')).trim());
  		
  		return complexPortions;
    }
    
    //https://leetcode.com/problems/valid-palindrome/
    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty()) return true;
        
        int i = 0, j = s.length() - 1;
        
        while(i < j)
        {
        		//removed characters that 
            while( i < j && !Character.isLetterOrDigit(s.charAt(i))) i++; 
            while( i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            
            if( i < j && Character.toLowerCase(s.charAt(i)) != 
               Character.toLowerCase(s.charAt(j)))
            {
                return false;
            }
            i++;
            j--;
        }
        
        return true;
    }
    
    //https://leetcode.com/problems/isomorphic-strings/
    //Input: s = "egg", t = "add"
    //Output: true
    //Input: s = "foo", t = "bar"
    //Output: false
    public boolean IsIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
        {
            return false;
        }

        HashMap<Character, Character> tracker = new HashMap<Character, Character>();

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int len = s.length();

        for (int i = 0; i < len; i++)
        {
            if (tracker.get(sChars[i]) != tChars[i])
            {
                return false;
            }
            //make sure the current value is not already to mapped
            //to another character
            //without this check foo and bar will give result true
            else if (!tracker.containsValue(tChars[i]))
            {
                tracker.put(sChars[i], tChars[i]);
            }
            else
            {
                return false;
            }
        }

        return true;
   }
    
    //https://leetcode.com/problems/add-binary/
    public String AddBinary(String a, String b)
    {
        if (a == null || a.isEmpty()) return b;

        if (b == null || b.isEmpty()) return a;


        int sum = 0;
        int carry = 0;
        int aVal = 0, bVal = 0;
        int aLen = a.length();
        int bLen = b.length();

        StringBuilder builder = new StringBuilder();

        for (int i = aLen - 1, j = bLen - 1; i >= 0 || j >= 0; )
        {
            if (i >= 0)
            {
                aVal = a.charAt(i) - '0';
                i--;
            }
            else
            {
                aVal = 0;
            }

            if (j >= 0)
            {
                bVal = b.charAt(j) - '0';
                j--;
            }
            else
            {
                bVal = 0;
            }

            sum = (aVal ^ bVal) ^ carry;
            // there's a carry only when we had a previous carry and any or both of a, b are 1
            carry = (aVal & bVal) | (bVal & carry)  | (aVal & carry);
            builder.append(sum);
        }

        if (carry == 1)
        {
            builder.append(carry);            
        }

        //TODO
        //Arrays.
        //char[] chars = builder.toString()..toCharArray().Reverse().ToArray();

        //return new String(chars);
        return builder.toString();
    }
  
  //https://leetcode.com/problems/bulls-and-cows/
  public String GetHint(String secret, String guess) {
      if ((secret == null && guess == null) || secret.length() != guess.length()) return "";

      char[] secretChars = secret.toCharArray();
      char[] guessChars = guess.toCharArray();

      int aBull = 0;
      int bCow = 0;

      HashMap<Integer, Integer> secretTracker = new HashMap<Integer, Integer>();

      // look for bulls (match) and also build tracker
      for (int i = 0; i < secretChars.length; i++ )
      {
          if (secretChars[i] == guessChars[i])
          {
              aBull++;
              guessChars[i] = 'X';
          }
          else
          {
              int currentNumber = secretChars[i] - '0';

              if (secretTracker.containsKey(currentNumber))
              {
            	  secretTracker.put(currentNumber, secretTracker.get(currentNumber) + 1);
              }
              else
              {
                  secretTracker.put(currentNumber, 1);
              }                
          }
      }

      for (int i = 0; i < guessChars.length; i++)
      {
          int currentNumber = guessChars[i] - '0';

          if (currentNumber < 10 && secretTracker.containsKey(currentNumber))
          {
              // the key will be always there but if it is zero that mean there is no more that element
              if (secretTracker.get(currentNumber) > 0)
              {
                  bCow++;                
              }

              secretTracker.put(currentNumber, secretTracker.get(currentNumber) - 1);
          }
      }

      return aBull + "A" + bCow + "B";
  }

  //https://leetcode.com/problems/valid-anagram/submissions/
  //TODO get the solved solution here
  
  //https://leetcode.com/problems/decode-ways/
  //TODO
//  public int numDecodings(String s) {
//      
//  }
  
  //https://leetcode.com/problems/valid-parenthesis-string/
  //https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass
  //TODO
//  public boolean checkValidString(String s) {
//      
//  }

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
}