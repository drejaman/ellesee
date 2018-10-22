package sorting;

import java.util.ArrayList;

public class TestSorting {

	public TestSorting()
	{
		
	}
	
	public void test()
	{
		//testAnagrams();
		StringSorting strSort=new StringSorting();
		//String[] companies={"acer","amazon","epic","google","microsoft","yahoo"};
		String[] companies={"","amazon","epic","","","google","microsoft","yahoo"};
		System.out.println(strSort.searchString("epic", companies, 0, 8));
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
    public String customSortString(String S, String T) {
    	
        ArrayList<Character> sourceIndex = new ArrayList<Character>();
        
        for(char ch: S.toCharArray())
        {
        	sourceIndex.add(ch);
        }
        
        // now sort the Test String, T
        
        return T;
    }
}
