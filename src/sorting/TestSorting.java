package sorting;

public class TestSorting {

	public TestSorting()
	{
		
	}
	
	public void test()
	{
		//testAnagrams();
		//String[] companies={"acer","amazon","epic","google","microsoft","yahoo"};
		String[] companies={"","amazon","epic","","","google","microsoft","yahoo"};
		System.out.println(searchString("epic", companies, 0, 8));
	}	
	
	public int searchString(String required,String[] str,int lo,int hi)
	{
		int mid=(lo+hi)/2;
		//System.out.println(mid);
		System.out.println("Printing mid string: "+str[mid]);
		if(str[mid].equals(""))
			while(str[mid].equals(""))
				mid++;
		if(required.equals(str[mid]))
			return mid;	
		else if(required.compareTo(str[mid])<0)
			return searchString(required,str,lo,mid-1);
		else if(required.compareTo(str[mid])>0)
			return searchString(required,str,mid+1,hi);
		else return -1;
	}

	public void testAnagrams()
	{
		String[] strings= {"Hello", "Hola","Voila", "Hillow"};
		
		for(String str:strings)
		{
			System.out.println(str);
		}
	}
	
	// https://leetcode.com/problems/custom-sort-string/description/
	//Input: 
	//	S = "cba"
	//	T = "abcd"
	//	Output: "cbad"
	public String customSortString(String S, String T) {
	    int[] freq = new int[26];
	    
	    StringBuilder builder = new StringBuilder();
	    
	    // get the character count for each character based on their appearance in T
	    for(int i = 0; i < T.length(); i++) freq[T.charAt(i) - 'a']++;
	    // at first add the characters based on their order in Source string S
	    for(int i = 0; i < S.length(); i++) while(freq[S.charAt(i) - 'a']-- > 0) builder.append(S.charAt(i));
	    // now add rest of the character in T and their order doesn't matter any more
	    for(int i = 0; i < T.length(); i++) while(freq[T.charAt(i) - 'a']-- > 0) builder.append(T.charAt(i));
	    
	    return builder.toString();
	}
}
