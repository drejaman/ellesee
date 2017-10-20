package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramComparator implements Comparator<String>{
public AnagramComparator()
{
}

public String sortChars(String str)
{
	char[] ch=str.toCharArray();
	Arrays.sort(ch);
	return new String(ch);
}

@Override
public int compare(String str1, String str2) {
	// TODO Auto-generated method stub
	return str1.compareTo(str2);
}

}
