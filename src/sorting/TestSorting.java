package sorting;

import java.util.Arrays;

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

}
