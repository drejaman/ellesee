package epic;

public class StringWork {
public StringWork()
{
}

public char[] reverseString(String word)
{
	char[] ch=word.toCharArray();
	char[] reverse=new char[word.length()];
	int index=0;
	//while(ch[index]!='\0')
	while(index<word.length())
	{
		index++;		
	}
	index--;
	int newIndex=0;
	
	while(index>=0)
	{
		reverse[newIndex++]=ch[index--];
	}

	return reverse;
}

public char[] reverseString(char[] word)
{
int len=word.length-1;
int index=0;
while(index<len)
{
	char temp=word[index];
	word[index++]=word[len];
	word[len--]=temp;
}
return word;
}

public char[] reverseLine(String line)
{
	char[] ch=new char[line.length()];
	ch=reverseString(line);
	//System.out.println(ch);
	
	int index=0;
	int start=0;
	while(index<line.length())
	{
		if(ch[index]==' '||index==(line.length()-1))
		{
			int end=0;
			if(ch[index]==' ')
				end = index-1;
			else
				end=index;
			//System.out.println("Start: "+start);
			//System.out.println("End: "+end);
			while(start<=end)
			{
				char temp=ch[start];
				ch[start]=ch[end];
				ch[end]=temp;
				start++;
				end--;
			}
			start=index+1;//start after the space	
		}
		index++;
		
	}
	
	return ch;	
}

public void replace4thString(String str)
{
	StringBuilder stb=new StringBuilder();
	int index=0,start=0,end=0;
	String space=" ";
	while(index<str.length())
	{
		if(str.indexOf(" ", start)!=-1)
			end=str.indexOf(" ", start);
		else 
			{
				end=str.length();
				space="";
			}
		if((end-start)>=4)
		{
			stb.append(str.substring(start, (start+end)/2));
			stb.append(" ");
			stb.append(str.substring(((start+end)/2),end));
		}
		else
			stb.append(str.substring(start, end));
		stb.append(space);
		start=end+1;
		index=start;
	}
	System.out.println(stb);
}

public char[] replaceVowels(String str)
{
	char[] ch=str.toCharArray();
	int index=0;
	int exempted=3;
	int len=str.length()-1;
	while(exempted>0)
	{
		if(isVowel(ch[index]))
			exempted--;
		index++;
	}
	int limit=index;

	while(len>limit)
	{
		if(isVowel(ch[len]))
		{
			ch[len]-=32;
		}
		len--;
	}
	return ch;
}

public boolean isVowel(char ch)
{
	if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
		return true;
	else return false;
}
}
