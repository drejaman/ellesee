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
	String tinyUrl = encode("https://leetcode.com/problems/design-tinyurl");
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


//https://leetcode.com/problems/find-and-replace-pattern/
public List<String> findAndReplacePattern(String[] words, String pattern) {
	List<String> matchedWords = new ArrayList<String>();
 HashMap<Character, Character> patternMapper = new HashMap<Character, Character>();
 char[] patternChars = pattern.toCharArray();
 
 for(String word: words)
 {
 	char[] wordChars = word.toCharArray();
 	
 	if(wordChars.length != patternChars.length) continue;
 	
 	// resets the mapper for new word
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
 		// this logic is important to make sure "abb" and "ccc" are not matched
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
 	
 	if(isMatched)
 	{
 		matchedWords.add(word);
 	}
 }
 
 return matchedWords;
}

//https://leetcode.com/problems/implement-strstr/
public int strStr(String haystack, String needle) {
	if(needle == null || needle.isEmpty()) return 0;
	
	if(haystack == null || haystack.isEmpty()) return -1;
	
	for(int i = 0; i < haystack.length(); i++)
	{
		int matchingIndex = i;
		
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


	//https://leetcode.com/problems/reverse-words-in-a-string/
    public String reverseWords1(String s) {
        if(s == null || s.isEmpty()) return s;
        
        int j = s.length();
        
        StringBuilder builder = new StringBuilder();
        
        for(int i = s.length() - 1; i >= 0; i--)
        {
            if(s.charAt(i) == ' '){
                j = i;
            }
            
            // we have reached end of a word
            else if(i == 0 || s.charAt(i - 1) == ' ')
            {
                if(builder.length() != 0)
                {
                    builder.append(' ');
                }
                
                builder.append(s.substring(i, j));
            }
        }
        
        return builder.toString();
    }


    //https://leetcode.com/problems/string-to-integer-atoi/
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
    
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/submissions/
    public int lengthOfLongestSubstring(String s) {
    	boolean exists[] = new boolean[256];
    	
    	int maxLen = 0, i = 0;
    	
    	for(int j = 0; j < s.length(); j++)
    	{
    		while(i < s.length() && exists[s.charAt(j)])
    		{
    			// keep on incrementing i until we past the first occurence of the repeated character
    			// in the current substring
    			// for example in abcdcefg j = 4 when we get the current max substring abcd
    			// the next possible non repeated substring will start from index, i = 3 (d) as then
    			// so after i is increased to 2 then exists[c] is reset resulting into i = 3 and this while 
    			// loop breaks
    			exists[s.charAt(i)] = false;
    			i++;
    		}
    		
    		exists[s.charAt(j)] = true;
    		maxLen = Math.max(j - i + 1, maxLen);
    	}
    	
    	return maxLen;
    }

}







