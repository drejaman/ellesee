package strings;

public class TestStrings {
public TestStrings()
{
}

public void test()
{
	//System.out.println(checkAnagram("last","stal"));
	//System.out.println(checkAnagram("last","fast"));	
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

}
